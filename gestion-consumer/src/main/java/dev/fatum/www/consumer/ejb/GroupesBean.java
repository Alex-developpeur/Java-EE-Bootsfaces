package dev.fatum.www.consumer.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dev.fatum.www.model.Groupes;

/**
 * @author LEONARD
 */
@Stateless
public class GroupesBean extends AbstractFacade<Groupes> {
    @PersistenceContext(name = "gestionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Groupes getGroupeByNom(String nom) {
        Query createNamedQuery = getEntityManager().createNamedQuery("Groupes.findByNom");

        createNamedQuery.setParameter("nom", nom);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Groupes) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }
    }

    public boolean checkNom(Groupes g) {
    	Query createNamedQuery = em.createNamedQuery("Groupes.nomDuplicated")
    			.setParameter("nom", g.getNom())
    			.setParameter("id", g.getId());
    	if(createNamedQuery.getResultList().size() > 0)
    		return false;
    	else
    		return true;
    }

    public GroupesBean() {
        super(Groupes.class);
    }
    
}
