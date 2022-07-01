package dev.fatum.www.consumer.dao;

import java.util.List;

import dev.fatum.www.model.Personne;

/**
 * @author LEONARD
 */
public interface PersonneDao {
	
	Personne getPersonneByNom(String pNom);
	
	List<Personne> getEntrepriseByProfilId(Integer pId);
}
