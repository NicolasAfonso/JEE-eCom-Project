package com.stlagora.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.stlagora.model.dao.CategoryDao;
import com.stlagora.model.dao.CategoryDaoImpl;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Product;

@ManagedBean(name = "searchController", eager = true)
@RequestScoped
public class SearchController implements Serializable {

	CategoryDao categoryDao = new CategoryDaoImpl();
	ProductDao productDao = new ProductDaoImpl();	
	UserDao userDao = new UserDaoImpl();
	
	@ManagedProperty(value="#{results}")
	private List<Product> results = new ArrayList<Product>();
	
	/**
	 * @return the t
	 */
	public List<Product> getResults() {
		results = productDao.findAll();
		return results;
	}

	/**
	 * @param t the t to set
	 */
	public void setResults(List<Product> results) {
		this.results = results;
	}

}
