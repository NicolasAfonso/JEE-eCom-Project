package com.stlagora.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ROLE;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;


@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7356175149995220589L;
	
	@ManagedProperty(value="#{param.pageId}")
	private String pageId;
	
	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String moveToPage1(){
		
		User u = new User();
		
		
		UserDao userDao = new UserDaoImpl();
		
		//userDao.create(new User("tutu", "tutu", "tutu", "tutu", new Date(0), "00000000", ROLE.MEMBER));
		
		User o = userDao.findByEmail("tutu");
		List<User> l = userDao.findByPhoneNumber("00000000");
		System.err.println(l.size());
		System.err.println(o.getEmail());
		return "page1";
	}
}
