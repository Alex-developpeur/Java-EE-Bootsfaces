package dev.fatum.www.consumer.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dev.fatum.www.model.*;


/**
 * @author LEONARD
 */
@Stateless
public class AdministrateurBean extends AbstractFacade<Administrateur> {

    @PersistenceContext(name="gestionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Create a new user verifying if the user already exists
     * TODO: Create custom exceptions ?
     * @param administrateur
     * @return 
     */

    public boolean createUser(Administrateur administrateur) {
        // check if user exists
        if (getUserByEmail(administrateur.getEmail()) == null) {
            Groupes adminGroupe = (Groupes) em.createNamedQuery("Groupes.findByNom")
                    .setParameter("nom", "ADMINS")
                    .getSingleResult();
            administrateur.getGroupesList().add(adminGroupe);
            adminGroupe.getProfilList().add(administrateur);

            super.create(administrateur);
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

    public AdministrateurBean() {
        super(Administrateur.class);
    }

}
