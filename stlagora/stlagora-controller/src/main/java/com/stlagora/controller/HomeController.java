package com.stlagora.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.apache.log4j.Logger;

import com.stlagora.beans.SessionUser;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.Transaction;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;
import com.stlagora.model.entities.enumerate.PRODUCT_STATUS;
import com.stlagora.model.entities.enumerate.ROLE;
import com.stlagora.model.entities.enumerate.TYPE_FICHIER;
import com.stlagora.model.dao.CategoryDao;
import com.stlagora.model.dao.CategoryDaoImpl;
import com.stlagora.model.dao.OpinionDao;
import com.stlagora.model.dao.OpinionDaoImpl;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.dao.TransactionDao;
import com.stlagora.model.dao.TransactionDaoImpl;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;


@ManagedBean(name = "homeController", eager = true)
@RequestScoped
public class HomeController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7356175149995220589L;
	private Logger log = Logger.getLogger(HomeController.class.getName());
	
	private String search;
	
	public HomeController(){
		
	}
	
	public String moveToMyProfile()
	{
		return "/profile/myProfile?faces-redirect=true";
	}

	
	public String moveToCart(){
		return "/cartView?faces-redirect=true";
	}
	
	public String moveToSell(){
		return "/sell/sellProduct?faces-redirect=true";
	}
	
	public String moveToSearch(){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();  
        flash.put("search", search);  
		return "/search/resultSearch";
	}
	
	public void initBDD(){
		UserDaoImpl userDao = new UserDaoImpl();
		
		CategoryDaoImpl categoryDao = new CategoryDaoImpl();
		ProductDaoImpl productDao = new ProductDaoImpl();	
		
		userDao.create(new User("tutu", "tutu", "tutu", "tutu@tata.com","test", new Date(0),"00000000","","","",ACCOUNT_TYPE.PRIVATE, ROLE.MEMBER));
		userDao.create(new User("tata", "tata", "tata", "tata@tata.com","test", new Date(0), "11111111","","","",ACCOUNT_TYPE.PRIVATE, ROLE.MEMBER));
		userDao.create(new User("toto", "toto", "toto", "toto@tata.com","test", new Date(0), "22222222","","","",ACCOUNT_TYPE.PRIVATE, ROLE.ADMIN));
		categoryDao.create(new Category("Test","test-desc"));
		Category c = categoryDao.findByName("Test");
		User u1 = userDao.findByEmail("tata@tata.com");
		User u2 = userDao.findByEmail("tutu@tata.com");

		productDao.create(new Product("p1", "tato", "toto", "toto", c,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, 1f, u1 ,new Date(0), new Date(0)));  
		productDao.create(new Product("p2", "toto", "toto", "toto", c,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, 2f, u1 ,new Date(0), new Date(0)));  
		productDao.create(new Product("p3", "toto", "toto", "toto", c,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, 2f, u2 ,new Date(0), new Date(0)));  
		
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