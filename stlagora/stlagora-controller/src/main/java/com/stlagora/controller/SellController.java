package com.stlagora.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import com.stlagora.beans.SessionUser;
import com.stlagora.model.dao.CategoryDao;
import com.stlagora.model.dao.CategoryDaoImpl;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.enumerate.PRODUCT_STATUS;
import com.stlagora.model.entities.enumerate.TYPE_FICHIER;

@ManagedBean(name = "sellController", eager = true)
@RequestScoped
public class SellController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UploadedFile file;  
	private ProductDao productDao= new ProductDaoImpl();
	private CategoryDao categoryDao = new CategoryDaoImpl();

	private List<Category> categories = new ArrayList<Category>();

	private String name, description, images, plan;
	Float price;
	private Category productCategory ; 

	public UploadedFile getFile() {  
		return file;  
	}  

	public void setFile(UploadedFile file) {  
		this.file = file;  
	}  

	public String validateSell(){
		if(file != null) {  
			FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
			
			
			//TODO A SUPPRIMER
			this.productCategory = categoryDao.findByName("Test");
			
		}
		return "/sell/validationUpload?faces-redirect=true";
	}
	
	public String confirmSell(){
		SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		Product p = new Product(name, description, images, plan,productCategory,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, price, sessionUser.getUser(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		productDao.create(p);
		return "/sell/confirmSell?faces-redirect=true";
	}

	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		categories =  categoryDao.findAll();
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the images
	 */
	public String getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(String images) {
		this.images = images;
	}

	/**
	 * @return the plan
	 */
	public String getPlan() {
		return plan;
	}

	/**
	 * @param plan the plan to set
	 */
	public void setPlan(String plan) {
		this.plan = plan;
	}

	/**
	 * @return the price
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * @return the productCategory
	 */
	public Category getProductCategory() {
		return productCategory;
	}

	/**
	 * @param productCategory the productCategory to set
	 */
	public void setProductCategory(Category productCategory) {
		this.productCategory = productCategory;
	}
	
	



}
