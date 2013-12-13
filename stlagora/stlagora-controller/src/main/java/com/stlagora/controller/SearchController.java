package com.stlagora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	ProductDao productDao;
	
	@EJB
	CategoryDao categoryDao;
	
	@EJB
	UserDao userDao = new UserDaoImpl();
	

	private List<Product> results = new ArrayList<Product>();

	private String search;
	private Category categorySearch;
	private int nbResults;
	
	private int page;
	
	public List<Product> getProducts(){
		return productDao.findAll();
	}
	/**
	 * @return the t
	 */
	public List<Product> getResults() {
		
		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
		//System.out.println("FLASH SIZE "+( flash.values()));
		search = (String)flash.get("search");
		
		String cat = (String) flash.get("categorySearch");
		//setCategorySearch();
		
		log.debug(search);
		log.debug(categorySearch);
////		categorySearch = categoryDao.findByName("Test");		
		if(cat==null && search != null)
		{
			this.setResults(productDao.findBySearch(search));
		}
		else if(cat !=null && search == null)
		{categorySearch = categoryDao.findByName(cat);
			this.setResults(productDao.findByCategory(categorySearch));
			//this.setResults(productDao.findAll());
		}else if(search != null && cat != null) {
			categorySearch = categoryDao.findByName(cat);
			this.setResults(productDao.findBySearchCategory(search, categorySearch));
		}
		
		
		setNbResults(results.size()) ;
		log.info("NbResults : " +results.size());
		return results;
	}
	
	public String moveToSearch(String cat){
		this.categorySearch = categoryDao.findByName(cat);
		//System.out.println(this.categorySearch);
		//System.out.println(this.categorySearch.getCategoryName());
		return "/search/search?faces-redirect=true";
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
	
	

}
