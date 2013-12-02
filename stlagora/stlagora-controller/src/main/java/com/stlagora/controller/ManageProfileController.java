package com.stlagora.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.stlagora.beans.SessionUser;
import com.stlagora.model.dao.TransactionDao;
import com.stlagora.model.dao.TransactionDaoImpl;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Transaction;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ROLE;
import com.stlagora.model.entities.Product;

@ManagedBean(name = "manageProfileController", eager = true)
@RequestScoped
public class ManageProfileController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDaoImpl();
	private TransactionDao transactionDao = new TransactionDaoImpl();
	
	/**
	 * Subscription variable
	 */
	private String username ;
	private String name ;
	private String surname ; 
	private String email ;
	private String password ;
	private String phoneNumber ;
	
	private List<Transaction> transactionBuy = new ArrayList<Transaction>() ;
	private List<Transaction> transactionSold = new ArrayList<Transaction>();
	public String createUser(){
		userDao.create(new User(username,name,surname,email,password,new Date(System.currentTimeMillis()),phoneNumber,ROLE.MEMBER));
        SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		sessionUser.setLoggedIn(true);
		sessionUser.setUser(userDao.findByPseudo(username));
		return "/profile/myProfile";
	}
	
	public ManageProfileController(){
		 SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		 if(sessionUser != null)
		 {
			 transactionBuy =  transactionDao.findByBuyer(sessionUser.getUser());
			 transactionSold = transactionDao.findBySeller(sessionUser.getUser());
		 }
		
	}
	
	public String showPurchase(){
		return "";
	}
	
	public String showSell(){
		return "";
	}

	
	/**
	 * GETTER ET SETTER
	 */
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param pseudo the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the transactionBuy
	 */
	public List<Transaction> getTransactionBuy() {
		return transactionBuy;
	}

	/**
	 * @param transactionBuy the transactionBuy to set
	 */
	public void setTransactionBuy(List<Transaction> transactionBuy) {
		this.transactionBuy = transactionBuy;
	}

	/**
	 * @return the transactionSold
	 */
	public List<Transaction> getTransactionSold() {
		return transactionSold;
	}

	/**
	 * @param transactionSold the transactionSold to set
	 */
	public void setTransactionSold(List<Transaction> transactionSold) {
		this.transactionSold = transactionSold;
	}
	
	
}
