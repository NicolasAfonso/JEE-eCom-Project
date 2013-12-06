package com.stlagora.model.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryImmediateEditor;

import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Product;

@Stateless
public class CategoryDaoImpl extends GenericDaoImpl< Category > implements CategoryDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryDaoImpl(String persistanceUnit){
		super(persistanceUnit);
	}
	
	public CategoryDaoImpl()
	{
		super();
	}
	
	public Category findByName(String categoryName) {
		Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryName =:categoryName");
		q.setParameter("categoryName", categoryName);
		return (Category) q.getSingleResult();
	}
	

}
