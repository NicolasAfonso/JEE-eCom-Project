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
import com.stlagora.model.entities.enumerate.PRODUCT_STATUS;
import com.stlagora.model.entities.enumerate.TYPE_FICHIER;

@ManagedBean(name = "sellController", eager = true)
@RequestScoped
public class SellController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(SessionUser.class.getName());
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
	private Category productCategory ; 

	public String validateSell(){
		if(plan != null && image !=null) {  
			FacesMessage msg = new FacesMessage("Succesful", plan.getFileName() + " is uploaded.");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
			log.debug(plan.getFileName());
			log.debug(image.getFileName());
			this.productCategory = categoryDao.findByName("Test");
			SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
			Product p = new Product(name, description, "", "",productCategory,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, price, sessionUser.getUser(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
			try{
				productDao.create(p);
			}catch (Exception e)
			{
				log.error("Product Already Exist");
			}
			p = productDao.findByName(name);
			   if(!new File("C:/FILER/"+p.getId()+"/").exists())
		        {
		            // Créer le dossier avec tous ses parents
		            new File("C:/FILER/"+p.getId()+"/").mkdirs();
		 
		        }
			
			p.setPlan("C:/FILER/"+p.getId()+"/"+plan.getFileName());
			p.setPlan("C:/FILER/"+p.getId()+"/"+image.getFileName());
			try {
				File fimage = new File("C:/FILER/"+p.getId()+"/"+image.getFileName());
				File fplan = new File("C:/FILER/"+p.getId()+"/"+plan.getFileName());
				fimage.createNewFile();
				fplan.createNewFile();
				saveFile(plan.getInputstream(),new FileOutputStream(fplan));
				saveFile(image.getInputstream(),new FileOutputStream(fimage));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				log.error("File creation Problem");
				e.printStackTrace();
			}
			
		}
		return "/sell/validationUpload";
	}
	
	
	private void saveFile(InputStream inputStream, OutputStream out){
		try {
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public String confirmSell(){
		
		//TODO A SUPPRIMER
		this.productCategory = categoryDao.findByName("Test");
		SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		Product p = new Product(
				name, description, image.getFileName(), plan.getFileName(),productCategory,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, price, sessionUser.getUser(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
		productDao.create(p);
		return "/sell/confirmSell";
	}

	public void handleFileUpload(FileUploadEvent event) {
        try {
            File targetFolder = new File("C:/FILER/");
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    event.getFile().getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];
            log.debug( event.getFile().getFileName());

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	/**
	 * 
	 * GETTER ET SETTER
	 */

	public UploadedFile getFile() {  
		return plan;  
	}  

	public void setFile(UploadedFile file) {  
		this.plan = file;  
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
