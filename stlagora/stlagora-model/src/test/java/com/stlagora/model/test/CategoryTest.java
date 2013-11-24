package com.stlagora.model.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.List;

import org.apache.derby.iapi.services.info.ProductVersionHolder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stlagora.model.dao.CategoryDao;
import com.stlagora.model.dao.CategoryDaoImpl;
import com.stlagora.model.dao.ProductDao;
import com.stlagora.model.dao.ProductDaoImpl;
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.PRODUCT_STATUS;
import com.stlagora.model.entities.enumerate.ROLE;
import com.stlagora.model.entities.enumerate.TYPE_FICHIER;

public class CategoryTest {
	
	private  static String persistanceUnit = "stlagora-Test";
	private static CategoryDao categoryDao;
	private static ProductDao productDao;
	private static UserDao userDao;
	@BeforeClass
	public static void initTestFixture() throws Exception {
	    // Get the entity manager for the tests.
		categoryDao = new CategoryDaoImpl(persistanceUnit);
		productDao = new ProductDaoImpl(persistanceUnit);	
		userDao = new UserDaoImpl(persistanceUnit);
		categoryDao.create(new Category("Test","test-desc"));
		
		//Create Users test
		userDao.create(new User("tutu", "tutu", "tutu", "tutu", new Date(0), "00000000", ROLE.MEMBER));
    	Category c = categoryDao.findByName("Test");
		User u1 = userDao.findByEmail("tutu");
    	productDao.create(new Product("p1", "tato", "toto", "toto", c,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, 1f, u1 ,new Date(0), new Date(0)));  

       	
	}

	 /**
	 * Cleans up the session.
	 */
	@AfterClass
	public static void closeTestFixture() {
		
		productDao.delete(productDao.findByName("p1"));
		userDao.delete(userDao.findByEmail("tutu"));
		categoryDao.delete(categoryDao.findByName("Test"));
		
	}
    
    @Test
   	public void testFindByName() {
       	Category c = categoryDao.findByName("Test");
       	assertEquals(categoryDao.findByName("Test").getId(),1);
   	  }
    
    @Test 
    public void testUpdate() {
    	Category c = categoryDao.findByName("Test");
    	c.setDescription("update");
    	categoryDao.update(c);
    	assertEquals(categoryDao.findByName("Test").getDescription(),"update");
    	
    }
    
}
