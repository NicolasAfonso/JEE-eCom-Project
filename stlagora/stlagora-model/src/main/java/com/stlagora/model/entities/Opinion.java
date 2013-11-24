package com.stlagora.model.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="opinions")
public class Opinion implements Serializable {

private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@ManyToOne
@JoinColumn(name = "writer_id", nullable = false)
private User writer;

@Column (name="date" ,nullable=false)
private Date date;

@Column (name="mark")
private float mark;

@Column (name="comment")
private String comment;

@ManyToOne
@JoinColumn(name = "product_id")
private Product product;

@ManyToOne
@JoinColumn(name = "usermarked_id")
private User usermarked;

public Opinion(){ 

}

public Opinion(User writer, Date date, float mark, String comment,
		Product product, User usermarked) {
	super();
	this.writer = writer;
	this.date = date;
	this.mark = mark;
	this.comment = comment;
	this.product = product;
	this.usermarked = usermarked;
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

/**
 * @return the usermarked
 */
public User getUsermarked() {
	return usermarked;
}

/**
 * @param usermarked the usermarked to set
 */
public void setUsermarked(User usermarked) {
	this.usermarked = usermarked;
}

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
 * @return the writer
 */
public User getWriter() {
	return writer;
}

/**
 * @param writer the writer to set
 */
public void setWriter(User writer) {
	this.writer = writer;
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
 * @return the mark
 */
public float getMark() {
	return mark;
}

/**
 * @param mark the mark to set
 */
public void setMark(float mark) {
	this.mark = mark;
}

/**
 * @return the comment
 */
public String getComment() {
	return comment;
}

/**
 * @param comment the comment to set
 */
public void setComment(String comment) {
	this.comment = comment;
}


}
