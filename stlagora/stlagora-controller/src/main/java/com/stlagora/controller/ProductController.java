package com.stlagora.controller;

import java.io.File;
import java.io.FileInputStream;
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
import javax.faces.context.Flash;
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
@SessionScoped
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
	private String productCategory ;
	private String name, description, images, plann;
	private Category category;
	private PRODUCT_STATUS status;
	private Float price;
	
	// /!\ WARNING : here is the path of the directory were the files uploaded by the client (images & plans)  /!\
	private static String FILER = "/home/stladmin/filer";

	@EJB
	private ProductDao productDao;
	@EJB
	private CategoryDao categoryDao ;
	
	private boolean modif = false;
	
	public ProductController(){

	}
	
	private void init()
	{
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if(id != null )
		{
			modif = true ; 
			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
			flash.put("modif", true);
			product = productDao.findById(Long.parseLong(id));
			this.name = product.getName();
			this.category = product.getCategory();
			this.productCategory=product.getCategory().getCategoryName();
			this.description = product.getDescription();
			this.status = product.getStatus();
			this.price = product.getPrice();

		}
	}
	public String validateSellModif(String id){
		SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		//product = productDao.findById(Long.parseLong(id));
		modif = false ; 
		product.setName(name);
		Category cat = categoryDao.findByName(productCategory);
		product.setCategory(cat);
		product.setDescription(description);
		product.setPrice(price);
		product.setStatus(status);
		
		try{
			productDao.update(product);
		}catch (Exception e)
		{
			log.error("Product Update failed");
		}
//		if(image != null)
//			
//			try {
//				File fimage = new File(FILER+"/public/"+product.getImages());
//				fimage.createNewFile();
//				saveFile(image.getInputstream(),new FileOutputStream(fimage));	
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				log.error("File creation Problem");
//				e.printStackTrace();
//			}
//		
//		if(plan != null)
//		{
//			try{
//				File fplan = new File(FILER+"/private/"+product.getPlan());
//				fplan.createNewFile();
//				saveFile(plan.getInputstream(),new FileOutputStream(fplan));
//			}catch(Exception e)
//			{
//				log.error("File creation Problem");
//			}
//		}
//			FacesMessage msg = new FacesMessage("Succesful", plan.getFileName() + " is uploaded.");  
//			FacesContext.getCurrentInstance().addMessage(null, msg);
			log.debug("en bas");
		return "/profile/forSale?faces-redirect=true";
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
		log.debug("Product ID : "+productDownload.getId());
		log.debug(FILER+"/private/"+productDownload.getPlan());
		//TODO Récupérer le vrai chemin du fichier
		File f = new File(FILER+"/private/"+productDownload.getPlan());
		InputStream stream;
		try {
			stream = new FileInputStream(f);
	        file = new DefaultStreamedContent(stream, "image/png", f.getName());  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}//((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(FILER+"/private/"+productDownload.getPlan());  
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

	/**
	 * @return the name
	 */
	public String getName() {
		init();
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the plann
	 */
	public String getPlann() {
		return plann;
	}

	/**
	 * @param plann the plann to set
	 */
	public void setPlann(String plann) {
		this.plann = plann;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the status
	 */
	public PRODUCT_STATUS getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(PRODUCT_STATUS status) {
		this.status = status;
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
	 * @return the productDao
	 */
	public ProductDao getProductDao() {
		return productDao;
	}

	/**
	 * @param productDao the productDao to set
	 */
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
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
	 * @return the modif
	 */
	public boolean isModif() {
		return modif;
	}

	/**
	 * @param modif the modif to set
	 */
	public void setModif(boolean modif) {
		this.modif = modif;
	}
	
	
	
}
