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
		
		
		
		
		//log.debug("Tutu");
		System.out.println("#TEST USER#");
		UserDao userDao = new UserDaoImpl();
//		
//		userDao.create(new User("tutu", "tutu", "tutu", "tutu", new Date(0), "00000000", ROLE.MEMBER));
//		userDao.create(new User("toto", "toto", "toto", "toto", new Date(0), "00000000", ROLE.MEMBER));
//		List<User> l = userDao.findByPhoneNumber("00000000");
//		User o = userDao.findByEmail("toto");
//		System.err.println(l.size());
//		System.err.println(o.getEmail());
		System.out.println("#USER FINISH#");
		
		System.out.println("#TEST PRODUCT#");
		ProductDao productDao = new ProductDaoImpl();
//		productDao.create(new Product("toto", "toto", "toto", "toto", 1f, o,new Date(0), new Date(0)));
		
		
//		Product k = productDao.findByName("toto");
//		Opinion op = new Opinion();
//		op.setComment("pop");
//		op.setMark(1f);
//		op.setWriter(o);
//		op.setDate(new Date(0));
//		op.setProduct(k);
//		System.out.println(k.getOpinions().size());
//		System.out.println(k.getOpinions().get(0).getComment());
//		productDao.update(k);
//		op.setComment("pop");
//		op.setMark(1f);
//		op.setWriter(o);
//		op.setDate(new Date(0));
//		op.setProduct(k);
//		productDao.create(new Product("toto", "toto", "toto", "toto", 1f, o,new Date(0), new Date(0)));
		t = productDao.findByName("toto");

//		
		// Normalement Inutile
//    	OpinionDao oz = new OpinionDaoImpl();
//		oz.create(op);
//		p.getOpinions().add(op);
//		productDao.update(p);
//		Product i = productDao.findByName("toto");
//		System.out.println(i.getOpinions().get(0).getComment().toString());
//		
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
