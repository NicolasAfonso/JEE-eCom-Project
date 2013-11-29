package com.stlagora.controller;

import java.io.Serializable;
import java.sql.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.stlagora.beans.Cart;
import com.stlagora.beans.SessionUser;
import com.stlagora.model.dao.TransactionDao;
import com.stlagora.model.dao.TransactionDaoImpl;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.Transaction;

@ManagedBean(name = "purchaseController", eager = true)
@RequestScoped
public class PurchaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(SessionUser.class.getName());
	private TransactionDao transactionDao = new TransactionDaoImpl();
	
	private boolean validate = false ; 
	
	public PurchaseController() {
		
	}
	
	public String validateCart(){
		SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		 if(sessionUser == null || sessionUser.isLoggedIn()==false)
		 {
			 return "/login";
		 }
		 else
		 {
			 validate = true ;
			 return "/purchase/payementView";
		 }
		
		
	}
	
	public String payCart()
	{
		SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		Cart cart = (Cart) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart");
		if(validate)
		{
			for (Product product : cart.getProducts() ) {
				transactionDao.create(new Transaction(product.getSeller(), sessionUser.getUser(), product.getPrice(),new Date(System.currentTimeMillis()) , product));
			}
			
			cart.clean();
			return "/purchase/validationPayement";
		}
		else
		{
			return "/purchase/cartView";
		}
		
	}
}
