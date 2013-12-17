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
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
		String id = (String) req.getParameter("id");
		sessionUser.setUrl(req.getRequestURI().replace("/stlagora",""));
		Long idL = null;
		try{
		idL = Long.parseLong(id);
		}catch(Exception e)
		{
			chain.doFilter(request, response);
		}
		
		if(idL==null)
		{
			chain.doFilter(request, response);
		}else
		{
			Product p = productDao.findById(idL);
			log.debug("Product controller");
			if(p == null || p.isDeleted()||p.getStatus() == PRODUCT_STATUS.NOTAVAILABLE)
			{
				String contextPath = ((HttpServletRequest)request).getContextPath();
				((HttpServletResponse)response).sendRedirect(contextPath + "/error/errorProduct.xhtml?faces-redirect=true");

			}
				chain.doFilter(request, response);

		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
