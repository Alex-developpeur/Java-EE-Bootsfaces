package dev.fatum.www.consumer.dao;

import dev.fatum.www.model.Administrateur;
import dev.fatum.www.model.Profil;

/**
 * @author LEONARD
 */
public interface AdministrateurDao {
	
	boolean createUser(Administrateur pAdministrateur);
	
	Profil getUserByEmail(String pEmail);
	
	boolean checkEmail(Profil pProfil);
}
