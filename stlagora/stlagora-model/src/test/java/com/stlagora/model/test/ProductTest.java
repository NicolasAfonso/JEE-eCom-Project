package com.stlagora.model.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ROLE;

public class ProductTest {
	
	static ProductDao productDao;
	static UserDao userDao ; 
	static String persistanceUnit = "stlagora-Test";
	@BeforeClass
	public static void initTestFixture() throws Exception {
	    // Get the entity manager for the tests.
		productDao = new ProductDaoImpl(persistanceUnit);
		userDao = new UserDaoImpl(persistanceUnit);
		
		//Create Users test
		userDao.create(new User("tutu", "tutu", "tutu", "tutu", new Date(0), "00000000", ROLE.MEMBER));
		userDao.create(new User("toto", "toto", "toto", "toto", new Date(0), "00000000", ROLE.MEMBER));
	}

	 /**
	 * Cleans up the session.
	 */
	@AfterClass
	public static void closeTestFixture() {
		
	}

    @Test
	public void testCreateProduct() {
		  User a = userDao.findByEmail("tutu");
		  productDao.create(new Product("toto", "toto", "toto", "toto", 1f, a,new Date(0), new Date(0)));
	  }
} 