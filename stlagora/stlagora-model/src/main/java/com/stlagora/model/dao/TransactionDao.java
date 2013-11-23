package com.stlagora.model.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.Transaction;
import com.stlagora.model.entities.User;


public interface TransactionDao extends GenericDao<Transaction> {

	public List<Transaction> findBySeller(User seller);
	public List<Transaction> findByBuyer(User buyer);
	public List<Transaction> findByAmount(float amount);
	public List<Transaction> findByDate(Date date);
	public List<Transaction> findByProduct(Product product);
}
