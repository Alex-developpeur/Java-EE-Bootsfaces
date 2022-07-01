package dev.fatum.www.business.manager;

import dev.fatum.www.model.Groupes;

public interface GroupesManager {

	Groupes getGroupeByNom(String pNom);
	
	boolean checkNom(Groupes pGroupes);
}
