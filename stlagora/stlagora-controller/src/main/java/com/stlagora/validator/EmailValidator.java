package com.stlagora.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.PatternParser;

import com.stlagora.model.dao.UserDao;
import com.stlagora.model.entities.User;
@FacesValidator("emailValidator")
public class EmailValidator implements Validator{
	@EJB
	private UserDao userDao;
	private Logger log = Logger.getLogger(EmailValidator.class.getName());
	private static final String EMAIL_PATTERN ="^[_A-Za-z0-9-]+(\\." +"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;
	
	public EmailValidator(){
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	 public void validate(FacesContext context, UIComponent uiComponent,Object value) throws ValidatorException {
	
		String email = (String) value;
		log.debug(value);
		User user = null;
		try{
			user = userDao.findByEmail(email);
		}catch(Exception e)
		{
			log.error("User failed");
		}
		if(user!=null)
		{
	           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Cet email est déjà utilisé","");
	           throw new ValidatorException(message);
		}
		
		matcher = pattern.matcher(email);
		if(!matcher.matches()){
			 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Mauvais format d'email","");
	         throw new ValidatorException(message);
		}
	}
}

