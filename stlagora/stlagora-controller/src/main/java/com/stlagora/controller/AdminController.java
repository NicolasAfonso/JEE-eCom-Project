package com.stlagora.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;

@ManagedBean(name = "adminController")
@SessionScoped
public class AdminController {
	private Logger log = Logger.getLogger(AdminController.class.getName());
	int nbMember;
	int nbMemberPro;
	int nbMemberPriv;
	int nbProduct;
	@EJB
	private UserDao userDao;
	@EJB
	private ProductDao productDao;
	
	private PieChartModel pieModelMember;
	private CartesianChartModel categoryModel;  
	
	
	private void createCategoryModel() {  
        categoryModel = new CartesianChartModel();  
  
        ChartSeries boys = new ChartSeries();  
        boys.setLabel("Boys");  
  
        boys.set("2004", 120);  
        boys.set("2005", 100);  
        boys.set("2006", 44);  
        boys.set("2007", 150);  
        boys.set("2008", 25);  
  
        ChartSeries girls = new ChartSeries();  
        girls.setLabel("Girls");  
  
        girls.set("2004", 52);  
        girls.set("2005", 60);  
        girls.set("2006", 110);  
        girls.set("2007", 135);  
        girls.set("2008", 120);  
  
        categoryModel.addSeries(boys);  
        categoryModel.addSeries(girls);  
    } 
	
	
	public void createPieModelMember() {  
		pieModelMember = new PieChartModel();  
		
		List<User> listNbMemberPriv = userDao.findByAccountType(ACCOUNT_TYPE.PRIVATE);
		nbMemberPriv = listNbMemberPriv.size();
		List<User> listNbMemberPro = userDao.findByAccountType(ACCOUNT_TYPE.PRO);
		nbMemberPro = listNbMemberPro.size();
		
		pieModelMember.set("Particuliers", 324);  
		pieModelMember.set("Professionnels", 512);  
		pieModelMember.set("Particuliers", 340);  
		pieModelMember.set("Professionnels", 200);  
    } 
	
	/**
	 * @return the nbMember
	 */
	public int getNbMember() {
		createPieModelMember();
		nbMember = userDao.findAll().size();
		return nbMember;
	}

	/**
	 * @param nbMember the nbMember to set
	 */
	public void setNbMember(int nbMember) {
		this.nbMember = nbMember;
	}

	/**
	 * @return the nbMemberPro
	 */
	public int getNbMemberPro() {
		List<User> listNbMemberPro = userDao.findByAccountType(ACCOUNT_TYPE.PRO);
		nbMemberPro = listNbMemberPro.size();
		return nbMemberPro;
	}

	/**
	 * @param nbMemberPro the nbMemberPro to set
	 */
	public void setNbMemberPro(int nbMemberPro) {
		this.nbMemberPro = nbMemberPro;
	}

	/**
	 * @return the nbMemberPriv
	 */
	public int getNbMemberPriv() {
		List<User> listNbMemberPriv = userDao.findByAccountType(ACCOUNT_TYPE.PRIVATE);
		nbMemberPriv = listNbMemberPriv.size();
		return nbMemberPriv;
	}

	/**
	 * @param nbMemberPriv the nbMemberPriv to set
	 */
	public void setNbMemberPriv(int nbMemberPriv) {
		this.nbMemberPriv = nbMemberPriv;
	}

	/**
	 * @return the pieModelMember
	 */
	public PieChartModel getPieModelMember() {
		return pieModelMember;
	}

	/**
	 * @param pieModelMember the pieModelMember to set
	 */
	public void setPieModelMember(PieChartModel pieModelMember) {
		this.pieModelMember = pieModelMember;
	}

	/**
	 * @return the nbProduct
	 */
	public int getNbProduct() {
		nbProduct = productDao.findAll().size();
		return nbProduct;
	}

	/**
	 * @param nbProduct the nbProduct to set
	 */
	public void setNbProduct(int nbProduct) {
		this.nbProduct = nbProduct;
	}

	/**
	 * @return the categoryModel
	 */
	public CartesianChartModel getCategoryModel() {
		createCategoryModel();
		return categoryModel;
	}

	/**
	 * @param categoryModel the categoryModel to set
	 */
	public void setCategoryModel(CartesianChartModel categoryModel) {
		this.categoryModel = categoryModel;
	}
	
	
	
}
