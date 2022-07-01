package dev.fatum.www.consumer.dao;

import dev.fatum.www.model.Profil;
import dev.fatum.www.model.Utilisateur;

/**
 * @author LEONARD
 */
public interface UtilisateurDao {
	
	int createUser(Utilisateur pUtilisateur);
	
	int getUserByEmail(String pEmail);
	
	boolean checkEmail(Profil pProfil);
	
	int getCountProfil();
}
