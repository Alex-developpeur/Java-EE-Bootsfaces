package dev.fatum.www.business.manager;

import java.util.List;

import dev.fatum.www.model.Personne;

public interface PersonneManager {

	Personne getPersonneByNom(String pNom);
	
	List<Personne> getEntrepriseByProfilId(Integer pId);
}
