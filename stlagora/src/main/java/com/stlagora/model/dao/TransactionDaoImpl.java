package com.stlagora.model.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.Transaction;
import com.stlagora.model.entities.User;

public class TransactionDaoImpl extends GenericDAOImpl<Transaction> implements TransactionDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TransactionDaoImpl(){
		super();
	}
		
	public List<Transaction> findBySeller(User seller) {
		Query q = em.createQuery("SELECT t FROM User t WHERE t.seller_id=:seller_id",Transaction.class);
		q.setParameter("seller_id",seller.getId());
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}

	public List<Transaction> findByBuyer(User buyer) {
		Query q = em.createQuery("SELECT t FROM User t WHERE t.buyer=:buyer_id",Transaction.class);
		q.setParameter("seller_id",buyer.getId());
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}

	public List<Transaction> findByAmount(float amount) {
		Query q = em.createQuery("SELECT t FROM User t WHERE t.amount=:amount",Transaction.class);
		q.setParameter("amount",amount);
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}

	public List<Transaction> findByDate(Date date) {
		Query q = em.createQuery("SELECT t FROM User t WHERE t.date=:date",Transaction.class);
		q.setParameter("date",date);
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}

	public List<Transaction> findByProduct(Product product) {
		Query q = em.createQuery("SELECT t FROM User t WHERE t.product_id=:product",Transaction.class);
		q.setParameter("product",product.getId());
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}
	

}
