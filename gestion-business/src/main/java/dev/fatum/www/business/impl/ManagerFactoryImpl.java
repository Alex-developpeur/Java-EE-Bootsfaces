package dev.fatum.www.business.impl;

import dev.fatum.www.business.ManagerFactory;
import dev.fatum.www.business.manager.AdministrateurManager;
import dev.fatum.www.business.manager.CoordonneesManager;
import dev.fatum.www.business.manager.EntrepriseManager;
import dev.fatum.www.business.manager.GroupesManager;
import dev.fatum.www.business.manager.ParticuliersManager;
import dev.fatum.www.business.manager.PersonneManager;
import dev.fatum.www.business.manager.UtilisateurManager;

public class ManagerFactoryImpl implements ManagerFactory {

	private AdministrateurManager administrateurManager;
	
	private CoordonneesManager coordonneesManager;
	
	private EntrepriseManager entrepriseManager;
	
	private GroupesManager groupesManager;
	
	private ParticuliersManager particuliersManager;
	
	private PersonneManager personneManager;
	
	private UtilisateurManager utilisateurManager;
	
	@Override
	public AdministrateurManager getAdministrateurManager() {
		return administrateurManager;
	}

	public void setAdministrateurManager(AdministrateurManager pAdministrateurManager) {
		this.administrateurManager = pAdministrateurManager;
	}

	@Override
	public CoordonneesManager getCoordonneesManager() {
		return coordonneesManager;
	}

	public void setCoordonneesManager(CoordonneesManager pCoordonneesManager) {
		this.coordonneesManager = pCoordonneesManager;
	}

	@Override
	public EntrepriseManager getEntrepriseManager() {
		return entrepriseManager;
	}

	public void setGroupesManager(GroupesManager pGroupesManager) {
		this.groupesManager = pGroupesManager;
	}

	@Override
	public GroupesManager getGroupesManager() {
		return groupesManager;
	}

	public void setEntrepriseManager(EntrepriseManager pEntrepriseManager) {
		this.entrepriseManager = pEntrepriseManager;
	}

	@Override
	public ParticuliersManager getParticuliersManager() {
		return particuliersManager;
	}

	public void setParticuliersManager(ParticuliersManager pParticuliersManager) {
		this.particuliersManager = pParticuliersManager;
	}

	@Override
	public PersonneManager getPersonneManager() {
		return personneManager;
	}

	public void setPersonneManager(PersonneManager pPersonneManager) {
		this.personneManager = pPersonneManager;
	}

	@Override
	public UtilisateurManager getUtilisateurManager() {
		return utilisateurManager;
	}

	public void setUtilisateurManager(UtilisateurManager utilisateurManager) {
		this.utilisateurManager = utilisateurManager;
	}

}
