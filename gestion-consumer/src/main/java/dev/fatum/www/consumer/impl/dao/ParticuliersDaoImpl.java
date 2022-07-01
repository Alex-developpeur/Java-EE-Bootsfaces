package dev.fatum.www.consumer.impl.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import dev.fatum.www.consumer.dao.ParticuliersDao;
import dev.fatum.www.model.Entreprise;

public class ParticuliersDaoImpl extends AbstractDaoImpl<Entreprise> implements ParticuliersDao {

	@Override
	public Entreprise getEntrepriseByNom(String pRaisonSociale) {
		/*
        Query createNamedQuery = getEntityManager().createNamedQuery("Entreprise.findByRaisonSociale");

        createNamedQuery.setParameter("raisonSociale", pRaisonSociale);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Entreprise) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }
        */
		return null;
	}

	@Override
	public List<Entreprise> getEntrepriseByProfilId(Integer pId) {
		/*
        Query createNamedQuery = getEntityManager().createNamedQuery("Entreprise.findByProfilId");

        createNamedQuery.setParameter("id", pId);
        
        List<Entreprise> entrepriseListe = castList(Entreprise.class, createNamedQuery.getResultList());

        return entrepriseListe;
//        return createNamedQuery.getResultList();
 * 
 */
		return null;
	}
	
    public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>(c.size());
        for(Object o: c)
          r.add(clazz.cast(o));
        return r;
    }

    public ParticuliersDaoImpl() {
        super(Entreprise.class);
    }
}
