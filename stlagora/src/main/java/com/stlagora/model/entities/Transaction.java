package com.stlagora.model.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction implements Serializable {

private static final long serialVersionUID = 1L;

@Id
@Column(name = "id", nullable = false) 
@GeneratedValue( strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name = "seller",unique = true, nullable = false)
private User seller;

@ManyToOne
@JoinColumn(name = "buyer",unique = true, nullable = false)
private User buyer;

@Column(name ="amount", nullable=false)
private float amount;

@Column(name ="date" ,nullable=false)
private Date date;

@ManyToOne
@JoinColumn(name = "product_id",unique = true, nullable = false)
private Product product;

/**
 * @return the id
 */
public long getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(long id) {
	this.id = id;
}

/**
 * @return the seller
 */
public User getSeller() {
	return seller;
}

/**
 * @param seller the seller to set
 */
public void setSeller(User seller) {
	this.seller = seller;
}

/**
 * @return the buyer
 */
public User getBuyer() {
	return buyer;
}

/**
 * @param buyer the buyer to set
 */
public void setBuyer(User buyer) {
	this.buyer = buyer;
}

/**
 * @return the amount
 */
public float getAmount() {
	return amount;
}

/**
 * @param amount the amount to set
 */
public void setAmount(float amount) {
	this.amount = amount;
}

/**
 * @return the date
 */
public Date getDate() {
	return date;
}

/**
 * @param date the date to set
 */
public void setDate(Date date) {
	this.date = date;
}

/**
 * @return the product
 */
public Product getProduct() {
	return product;
}

/**
 * @param product the product to set
 */
public void setProduct(Product product) {
	this.product = product;
} 


}