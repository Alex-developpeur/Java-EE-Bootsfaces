package dev.fatum.www.business.impl.manager;

import dev.fatum.www.business.manager.UtilisateurManager;
import dev.fatum.www.model.Profil;
import dev.fatum.www.model.Utilisateur;

public class UtilisateurManagerImpl extends AbstractManagerImpl implements UtilisateurManager {

	public int getCountProfil() {
		return getDaoFactory().getUtilisateurDao().getCountProfil();
	}
	
	@Override
	public int createUser(Utilisateur pUtilisateur) {
		if(getDaoFactory().getUtilisateurDao().getUserByEmail(pUtilisateur.getEmail()) < 0) {
			return getDaoFactory().getUtilisateurDao().createUser(pUtilisateur);
		}
		return 0;
	}

	@Override
	public boolean checkEmail(Profil pProfil) {
		return getDaoFactory().getUtilisateurDao().checkEmail(pProfil);
	}

}
