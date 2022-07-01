package dev.fatum.www.consumer.impl.dao;

import java.sql.Types;

import javax.inject.Named;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import dev.fatum.www.consumer.dao.UtilisateurDao;
import dev.fatum.www.model.Groupes;
import dev.fatum.www.model.Profil;
import dev.fatum.www.model.Utilisateur;

@Named
public class UtilisateurDaoImpl extends AbstractDaoImpl<Utilisateur> implements UtilisateurDao {
	
	private static final Log LOGGER = LogFactory.getLog(UtilisateurDaoImpl.class);

	public int getCountProfil() {
	    int vNbrTicket = getJdbcTemplate().queryForObject(
	        "SELECT COUNT(*) FROM profil",
	        Integer.class);
	
	    return vNbrTicket;
	}
	
	@Override
	public int createUser(Utilisateur pUtilisateur) {
/*        // check if user exists
        if (getUserByEmail(pUtilisateur.getEmail()) == null) {
            Groupes adminGroup = (Groupes) em.createNamedQuery("Groupes.findByNom")
                    .setParameter("nom", "USERS")
                    .getSingleResult();
            pUtilisateur.getGroupesList().add(adminGroup);
            adminGroup.getProfilList().add(pUtilisateur);

            super.create(pUtilisateur);
            return true;
        } else {
            return false;
        }*/
        String vSQL = "INSERT INTO profil (nom, mdp, email, expiration) " +
        			"VALUES (:nom, :mdp, :email, :expiration)";

        BeanPropertySqlParameterSource vParams = new BeanPropertySqlParameterSource(pUtilisateur);
        vParams.registerSqlType("nom", Types.VARCHAR);
        vParams.registerSqlType("mdp", Types.VARCHAR);
        vParams.registerSqlType("email", Types.VARCHAR);
        vParams.registerSqlType("expiration", Types.DATE);

        try {
            return getNamedParameterJdbcTemplate().update(vSQL, vParams);
        } catch (DuplicateKeyException vEx) {
            LOGGER.error("L'utilisateur existe déjà ! id=" + pUtilisateur.getId(), vEx);
            return 0;
        }
	}

	@Override
	public int getUserByEmail(String pEmail) {
        MapSqlParameterSource vParams = new MapSqlParameterSource();

        StringBuilder vSQL = new StringBuilder("SELECT COUNT(*) FROM profil WHERE 1=1");

        if (pEmail != null) {
            vSQL.append(" AND email = :email");
            vParams.addValue("email", pEmail);
        }

        int vNbrTicket = getNamedParameterJdbcTemplate().queryForObject(vSQL.toString(), vParams, Integer.class);
        
/*        Query createNamedQuery = getEntityManager().createNamedQuery("Profil.findByEmail");

        createNamedQuery.setParameter("email", pEmail);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Profil) createNamedQuery.getSingleResult();
        }
        else {
            return null;
        }*/
		return vNbrTicket;
	}

	@Override
	public boolean checkEmail(Profil pProfil) {
/*    	Query createNamedQuery = em.createNamedQuery("Profil.emailDuplicated")
    			.setParameter("email", pProfil.getEmail())
    			.setParameter("id", pProfil.getId());
    	if(createNamedQuery.getResultList().size() > 0)
    		return false;
    	else
  */  		return true;
	}
	
    public UtilisateurDaoImpl() {
        super(Utilisateur.class);
    }
}
