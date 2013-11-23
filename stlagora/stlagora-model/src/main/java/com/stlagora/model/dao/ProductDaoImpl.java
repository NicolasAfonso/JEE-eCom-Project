package com.stlagora.model.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;

public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProductDaoImpl(){
		super("STLAGORA_PU");
	}
	
	public ProductDaoImpl(String persistanceUnit){
		super(persistanceUnit);
	}
	
	public Product findByName(String name) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.name=:name",Product.class);
		q.setParameter("name",name);
		return (Product) q.getSingleResult();
		
	}

	public List<Product> findByPrice(Float price) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.price=:price",Product.class);
		q.setParameter("price",price);
		List<Product> products = q.getResultList();
		return (List<Product>) products.get(0);
	}

	public List<Product> findBySeller(User seller) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.seller_id=:seller_id",Product.class);
		q.setParameter("seller_id",seller.getId());
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findByAvailableDate(Date availableDate) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.availableDate=:availableDate",Product.class);
		q.setParameter("availableDate",availableDate);
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findByLastUpdate(Date lastUpdate) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.lastUpdate=:lastUpdate",Product.class);
		q.setParameter("availableDate",lastUpdate);
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findByGlobalMark(Float globalMark) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.globalMark=:globalMark",Product.class);
		q.setParameter("availableDate",globalMark);
		List<Product> products = q.getResultList();
		return products;
	}

}
