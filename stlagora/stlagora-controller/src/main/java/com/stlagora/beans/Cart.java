package com.stlagora.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.stlagora.model.entities.Product;

@ManagedBean(name="cart")
@SessionScoped
public class Cart implements Serializable {

    /**
	 * Cart Controller
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(Cart.class.getName());
	private List<Product> products = new ArrayList<Product>();
	private float amount = 0 ;
	private int numberArticle = 0 ;

	public void add(Product product) {
    	if(!containsProduct(products, product)){
            products.add(product);
            amount += product.getPrice();
            numberArticle +=1 ; 
		     FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Produit ajouté au panier","");  
		     FacesContext.getCurrentInstance().addMessage(null, msg);  
    	}
    }

    public void remove(Product product) {
        products.remove(product);
        amount -= product.getPrice();
        numberArticle -=1 ; 
	     FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Produit supprimé du panier","");  
	     FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    

    public List<Product> getProducts() {
        return products;
    }
    
    private boolean containsProduct(List<Product> lp, Product p){
    	for (Product product : lp) {
			if(product.getName().equals(p.getName())){
				return true;
			}
		}
    	return false;
    }
    
	
    /**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @return the numberArticle
	 */
	public int getNumberArticle() {
		return numberArticle;
	}

	/**
	 * @param numberArticle the numberArticle to set
	 */
	public void setNumberArticle(int numberArticle) {
		this.numberArticle = numberArticle;
	}
	
	/**
	 * clean the cart
	 */
	public void clean(){
		products.clear();
		numberArticle = 0 ;
		amount = 0 ;
	}
}