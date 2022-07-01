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

import dev.fatum.www.consumer.ejb.PersonneBean;
import dev.fatum.www.business.qualifiers.LoggedIn;
import dev.fatum.www.business.web.util.JsfUtil;
import dev.fatum.www.business.web.util.PageNavigation;
import dev.fatum.www.model.Coordonnees;
import dev.fatum.www.model.Entreprise;
import dev.fatum.www.model.Personne;
import dev.fatum.www.model.Profil;

/**
 * @author LEONARD
 */
@Named(value = "personneController")
@SessionScoped
public class PersonneController implements Serializable {

	private static final String BUNDLE = "bundles.Bundle";
	private static final long serialVersionUID = 8170385117226855466L;

    @Inject
    @LoggedIn
    Profil authenticated;
	private Personne personne;
	private Coordonnees coordonnees;
	private Entreprise entreprise;
    private Personne current;
    private DataModel<Personne> items = null;
    @Inject
    private EntrepriseController entrepriseController;

    @EJB
    private dev.fatum.www.consumer.ejb.PersonneBean ejbFacade;

    private static final Logger logger = Logger.getLogger(PersonneController.class.getCanonicalName());
    
    public PersonneController() {
    }
    
    public Personne getSelected() {
        if (current == null) {
            current = new Personne();
        }
        return current;
    }

    public Coordonnees getCoordonnees() {
    	if (coordonnees == null) {
    		coordonnees = new Coordonnees();
        }
    	return coordonnees;
    }
    
    public Entreprise getEntreprise() {
    	if(entreprise == null) {
    		entreprise = entrepriseController.getEntreprise();
    	}
    	return entreprise;
    }

    private PersonneBean getFacade() {
        return ejbFacade;
    }
    
    public PageNavigation prepareList() {
        recreateModel();
        return PageNavigation.LISTE;
    }
    
    public PageNavigation prepareView() {
        current = (Personne) getItems().getRowData();
        coordonnees = current.getCoordonnees();
        return PageNavigation.VOIR;
    }

    public PageNavigation prepareCreate() {
    	getEntreprise();
        current = new Personne();
        coordonnees = new Coordonnees();
        return PageNavigation.CREER;
    }

    public PageNavigation create() {
        try {
        	List<Personne> personneList = new ArrayList<Personne>();
        	personneList.add(current);
        	coordonnees.setPersonneList(personneList);        	
        	current.setCoordonnees(coordonnees);
        	entreprise.getPersonneList().add(current);
        	current.setEntreprise(entreprise);
        	getFacade().create(current);
            JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("ParticulierCreated"));
            recreateModel();
            return PageNavigation.VOIR;
        } catch (Exception e) {
        	JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("ParticulierCreatedError"));
        	return PageNavigation.CREER;
        }
    }
    
    public PageNavigation prepareEdit() {
    	current = (Personne) getItems().getRowData();
        return PageNavigation.EDITER;
    }
    
    public PageNavigation update() {
        try {
            logger.log(Level.INFO, "Updating utilisateur ID:{0}", current.getId());
            getFacade().edit(current);
            JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("ParticulierUpdated"));
            return PageNavigation.VOIR;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("PersistenceErrorOccured"));
            return PageNavigation.EDITER;
        }
    }
    
    public PageNavigation destroyInlist() {
    	current = (Personne) getItems().getRowData();
        return destroy();
    }
        
    public PageNavigation destroy() {
        performDestroy();
        recreateModel();
        JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("ParticulierDeleted"));
        return PageNavigation.LISTE;
    }

    private void performDestroy() {
        try {
            getFacade().remove(personne);
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("PersistenceErrorOccured"));
        }
    }
    
    public DataModel<Personne> getItems() {
        if (items == null) {
        	items = new ListDataModel<Personne>(getFacade().getEntrepriseByProfilId(getEntreprise().getId()));
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
