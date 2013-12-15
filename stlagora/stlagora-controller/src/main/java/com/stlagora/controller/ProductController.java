package com.stlagora.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.stlagora.beans.SessionUser;
import com.stlagora.model.dao.CategoryDao;
import com.stlagora.model.dao.OpinionDao;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;
import com.stlagora.model.entities.enumerate.PRODUCT_STATUS;
import com.stlagora.model.entities.enumerate.TYPE_FICHIER;

@ManagedBean(name = "productController")
@RequestScoped
public class ProductController {
	
	private Logger log = Logger.getLogger(ProductController.class.getName());
	
	private Product product;
	private String id;
	private int nbOpinion;
	private StreamedContent file; 
	private UploadedFile plan;  
	private UploadedFile image;
	private List<Category> categories = new ArrayList<Category>();
	private List<Product> products;
	

	@EJB
	private ProductDao productDao;
	@EJB
	private CategoryDao categoryDao ;
	
	
	public String validateSellModif(){
		SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		log.debug("ici :)");
		try{
			productDao.update(product);
		}catch (Exception e)
		{
			log.error("Product Update failed");
		}
		if(image != null)
			
			try {
				File fimage = new File("C:/FILER/public/"+product.getImages());
				File fplan = new File("C:/FILER/private/"+product.getPlan());
				fimage.createNewFile();
				fplan.createNewFile();
				saveFile(plan.getInputstream(),new FileOutputStream(fplan));
				saveFile(image.getInputstream(),new FileOutputStream(fimage));	
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				log.error("File creation Problem");
				e.printStackTrace();
				FacesMessage msg = new FacesMessage("Error", "Creation failed uploaded.");  
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			FacesMessage msg = new FacesMessage("Succesful", plan.getFileName() + " is uploaded.");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.debug("en bas");
		return "/home";
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
	
	public void removeProduct(Product p){
		p.setDeleted(true);
		productDao.update(p);
	}
	
	public String goToSellModif(Product p){
		product = p;
		return "/sell/sellModif?faces-redirect=true&id="+p.getId();
	}
	
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
	
}
