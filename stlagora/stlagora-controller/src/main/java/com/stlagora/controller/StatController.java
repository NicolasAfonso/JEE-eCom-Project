package com.stlagora.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.PieChartModel;

import com.stlagora.model.dao.CategoryDao;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;


@ManagedBean(name="statController")
@RequestScoped
public class StatController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserDao userDao;
	
	@EJB
	private ProductDao productDao;
	@EJB
	private CategoryDao categoryDao ;
	
	private int nbMembre ;
	private int nbProduct;
	
	private PieChartModel pie;
	private PieChartModel pieProduct;
	public StatController() {
		
	}
	
	
	public void createPie() {  
		pie = new PieChartModel();  	
		pie.set("Compte Particulier", userDao.findByAccountType(ACCOUNT_TYPE.PRIVATE).size());
		pie.set("Compte Pro", userDao.findByAccountType(ACCOUNT_TYPE.PRO).size());


    }


	public PieChartModel getPie() {
		createPie();	
		return pie;
	}
	
	public void createPieProduct(){
		pieProduct = new PieChartModel();  
		
		List<Category> categories = categoryDao.findAll();
		for (Category c : categories) {
			pieProduct.set(c.getCategoryName(), productDao.findByCategory(c).size());
		}
	}
	
	public PieChartModel getPieProduct() {
		createPieProduct();	
		return pieProduct;
	}


	public int getNbMembre() {
		return nbMembre;
	}


	public void setNbMembre(int nbMembre) {
		this.nbMembre = nbMembre;
	}


	public int getNbProduct() {
		return nbProduct;
	}


	public void setNbProduct(int nbProduct) {
		this.nbProduct = nbProduct;
	}
	
	

}