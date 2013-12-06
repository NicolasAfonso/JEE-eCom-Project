package com.stlagora.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public interface GenericDao<T> extends Serializable{
	
	public void create(T newObject);
	
	public T findById(long objectId);
	
	public List<T> findAll();
	
	public void update(T object);
	
	public void delete(T object);

	public EntityManager getEntityManager();

}
