package com.stlagora.model.dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;
@Stateless
public class OpinionDaoImpl extends GenericDaoImpl< Opinion > implements OpinionDao {
	
	private Logger log = Logger.getLogger(OpinionDaoImpl.class.getName());

	public OpinionDaoImpl(String persistanceUnit) {
		super(persistanceUnit);
	}
	
	public OpinionDaoImpl(){
		super();
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
	
	public Opinion findByUserProduct(Product product, User user){
		Query q = em.createQuery("SELECT o FROM Opinion o WHERE o.product =:product and o.writer =:writer");
		q.setParameter("product", product);
		q.setParameter("writer", user);
		Opinion opinion;
		try{
			opinion = (Opinion) q.getSingleResult();
		}
		catch(Exception e){
			opinion = null;
			log.debug("error no opinion result");
		}
		return opinion;
		
	}
}
