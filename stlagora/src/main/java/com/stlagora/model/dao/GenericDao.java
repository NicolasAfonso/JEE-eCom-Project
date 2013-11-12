package com.stlagora.model.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.eclipse.persistence.internal.oxm.schema.model.List;

public interface GenericDao<T> extends Serializable{
	
	public void create(T newObject);
	
	public T findById(long objectId);
	
	public Collection<T> findAll(Class<T> c ,long objectId);
	
	public void update(T object);
	
	public void delete(T object);
}
