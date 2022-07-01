package dev.fatum.www.business.impl.manager;

import java.util.List;

import dev.fatum.www.business.manager.PersonneManager;
import dev.fatum.www.model.Personne;

public class PersonneManagerImpl extends AbstractManagerImpl implements PersonneManager {

	@Override
	public Personne getPersonneByNom(String pNom) {
		return getDaoFactory().getPersonneDao().getPersonneByNom(pNom);
	}

	@Override
	public List<Personne> getEntrepriseByProfilId(Integer pId) {
		return getDaoFactory().getPersonneDao().getEntrepriseByProfilId(pId);
	}

}
