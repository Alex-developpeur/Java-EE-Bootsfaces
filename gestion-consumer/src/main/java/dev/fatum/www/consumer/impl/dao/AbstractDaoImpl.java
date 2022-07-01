package dev.fatum.www.consumer.impl.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author LEONARD
 */
@Repository
public abstract class AbstractDaoImpl<T> {
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
    private Class<T> entityClass;

	public AbstractDaoImpl() {}

	public AbstractDaoImpl(final Class<T> pEntityClass) {
		this.entityClass = pEntityClass;
	}
	
	@Autowired
    public void setDataSource(DataSource pDataSource) {
		this.dataSource = pDataSource;
		this.jdbcTemplate = new JdbcTemplate(pDataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(pDataSource);
    }
	
    public DataSource getDataSource() { 
        return dataSource;
    }
    
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}


/*
	@PersistenceContext(name="gestionPU")
	protected EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public void create(T pEntity) {
        getEntityManager().persist(pEntity);
    }

    public void edit(T pEntity) {
        getEntityManager().merge(pEntity);
    }

    public void remove(T pEntity) {
        getEntityManager().remove(getEntityManager().merge(pEntity));
    }

    public T find(Object pId) {
        return getEntityManager().find(entityClass, pId);
    }
    
    public List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public List<T> findAllDesc() {
    	CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        cq.orderBy(cb.desc(root.get("id")));
        return getEntityManager().createQuery(cq).getResultList();
    }
    

/* 
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    public List<T> findRange(int[] range, CriteriaQuery<?> query) {
        javax.persistence.Query q = getEntityManager().createQuery(query);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    
    public CriteriaBuilder getCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }
*/    /*
    public int count() {
        javax.persistence.criteria.CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
*/
}
