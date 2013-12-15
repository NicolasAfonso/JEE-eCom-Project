package com.stlagora.controller;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.stlagora.beans.Cart;
import com.stlagora.beans.SessionUser;
import com.stlagora.model.dao.TransactionDao;
import com.stlagora.model.dao.TransactionDaoImpl;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.Transaction;
import com.stlagora.model.mail.SendMail;

@ManagedBean(name = "purchaseController", eager = true)
@RequestScoped
public class PurchaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(SessionUser.class.getName());
	@EJB
	private TransactionDao transactionDao;

	private boolean validate = false;
	private String month;
	private String year;

	@ManagedProperty("productList")
	private List<Product> productList = new ArrayList<Product>();
	private List<String> months = Arrays.asList("Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre");
	private List<String> years = Arrays.asList("2013","2014","2015","2016","2017");
	

	public PurchaseController() {

	}

	public String validateCart() {
		productList.clear();
		validate = true;
		Cart cart = (Cart) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("cart");
		for (Product product : cart.getProducts()) {
			productList.add(product);
		}

		return "/purchase/payment?faces-redirect=true";
	}

	public String payCart() {
		SessionUser sessionUser = (SessionUser) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("sessionUser");
		SendMail mail = new SendMail();
		String msg;
		if (validate && sessionUser.isLoggedIn()) {
			for (Product product : productList) {
				transactionDao.create(new Transaction(product.getSeller(),
						sessionUser.getUser(), product.getPrice(), new Date(
								System.currentTimeMillis()), product));
			}
			// Purchase validate, clean cart
			Cart cart = (Cart) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("cart");
			cart.clean();

			validate = false;
			msg = "Bonjour "+sessionUser.getUser().getFirstname()+" "+sessionUser.getUser().getSurname()+",\n\n"+"Merci de votre achat sur le site de Stl-Agora, vous pouvez désormais télécharger vos plans depuis le site. \n\nA très bientôt.\n\nL'équipe Stl-Agora.";
			mail.sendMessage("Confirmation de commande", msg, sessionUser.getUser().getEmail(), "stl-agora@outlook.com");
			return "/purchase/download?faces-redirect=true";
		} else {
			return "/purchase/cartView?faces-redirect=true";
		}

	}

	public String goPay() {
		return "/purchase/payment?faces-redirect=true";
	}

	/**
	 * GETTER/SETTER
	 */

	/**
	 * @return the productList
	 */
	public List<Product> getProductList() {
		return productList;
	}

	/**
	 * @param productList
	 *            the productList to set
	 */
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * @return the months
	 */
	public List<String> getMonths() {
		return months;
	}

	/**
	 * @param months the months to set
	 */
	public void setMonths(List<String> months) {
		this.months = months;
	}

	/**
	 * @return the years
	 */
	public List<String> getYears() {
		return years;
	}

	/**
	 * @param years the years to set
	 */
	public void setYears(List<String> years) {
		this.years = years;
	}
	
	

}
