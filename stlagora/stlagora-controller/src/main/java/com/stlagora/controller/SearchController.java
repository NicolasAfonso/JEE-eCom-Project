package com.stlagora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

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
	CategoryDao categoryDao = new CategoryDaoImpl();
	ProductDao productDao = new ProductDaoImpl();	
	UserDao userDao = new UserDaoImpl();
	

	private List<Product> results = new ArrayList<Product>();

	private String search;
	private Category categorySearch;
	
	/**
	 * @return the t
	 */
	public List<Product> getResults() {
		
		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
		search = (String)flash.get("search");
		categorySearch = (Category) flash.get("category");
//		categorySearch = categoryDao.findByName("Test");		
		if(categorySearch==null)
		{
			this.setResults(productDao.findBySearch(search));
		}
		else
		{
			this.setResults(productDao.findBySearchCategory(search, categorySearch));
		}
		return results;
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
	
	

}
