package com.stlagora.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
	
	@ManagedProperty("productList")
	private List<Product> productList = new ArrayList<Product>();
	
	public PurchaseController() {
		
	}
	
	public String validateCart(){
			 validate = true ;
			 Cart cart = (Cart) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart");
			 for (Product product : cart.getProducts() ) {
				 productList.add(product);
				}
			
			 return "/purchase/payementView?faces-redirect=true";
		 
		
		
	}
	
	public String payCart()
	{
		SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");

		if(validate && sessionUser.isLoggedIn())
		{
			for (Product product : productList) {
				transactionDao.create(new Transaction(product.getSeller(), sessionUser.getUser(), product.getPrice(),new Date(System.currentTimeMillis()) , product));
			}
			//Purchase validate, clean cart
			Cart cart = (Cart) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart");
			cart.clean();
			productList.clear();
			validate = false;
			return "/purchase/validationPayement?faces-redirect=true";
		}
		else
		{
			return "/purchase/cartView?faces-redirect=true";
		}
		
	}

	
	/**
	 * GETTER/SETTER
	 */

	/**
	 * @return the productList
	 */
	public List<Product> getProductList() {
		return productList;
	}

	/**
	 * @param productList the productList to set
	 */
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
}
