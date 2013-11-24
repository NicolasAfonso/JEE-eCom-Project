package com.stlagora.model.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;

public interface ProductDao extends GenericDao<Product> {

	
	public Product findByName(String name);
	public List<Product> findByPrice(Float price) ; 
	public List<Product> findBySeller(User seller);
	public List<Product> findByCategory(Category category);
	public List<Product> findByAvailableDate(Date availableDate);
	public List<Product> findByLastUpdate(Date lastUpdate);
	public List<Product> findByGlobalMark(Float globalMark);

}
