package com.stlagora.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;
@FacesValidator("cbValidator")
public class CBValidator implements Validator{
	private static final String CB_PATTERN_VISA ="^4[0-9]{12}(?:[0-9]{3})?$";
	private static final String CB_PATTERN_MASTERCARD ="^5[1-5][0-9]{14}$";
	private static final String CB_PATTERN_AMERICAN ="^3[47][0-9]{13}$";
	private Logger log = Logger.getLogger(CBValidator.class.getName());
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		String cbUser = (String)  value;
		Pattern patternVisa = Pattern.compile(CB_PATTERN_VISA);
		Matcher matcherVisa = patternVisa.matcher(cbUser);
		
		Pattern patternMastercard = Pattern.compile(CB_PATTERN_MASTERCARD);
		Matcher matcherMastercard = patternMastercard.matcher(cbUser);
		
		Pattern patternAmerican = Pattern.compile(CB_PATTERN_AMERICAN);
		Matcher matcherAmerican = patternAmerican.matcher(cbUser);
		
		log.debug(cbUser);
		if(!matcherVisa.matches() &&  !matcherMastercard.matches() && !matcherAmerican.matches()){
			 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Votre numéro de carte est invalide","");
	         throw new ValidatorException(message);
		}
	}

}
