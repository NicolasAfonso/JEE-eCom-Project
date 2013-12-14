package com.stlagora.filters;

import java.io.IOException;
import java.util.List;

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
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;
import com.stlagora.model.entities.enumerate.PRODUCT_STATUS;

public class ProductCardFilter implements Filter {
	private Logger log = Logger.getLogger(ProductCardFilter.class.getName());

	@EJB
	ProductDao productDao;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
		String id = (String) req.getParameter("id");
		sessionUser.setUrl(req.getRequestURI().replace("/stlagora",""));
		Product p = productDao.findById(Long.parseLong(id));
		log.debug("Product controller");
		if(p == null || p.isDeleted()||p.getStatus() == PRODUCT_STATUS.NOTAVAILABLE)
		{
			String contextPath = ((HttpServletRequest)request).getContextPath();
			((HttpServletResponse)response).sendRedirect(contextPath + "/error/errorProduct.xhtml?faces-redirect=true");

		}
			chain.doFilter(request, response);

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}
