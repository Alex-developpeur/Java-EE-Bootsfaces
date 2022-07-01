package dev.fatum.www.business.impl.manager;

import dev.fatum.www.business.manager.AdministrateurManager;
import dev.fatum.www.model.Administrateur;
import dev.fatum.www.model.Profil;

public class AdministrateurManagerImpl extends AbstractManagerImpl implements AdministrateurManager {

	@Override
	public boolean createUser(Administrateur pAdministrateur) {
		return getDaoFactory().getAdministrateurDao().createUser(pAdministrateur);
	}

	@Override
	public Profil getUserByEmail(String pEmail) {
		return getDaoFactory().getAdministrateurDao().getUserByEmail(pEmail);
	}

	@Override
	public boolean checkEmail(Profil pProfil) {
		return getDaoFactory().getAdministrateurDao().checkEmail(pProfil);
	}

}
