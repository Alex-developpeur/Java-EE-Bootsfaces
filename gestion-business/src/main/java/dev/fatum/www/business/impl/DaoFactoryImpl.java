package dev.fatum.www.business.impl;

import dev.fatum.www.business.DaoFactory;
import dev.fatum.www.consumer.dao.AdministrateurDao;
import dev.fatum.www.consumer.dao.CoordonneesDao;
import dev.fatum.www.consumer.dao.EntrepriseDao;
import dev.fatum.www.consumer.dao.GroupesDao;
import dev.fatum.www.consumer.dao.ParticuliersDao;
import dev.fatum.www.consumer.dao.PersonneDao;
import dev.fatum.www.consumer.dao.UtilisateurDao;

public class DaoFactoryImpl implements DaoFactory {
	
	private AdministrateurDao administrateurDao;
	
	private CoordonneesDao coordonneesDao;
	
	private EntrepriseDao entrepriseDao;
	
	private GroupesDao groupesDao;
	
	private ParticuliersDao particuliersDao;
	
	private PersonneDao personneDao;
	
	private UtilisateurDao utilisateurDao;

	@Override
	public AdministrateurDao getAdministrateurDao() {
		return administrateurDao;
	}

	public void setAdministrateurDao(AdministrateurDao pAdministrateurDao) {
		this.administrateurDao = pAdministrateurDao;
	}

	@Override
	public CoordonneesDao getCoordonneesDao() {
		return coordonneesDao;
	}

	public void setCoordonneesDao(CoordonneesDao pCoordonneesDao) {
		this.coordonneesDao = pCoordonneesDao;
	}

	@Override
	public EntrepriseDao getEntrepriseDao() {
		return entrepriseDao;
	}

	public void setEntrepriseDao(EntrepriseDao pEntrepriseDao) {
		this.entrepriseDao = pEntrepriseDao;
	}

	@Override
	public GroupesDao getGroupesDao() {
		return groupesDao;
	}

	public void setGroupesDao(GroupesDao pGroupesDao) {
		this.groupesDao = pGroupesDao;
	}

	@Override
	public ParticuliersDao getParticuliersDao() {
		return particuliersDao;
	}

	public void setParticuliersDao(ParticuliersDao pParticuliersDao) {
		this.particuliersDao = pParticuliersDao;
	}

	@Override
	public PersonneDao getPersonneDao() {
		return personneDao;
	}

	public void setPersonneDao(PersonneDao pPersonneDao) {
		this.personneDao = pPersonneDao;
	}

	@Override
	public UtilisateurDao getUtilisateurDao() {
		return utilisateurDao;
	}

	public void setUtilisateurDao(UtilisateurDao pUtilisateurDao) {
		this.utilisateurDao = pUtilisateurDao;
	}
}
