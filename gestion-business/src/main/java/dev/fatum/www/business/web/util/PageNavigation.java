package dev.fatum.www.business.web.util;

/**
 * @author LEONARD
 * 
 * Redirection avec réécriture des URLs :
 * ?faces-redirect=true
 * 
 */
public enum PageNavigation {

    CREER("Creer?faces-redirect=true"),
    LISTE("Liste?faces-redirect=true"),
    EDITER("Editer?faces-redirect=true"),
    EDITER_MDP("EditerMDP?faces-redirect=true"),
    VOIR("Voir?faces-redirect=true"),
    INDEX("index?faces-redirect=true"),
	CONNEXION("/connexion?faces-redirect=true");
    private String text;

    PageNavigation(final String s) {
        this.text = s;
    }

    public String getText() {
        return this.text;
    }
    
    @Override
    public String toString() {
        return this.text;
    }
}
