package com.stlagora.controller;

import java.sql.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.stlagora.beans.SessionUser;
import com.stlagora.model.dao.OpinionDao;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;

@ManagedBean(name = "opinionController")
@SessionScoped
public class OpinionController {
	private Logger log = Logger.getLogger(OpinionController.class.getName());
	private Product product;
	private String opinionComment ;
	private int opinionMark;
	private long idProduct;
	private Opinion opinion;
	
	@EJB
	private OpinionDao opinionDao;
	@EJB
	private ProductDao productDao;
	
	public String moveToOpinion(Product prod){
		product = prod;
		log.debug(product.getId());
		return "/profile/opinion";
	}
	
	public String createOpinion(){
		if(opinion != null){
			opinion.setComment(opinionComment);
			opinion.setMark(opinionMark);
			opinionDao.update(opinion);
			float new_global = (product.getGlobalMark()+opinionMark)/2;
			product.setGlobalMark(new_global);
			productDao.update(product);
		}
		else{
			log.debug(product.getId());
			SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
			opinion = new Opinion(sessionUser.getUser(), new Date(System.currentTimeMillis()), opinionMark, opinionComment, product, null);
			
			int jesuisbete = product.getOpinions().size();
			log.debug(jesuisbete);
			if(jesuisbete == 0){
				float new_global = opinionMark;
				product.setGlobalMark(new_global);
			}
			else{
				float new_global = (product.getGlobalMark()+opinionMark)/2;
				product.setGlobalMark(new_global);
			}
			opinionDao.create(opinion);
			productDao.update(product);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Avis enregistré","");  
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return "/profile/historyPurchase?faces-redirect=true";
	}
	
	
	/**
	 * @return the opinionComment
	 */
	public String getOpinionComment() {
		return opinionComment;
	}

	/**
	 * @param opinionComment the opinionComment to set
	 */
	public void setOpinionComment(String opinionComment) {
		this.opinionComment = opinionComment;
	}

	/**
	 * @return the opinionMark
	 */
	public int getOpinionMark() {
		SessionUser sessionUser = (SessionUser) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUser");
		log.debug(product.getId());
		try{
			opinion = opinionDao.findByUserProduct(product, sessionUser.getUser());
		}
		catch(Exception e){
			opinion = null;
		}
		if(opinion != null){
			opinionComment=opinion.getComment();
			opinionMark=Math.round(opinion.getMark());
		}
		return opinionMark;
	}

	/**
	 * @param opinionMark the opinionMark to set
	 */
	public void setOpinionMark(int opinionMark) {
		this.opinionMark = opinionMark;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the idProduct
	 */
	public long getIdProduct() {
		return idProduct;
	}

	/**
	 * @param idProduct the idProduct to set
	 */
	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}

	
}
