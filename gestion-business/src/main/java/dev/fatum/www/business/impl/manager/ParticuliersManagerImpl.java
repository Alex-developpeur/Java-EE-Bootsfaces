package dev.fatum.www.business.impl.manager;

import java.util.List;

import dev.fatum.www.business.manager.ParticuliersManager;
import dev.fatum.www.model.Entreprise;

public class ParticuliersManagerImpl extends AbstractManagerImpl implements ParticuliersManager {

	@Override
	public Entreprise getEntrepriseByNom(String pRaisonSociale) {
		return getDaoFactory().getParticuliersDao().getEntrepriseByNom(pRaisonSociale);
	}

	@Override
	public List<Entreprise> getEntrepriseByProfilId(Integer pId) {
		return getDaoFactory().getParticuliersDao().getEntrepriseByProfilId(pId);
	}

}
