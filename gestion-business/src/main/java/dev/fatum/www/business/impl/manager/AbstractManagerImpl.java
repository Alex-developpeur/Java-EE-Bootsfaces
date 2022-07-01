package dev.fatum.www.business.impl.manager;

import dev.fatum.www.business.DaoFactory;

public class AbstractManagerImpl {

	private DaoFactory daoFactory;
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}
	
	public void setDaoFactory(DaoFactory pDaoFactory) {
		daoFactory = pDaoFactory;
	}
}
