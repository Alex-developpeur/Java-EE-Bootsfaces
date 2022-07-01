package dev.fatum.www.consumer.dao;

import dev.fatum.www.model.Groupes;

/**
 * @author LEONARD
 */
public interface GroupesDao {
	
	Groupes getGroupeByNom(String pNom);
	
	boolean checkNom(Groupes pGroupes);
}
