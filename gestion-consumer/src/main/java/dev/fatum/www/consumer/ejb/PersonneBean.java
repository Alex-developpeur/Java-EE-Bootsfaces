package dev.fatum.www.consumer.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dev.fatum.www.model.Personne;

/**
 * @author LEONARD
 */
@Stateless
public class PersonneBean extends AbstractFacade<Personne> {
    @PersistenceContext(name = "gestionPU")
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
    public Personne getPersonneByNom(String nom) {
        Query createNamedQuery = getEntityManager().createNamedQuery("Personne.findByNom");

        createNamedQuery.setParameter("nom", nom);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Personne) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }
    }
    
    public List<Personne> getEntrepriseByProfilId(Integer id) {
        Query createNamedQuery = getEntityManager().createNamedQuery("Personne.findByEntrepriseId");
        createNamedQuery.setParameter("id", id);
        return castList(Personne.class, createNamedQuery.getResultList());
    }
    
    public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>(c.size());
        for(Object o: c)
          r.add(clazz.cast(o));
        return r;
    }

    public PersonneBean() {
        super(Personne.class);
    }
}
