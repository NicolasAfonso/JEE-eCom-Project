package com.stlagora.validator;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;
import org.primefaces.component.log.Log;
import org.primefaces.model.UploadedFile;

import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.entities.Product;

@FacesValidator("productValidator")
public class ProductValidator implements Validator {
	@EJB
	private ProductDao productDao;
	
	private Logger log = Logger.getLogger(ProductValidator.class.getName());
	public void validate(FacesContext context, UIComponent uiComponent,	Object value) throws ValidatorException {
		
		 String name = (String) value;
		 log.debug("Product "+name);
		 Product p = null;
		 try{
			 p = productDao.findByNameAll(name); 
		 }catch(Exception e)
		 {
			 log.debug("New Production creation "+name);
		 }
		 
		 
		 if(p!=null)
		 {
        	 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Name: already used","");
             throw new ValidatorException(message);
		 }
		
	}

}
