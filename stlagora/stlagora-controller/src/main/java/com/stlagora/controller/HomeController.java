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

import org.apache.log4j.Logger;

import com.stlagora.beans.SessionUser;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.Transaction;
import com.stlagora.model.entities.User;
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
	public HomeController(){
		
	}
	@ManagedProperty(value="#{param.pageId}")
	private String pageId;
	@ManagedProperty(value="#{t}")
	private List<Product> t = new ArrayList<Product>();
	
	/**
	 * @return the t
	 */
	public List<Product> getT() {
		return t;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(List<Product> t) {
		this.t = t;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String moveToMyProfile(){
		 SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		 if(sessionUser == null || sessionUser.isLoggedIn()==false)
		 {
			 return "/login";
		 }
		 else
		 {
			 return "/profile/myProfile";
		 }
		
	}
	
	public String moveToSearch(){
		CategoryDao categoryDao = new CategoryDaoImpl();
		ProductDao productDao = new ProductDaoImpl();	
		UserDao userDao = new UserDaoImpl();
		t = productDao.findAll();
		return "/search/resultSearch";
	}
	
	public String moveToCart(){
		return "/purchase/cartView";
	}
	
	public void initBDD(){
		UserDaoImpl userDao = new UserDaoImpl();
		
		CategoryDaoImpl categoryDao = new CategoryDaoImpl();
		ProductDaoImpl productDao = new ProductDaoImpl();	
		TransactionDaoImpl transactionDao = new TransactionDaoImpl();
		
		userDao.create(new User("tutu", "tutu", "tutu", "tutu@tata.com","test", new Date(0),"00000000", ROLE.MEMBER));
		userDao.create(new User("tata", "tata", "tata", "tata@tata.com","test", new Date(0), "11111111", ROLE.MEMBER));
		userDao.create(new User("toto", "toto", "toto", "toto@tata.com","test", new Date(0), "22222222", ROLE.ADMIN));
		categoryDao.create(new Category("Test","test-desc"));
		Category c = categoryDao.findByName("Test");
		User u1 = userDao.findByEmail("tata@tata.com");
		User u2 = userDao.findByEmail("tutu@tata.com");

		productDao.create(new Product("p1", "tato", "toto", "toto", c,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, 1f, u1 ,new Date(0), new Date(0)));  
		productDao.create(new Product("p2", "toto", "toto", "toto", c,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, 2f, u1 ,new Date(0), new Date(0)));  
		productDao.create(new Product("p3", "toto", "toto", "toto", c,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, 2f, u2 ,new Date(0), new Date(0)));  
		
	}
}