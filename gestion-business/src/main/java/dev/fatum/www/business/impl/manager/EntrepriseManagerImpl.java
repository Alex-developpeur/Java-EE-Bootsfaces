package dev.fatum.www.business.impl.manager;

import java.util.List;

import dev.fatum.www.business.manager.EntrepriseManager;
import dev.fatum.www.model.Entreprise;

public class EntrepriseManagerImpl extends AbstractManagerImpl implements EntrepriseManager {

	@Override
	public Entreprise getEntrepriseByNom(String pRaisonSociale) {
		return getDaoFactory().getEntrepriseDao().getEntrepriseByNom(pRaisonSociale);
	}

	@Override
	public List<Entreprise> getEntrepriseByProfilId(Integer pId) {
		return getDaoFactory().getEntrepriseDao().getEntrepriseByProfilId(pId);
	}

	@Override
	public List<Entreprise> getEntrepriseByEntrepriseId(Integer pId) {
		return getDaoFactory().getEntrepriseDao().getEntrepriseByEntrepriseId(pId);
	}

}
