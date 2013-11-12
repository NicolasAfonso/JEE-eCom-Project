package com.stlagora.model.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericDAOImpl<T> implements GenericDAO<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
    protected EntityManager em;

	
	
	public void create(T newObject) {
		em.getTransaction().begin();
		try {
			em.persist(newObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		em.getTransaction().commit();
	}

	public T findById(Class<T> c, long objectId) {
		return (T) em.find(c.getClass(), objectId);
	}

	public Collection<T> findAll(Class<T> c, long objectId) {
		Query query = em.createQuery("SELECT e FROM "+ c.getName() +"e");
	    return (Collection<T>) query.getResultList();
	}

	public void update(T object) {
		em.merge(object);
		
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


	
	

}
