package com.stlagora.model.dao;

import java.util.List;

import javax.persistence.Query;

import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Product;


public class CategoryDaoImpl extends GenericDAOImpl< Category > implements CategoryDao{

	public CategoryDaoImpl(){
		super();
	}
	
	public Category findByName(String categoryName) {
		Query q = em.createQuery("SELECT c FROM CATEGORY WHERE c.categoryName =:categoryName");
		q.setParameter("categoryName", categoryName);
		List<Category> results = q.getResultList();
		return results.get(0);
	}
	
	

}
