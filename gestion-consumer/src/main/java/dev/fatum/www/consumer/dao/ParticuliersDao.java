package dev.fatum.www.consumer.dao;

import java.util.List;

import dev.fatum.www.model.Entreprise;

/**
 * @author LEONARD
 */
public interface ParticuliersDao {
	
	Entreprise getEntrepriseByNom(String pRaisonSociale);
	
	List<Entreprise> getEntrepriseByProfilId(Integer pId);
}
