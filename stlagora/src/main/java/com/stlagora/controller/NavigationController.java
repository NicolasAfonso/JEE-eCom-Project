package com.stlagora.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.stlagora.model.entities.User;
import com.stlagora.model.dao.UtilisateurDao;


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
		
		
		UtilisateurDao userDao = new UtilisateurDao();
		
		userDao.creer(u);
		
//		User o = userDao.trouver("tuu");
//		System.err.println(o.getEmail());
		return "page1";
	}
}
