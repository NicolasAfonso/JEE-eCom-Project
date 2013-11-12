package com.stlagora.model.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.User;

public class OpinionDaoImpl extends GenericDaoImpl< Opinion > implements OpinionDao {

	public List<Opinion> findByDate(Date date) {
		Query q = em.createQuery("SELECT o FROM Opinion WHERE o.date =:date");
		q.setParameter("date", date);
		List<Opinion> results = q.getResultList();
		return results;
	}

	public List<Opinion> findByMark(float mark) {
		Query q = em.createQuery("SELECT o FROM Opinion WHERE o.mark =:mark");
		q.setParameter("mark", mark);
		List<Opinion> results = q.getResultList();
		return results;
	}

	public List<Opinion> findByWriterId(User user) {
		Query q = em.createQuery("SELECT o FROM Opinion WHERE o.writerId =:user");
		q.setParameter("user", user.getId());
		List<Opinion> results = q.getResultList();
		return results;
	}

}
