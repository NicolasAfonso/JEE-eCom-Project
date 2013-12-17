package com.stlagora.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
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
import com.stlagora.model.entities.enumerate.TITLE;
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


@ManagedBean(name = "homeController")
@RequestScoped
public class HomeController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7356175149995220589L;
	private Logger log = Logger.getLogger(HomeController.class.getName());

	private String search;
	private String category;

	@EJB
	private UserDao userDao;
	@EJB
	private TransactionDao transactionDao;
	@EJB
	private ProductDao productDao;
	@EJB
	private CategoryDao categoryDao;

	public HomeController(){

	}

	public String moveToHome() {
		return "/home.xhtml?faces-redirect=true";
	}

	public String moveToMyProfile()
	{
		return "/profile/accountParameters?faces-redirect=true";
	}

	public String moveToHistSell() {
		return "/profile/historySell.xhtml?faces-redirect=true";
	}

	public String moveToHistPurchase() {
		return "/profile/historyPurchase.xhtml?faces-redirect=true";
	}

	public String moveToCart(){
		return "/global/cart?faces-redirect=true";
	}

	public String moveToSell(){
		return "/profile/forSale?faces-redirect=true";
	}

	public String moveToSearch(){
		return "/search/search?faces-redirect=true&categorySearch="+category+"&search="+search;
	}


	public String moveToSearchFromMenu(String category){
		return "/search/search?categorySearch="+category+"&faces-redirect=true";
	}

	public void initBDD(){

		//Create minimum User list in DB
		userDao.create(new User("tutu",TITLE.Mr ,"tutu", "tutu", "tutu@tata.com","test", new Date(0),"00000000","","","",ACCOUNT_TYPE.PRIVATE, ROLE.MEMBER));
		userDao.create(new User("tata",TITLE.Mr ,"tata", "tata", "tata@tata.com","test", new Date(0), "11111111","","","",ACCOUNT_TYPE.PRIVATE, ROLE.MEMBER));
		userDao.create(new User("toto",TITLE.Mr ,"toto", "toto", "toto@tata.com","test", new Date(0), "22222222","","","",ACCOUNT_TYPE.PRIVATE, ROLE.ADMIN));

		//Create minimum Category in DB
		categoryDao.create(new Category("Jouets", "Jouets desc"));
		categoryDao.create(new Category("Gadgets", "Gadgets desc"));
		categoryDao.create(new Category("Pieces de rechange", "Pièces de rechange desc"));
		categoryDao.create(new Category("Art et Deco", "Art & Déco desc"));
		categoryDao.create(new Category("Outils", "Outils desc"));
		categoryDao.create(new Category("Objets du quotidien", "Objets du quotidien desc"));

		Category c = categoryDao.findByName("Jouets");
		User u1 = userDao.findByEmail("tata@tata.com");
		
		//Create minimum Product list in DB
		productDao.create(new Product("Satria Neo 2000","Voiture miniature plus vraie que nature !", "toto","/home/stladmin/filer/private/1/satria.stl",c,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, 12.99f, u1 ,new Date(0), new Date(0)));		
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


}
