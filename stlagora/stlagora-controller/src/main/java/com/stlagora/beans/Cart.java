package com.stlagora.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.stlagora.model.entities.Product;

@ManagedBean(name="cart")
@SessionScoped
public class Cart implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Product> products = new ArrayList<Product>();
	private Logger log = Logger.getLogger(Cart.class.getName());
	private float amount = 0 ;
	private int numberArticle = 0 ;

	public void add(Product product) {
    	if(!products.contains(product)){
            products.add(product);
            amount += product.getPrice();
            numberArticle +=1 ; 
    	}
    }

    public void remove(Product product) {
        products.remove(product);
        amount -= product.getPrice();
        numberArticle -=1 ; 
    }
    

    public List<Product> getProducts() {
        return products;
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
	
	public void clean(){
		products.clear();
		numberArticle = 0 ;
		amount = 0 ;
	}
}