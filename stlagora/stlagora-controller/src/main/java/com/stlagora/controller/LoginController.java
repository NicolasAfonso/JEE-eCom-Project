package com.stlagora.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.stlagora.beans.SessionUser;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;

@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger(LoginController.class.getName());
	@EJB
	private UserDao userDao;
	
	@EJB 
	private ProductDao productDao;
	
	private String loginUser;  
    private String password;  

   
    
	public String login()
	{ 
        SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
        User user = sessionUser.getUser();
		if(user==null)
		{
			try{
				user = userDao.findByLogin(loginUser);	
			}catch(Exception e){
				log.error("User "+loginUser+ "not found ");
			}
			
			if(user == null)
			{
				try{
				user = userDao.findByEmail(loginUser);
			}catch(Exception e){
				log.error("User "+loginUser+ "not found ");
			     FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Bad credential","");  
			     FacesContext.getCurrentInstance().addMessage(null, msg);  
			}
			}
		}
		
		if(user != null)
		{
			String shaPassword = new String(DigestUtils.sha512(password));
			if(user.getPassword().equals(shaPassword))
			{
				log.debug(user.getEmail());
				sessionUser.setLoggedIn(true);
				sessionUser.setUser(user);
	            FacesMessage msg = new FacesMessage("Succesful","Welcome");  
	            FacesContext.getCurrentInstance().addMessage(null, msg); 
	            log.debug(sessionUser.getUrl());
	            return sessionUser.getUrl()+"?faces-redirect=true";
			}
			else
			{
			     FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Bad credential","");  
			     FacesContext.getCurrentInstance().addMessage(null, msg);  
			     return "/login";
			}
		}
		else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Bad credential","");  
		     FacesContext.getCurrentInstance().addMessage(null, msg);  
		     return "/login";
		}



	}
	
	public String logout()
	{ 
        SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
        User user = sessionUser.getUser();
		if(user!=null)
		{
			sessionUser.setUser(null);
			sessionUser.setLoggedIn(false);

		}
		return "/home?faces-redirect=true";

	}


	/**
	 * Getter and Setter
	 */
	/**
	 * 
	 * @return
	 */
	 public String getLoginUser() {  
	        return loginUser;  
	    }  
	  
	    public void setLoginUser(String loginUser) {  
	        this.loginUser = loginUser;  
	    }  
	  
	    public String getPassword() {  
	        return password;  
	    }  
	  
	    public void setPassword(String password) {  
	        this.password = password;  
	    }  
	
	
}
