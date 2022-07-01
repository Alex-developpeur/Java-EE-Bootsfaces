package dev.fatum.www.business.manager;

import dev.fatum.www.model.Profil;
import dev.fatum.www.model.Utilisateur;

public interface UtilisateurManager {

	int createUser(Utilisateur pUtilisateur);
	
	boolean checkEmail(Profil pProfil);
	
	int getCountProfil();
}
