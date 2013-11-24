package com.stlagora.model.dao;

import java.util.List;

import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Product;

public interface CategoryDao extends GenericDao<Category>{

	public Category findByName(String categoryName);

	
}
