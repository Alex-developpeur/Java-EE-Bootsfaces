package dev.fatum.www.business;

import dev.fatum.www.business.manager.AdministrateurManager;
import dev.fatum.www.business.manager.CoordonneesManager;
import dev.fatum.www.business.manager.EntrepriseManager;
import dev.fatum.www.business.manager.GroupesManager;
import dev.fatum.www.business.manager.ParticuliersManager;
import dev.fatum.www.business.manager.PersonneManager;
import dev.fatum.www.business.manager.UtilisateurManager;

public interface ManagerFactory {
	
	AdministrateurManager getAdministrateurManager();

	CoordonneesManager getCoordonneesManager();
	
	EntrepriseManager getEntrepriseManager();
	
	GroupesManager getGroupesManager();
	
	ParticuliersManager getParticuliersManager();
	
	PersonneManager getPersonneManager();
	
	UtilisateurManager getUtilisateurManager();
}
