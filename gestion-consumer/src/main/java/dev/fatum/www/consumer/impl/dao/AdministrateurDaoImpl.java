package dev.fatum.www.consumer.impl.dao;

import javax.persistence.Query;

import dev.fatum.www.consumer.dao.AdministrateurDao;
import dev.fatum.www.model.Administrateur;
import dev.fatum.www.model.Groupes;
import dev.fatum.www.model.Profil;

public class AdministrateurDaoImpl extends AbstractDaoImpl<Administrateur> implements AdministrateurDao {

	@Override
	public boolean createUser(Administrateur pAdministrateur) {
/*        // check if user exists
        if (getUserByEmail(pAdministrateur.getEmail()) == null) {
            Groupes adminGroupe = (Groupes) em.createNamedQuery("Groupes.findByNom")
                    .setParameter("nom", "ADMINS")
                    .getSingleResult();
            pAdministrateur.getGroupesList().add(adminGroupe);
            adminGroupe.getProfilList().add(pAdministrateur);

            super.create(pAdministrateur);
            return true;
        } else {
            return false;
        }*/
		return false;
	}

	@Override
	public Profil getUserByEmail(String pEmail) {
/*        Query createNamedQuery = getEntityManager().createNamedQuery("Profil.findByEmail");

        createNamedQuery.setParameter("email", pEmail);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Profil) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }*/
		return null;
	}

	@Override
	public boolean checkEmail(Profil pProfil) {
/*		Query createNamedQuery = em.createNamedQuery("Profil.emailDuplicated")
    			.setParameter("email", pProfil.getEmail())
    			.setParameter("id", pProfil.getId());
    	if(createNamedQuery.getResultList().size() > 0)
    		return false;
    	else
    	*/	return true;
	}

    public AdministrateurDaoImpl() {
        super(Administrateur.class);
    }
		
}
