package com.stlagora.model.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stlagora.model.dao.CategoryDao;
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
import com.stlagora.model.entities.enumerate.ACCOUNT_TYPE;
import com.stlagora.model.entities.enumerate.PRODUCT_STATUS;
import com.stlagora.model.entities.enumerate.ROLE;
import com.stlagora.model.entities.enumerate.TYPE_FICHIER;

public class OpinionTest {

	private  static String persistanceUnit = "stlagora-Test";
	private static CategoryDao categoryDao;
	private static ProductDao productDao;
	private static UserDao userDao;
	private static OpinionDao opinionDao;

	@BeforeClass
	public static void initTestFixture() throws Exception {
		// Get the entity manager for the tests.
		categoryDao = new CategoryDaoImpl(persistanceUnit);
		productDao = new ProductDaoImpl(persistanceUnit);	
		userDao = new UserDaoImpl(persistanceUnit);
		categoryDao.create(new Category("Test","test-desc"));

		//Create Users test
		userDao.create(new User("tutu", "tutu", "tutu", "tutu@tata.com","test", new Date(0), "00000000","","","",ACCOUNT_TYPE.PRIVATE, ROLE.MEMBER));
		userDao.create(new User("tata", "tata", "tata", "tata@tata.com","test", new Date(0), "11111111","","","",ACCOUNT_TYPE.PRIVATE, ROLE.MEMBER));
		
		Category c = categoryDao.findByName("Test");
		User u1 = userDao.findByEmail("tata@tata.com");
		productDao.create(new Product("p1", "tato", "toto", "toto", c,TYPE_FICHIER.STL,PRODUCT_STATUS.AVAILABLE, 1f, u1 ,new Date(0), new Date(0)));  
		
		opinionDao = new OpinionDaoImpl(persistanceUnit);
		opinionDao.create(new Opinion(userDao.findByEmail("tutu@tata.com"),new Date(0),1f,"tutu",null,userDao.findByEmail("tata@tata.com")));
		opinionDao.create(new Opinion(userDao.findByEmail("tutu@tata.com"),new Date(0),1f,"tutu",productDao.findByName("p1"),null));

	}

	/**
	 * Cleans up the session.
	 */
	@AfterClass
	public static void closeTestFixture() {
		
		opinionDao.delete(opinionDao.findById(1));
		opinionDao.delete(opinionDao.findById(2));	
		opinionDao.delete(opinionDao.findById(3));	
		productDao.delete(productDao.findByName("p1"));
		userDao.delete(userDao.findByEmail("tutu@tata.com"));
		userDao.delete(userDao.findByEmail("tata@tata.com"));
		categoryDao.delete(categoryDao.findByName("Test"));

	}

	@Test 
	public void testCreate(){
		opinionDao.create(new Opinion(userDao.findByEmail("tutu@tata.com"),new Date(0),1f,"tutu",productDao.findByName("p1"),null));
	}
	
	@Test
	public void testfindByMark() {
		assertEquals(2,opinionDao.findByMark(1f).size());
	}

	@Test
	public void testFindByWriterId() {
		assertEquals(2,opinionDao.findByWriterId(userDao.findByEmail("tutu@tata.com")).size());
	}
	
	@Test
	public void testFindByDate(){
		assertEquals(2,opinionDao.findByDate(new Date(0)).size());
	}
	
	@Test
	public void testfindByUserMarked(){
		assertEquals(1,opinionDao.findByUserMarked(userDao.findByEmail("tata@tata.com")).size());
	}
	
	@Test
	public void testFindByProduct(){
		assertEquals(1,opinionDao.findByProduct(productDao.findByName("p1")).size());
	}
}
