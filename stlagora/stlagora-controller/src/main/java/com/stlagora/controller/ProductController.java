package com.stlagora.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.entities.Product;

@ManagedBean(name = "productController")
@RequestScoped
public class ProductController {
	
	private Logger log = Logger.getLogger(ProductController.class.getName());
	
	private Product product;
	private String id;
	private int nbOpinion;
	@EJB
	private ProductDao productDao;
	
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
		try{
			product = productDao.findById(Long.parseLong(id));
			nbOpinion = product.getOpinions().size();
		}
		catch(Exception e){
			log.error("Bad request, id = "+id);
		}
	}

	/**
	 * @return the nbOpinion
	 */
	public int getNbOpinion() {
		return nbOpinion;
	}

	/**
	 * @param nbOpinion the nbOpinion to set
	 */
	public void setNbOpinion(int nbOpinion) {
		this.nbOpinion = nbOpinion;
	}
	
}
