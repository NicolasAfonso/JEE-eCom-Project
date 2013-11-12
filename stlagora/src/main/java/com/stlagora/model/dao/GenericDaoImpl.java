package com.stlagora.model.dao;

import java.util.Collection;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class GenericDaoImpl<T> implements GenericDao<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    protected EntityManager em;
    private Class type;
    public GenericDaoImpl(){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");   
    	this.em = emf.createEntityManager(); 
    	 Type t = getClass().getGenericSuperclass();
         ParameterizedType pt = (ParameterizedType) t;
         type = (Class) pt.getActualTypeArguments()[0];

    }
	public void create(T newObject) {
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
