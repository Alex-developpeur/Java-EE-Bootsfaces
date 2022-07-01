package dev.fatum.www.consumer.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dev.fatum.www.model.Groupes;
import dev.fatum.www.model.Profil;
import dev.fatum.www.model.Utilisateur;

/**
 * @author LEONARD
 */
@Stateless
public class UtilisateurBean extends AbstractFacade<Utilisateur> {

    @PersistenceContext(name="gestionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Create a new user verifying if the user already exists
     * TODO: Create custom exceptions ?
     * @param utilisateur
     * @return 
     */

    public boolean createUser(Utilisateur utilisateur) {
        // check if user exists
        if (getUserByEmail(utilisateur.getEmail()) == null) {
            Groupes adminGroup = (Groupes) em.createNamedQuery("Groupes.findByNom")
                    .setParameter("nom", "USERS")
                    .getSingleResult();
            utilisateur.getGroupesList().add(adminGroup);
            adminGroup.getProfilList().add(utilisateur);

            super.create(utilisateur);
            return true;
        } else {
            return false;
        }
    }

    public Profil getUserByEmail(String email) {
        Query createNamedQuery = getEntityManager().createNamedQuery("Profil.findByEmail");

        createNamedQuery.setParameter("email", email);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Profil) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }
    }
    
    public boolean checkEmail(Profil p) {
    	Query createNamedQuery = em.createNamedQuery("Profil.emailDuplicated")
    			.setParameter("email", p.getEmail())
    			.setParameter("id", p.getId());
    	if(createNamedQuery.getResultList().size() > 0)
    		return false;
    	else
    		return true;
    }
    
    public UtilisateurBean() {
        super(Utilisateur.class);
    }

}
