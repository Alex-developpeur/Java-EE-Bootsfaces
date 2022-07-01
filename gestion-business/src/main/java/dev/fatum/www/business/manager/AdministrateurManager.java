package dev.fatum.www.business.manager;

import dev.fatum.www.model.Administrateur;
import dev.fatum.www.model.Profil;

public interface AdministrateurManager {
	
	boolean createUser(Administrateur pAdministrateur);
	
	Profil getUserByEmail(String pEmail);
	
	boolean checkEmail(Profil pProfil);
}
