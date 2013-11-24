package com.stlagora.model.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;

public class OpinionDaoImpl extends GenericDaoImpl< Opinion > implements OpinionDao {

	public OpinionDaoImpl(String persistanceUnit) {
		super(persistanceUnit);
	}
	
	public OpinionDaoImpl(){
		super("STLAGORA_PU");
	}

	public List<Opinion> findByDate(Date date) {
		Query q = em.createQuery("SELECT o FROM Opinion o WHERE o.date =:date");
		q.setParameter("date", date);
		List<Opinion> results = q.getResultList();
		return results;
	}

	public List<Opinion> findByMark(float mark) {
		Query q = em.createQuery("SELECT o FROM Opinion o  WHERE o.mark =:mark");
		q.setParameter("mark", mark);
		List<Opinion> results = q.getResultList();
		return results;
	}

	public List<Opinion> findByWriterId(User user) {
		Query q = em.createQuery("SELECT o FROM Opinion o WHERE o.writer =:user");
		q.setParameter("user", user);
		List<Opinion> results = q.getResultList();
		return results;
	}
	
	public List<Opinion> findByUserMarked(User user) {
		Query q = em.createQuery("SELECT o FROM Opinion o WHERE o.usermarked =:user");
		q.setParameter("user", user);
		List<Opinion> results = q.getResultList();
		return results;
	}
	
	public List<Opinion> findByProduct(Product product) {
		Query q = em.createQuery("SELECT o FROM Opinion o WHERE o.product =:product");
		q.setParameter("product", product);
		List<Opinion> results = q.getResultList();
		return results;
	}
	
}
