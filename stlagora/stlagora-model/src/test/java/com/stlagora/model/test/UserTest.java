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
import com.stlagora.model.dao.UserDao;
import com.stlagora.model.dao.UserDaoImpl;
import com.stlagora.model.entities.Category;
import com.stlagora.model.entities.Opinion;
import com.stlagora.model.entities.Product;
import com.stlagora.model.entities.User;
import com.stlagora.model.entities.enumerate.ROLE;

public class UserTest {

	//TODO Transaction et Opinions 
	
	private  static String persistanceUnit = "stlagora-Test";
	private static CategoryDao categoryDao;
	private static ProductDao productDao;
	private static UserDao userDao;
	private static OpinionDao opinionDao;
	
	@BeforeClass
	public static void initTestFixture() throws Exception {
		// Get the entity manager for the tests.	
		userDao = new UserDaoImpl(persistanceUnit);

		//Create Users test
		userDao.create(new User("tutu", "tutu", "tutu", "tutu@tata.com", new Date(0), "00000000", ROLE.MEMBER));
		userDao.create(new User("tata", "tata", "tata", "tata@tata.com", new Date(0), "11111111", ROLE.MEMBER));
		userDao.create(new User("toto", "toto", "toto", "toto@tata.com", new Date(0), "22222222", ROLE.ADMIN));
		
		opinionDao = new OpinionDaoImpl(persistanceUnit);
		
		opinionDao.create(new Opinion(userDao.findByEmail("tutu@tata.com"),new Date(0),1f,"tutu",null,userDao.findByEmail("tata@tata.com")));
		
	}

	/**
	 * Cleans up the session.
	 */
	@AfterClass
	public static void closeTestFixture() {
		opinionDao.delete(opinionDao.findById(1));
		userDao.delete(userDao.findByEmail("tutu@tata.com"));
		userDao.delete(userDao.findByEmail("tata@tata.com"));
		userDao.delete(userDao.findByEmail("toto@tata.com"));
	}


	@Test
	public void testFindByEmail() {
		assertEquals(1,(long) userDao.findByEmail("tutu@tata.com").getId());
	}

	@Test
	public void testFirstname() {
		assertEquals(1,userDao.findByFirstname("tutu").size());
	}
	
	@Test
	public void testSurname() {
		assertEquals(1,userDao.findBySurname("tutu").size());
	}
	
	@Test
	public void testPseudo() {
		assertEquals(1,(long)userDao.findByPseudo("tutu").getId());
	}
	
	@Test
	public void testFindBySubscriptionDate() {
		assertEquals(3,userDao.findBySubscriptionDate(new Date(0)).size());
	}
	@Test
	public void testByPhoneNumber() {
		assertEquals(1,userDao.findByPhoneNumber("00000000").size());
	}
	@Test
	public void testByRole() {
		assertEquals(2,(long) userDao.findByRole(ROLE.MEMBER).size());
	}
	
	@Test 
	public void testUpdate() {
		User u = userDao.findByEmail("tutu@tata.com");
		u.setFirstname("tututututu");
		userDao.update(u);
		assertEquals(userDao.findByEmail("tutu@tata.com").getFirstname(),"tututututu");

	}
}
