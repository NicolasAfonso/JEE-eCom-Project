package com.stlagora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.apache.log4j.Logger;

import com.stlagora.model.dao.CategoryDao;
import com.stlagora.model.dao.CategoryDaoImpl;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Product;

@ManagedBean(name = "searchController")
@RequestScoped
public class SearchController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6448263727822874238L;
	private Logger log = Logger.getLogger(SearchController.class.getName());
	
	@EJB
	private ProductDao productDao;
	
	@EJB
	private CategoryDao categoryDao;
	
	@EJB
	private UserDao userDao;
	

	private List<Product> results = new ArrayList<Product>();
	private int prodId;

	private String search;
	private Category categorySearch;
	private int nbResults;
	
	private int page;
	
	private Product selectedProduct;
	
	private boolean notInit = true;
	
	public SearchController() {
	
		
	}
	
	
	private void init(){
		String cat = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categorySearch");
		String name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("search");
		
		System.out.println(name);
		System.out.println(cat);
		
//		if (cat.equals("null"))
//			cat=null;
//		if (name.equals("null"))
//			name=null;
		
		
		if (cat == null && name != null) {
			results= productDao.findBySearch(name);
			notInit = false;
		}
		
		else if (cat != null && name == null) {
			categorySearch = categoryDao.findByName(cat);
			results=productDao.findByCategory(categorySearch);
			notInit =false;
		}
		else if (cat != null && name != null){
			categorySearch = categoryDao.findByName(cat);
			results = productDao.findBySearchCategory(name, categorySearch);
			notInit = false;
		}
	
	}
	
	
	public List<Product> getProducts(){
		
		return productDao.findAll();
	}
	/**
	 * @return the t
	 */
	public List<Product> getResults() {
		
	
		if (notInit)
			init();
		
		
//		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
//		//System.out.println("FLASH SIZE "+( flash.values()));
//		search = (String)flash.get("search");
//		
//		String cat = (String) flash.get("categorySearch");
//		//setCategorySearch();
//		
//		log.debug(search);
//		log.debug(categorySearch);
//////		categorySearch = categoryDao.findByName("Test");		
//		if(cat==null && search != null)
//		{
//			this.setResults(productDao.findBySearch(search));
//		}
//		else if(cat !=null && search == null)
//		{categorySearch = categoryDao.findByName(cat);
//			this.setResults(productDao.findByCategory(categorySearch));
//			//this.setResults(productDao.findAll());
//		}else if(search != null && cat != null) {
//			categorySearch = categoryDao.findByName(cat);
//			this.setResults(productDao.findBySearchCategory(search, categorySearch));
//		}
//		
//		
//		setNbResults(results.size()) ;
//		log.info("NbResults : " +results.size());
		return results;
	}
	
	public String moveToSearch(String cat){
		this.categorySearch = categoryDao.findByName(cat);
		//System.out.println(this.categorySearch);
		//System.out.println(this.categorySearch.getCategoryName());
		return "/search/search?faces-redirect=true";
	}
	
	public String moveToProdCard(){
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String s = params.get("prodId");
		System.out.println("PROD ID"+s);
		String tmp = "/global/productCard?id="+s;
		return tmp;
	}
	

	/**
	 * @param t the t to set
	 */
	public void setResults(List<Product> results) {
		this.results = results;
	}

	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		log.info("SEARCH SETTE : " +search);
		this.search = search;
	}

	/**
	 * @return the categorySearch
	 */
	public Category getCategorySearch() {
		return categorySearch;
	}

	/**
	 * @param categorySearch the categorySearch to set
	 */
	public void setCategorySearch(Category categorySearch) {
		this.categorySearch = categorySearch;
	}
	/**
	 * @return the nbResults
	 */
	public int getNbResults() {
		return nbResults;
	}
	/**
	 * @param nbResults the nbResults to set
	 */
	public void setNbResults(int nbResults) {
		this.nbResults = nbResults;
	}
	/**
	 * @return the prodId
	 */
	public int getProdId() {
		return prodId;
	}
	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public Product getSelectedProduct() {
		return selectedProduct;
	}
	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	
	

}
