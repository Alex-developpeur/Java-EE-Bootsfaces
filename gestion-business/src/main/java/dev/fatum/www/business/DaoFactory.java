package dev.fatum.www.business;

import dev.fatum.www.consumer.dao.AdministrateurDao;
import dev.fatum.www.consumer.dao.CoordonneesDao;
import dev.fatum.www.consumer.dao.EntrepriseDao;
import dev.fatum.www.consumer.dao.GroupesDao;
import dev.fatum.www.consumer.dao.ParticuliersDao;
import dev.fatum.www.consumer.dao.PersonneDao;
import dev.fatum.www.consumer.dao.UtilisateurDao;

public interface DaoFactory {
	
	AdministrateurDao getAdministrateurDao();
	
	CoordonneesDao getCoordonneesDao();
	
	EntrepriseDao getEntrepriseDao();
	
	GroupesDao getGroupesDao();
	
	ParticuliersDao getParticuliersDao();
	
	PersonneDao getPersonneDao();
	
	UtilisateurDao getUtilisateurDao();
}
