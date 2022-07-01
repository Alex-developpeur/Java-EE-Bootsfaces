package dev.fatum.www.business.manager;

import java.util.List;

import dev.fatum.www.model.Entreprise;

public interface ParticuliersManager {

	Entreprise getEntrepriseByNom(String pRaisonSociale);
	
	List<Entreprise> getEntrepriseByProfilId(Integer pId);
}
