package com.stlagora.filters;

import java.io.IOException;

import javax.faces.context.FacesContext;
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
import com.stlagora.controller.LoginController;

public class LoginFilter implements Filter {
	
	private Logger log = Logger.getLogger(LoginFilter.class.getName());
	/**
	 * Checks if user is logged in. If not it redirects to the login.xhtml page.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Get the loginBean from session attribute
		HttpServletRequest req = (HttpServletRequest) request;
		SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
		sessionUser.setUrl(req.getRequestURI().replace("/stlagora",""));
		log.debug(sessionUser.getUrl());
		// For the first application request there is no loginBean in the session so user needs to log in
		// For other requests loginBean is present but we need to check if user has logged in successfully
		if ( sessionUser == null ||! sessionUser.isLoggedIn()) {
			String contextPath = ((HttpServletRequest)request).getContextPath();
			
			((HttpServletResponse)response).sendRedirect(contextPath + "/login.xhtml");
		}
		else
		{
			chain.doFilter(request, response);
		}
		
			
	}

	public void init(FilterConfig config) throws ServletException {
	
	}

	public void destroy() {
		// Nothing to do here!
	}
}
