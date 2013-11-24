package com.stlagora.model.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stlagora.model.dao.CategoryDaoImpl;
import com.stlagora.model.dao.OpinionDao;
import com.stlagora.model.dao.OpinionDaoImpl;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ROLE;

public class ProductTest {

	private  static ProductDao productDao;
	private  static UserDao userDao ; 
	private  static String persistanceUnit = "stlagora-Test";
	private static CategoryDaoImpl categoryDao;
	private static OpinionDao opinionDao;
	
	@BeforeClass
	public static void initTestFixture() throws Exception {
	    // Get the entity manager for the tests.
		productDao = new ProductDaoImpl(persistanceUnit);
		userDao = new UserDaoImpl(persistanceUnit);
		categoryDao = new CategoryDaoImpl(persistanceUnit);
		opinionDao = new OpinionDaoImpl(persistanceUnit);
		//Create Users test
		userDao.create(new User("tutu", "tutu", "tutu", "tutu", new Date(0), "00000000", ROLE.MEMBER));
		userDao.create(new User("toto", "toto", "toto", "toto", new Date(0), "00000000", ROLE.MEMBER));
		
		//Create Category for test
		categoryDao.create(new Category("Test","TestObj"));
		User u1 = userDao.findByEmail("tutu");
		productDao.create(new Product("p1", "toto", "toto", "toto", categoryDao.findByName("Test"),1f , u1,new Date(0), new Date(0)));
		opinionDao.create(new Opinion(u1,new Date(0),1f,"tutu",productDao.findByName("p1"),null));
	}

	 /**
	 * Cleans up the session.
	 */
	@AfterClass
	public static void closeTestFixture() {

		opinionDao.delete(opinionDao.findById(1));
		productDao.delete(productDao.findByName("p1"));
		productDao.delete(productDao.findByName("p2"));
		userDao.delete(userDao.findByEmail("toto"));
		userDao.delete(userDao.findByEmail("tutu"));
		categoryDao.delete(categoryDao.findByName("Test"));
		
	}
	
    @Test
	public void testCreateProduct() {
		  User u1 = userDao.findByEmail("tutu");
		  productDao.create(new Product("p2", "tato", "toto", "toto", categoryDao.findByName("Test"), 1f, u1 ,new Date(0), new Date(0)));
	  }
    
    @Test 
    public void testUpdate() {
    	Product p = productDao.findByName("p1");
    	p.setPrice(6f);
    	productDao.update(p);
    	Product p2 = productDao.findByName("p1");
    	assertEquals(6f,p2.getPrice(),0);
    }
    
    @Test
    public void testSearchById(){
		  Product p1 = productDao.findById(1);
		  assertEquals(1,(long) p1.getId());
    }
    
    @Test 
    public void testSearchByName() {
		  Product p2 = productDao.findByName("p1");
		  assertEquals("p1",p2.getName());
    }
    
    @Test 
    public void testSearchAvailableDate() {
		  List<Product> l1 = productDao.findByAvailableDate(new Date(0));
		  assertEquals(1,l1.size());
		  
		  long time = System.currentTimeMillis();
		  Date date = new java.sql.Date(time);

		  List<Product> l2 = productDao.findByAvailableDate(date);
		  assertEquals(0,l2.size());
    }
    
    @Test 
    public void testSearchGlobalMark() {
		  List<Product> l3 = productDao.findByGlobalMark(0f);
		  assertEquals(2,l3.size());
		  assertEquals(l3.get(1).getGlobalMark(),0f,0);
		  
		  List<Product> l4 = productDao.findByGlobalMark(1f);
		  assertEquals(0,l4.size());
    }
    
    @Test 
    public void testSearchLastUpdate() {
		  List<Product> l3 = productDao.findByLastUpdate(new Date(0));
		  assertEquals(1,l3.size());
		  
		  long time = System.currentTimeMillis();
		  Date date = new java.sql.Date(time);

		  List<Product> l2 = productDao.findByLastUpdate(date);
		  assertEquals(0,l2.size());
    }
    
    @Test 
    public void testSearchPrice() {
		  List<Product> l3 = productDao.findByPrice(1f);
		  assertEquals(1,l3.size());	  
    }
    
    @Test 
    public void testSearchSeller() {
    	  User u1 = userDao.findByEmail("tutu");
		  List<Product> l3 = productDao.findBySeller(u1);
		  assertEquals(2,l3.size());
    }
    
    @Test
    public void testCategory(){
    	 List<Product> l3 = productDao.findByCategory(categoryDao.findByName("Test"));
		 assertEquals(2,l3.size());
    }
    
    
    
} 