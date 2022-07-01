package dev.fatum.www.consumer.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dev.fatum.www.model.Entreprise;

/**
 * @author LEONARD
 */
@Stateless
public class EntrepriseBean extends AbstractFacade<Entreprise> {
    @PersistenceContext(name = "gestionPU")
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

    public Entreprise getEntrepriseByNom(String raisonSociale) {
        Query createNamedQuery = getEntityManager().createNamedQuery("Entreprise.findByRaisonSociale");

        createNamedQuery.setParameter("raisonSociale", raisonSociale);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Entreprise) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }
    }
    
    public List<Entreprise> getEntrepriseByProfilId(Integer id) {
        Query createNamedQuery = getEntityManager().createNamedQuery("Entreprise.findByProfilId");
        createNamedQuery.setParameter("id", id);
        return castList(Entreprise.class, createNamedQuery.getResultList());
    }

    public List<Entreprise> getEntrepriseByEntrepriseId(Integer id) {
        Query createNamedQuery = getEntityManager().createNamedQuery("Entreprise.findByEntrepriseId");
        createNamedQuery.setParameter("id", id);
        return castList(Entreprise.class, createNamedQuery.getResultList());
    }

    public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>(c.size());
        for(Object o: c)
          r.add(clazz.cast(o));
        return r;
    }

    public EntrepriseBean() {
        super(Entreprise.class);
    }
}
