package dev.fatum.www.consumer.impl.dao;

import dev.fatum.www.consumer.dao.CoordonneesDao;
import dev.fatum.www.model.Coordonnees;

public class CoordonneesDaoImpl extends AbstractDaoImpl<Coordonnees> implements CoordonneesDao {

    public CoordonneesDaoImpl() {
        super(Coordonnees.class);
    }
}
