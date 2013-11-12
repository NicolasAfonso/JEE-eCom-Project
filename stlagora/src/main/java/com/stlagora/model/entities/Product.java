package com.stlagora.model.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="products")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, unique=true) 
	private String name;
	
	@Column(name = "description", nullable = false) 
	private String description;
	
	@Column(name = "images", nullable = true) 
	private String images;
	
	@Column(name = "plan", nullable = false)
	private String plan ;
	
	@Column(name = "price", nullable = false)
	private Float price ; 
	
	@ManyToOne
	@JoinColumn(name = "user_id",unique = true, nullable = false)
	private User seller;
	
	@Column(name = "availableDate", nullable = false)
	private Date availableDate;
	
	@Column(name = "lastUpdate", nullable = false)
	private Date lastUpdate;
	
	@Column(name = "globalMark", nullable = true)
	private Float globalMark;
	
	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@ElementCollection
	private List<Opinion> opinions = new ArrayList<Opinion>();  
	
	public Product(){
	
	}
	
	
	/**
	 * @param name
	 * @param description
	 * @param images
	 * @param plan
	 * @param price
	 * @param seller
	 * @param availableDate
	 * @param updateDate
	 */
	public Product(String name, String description, String images, String plan,
			Float price, User seller, Date availableDate, Date lastUpdate) {
		super();
		this.name = name;
		this.description = description;
		this.images = images;
		this.plan = plan;
		this.price = price;
		this.seller = seller;
		this.availableDate = availableDate;
		this.lastUpdate = lastUpdate;
		
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the images
	 */
	public String getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(String images) {
		this.images = images;
	}

	/**
	 * @return the plan
	 */
	public String getPlan() {
		return plan;
	}

	/**
	 * @param plan the plan to set
	 */
	public void setPlan(String plan) {
		this.plan = plan;
	}

	/**
	 * @return the price
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Float price) {
		this.price = price;
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
	 * @return the availableDate
	 */
	public Date getAvailableDate() {
		return availableDate;
	}

	/**
	 * @param availableDate the availableDate to set
	 */
	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	/**
	 * @return the update
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param update the update to set
	 */
	public void setUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * @return the globalMark
	 */
	public Float getGlobalMark() {
		return globalMark;
	}


	/**
	 * @param globalMark the globalMark to set
	 */
	public void setGlobalMark(Float globalMark) {
		this.globalMark = globalMark;
	}


	/**
	 * @return the opinions
	 */
	public List<Opinion> getOpinions() {
		return opinions;
	}


	/**
	 * @param opinions the opinions to set
	 */
	public void setOpinions(List<Opinion> opinions) {
		this.opinions = opinions;
	}

	

}
