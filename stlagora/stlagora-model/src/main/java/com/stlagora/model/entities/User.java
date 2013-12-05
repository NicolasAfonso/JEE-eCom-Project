package com.stlagora.model.entities;


import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;

import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;
import com.stlagora.model.entities.enumerate.ROLE;

@Entity
@Table(name="users")

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "login", nullable = false, unique=true) 
	private String login;
	
	@Column(name = "surname", nullable = false) 
	private String surname;
	
	@Column(name = "firstname", nullable = false) 
	private String firstname;
	
	@Column(name = "email", nullable = false, unique=true) 
	private String email;
	
	@Column(name = "password", nullable = false) 
	private String password;
	
	@Column(name = "subscriptionDate", nullable = false) 
	private Date subscriptionDate;
	
	@Column(name = "phoneNumber", nullable = false) 
	private String phoneNumber;
	
	@Column(name = "siret" , nullable = true)
	private String siret ;
	
	@Column(name="companyName")
	private String companyName; 
	
	@Column (name="rib",nullable=true)
	private String rib; 
	
	@Enumerated(EnumType.STRING)
	private ROLE role;
	
	@Enumerated(EnumType.STRING)
	private ACCOUNT_TYPE accountType; 
	
	@OneToMany(mappedBy = "usermarked", cascade=CascadeType.ALL)
	private List<Opinion> opinions = new ArrayList<Opinion>(); 
	
	public User(){
		
	}
	
	/**
	 * 
	 * @param pseudo
	 * @param surname
	 * @param firstname
	 * @param email
	 * @param subscriptionDate
	 * @param phoneNumber
	 * @param role
	 */
	public User(String login, String surname, String firstname, String email, String password,Date subscriptionDate, String phoneNumber,String siret,String companyName,String rib,ACCOUNT_TYPE accountType, ROLE role) {
		super();
		this.login = login;
		this.surname = surname;
		this.firstname = firstname;
		this.email = email;
		this.subscriptionDate = subscriptionDate;
		this.phoneNumber = phoneNumber;	
		this.companyName = companyName; 
		this.role = role;
		this.siret = siret;
		this.accountType = accountType;
		this.rib = rib; 
		
		//Crypt Password	 
		this.password = new String(DigestUtils.sha512(password));
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the pseudo
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setLogin(String login) {
		this.login = login;
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
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
	 * @return the subscriptionDate
	 */
	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	/**
	 * @param subscriptionDate the subscriptionDate to set
	 */
	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
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
	 * @return the status
	 */
	public ROLE getRole() {
		return role;
	}

	/**
	 * @param status the status to set
	 */
	public void setRole(ROLE role) {
		this.role = role;
	}

	/**
	 * @return the opinions
	 */
	public List<Opinion> getOpinions() {
		return opinions;
	}

	/**
	 * @param opinions the opinions to set
	 */
	public void setOpinions(List<Opinion> opinions) {
		this.opinions = opinions;
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
		this.password = new String(DigestUtils.sha512(password));
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
