package dev.fatum.www.webapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import dev.fatum.www.consumer.ejb.EntrepriseBean;
import dev.fatum.www.business.qualifiers.LoggedIn;
import dev.fatum.www.business.web.util.JsfUtil;
import dev.fatum.www.business.web.util.PageNavigation;
import dev.fatum.www.model.Coordonnees;
import dev.fatum.www.model.Entreprise;
import dev.fatum.www.model.Profil;

/**
 * @author LEONARD
 */
@Named(value = "entrepriseController")
@SessionScoped
public class EntrepriseController implements Serializable {

	private static final String BUNDLE = "bundles.Bundle";
	private static final long serialVersionUID = 8170385117226855466L;

    @Inject
    @LoggedIn
    Profil authenticated;
	private Entreprise entreprise;
	private Coordonnees coordonnees;
	private List<Entreprise> entrepriseListe;
	private List<Coordonnees> coordonneesListe;
    private Entreprise current;
    private DataModel<Entreprise> items = null;
    @EJB
    private dev.fatum.www.consumer.ejb.EntrepriseBean ejbFacade;

    private static final Logger logger = Logger.getLogger(EntrepriseController.class.getCanonicalName());
    
    public EntrepriseController() {
    	entrepriseListe = new ArrayList<Entreprise>();
    	coordonneesListe = new ArrayList<Coordonnees>();
    }
    
    public Entreprise getSelected() {
        if (current == null) {
            current = new Entreprise();
        }
        return current;
    }
    
    public Entreprise getEntreprise() {
    	if (entreprise == null || entrepriseListe.isEmpty()) {
    		entrepriseListe = ejbFacade.getEntrepriseByProfilId(getAuthenticated().getId());
    		if(!entrepriseListe.isEmpty() ) {
        		entreprise = entrepriseListe.get(0);
        		coordonnees = entreprise.getCoordonneesList().get(0);
    		}
        } else if(coordonnees.getId() != entreprise.getCoordonneesList().get(0).getId()) {
        	coordonnees = entreprise.getCoordonneesList().get(0);
        }
    	return entreprise;
    }

    public Coordonnees getCoordonnees() {
    	if (coordonnees == null) {
    		coordonnees = new Coordonnees();
        }
    	return coordonnees;
    }
    
    private EntrepriseBean getFacade() {
        return ejbFacade;
    }
    
    public PageNavigation prepareList() {
        recreateModel();
        return PageNavigation.LISTE;
    }
    
    public PageNavigation prepareView() {
        current = (Entreprise) getItems().getRowData();
        coordonnees = current.getCoordonneesList().get(0);
        return PageNavigation.VOIR;
    }

    public PageNavigation prepareCreate() {
        current = new Entreprise();
        coordonnees = new Coordonnees();
        return PageNavigation.CREER;
    }

    public PageNavigation create() {
        try {        	
        	entrepriseListe = new ArrayList<Entreprise>();
        	coordonneesListe = new ArrayList<Coordonnees>();
        	
        	entrepriseListe.add(current);
        	entreprise.setEntrepriseList(entrepriseListe);        	
        	current.setEntreprise(entreprise);
        	
            coordonnees.setEntreprise(current);
            coordonneesListe.add(coordonnees);
            current.setCoordonneesList(coordonneesListe);
            
            getFacade().create(current);
            JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("EntrepriseCreated"));
            recreateModel();
            return PageNavigation.VOIR;
        } catch (Exception e) {
        	JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("EntrepriseCreatedError"));
        	return PageNavigation.CREER;
        }
    }

    public PageNavigation createEntreprise() {
        try {
        	coordonneesListe = new ArrayList<Coordonnees>();
            coordonnees.setEntreprise(current);
            coordonneesListe.add(coordonnees);            
            current.setCoordonneesList(coordonneesListe);
            current.setProfil(getAuthenticated());
            getFacade().create(current);
            JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("EntrepriseCreated"));
            recreateModel();
            return PageNavigation.VOIR;
        } catch (Exception e) {
        	JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("EntrepriseCreatedError"));
        	return PageNavigation.CREER;
        }
    }

    public PageNavigation editEntreprise() {
    	current = entreprise;
    	coordonnees = entreprise.getCoordonneesList().get(0);
        return PageNavigation.EDITER;
    }

    public PageNavigation prepareEdit() {
    	current = (Entreprise) getItems().getRowData();
    	coordonnees = current.getCoordonneesList().get(0);
        return PageNavigation.EDITER;
    }
    
    public PageNavigation update() {
        try {
            logger.log(Level.INFO, "Updating utilisateur ID:{0}", current.getId());
            getFacade().edit(current);
            JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("EntrepriseUpdated"));
            return PageNavigation.VOIR;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("PersistenceErrorOccured"));
            return PageNavigation.EDITER;
        }
    }
    
    public PageNavigation destroyInlist() {
    	current = (Entreprise) getItems().getRowData();
        return destroy();
    }
        
    public PageNavigation destroy() {
        performDestroy();
        recreateModel();
        JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("EntrepriseDeleted"));
        return PageNavigation.LISTE;
    }

    private void performDestroy() {
        try {
            getFacade().remove(entreprise);
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("PersistenceErrorOccured"));
        }
    }
    
    public DataModel<Entreprise> getItems() {
        if (items == null) {
        	items = new ListDataModel<Entreprise>(getFacade().getEntrepriseByEntrepriseId(getEntreprise().getId()));
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }
    
    public int getAdministrateursCount() {
    	return ejbFacade.count();
    }
    
    public Profil getAuthenticated() {
        return authenticated;
    }
    
    public void setAuthenticated(Profil p) {
        this.authenticated = p;
    }
}
