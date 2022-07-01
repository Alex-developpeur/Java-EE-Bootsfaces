package dev.fatum.www.webapp.web;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import dev.fatum.www.consumer.ejb.AdministrateurBean;
import dev.fatum.www.business.qualifiers.LoggedIn;
import dev.fatum.www.business.web.util.ChangePasswordAction;
import dev.fatum.www.business.web.util.JsfUtil;
import dev.fatum.www.business.web.util.PageNavigation;
import dev.fatum.www.business.web.util.PasswordStorage;
import dev.fatum.www.model.Administrateur;
import dev.fatum.www.model.Profil;

/**
 * @author LEONARD
 */
@Named(value = "administrateurController")
@SessionScoped
public class AdministrateurController implements Serializable {

	private static final String BUNDLE = "bundles.Bundle";
	private static final long serialVersionUID = 8170385117226855466L;

    @Inject
    @LoggedIn
    Profil authenticated;
    private Administrateur current;
    private DataModel<Administrateur> items = null;
    @EJB
    private dev.fatum.www.consumer.ejb.AdministrateurBean ejbFacade;
    private ChangePasswordAction changePasswordAction;

    private static final Logger logger = Logger.getLogger(AdministrateurController.class.getCanonicalName());
    
    public AdministrateurController() {
    	changePasswordAction = new ChangePasswordAction();
    }

    public Administrateur getSelected() {
        if (current == null) {
            current = new Administrateur();
        }
        return current;
    }

    private AdministrateurBean getFacade() {
        return ejbFacade;
    }
        
    public PageNavigation prepareList() {
        recreateModel();
        return PageNavigation.LISTE;
    }
    
    public PageNavigation prepareView() {
        current = (Administrateur) getItems().getRowData();
        return PageNavigation.VOIR;
    }

    public PageNavigation prepareCreate() {
        current = new Administrateur();
        return PageNavigation.CREER;
    }

    private boolean isUserDuplicated(Profil p) {
        return (getFacade().getUserByEmail(p.getEmail()) == null) ? false : true;
    }

    public PageNavigation create() {
        try {
            if (!isUserDuplicated(current)) {
                // password encrypt
                current.setMdp(PasswordStorage.createHash(current.getMdp()));
                getFacade().createUser(current);
                JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("AdministrateurCreated"));
                recreateModel();
                return PageNavigation.LISTE;
            } else {
            	JsfUtil.addErrorMessage(ResourceBundle.getBundle(BUNDLE).getString("DuplicatedAdministrateurError"));
            	return PageNavigation.CREER;
            }
        } catch (Exception e) {
        	JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("AdministrateurCreatedError"));
        	return PageNavigation.CREER;
        }
    }
    
    public PageNavigation profilUpdate() {
    	current = (Administrateur) getAuthenticated();
    	return update();
    }
    
    public PageNavigation updatePassword() {
    	if(!changePasswordAction.changePassword(getAuthenticated().getMdp())) {
    		JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle(BUNDLE, "MotDePasseEditError"));
    		changePasswordAction = new ChangePasswordAction();
    		return PageNavigation.EDITER_MDP;
    	}
    	current = (Administrateur) getAuthenticated();
    	current.setMdp(changePasswordAction.getNewPassword());
    	getFacade().edit(current);
    	JsfUtil.addInformationMessage(JsfUtil.getStringFromBundle(BUNDLE, "MotDePasseEditSuccess"));
    	changePasswordAction = new ChangePasswordAction();
    	return PageNavigation.VOIR;
    }

    public PageNavigation prepareEdit() {
    	current = (Administrateur) getItems().getRowData();
        return PageNavigation.EDITER;
    }
    
    private boolean isEmailDuplicated(Profil p) {
        return getFacade().checkEmail(p);
    }
    
    public PageNavigation update() {
        try {
        	if(isEmailDuplicated(current)) {
	            logger.log(Level.INFO, "Updating utilisateur ID:{0}", current.getId());
	            // password encrypt
	            getFacade().edit(current);
	            JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("AdministrateurUpdated"));
	            return PageNavigation.VOIR;
        	} else {
        		JsfUtil.addErrorMessage(ResourceBundle.getBundle(BUNDLE).getString("DuplicatedEmailError"));
        		return PageNavigation.EDITER;
        	}
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("PersistenceErrorOccured"));
            return PageNavigation.EDITER;
        }
    }
    
    public PageNavigation destroyProfil() {
    	current = (Administrateur) getAuthenticated();
    	performDestroy();
    	JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("ProfilDeleted"));
    	ConnexionController connexion = new ConnexionController();
    	return connexion.logout();
    }
    
    public PageNavigation destroyInlist() {
    	current = (Administrateur) getItems().getRowData();
        return destroy();
    }
    
    public PageNavigation destroy() {
        performDestroy();
        recreateModel();
        JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("AdministrateurDeleted"));
        return PageNavigation.LISTE;
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("PersistenceErrorOccured"));
        }
    }

    public DataModel<Administrateur> getItems() {
        if (items == null) {
        	items = new ListDataModel<Administrateur>(getFacade().findAllDesc());
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }
    
    public int getAdministrateursCount() {
    	return ejbFacade.count();
    }

    public void setAdministrateur(Administrateur user) {
        this.authenticated = user;
    }

    public Profil getAuthenticated() {
        return authenticated;
    }
    
    public void setAuthenticated(Profil p) {
        this.authenticated = p;
    }

	public ChangePasswordAction getChangePasswordAction() {
		return changePasswordAction;
	}

	public void setChangePasswordAction(ChangePasswordAction changePasswordAction) {
		this.changePasswordAction = changePasswordAction;
	}
    
}
