package com.stlagora.model.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;

public class ProductDaoImpl extends GenericDAOImpl<Product> implements ProductDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Product findByName(String name) {
		Query q = em.createQuery("SELECT p FROM User p WHERE p.name=:name",Product.class);
		q.setParameter("name",name);
		List<Product> products = q.getResultList();
		if(products.isEmpty())
		{
			return null;
		}
		else
		{
			return products.get(0);
		}
		
	}

	public List<Product> findByPrice(Float price) {
		Query q = em.createQuery("SELECT p FROM User p WHERE p.price=:price",Product.class);
		q.setParameter("price",price);
		List<Product> products = q.getResultList();
		return (List<Product>) products.get(0);
	}

	public List<Product> findBySeller(User seller) {
		Query q = em.createQuery("SELECT p FROM User p WHERE p.seller_id=:seller_id",Product.class);
		q.setParameter("seller_id",seller.getId());
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findByAvailableDate(Date availableDate) {
		Query q = em.createQuery("SELECT p FROM User p WHERE p.availableDate=:availableDate",Product.class);
		q.setParameter("availableDate",availableDate);
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findByLastUpdate(Date lastUpdate) {
		Query q = em.createQuery("SELECT p FROM User p WHERE p.lastUpdate=:lastUpdate",Product.class);
		q.setParameter("availableDate",lastUpdate);
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findByGlobalMark(Float globalMark) {
		Query q = em.createQuery("SELECT p FROM User p WHERE p.globalMark=:globalMark",Product.class);
		q.setParameter("availableDate",globalMark);
		List<Product> products = q.getResultList();
		return products;
	}

}
