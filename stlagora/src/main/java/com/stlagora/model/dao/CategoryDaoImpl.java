package com.stlagora.model.dao;

import java.util.List;

import javax.persistence.Query;

import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Product;


public class CategoryDaoImpl extends GenericDaoImpl< Category > implements CategoryDao{

	public CategoryDaoImpl(){
		super();
	}
	
	public Category findByName(String categoryName) {
		Query q = em.createQuery("SELECT c FROM Category WHERE c.categoryName =:categoryName");
		q.setParameter("categoryName", categoryName);
		List<Category> results = q.getResultList();
		return results.get(0);
	}
	
	//idée: faire findByProduct
	// on va dire dans un premier temps qu'un produit ne peut être associé qu'a UNE SEULE catégorie
	public Category findByProduct(Product product) {
		Query q = em.createQuery("SELECT c FROM Category WHERE :product IN c.products");
		q.setParameter("product", product);
		List<Category> results = q.getResultList();
		return results.get(0);
	}

}
