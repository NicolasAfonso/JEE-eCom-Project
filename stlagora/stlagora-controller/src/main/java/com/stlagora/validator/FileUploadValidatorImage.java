package com.stlagora.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.primefaces.component.log.Log;
import org.primefaces.model.UploadedFile;

import com.stlagora.controller.SellController;

@FacesValidator("fileUploadValidatorImage")
public class FileUploadValidatorImage implements Validator {
	private Logger log = Logger.getLogger(FileUploadValidatorImage.class.getName());
	public void validate(FacesContext context, UIComponent uiComponent,
			Object value) throws ValidatorException {

		UploadedFile file = (UploadedFile) value;
		Boolean modif =  (Boolean) context.getExternalContext().getFlash().get("modif");
		log.debug(file.getContentType());
		log.debug(file.getFileName());
		log.debug(file.getSize());
		
		if(modif==null)
		{
			modif = false;
		}
		if(modif)
		{
			if(file.getSize() > 0 && !file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg"))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Mauvais type de fichier. Type accepté : PNG/JPEG","");
				throw new ValidatorException(message);
			}


			if(file.getSize() > 1000000)
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Votre image est trop volumineuse (taille maximale : 1 Mo)","");
				throw new ValidatorException(message);
			}
		}else
		{
			if(!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg"))
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Mauvais type de fichier. Type accepté : PNG/JPEG","");
				throw new ValidatorException(message);
			}


			if(file.getSize() > 1000000)
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Votre image est trop volumineuse (taille maximale : 1 Mo)","");
				throw new ValidatorException(message);
			}
		}
	
		
		log.debug("Validator Image OK");
	}
}
