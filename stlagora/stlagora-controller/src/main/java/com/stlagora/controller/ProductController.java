package com.stlagora.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.stlagora.beans.SessionUser;
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
	private StreamedContent file; 
	@EJB
	private ProductDao productDao;
	
	private List<Product> products;
	
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

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		if(sessionUser != null)
		{
			products = productDao.findBySeller(sessionUser.getUser());
		}
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	public int getGlobalMarkInt(Product product){
		return Math.round(product.getGlobalMark());
	}
	
	public int getGlobalMarkInt(){
		return Math.round(this.product.getGlobalMark());
	}
	
	/**
	 * @return the file
	 */
	public StreamedContent getFile() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String s = params.get("productDownload");
		Product productDownload = productDao.findById(Long.parseLong(s));
		log.debug(productDownload.getId());
		//TODO Récupérer le vrai chemin du fichier
		InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/images/home.png");  
        file = new DefaultStreamedContent(stream, "image/png", "home.png");  
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(StreamedContent file) {
		this.file = file;
	}
	
	
}
