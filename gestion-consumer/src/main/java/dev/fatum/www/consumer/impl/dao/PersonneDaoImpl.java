package dev.fatum.www.consumer.impl.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import dev.fatum.www.consumer.dao.PersonneDao;
import dev.fatum.www.model.Personne;

public class PersonneDaoImpl extends AbstractDaoImpl<Personne> implements PersonneDao {

	@Override
	public Personne getPersonneByNom(String pNom) {
		/*
        Query createNamedQuery = getEntityManager().createNamedQuery("Personne.findByNom");

        createNamedQuery.setParameter("nom", pNom);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Personne) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }
        */
		return null;
	}

	@Override
	public List<Personne> getEntrepriseByProfilId(Integer pId) {
		/*
        Query createNamedQuery = getEntityManager().createNamedQuery("Personne.findByEntrepriseId");
        createNamedQuery.setParameter("id", pId);
        return castList(Personne.class, createNamedQuery.getResultList());
        */
		return null;
	}
	
    public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>(c.size());
        for(Object o: c)
          r.add(clazz.cast(o));
        return r;
    }

    public PersonneDaoImpl() {
        super(Personne.class);
    }
}
