package com.stlagora.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.stlagora.beans.SessionUser;
import com.stlagora.model.dao.CategoryDao;
import com.stlagora.model.dao.CategoryDaoImpl;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;
import com.stlagora.model.entities.enumerate.PRODUCT_STATUS;
import com.stlagora.model.entities.enumerate.TYPE_FICHIER;

@ManagedBean(name = "sellController")
@RequestScoped
public class SellController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(SellController.class.getName());
	private UploadedFile plan;  
	private UploadedFile image;
	@EJB
	private ProductDao productDao;
	@EJB
	private CategoryDao categoryDao ;

	private List<Category> categories = new ArrayList<Category>();


	private String name ;
	private String description ;
	private Float price;
	private String productCategory ; 
	private PRODUCT_STATUS productStatus;

	private Product productUpload;

	// /!\ WARNING : here is the path of the directory were the files uploaded by the client (images & plans)  /!\
	private static String FILER = "/home/stladmin/filer";

	public String validateSell(){
		if(plan != null && image !=null) {    
			log.debug(plan.getFileName());
			log.debug(image.getFileName());
			SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
			if(sessionUser==null)
			{
				return "/home?faces-redirect=true";
			}
			else if(sessionUser.getUser().getAccountType() == ACCOUNT_TYPE.PRIVATE)
			{
				List<Product> listProduct = productDao.findBySeller(sessionUser.getUser());
				if(listProduct.size()>=10)
				{
					//					int inc = 0;
					//					for (Product p: listProduct) {
					//						if(!p.isDeleted())
					//						{
					//							inc ++;
					//						}
					//					}
					//					
					//					if(inc>=10)
					//					{
					return "/sell/error_private?faces-redirect=true";
					//					}
				}
			}
			Category c = categoryDao.findByName(productCategory);
			Product p = new Product(name, description, "NULL", "NULL",c,TYPE_FICHIER.STL,productStatus, price, sessionUser.getUser(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
			try{
				productDao.create(p);
			}catch (Exception e)
			{
				log.error("Product Already Exist");
			}
			p = productDao.findByName(name);
			if(!new File(FILER+"/private/"+p.getId()+"/").exists())
			{
				// Cr�er le dossier avec tous ses parents
				new File(FILER+"/private/"+p.getId()+"/").mkdirs();

			}
			if(!new File(FILER+"/public/"+p.getId()+"/").exists())
			{
				// Cr�er le dossier avec tous ses parents
				new File(FILER+"/public/"+p.getId()+"/").mkdirs();

			}

			try {
				File fimage = new File(FILER+"/public/"+p.getId()+"/"+image.getFileName());
				File fplan = new File(FILER+"/private/"+p.getId()+"/"+plan.getFileName());
				fimage.createNewFile();
				fplan.createNewFile();
				saveFile(plan.getInputstream(),new FileOutputStream(fplan));
				saveFile(image.getInputstream(),new FileOutputStream(fimage));	
				p.setPlan("/"+p.getId()+"/"+plan.getFileName());
				p.setImages("/"+p.getId()+"/"+image.getFileName());
				productDao.update(p);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				productDao.delete(p);
			} catch (IOException e) {
				log.error("File creation Problem");
				e.printStackTrace();
				productDao.delete(p);
			}
			productUpload = p;
		}
		return "/sell/validationUpload";
	}


	private void saveFile(InputStream inputStream, OutputStream out) throws IOException{
		int read = 0;
		byte[] bytes = new byte[1024];
		while ((read = inputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		inputStream.close();
		out.flush();
		out.close();

	}

	public PRODUCT_STATUS[] getAllProductStatus() {
		return PRODUCT_STATUS.values();
	}



	public String goToSell(){
		return "/sell/sell?faces-redirect=true";
	}


	/**
	 * 
	 * GETTER ET SETTER
	 */



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
	 * @param productStatus the productStatus to set
	 */
	public void setProductStatus(PRODUCT_STATUS productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * @return the productStatus
	 */
	public PRODUCT_STATUS getProductStatus() {
		return productStatus;
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
	public UploadedFile getImage() {
		return image;
	}

	/**
	 * @param images the images to set
	 */
	public void setImage(UploadedFile image) {
		this.image = image;
	}

	/**
	 * @return the plan
	 */
	public UploadedFile getPlan() {
		return plan;
	}

	/**
	 * @param plan the plan to set
	 */
	public void setPlan(UploadedFile plan) {
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
	public String getProductCategory() {
		return productCategory;
	}

	/**
	 * @param productCategory the productCategory to set
	 */
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}


	/**
	 * @return the productUpload
	 */
	public Product getProductUpload() {
		return productUpload;
	}


	/**
	 * @param productUpload the productUpload to set
	 */
	public void setProductUpload(Product productUpload) {
		this.productUpload = productUpload;
	}





}
