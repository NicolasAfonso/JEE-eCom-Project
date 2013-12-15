package com.stlagora.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.PieChartModel;

import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;

@ManagedBean(name = "adminController")
@SessionScoped
public class AdminController {
	
	int nbMember;
	int nbMemberPro;
	int nbMemberPriv;
	int nbProduct;
	@EJB
	private UserDao userDao;
	@EJB
	private ProductDao productDao;
	
	private PieChartModel pieModelMember;

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
	
	
}
