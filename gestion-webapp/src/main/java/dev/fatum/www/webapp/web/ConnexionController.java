package dev.fatum.www.webapp.web;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dev.fatum.www.business.qualifiers.LoggedIn;
import dev.fatum.www.business.web.util.JsfUtil;
import dev.fatum.www.business.web.util.PageNavigation;
import dev.fatum.www.business.web.util.PasswordStorage;
import dev.fatum.www.business.web.util.PasswordStorage.CannotPerformOperationException;
import dev.fatum.www.business.web.util.PasswordStorage.InvalidHashException;
import dev.fatum.www.model.Groupes;
import dev.fatum.www.model.Profil;

/**
 * @author LEONARD
 */
@Named(value = "connexionController")
@SessionScoped
public class ConnexionController implements Serializable {
    
    private static final String BUNDLE = "bundles.Bundle";
    private static final long serialVersionUID = -8851462237612818158L;

    Profil user;
    @EJB
    private dev.fatum.www.consumer.ejb.UtilisateurBean ejbFacade;
    private String username;
    private String password;
    @Inject
    UtilisateurController utilisateurController;

//    private static final Logger logger = Logger.getLogger(ConnexionController.class.getName());

    public ConnexionController() {
    }    
    /**
     * Login method based on
     * <code>HttpServletRequest</code> and security realm
     * @throws CannotPerformOperationException 
     * @throws InvalidHashException 
     */
    public String login() {
    	
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String result;

        try {
        	Profil profil = ejbFacade.getUserByEmail(this.getUsername());

        	if (profil != null) {
        		this.setPassword(PasswordStorage.verifyPassword(this.getPassword(), profil.getMdp()));
        	}

            request.login(this.getUsername(), this.getPassword());

//            logger.log(Level.INFO, "##### Test ##### " +  this.getPassword());

            JsfUtil.addInformationMessage(JsfUtil.getStringFromBundle(BUNDLE, "Login_Success"));

            this.user = ejbFacade.getUserByEmail(getUsername());
            this.getAuthenticatedUser();

            if (isAdmin()) {
                result = "/admin/" + PageNavigation.INDEX;
            } else {
                result = "/mon-compte/" + PageNavigation.INDEX;
            }

        } catch (ServletException ex) {
            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle(BUNDLE, "Login_Failed"));

            result = PageNavigation.CONNEXION.toString();
        }

        return result;
    }

    @SuppressWarnings("finally")
	public PageNavigation logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {
            this.user = null;
            
            request.logout();
            // clear the session
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
            
            JsfUtil.addInformationMessage(JsfUtil.getStringFromBundle(BUNDLE, "Logout_Success"));

        } catch (ServletException ex) {

            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(JsfUtil.getStringFromBundle(BUNDLE, "Logout_Failed"));
        } finally {
            return PageNavigation.CONNEXION;
        }
    }

    /**
     * @return the ejbFacade
     */
    public dev.fatum.www.consumer.ejb.UtilisateurBean getEjbFacade() {
        return ejbFacade;
    }

    public @Produces
    @LoggedIn
    Profil getAuthenticatedUser() {
        return user;
    }

    public boolean isLogged() {
        return (getUser() == null) ? false : true;
    }

    public boolean isAdmin() {
        for (Groupes g : user.getGroupesList()) {
            if (g.getNom().equals("ADMINS")) {
                return true;
            }
        }
        return false;
    }

    public String goAdmin() {
        if (isAdmin()) {
            return "/admin/" + PageNavigation.INDEX;
        } else {
            return PageNavigation.INDEX.toString();
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the user
     */
    public Profil getUser() {
        return user;
    }
}
