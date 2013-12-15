package com.stlagora.model.dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.PRODUCT_STATUS;
import com.stlagora.model.entities.enumerate.TYPE_FICHIER;
@Stateless
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProductDaoImpl(){
		super();
	}
	
	public ProductDaoImpl(String persistanceUnit){
		super(persistanceUnit);
	}
	
	public Product findByName(String name) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.name=:name and p.isDeleted=0",Product.class);
		q.setParameter("name",name);
		return (Product) q.getSingleResult();
		
	}
	
	public Product findByNameAll(String name) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.name=:name",Product.class);
		q.setParameter("name",name);
		return (Product) q.getSingleResult();
		
	}

	public List<Product> findByPrice(Float price) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.price=:price and p.isDeleted=0",Product.class);
		q.setParameter("price",price);
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findBySeller(User seller) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.seller=:seller_id and p.isDeleted=0",Product.class);
		q.setParameter("seller_id",seller);
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findByAvailableDate(Date availableDate) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.availableDate=:availableDate and p.isDeleted=0",Product.class);
		q.setParameter("availableDate",availableDate);
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findByLastUpdate(Date lastUpdate) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.lastUpdate=:lastUpdate and p.isDeleted=0",Product.class);
		q.setParameter("lastUpdate",lastUpdate);
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findByGlobalMark(Float globalMark) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.globalMark=:globalMark and p.isDeleted=0",Product.class);
		q.setParameter("globalMark",globalMark);
		List<Product> products = q.getResultList();
		return products;
	}
	
	public List<Product> findByCategory(Category category) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.category=:category and p.isDeleted=0",Product.class);
		q.setParameter("category",category);
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findByProductStatus(PRODUCT_STATUS status) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.status=:status and p.isDeleted=0",Product.class);
		q.setParameter("status",status);
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findByProductType(TYPE_FICHIER type) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.type=:type and p.isDeleted=0",Product.class);
		q.setParameter("type",type);
		List<Product> products = q.getResultList();
		return products;
	}

	public List<Product> findBySearch(String search) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.name LIKE :search and p.isDeleted=0",Product.class);
		q.setParameter("search","%"+search+"%");
		List<Product> products = q.getResultList();
		return products;
	}
	
	public List<Product> findBySearchCategory(String search,Category category) {
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.name LIKE :search and p.category=:cat and p.isDeleted=0",Product.class);
		q.setParameter("search","%"+search+"%");
		q.setParameter("cat",category);
		List<Product> products = q.getResultList();
		return products;
	}
	
}
