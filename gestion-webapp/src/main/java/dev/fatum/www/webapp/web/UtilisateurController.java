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

import dev.fatum.www.consumer.ejb.UtilisateurBean;
import dev.fatum.www.business.qualifiers.LoggedIn;
import dev.fatum.www.business.web.util.ChangePasswordAction;
import dev.fatum.www.business.web.util.JsfUtil;
import dev.fatum.www.business.web.util.PageNavigation;
import dev.fatum.www.business.web.util.PasswordStorage;
import dev.fatum.www.model.Profil;
import dev.fatum.www.model.Utilisateur;

/**
 * @author LEONARD
 */
@Named(value = "utilisateurController")
@SessionScoped
public class UtilisateurController implements Serializable {

	private static final String BUNDLE = "bundles.Bundle";
	private static final long serialVersionUID = -4570541637981509460L;

    @Inject
    @LoggedIn
    Profil authenticated;
    private Utilisateur current;
    private DataModel<Utilisateur> items = null;
    @EJB
    private dev.fatum.www.consumer.ejb.UtilisateurBean ejbFacade;
    private ChangePasswordAction changePasswordAction;

    private static final Logger logger = Logger.getLogger(UtilisateurController.class.getCanonicalName());
    
    public UtilisateurController() {
    	changePasswordAction = new ChangePasswordAction();
    }

    public Utilisateur getSelected() {
        if (current == null) {
            current = new Utilisateur();
        }
        return current;
    }
    
    public void setSelected(Utilisateur utilisateur) {
    	this.current = utilisateur;
    }

    private UtilisateurBean getFacade() {
        return ejbFacade;
    }
    
    public PageNavigation prepareList() {
        recreateModel();
        return PageNavigation.LISTE;
    }
    
    public PageNavigation prepareView() {
        current = (Utilisateur) getItems().getRowData();
        return PageNavigation.VOIR;
    }

    public PageNavigation prepareCreate() {
        current = new Utilisateur();
        return PageNavigation.CREER;
    }
    
    public String createNouveau(ConnexionController connexionController) {
    	if(create() != null) {
	    	connexionController.setUsername(current.getEmail());
	    	connexionController.setPassword(current.getMdp());
	    	return connexionController.login();
    	}
    	return null;
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
                JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("UtilisateurCreated"));
                recreateModel();
                return PageNavigation.LISTE;
            } else {
            	JsfUtil.addErrorMessage(ResourceBundle.getBundle(BUNDLE).getString("DuplicatedUtilisateurError"));
            	return PageNavigation.CREER;
            }
        } catch (Exception e) {
        	JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("UtlisateurCreatedError"));
        	return PageNavigation.CREER;
        }
    }
    
    public PageNavigation profilUpdate() {
    	current = (Utilisateur) getAuthenticated();
    	return update();
    }
    
    public PageNavigation updatePassword() {
    	if(!changePasswordAction.changePassword(getAuthenticated().getMdp())) {
    		JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle(BUNDLE, "MotDePasseEditError"));
    		changePasswordAction = new ChangePasswordAction();
    		return PageNavigation.EDITER_MDP;
    	}
    	current = (Utilisateur) getAuthenticated();
    	current.setMdp(changePasswordAction.getNewPassword());
    	getFacade().edit(current);
    	JsfUtil.addInformationMessage(JsfUtil.getStringFromBundle(BUNDLE, "MotDePasseEditSuccess"));
    	changePasswordAction = new ChangePasswordAction();
    	return PageNavigation.VOIR;
    }

    public PageNavigation prepareEdit() {
    	current = (Utilisateur) getItems().getRowData();
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
	            JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("UtilisateurUpdated"));
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
    	current = (Utilisateur) getAuthenticated();
    	performDestroy();
    	JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("ProfilDeleted"));
    	ConnexionController connexion = new ConnexionController();
    	return connexion.logout();
    }

    public PageNavigation destroyInlist() {
    	current = (Utilisateur) getItems().getRowData();
        return destroy();
    }
    
    public PageNavigation destroy() {
        performDestroy();
        recreateModel();
        JsfUtil.addInformationMessage(ResourceBundle.getBundle(BUNDLE).getString("UtilisateurDeleted"));
        return PageNavigation.LISTE;
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle(BUNDLE).getString("PersistenceErrorOccured"));
        }
    }

    public DataModel<Utilisateur> getItems() {
        if (items == null) {
        	items = new ListDataModel<Utilisateur>(getFacade().findAllDesc());
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }
    
    public int getUtilisateursCount() {
    	return ejbFacade.count();
    }

    public void setCustomer(Utilisateur user) {
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
