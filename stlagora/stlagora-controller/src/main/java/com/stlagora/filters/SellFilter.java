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

public class SellFilter implements Filter {
	private Logger log = Logger.getLogger(SellFilter.class.getName());

	@EJB
	ProductDao productDao;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("sessionUser");
		sessionUser.setUrl(req.getRequestURI().replace("/stlagora",""));
		log.debug(sessionUser.getUrl());
		if (sessionUser != null) {
			if(sessionUser.getUser().getAccountType()==ACCOUNT_TYPE.PRIVATE)
			{
				List<Product> listProduct = productDao.findBySeller(sessionUser.getUser());
				if(listProduct.size()>=10)
				{
//					log.debug("ICI");
//					int inc = 0;
//					for (Product p: listProduct) {
//						if(!p.isDeleted())
//						{
//							inc ++;
//						}
//					}
//
//					if(inc>=10)
//					{
					String contextPath = ((HttpServletRequest)request).getContextPath();
					((HttpServletResponse)response).sendRedirect(contextPath + "/sell/error_private.xhtml?faces-redirect=true");
//
//					}
				}
				//				log.debug(sessionUser.getUser().getAccountType());
				//				if(productDao.findBySeller(sessionUser.getUser()).size() >= 10)
				//				{
				//					
				//					String contextPath = ((HttpServletRequest)request).getContextPath();
				//					((HttpServletResponse)response).sendRedirect(contextPath + "/sell/error_private.xhtml?faces-redirect=true");
				//				}
			}

			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
