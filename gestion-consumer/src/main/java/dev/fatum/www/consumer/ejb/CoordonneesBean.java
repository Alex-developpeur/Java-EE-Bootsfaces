package dev.fatum.www.consumer.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dev.fatum.www.model.Coordonnees;

/**
 * @author LEONARD
 */
@Stateless
public class CoordonneesBean extends AbstractFacade<Coordonnees> {
    @PersistenceContext(name = "gestionPU")
    private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

    public CoordonneesBean() {
        super(Coordonnees.class);
    }
}
