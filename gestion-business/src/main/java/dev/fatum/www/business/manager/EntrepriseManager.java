package dev.fatum.www.business.manager;

import java.util.List;

import dev.fatum.www.model.Entreprise;

public interface EntrepriseManager {

	Entreprise getEntrepriseByNom(String pRaisonSociale);
	
	List<Entreprise> getEntrepriseByProfilId(Integer pId);
	
	List<Entreprise> getEntrepriseByEntrepriseId(Integer pId);
}
