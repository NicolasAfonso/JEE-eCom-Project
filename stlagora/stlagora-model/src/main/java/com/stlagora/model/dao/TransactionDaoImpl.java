package com.stlagora.model.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.Transaction;
import com.stlagora.model.entities.User;

public class TransactionDaoImpl extends GenericDaoImpl<Transaction> implements TransactionDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TransactionDaoImpl(){
		super("STLAGORA_PU");
	}
	
	public TransactionDaoImpl(String persistanceUnit)
	{
		super(persistanceUnit);
	}
	
	public List<Transaction> findBySeller(User seller) {
		Query q = em.createQuery("SELECT t FROM Transaction t WHERE t.seller=:seller",Transaction.class);
		q.setParameter("seller",seller);
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}

	public List<Transaction> findByBuyer(User buyer) {
		Query q = em.createQuery("SELECT t FROM Transaction t WHERE t.buyer=:buyer",Transaction.class);
		q.setParameter("buyer",buyer);
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}

	public List<Transaction> findByAmount(float amount) {
		Query q = em.createQuery("SELECT t FROM Transaction t WHERE t.amount=:amount",Transaction.class);
		q.setParameter("amount",amount);
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}

	public List<Transaction> findByDate(Date date) {
		Query q = em.createQuery("SELECT t FROM Transaction t WHERE t.date=:date",Transaction.class);
		q.setParameter("date",date);
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}

	public List<Transaction> findByProduct(Product product) {
		Query q = em.createQuery("SELECT t FROM Transaction t WHERE t.product=:product",Transaction.class);
		q.setParameter("product",product);
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}
	

}
