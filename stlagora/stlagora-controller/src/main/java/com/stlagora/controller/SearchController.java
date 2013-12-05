package com.stlagora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.stlagora.model.dao.CategoryDao;
import com.stlagora.model.dao.CategoryDaoImpl;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Product;

@ManagedBean(name = "searchController")
@SessionScoped
public class SearchController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6448263727822874238L;
	CategoryDao categoryDao = new CategoryDaoImpl();
	ProductDao productDao = new ProductDaoImpl();	
	UserDao userDao = new UserDaoImpl();
	
	@ManagedProperty(value="#{results}")
	private List<Product> results = new ArrayList<Product>();
	@ManagedProperty(value="#{search}")
	private String search;

	/**
	 * @return the t
	 */
	public List<Product> getResults() {
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
	
	

}
