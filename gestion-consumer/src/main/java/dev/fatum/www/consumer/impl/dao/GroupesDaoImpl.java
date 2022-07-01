package dev.fatum.www.consumer.impl.dao;

import javax.persistence.Query;

import dev.fatum.www.consumer.dao.GroupesDao;
import dev.fatum.www.model.Groupes;

public class GroupesDaoImpl extends AbstractDaoImpl<Groupes> implements GroupesDao {

	@Override
	public Groupes getGroupeByNom(String pNom) {
		/*
        Query createNamedQuery = getEntityManager().createNamedQuery("Groupes.findByNom");

        createNamedQuery.setParameter("nom", pNom);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Groupes) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }
        */
		return null;
	}

	@Override
	public boolean checkNom(Groupes pGroupes) {
		/*
    	Query createNamedQuery = em.createNamedQuery("Groupes.nomDuplicated")
    			.setParameter("nom", pGroupes.getNom())
    			.setParameter("id", pGroupes.getId());
    	if(createNamedQuery.getResultList().size() > 0)
    		return false;
    	else*/
    		return true;
	}

    public GroupesDaoImpl() {
        super(Groupes.class);
    }
}
