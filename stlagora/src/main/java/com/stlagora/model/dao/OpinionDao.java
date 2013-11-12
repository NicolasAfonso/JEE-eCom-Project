package com.stlagora.model.dao;

import java.sql.Date;
import java.util.List;

import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.User;


public interface OpinionDao extends GenericDAO<Opinion>{
	
	public List<Opinion> findByDate(Date date);
	public List<Opinion> findByMark(float mark);
	public List<Opinion> findByWriterId(User user);
	

}
