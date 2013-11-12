package com.stlagora.model.dao;

import java.util.List;

import javax.persistence.Query;

import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Product;


public class CategoryDaoImpl extends GenericDAOImpl< Category > implements CategoryDao{

	public CategoryDaoImpl(){
		super();
	}
	
	public Category findCategoryByName(String categoryName) {
		Query q = em.createQuery("SELECT u FROM CATEGORY WHERE u.categoryName =:categoryName");
		q.setParameter("categoryName", categoryName);
		List<Category> results = q.getResultList();
		return results.get(0);
	}
	
	

}
