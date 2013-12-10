package com.stlagora.filters;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.stlagora.beans.SessionUser;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;

public class SellFilter implements Filter {
	private Logger log = Logger.getLogger(SellFilter.class.getName());
	
	@EJB
	ProductDao productDao;
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
		sessionUser.setUrl(req.getRequestURI().replace("/stlagora",""));
		log.debug(sessionUser.getUrl());
		if (sessionUser != null) {
			if(sessionUser.getUser().getAccountType()==ACCOUNT_TYPE.PRIVATE)
			{
				log.debug(sessionUser.getUser().getAccountType());
				if(productDao.findBySeller(sessionUser.getUser()).size() >= 10)
				{
					String contextPath = ((HttpServletRequest)request).getContextPath();
					((HttpServletResponse)response).sendRedirect(contextPath + "/sell/error_private.xhtml?faces-redirect=true");
				}
			}
			
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
