/**
 * 
 */
package com.stlagora.model.dao;



import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ROLE;

public class UserDaoImpl extends GenericDaoImpl< User > implements UserDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserDaoImpl(){
		super("STLAGORA_PU");
	}
	
	public UserDaoImpl(String persistanceUnit)
	{
		super(persistanceUnit);
	}
	
	
	public User findByPseudo(String pseudo) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.pseudo =:pseudo");
		q.setParameter("pseudo", pseudo);
		return (User) q.getSingleResult();
	}

	public List<User> findBySurname(String surname) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.surname =:surname");
		q.setParameter("surname", surname);
		List<User> results = q.getResultList();
		return results;
	}

	public List<User> findByFirstname(String firstname) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.firstname =:firstname");
		q.setParameter("firstname", firstname);
		List<User> results = q.getResultList();
		return results;
	}

	public User findByEmail(String email) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email =:email",User.class);
		q.setParameter("email", email);
		return (User) q.getSingleResult();
	}

	public List<User> findBySubscriptionDate(Date subscriptionDate) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.subscriptionDate =:subscriptionDate");
		q.setParameter("subscriptionDate", subscriptionDate);
		List<User> results = q.getResultList();
		return results;
	}

	public List<User> findByPhoneNumber(String phoneNumber) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.phoneNumber =:phoneNumber");
		q.setParameter("phoneNumber", phoneNumber);
		List<User> results = q.getResultList();
		return results;
	}

	public List<User> findByRole(ROLE role) {
		Query q = em.createQuery("SELECT u FROM User u WHERE u.role =:role");
		q.setParameter("role", role);
		List<User> results = q.getResultList();
		return results;
	}

	

	

}
