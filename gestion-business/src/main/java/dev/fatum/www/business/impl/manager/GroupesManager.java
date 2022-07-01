package dev.fatum.www.business.impl.manager;

import dev.fatum.www.model.Groupes;

public class GroupesManager extends AbstractManagerImpl implements dev.fatum.www.business.manager.GroupesManager {

	@Override
	public Groupes getGroupeByNom(String pNom) {
		return getDaoFactory().getGroupesDao().getGroupeByNom(pNom);
	}

	@Override
	public boolean checkNom(Groupes pGroupes) {
		return getDaoFactory().getGroupesDao().checkNom(pGroupes);
	}

}
