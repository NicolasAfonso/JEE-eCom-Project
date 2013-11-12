/**
 * 
 */
package com.stlagora.model.dao;

import java.sql.Date;
import java.util.List;

import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ROLE;

public interface UserDao extends GenericDao<User>  {

	
	public User findByPseudo(String pseudo);
	public List<User> findBySurname(String surname);
	public List<User> findByFirstname(String firstname);
	public User findByEmail(String email);
	public List<User> findBySubscriptionDate(Date subscriptionDate);
	public List<User> findByPhoneNumber(String phoneNumber);
	public List<User> findByRole(ROLE role);
}
