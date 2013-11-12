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
public class Opinion implements Serializable {

private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column (name="id" ,unique=true, nullable=false)
private int id;

@ManyToOne
@JoinColumn(name = "writer_id",unique = true, nullable = false)
private User writer;

@Column (name="date" ,nullable=false)
private Date date;

@Column (name="mark")
private float mark;

@Column (name="comment")
private String comment;

/**
 * @return the id
 */
public int getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(int id) {
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
