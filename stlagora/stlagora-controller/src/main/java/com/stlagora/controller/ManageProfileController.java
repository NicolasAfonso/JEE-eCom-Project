package com.stlagora.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.stlagora.beans.SessionUser;
import com.stlagora.model.dao.OpinionDao;
import com.stlagora.model.dao.TransactionDao;
import com.stlagora.model.dao.TransactionDaoImpl;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Transaction;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;
import com.stlagora.model.entities.enumerate.ROLE;

@ManagedBean(name = "manageProfileController")
@SessionScoped
public class ManageProfileController implements Serializable {

	/**
	 * 
	 */
	private Logger log = Logger.getLogger(ManageProfileController.class.getName());
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserDao userDao;
	@EJB
	private TransactionDao transactionDao;
	
	/**
	 * Subscription variable
	 */
	private String login ;
	private String surname ="";
	private String firstname =""; 
	private String email ;
	private String password ;
	private String phoneNumber ="";
	private String siret ="";
	private String rib ="";
	private String companyName ="";
	private ACCOUNT_TYPE accountType;
	
	private List<Transaction> transactionBuy = new ArrayList<Transaction>() ;
	private List<Transaction> transactionSold = new ArrayList<Transaction>();
	
	public ManageProfileController(){
		
	}
	
	public String createUser(){
		log.debug(email);
		User u = new User(email,surname,firstname,email,password,new Date(System.currentTimeMillis()),phoneNumber,siret,companyName,rib,accountType,ROLE.MEMBER);
		userDao.create(u);
        SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		sessionUser.setLoggedIn(true);
		try{
		User userCreate = userDao.findByLogin(email);
		sessionUser.setUser(userCreate);
		}catch(Exception e){
			log.error("User not found");
		}
		return "/profile/accountParameters?faces-redirect=true";
	}
	
	public String update()
	{
		SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		User u = sessionUser.getUser();
		if(password.length() < 8){
			userDao.update(u);
			return "/profile/accountParameters?faces-redirect=true";
		}
		u.setPassword(password);
		userDao.update(u);
		return "/profile/accountParameters?faces-redirect=true";
		
	}
	
	public String remove(){
		SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		User u = sessionUser.getUser();
		u.setLogin("remove"+login+"remove");
		userDao.update(u);
		sessionUser.setUser(null);
		sessionUser.setLoggedIn(false);
		return "/home?faces-redirect=true";
	}
	
	public ACCOUNT_TYPE[] getAccountTypes() {
		return ACCOUNT_TYPE.values();
	}
	
	public String subscription(){
		switch (accountType) {
		case PRIVATE:
			return "/usercreate/createAccount";
		case PRO:
			return "/usercreate/createAccountPro";
		default:
			return "/global/error?faces-redirect=true";
		}
	}
	
	public String comeBackIdent(){
		return "/login?faces-redirect=true";
	}
	
	public String goToModificationProfile(){
		return "/profile/accountModif?faces-redirect=true";
	}
	
	public String goToForgetPassword(){
		return "/global/forgetPassword?faces-redirect=true";
	}
	
	public String goToHistoryPurchase(){
		return "/profile/historyPurchase?faces-redirect=true";
	}
	
	public String goToHistorySell(){
		return "/profile/historySell?faces-redirect=true";
	}
	
	public String goToForSale(){
		return "/profile/forSale?faces-redirect=true";
	}
	
	public String changeName(){
		return "/profile/newName?faces-redirect=true";
	}
	
	public String changeEmail(){
		return "/profile/newEmail?faces-redirect=true";
	}


	/**
	 * GETTER ET SETTER
	 */
	
	/**
	 * @return the username
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param pseudo the username to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the name
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param name the name to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
		 SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		 if(sessionUser != null)
		 {
			 transactionBuy =  transactionDao.findByBuyer(sessionUser.getUser());
		 }
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
		 SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		 if(sessionUser != null)
		 {
			 transactionSold = transactionDao.findBySeller(sessionUser.getUser());
		 }
		return transactionSold;
	}

	/**
	 * @param transactionSold the transactionSold to set
	 */
	public void setTransactionSold(List<Transaction> transactionSold) {
		this.transactionSold = transactionSold;
	}

	/**
	 * @return the siret
	 */
	public String getSiret() {
		return siret;
	}

	/**
	 * @param siret the siret to set
	 */
	public void setSiret(String siret) {
		this.siret = siret;
	}

	/**
	 * @return the rib
	 */
	public String getRib() {
		return rib;
	}

	/**
	 * @param rib the rib to set
	 */
	public void setRib(String rib) {
		this.rib = rib;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the accountType
	 */
	public ACCOUNT_TYPE getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(ACCOUNT_TYPE accountType) {
		this.accountType = accountType;
	}
	
	
	
}
