package com.stlagora.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.Transaction;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ROLE;
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


@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7356175149995220589L;
	private Logger log = Logger.getLogger(NavigationController.class.getName());
	public NavigationController(){
		
	}
	@ManagedProperty(value="#{param.pageId}")
	private String pageId;
	@ManagedProperty(value="#{t}")
	private Product t ;
	
	/**
	 * @return the t
	 */
	public Product getT() {
		return t;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(Product t) {
		this.t = t;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String moveToPage1(){
		
//		System.out.println("#TEST PRODUCT#");
		
//		productDao.create(new Product("toto", "toto", "toto", "toto", 1f, o,new Date(0), new Date(0)));
		
		CategoryDao categoryDao = new CategoryDaoImpl();
		ProductDao productDao = new ProductDaoImpl();	
		UserDao userDao = new UserDaoImpl();
//		categoryDao.create(new Category("Test","test-desc"));
//		
//		
//		//Create Users test
//		userDao.create(new User("tutu", "tutu", "tutu", "tutu", new Date(0), "00000000", ROLE.MEMBER));
//     	Category c = categoryDao.findByName("Test");
//		User u1 = userDao.findByEmail("tutu");
//    	productDao.create(new Product("p1", "tato", "toto", "toto", c, 1f, u1 ,new Date(0), new Date(0)));  
//		
//    	log.debug("Taille"+categoryDao.findByName("Test").getProducts().size());
		Product k = productDao.findByName("p1");
		OpinionDao oz = new OpinionDaoImpl();
//		//User
//		Opinion op = new Opinion();
//		op.setComment("popuser");
//		op.setMark(1f);
//		op.setWriter(u1);
//		op.setDate(new Date(0));
//		op.setUsermarked(u1);
//		oz.create(op);
//		//Product
//		Opinion op2 = new Opinion();
//		op2.setComment("popuser");
//		op2.setMark(1f);
//		op2.setWriter(u1);
//		op2.setProduct(k);
//		op2.setDate(new Date(0));
//		op2.setUsermarked(u1);
//		oz.create(op2);
//
//		
//		// Normalement Inutile
		
		
//		k.getOpinions().add(op);
//		productDao.update(k);
		Product i = productDao.findByName("p1");
		User u2 =  userDao.findByEmail("tutu");
		System.out.println("Opinion"+i.getOpinions().get(0).getComment().toString());
		System.out.println("Opinion user :" + u2.getOpinions().get(0).getComment().toString());
		System.out.println(categoryDao.findByName("Test").getProducts().get(0).getName());
		System.out.println("Opinino dep "+ oz.findByUserMarked(u2).size());
//		
//		System.out.println("titi"+k.getOpinions().get(0).getComment().toString());
//		System.out.println(k.getOpinions().size());
//		System.out.println("#FIN TEST PRODUCT#");
//		
//		System.out.println("#TEST CATEGORIES#");
//		CategoryDao categoryDao = new CategoryDaoImpl() ;
//		Category c = new Category();
//		c.setCategoryName("pop");
//		c.setDescription("pop pop");
//		c.getProducts().add(k);
//		categoryDao.create(c);
//		System.out.println("#FIN TEST CATEGORIES#");
//		
//		System.out.println("#TEST TRANSACTION#");
//		TransactionDao transactionDao = new TransactionDaoImpl();
//		Transaction t = new Transaction();
//		t.setAmount(1f);
//		t.setBuyer(o);
//		t.setSeller(o);
//		t.setDate(new Date(0));
//		t.setProduct(k);
//		
//		transactionDao.create(t);
		System.out.println("#FIN TEST TRANSACTION#");
		return "page1";
	}
}
