package com.stlagora.model.dao;

import java.util.Collection;
import java.util.List;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericDaoImpl<T> implements GenericDao<T>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="STLAGORA_PU")
    protected EntityManager em;
    private Class type;
    public GenericDaoImpl(String persistanceUnit){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistanceUnit);   
    	this.em = emf.createEntityManager(); 
    	 Type t = getClass().getGenericSuperclass();
         ParameterizedType pt = (ParameterizedType) t;
         type = (Class) pt.getActualTypeArguments()[0];

    }
    
    public GenericDaoImpl(){
    	 Type t = getClass().getGenericSuperclass();
         ParameterizedType pt = (ParameterizedType) t;
         type = (Class) pt.getActualTypeArguments()[0];

    }
	public void create(T newObject) {
		try {
			em.persist(newObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void createByEm(T newObject) {
		em.getTransaction().begin();
		try {
			em.persist(newObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		em.getTransaction().commit();
	}

	public T findById(long objectId) {
		return (T) em.find(type, objectId);
	}

	public List<T> findAll() {
		Query query = em.createQuery("SELECT e FROM "+ type.getName()+" e");
	    return  query.getResultList();
	}

	public void update(T object) {
		em.getTransaction().begin();
		em.merge(object);
		em.getTransaction().commit();
	}

	public void delete(T object) {
		em.getTransaction().begin();
		try {
			em.remove(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.getTransaction().commit();
	}
	

    public EntityManager getEntityManager() {
        return em;
    }

	
	

}
