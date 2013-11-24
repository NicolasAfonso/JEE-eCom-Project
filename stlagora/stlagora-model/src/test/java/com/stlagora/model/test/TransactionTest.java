package com.stlagora.model.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stlagora.model.dao.CategoryDao;
import com.stlagora.model.dao.CategoryDaoImpl;
import com.stlagora.model.dao.OpinionDao;
import com.stlagora.model.dao.OpinionDaoImpl;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.dao.TransactionDao;
import com.stlagora.model.dao.TransactionDaoImpl;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.Transaction;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ROLE;

public class TransactionTest {
	private  static String persistanceUnit = "stlagora-Test";
	private static CategoryDao categoryDao;
	private static ProductDao productDao;
	private static UserDao userDao;
	private static OpinionDao opinionDao;
	private static TransactionDao transactionDao;
	@BeforeClass
	public static void initTestFixture() throws Exception {
		// Get the entity manager for the tests.
		categoryDao = new CategoryDaoImpl(persistanceUnit);
		productDao = new ProductDaoImpl(persistanceUnit);	
		userDao = new UserDaoImpl(persistanceUnit);
		transactionDao = new TransactionDaoImpl(persistanceUnit);
		

		categoryDao.create(new Category("Test","test-desc"));

		//Create Users test
		userDao.create(new User("tutu", "tutu", "tutu", "tutu@tata.com", new Date(0), "00000000", ROLE.MEMBER));
		userDao.create(new User("tata", "tata", "tata", "tata@tata.com", new Date(0), "11111111", ROLE.MEMBER));
		
		Category c = categoryDao.findByName("Test");
		User u1 = userDao.findByEmail("tata@tata.com");
		User u2 = userDao.findByEmail("tutu@tata.com");
		
		
		productDao.create(new Product("p1", "tato", "toto", "toto", c, 1f, u1 ,new Date(0), new Date(0)));  
		productDao.create(new Product("p2", "toto", "toto", "toto", c, 2f, u1 ,new Date(0), new Date(0)));  
		productDao.create(new Product("p3", "toto", "toto", "toto", c, 2f, u2 ,new Date(0), new Date(0)));  
		
		Product p = productDao.findByName("p1");
		transactionDao.create(new Transaction(p.getSeller(),userDao.findByEmail("tutu@tata.com"),p.getPrice(),new Date(0),p));
		Product p2 = productDao.findByName("p2");
		transactionDao.create(new Transaction(p2.getSeller(),userDao.findByEmail("tutu@tata.com"),p2.getPrice(),new Date(0),p2));

	}

	/**
	 * Cleans up the session.
	 */
	@AfterClass
	public static void closeTestFixture() {
		
		transactionDao.delete(transactionDao.findById(1));
		transactionDao.delete(transactionDao.findById(2));

		productDao.delete(productDao.findByName("p1"));
		productDao.delete(productDao.findByName("p2"));
		productDao.delete(productDao.findByName("p3"));
		
		userDao.delete(userDao.findByEmail("tutu@tata.com"));
		userDao.delete(userDao.findByEmail("tata@tata.com"));
		
		categoryDao.delete(categoryDao.findByName("Test"));

	}

	@Test 
	public void testCreate(){

	}
	
	@Test
	public void testfindBySeller() {
		assertEquals(2,transactionDao.findBySeller(userDao.findByEmail("tata@tata.com")).size());
		assertEquals(0,transactionDao.findBySeller(userDao.findByEmail("tutu@tata.com")).size());
	}
	
	@Test
	public void testFindByAmount() {
		assertEquals(1,transactionDao.findByAmount(1f).size());
	}
	
	@Test
	public void testFindByDate(){
		assertEquals(2,transactionDao.findByDate(new Date(0)).size());
	}
	
	@Test
	public void testfindByBuyer(){
		assertEquals(0,transactionDao.findByBuyer(userDao.findByEmail("tata@tata.com")).size());
		assertEquals(2,transactionDao.findByBuyer(userDao.findByEmail("tutu@tata.com")).size());
	}
	
	@Test
	public void testFindByProduct(){
		assertEquals(1,transactionDao.findByProduct(productDao.findByName("p1")).size());
		assertEquals(1,transactionDao.findByProduct(productDao.findByName("p2")).size());
		assertEquals(0,transactionDao.findByProduct(productDao.findByName("p3")).size());
	}
}
