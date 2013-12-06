package com.stlagora.controller;

import java.io.Serializable;
import java.nio.channels.SeekableByteChannel;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.stlagora.beans.SessionUser;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.User;

@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger(LoginController.class.getName());
	private UserDao userDao = new UserDaoImpl();

	private String username;  
    
    private String password;  
    

    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
    
	public String login()
	{ 
        SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
        User user = sessionUser.getUser();
		if(user==null)
		{
			try{
				user = userDao.findByLogin(username);	
			}catch(Exception e){
				log.error("no exist");
			}
			
			if(user == null)
			{
				try{
				user = userDao.findByEmail(username);
			}catch(Exception e){
				log.error("no exist");
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
	            return sessionUser.getUrl();
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
        FacesMessage msg = null;  
        SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
        User user = sessionUser.getUser();
		if(user!=null)
		{
			sessionUser.setUser(null);
			sessionUser.setLoggedIn(false);

		}
		return "home";

	}



	
	
}
