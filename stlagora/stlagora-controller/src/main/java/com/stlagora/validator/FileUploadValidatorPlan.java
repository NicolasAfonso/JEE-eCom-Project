package com.stlagora.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

@FacesValidator("fileUploadValidatorPlan")
public class FileUploadValidatorPlan implements Validator {
	private Logger log = Logger.getLogger(FileUploadValidatorImage.class.getName());
    public void validate(FacesContext context, UIComponent uiComponent,
            Object value) throws ValidatorException {
 
        UploadedFile file = (UploadedFile) value;
        log.debug(file.getContentType());
        log.debug(file.getFileName());
        log.debug(file.getSize());
        if(!file.getContentType().equals("application/octet-stream") )
        {
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: File type is invalid !!","");
           throw new ValidatorException(message);
        }
        
        if(!file.getFileName().contains(".stl"))
        {
        	 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: File type is invalid . STL!!","");
             throw new ValidatorException(message);
        }
        
        if(file.getSize() > 50000000)
        {
        	 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: File size !!","");
             throw new ValidatorException(message);
        }
        log.debug("Validator Plan OK");
    }

}