package com.stlagora.model.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;


import javax.persistence.Query;

import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;


@Stateless
public class UtilisateurDao {

	//Injection du manager, qui s'occupe de la connexion avec la BDD
//	@PersistenceContext( unitName = "myPU" )
	private EntityManager em;
	//Enregistrement d'un nouvel utilisateur
	public void creer( User utilisateur )
	{
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    Product p = new Product();
	    em.persist(p);
		try {
			em.persist( utilisateur );
			em.getTransaction().commit();

		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	//Recherche d'un utilisateur à partir de son adresse email
	public User trouver( String email ){

	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	    EntityManager em = emf.createEntityManager();
		User utilisateur = null;
		
		utilisateur = em.find(User.class, 1L);
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email=:email",User.class);
		q.setParameter("email","tutu@toto.com");
		List<User> results = q.getResultList();
		User utilisateur2 =  results.get(0);
		System.out.println("User : "+utilisateur.getEmail());
		System.out.println("User : "+utilisateur2.getEmail());
		return utilisateur;
	}
}

