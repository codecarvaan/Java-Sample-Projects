package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.accenture.adf.businesstier.dao.EventDAO;
import com.accenture.adf.businesstier.dao.VisitorDAO;
import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;

/**
 * JUnit test case for VisitorDAO class for testing all repository methods to
 * call database sub-routines
 * 
 */
public class TestVisitorDAO {

	private Visitor visitor;
	private VisitorDAO visitorDAO;
	private ArrayList<Object[]> registeredEvents;

	/**
	 * Setting up initial objects
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		visitor = new Visitor();
		visitorDAO = new VisitorDAO();
		registeredEvents = new ArrayList<Object[]>();
	}

	/**
	 * Deallocating objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
		
		
	}

	/**
	 * Test case for method insertData
	 * @throws Exception 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testInsertData() throws ClassNotFoundException, SQLException, Exception {
		/**
		 * @TODO: Create visitor object by setting appropriate values
		 * Call insertData method by passing this visitor object
		 * Search this new visitor object by calling searchUser method
		 * Assert the values of username
		 */	
		try{
					visitor.setUserName("admin");
					visitor.setPassword("admin");
					visitor.setFirstName("ram");
					visitor.setLastName("nam");
					visitor.setEmail("ram@gmail.com");
					visitor.setPhoneNumber("3242424");
					visitor.setVisitorId(1);
					visitor.setAddress("cksgcjksgckdsgk");
					visitorDAO.insertData(visitor);
					visitorDAO.searchUser("admin", "admin");
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		assertEquals("admin", visitor.getUserName());
	}	

	/**
	 * Test case for method searchUser
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSearchUser() throws ClassNotFoundException, SQLException {
		/**
		 * @TODO: Call searchUser method for valid values of username
		 * and password and assert the value of username for the returned type of method
		 */	
		
		String username="admin";
		String password="admin";
		visitor=visitorDAO.searchUser(username, password);
		assertEquals(username, visitor.getUserName());
		
		
	}

	/**
	 * Test case for method registerVisitorToEvent
	 * @throws Exception 
	 */
	@Test
	public void testRegisterVisitorToEvent() throws Exception {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Pass this visitor object and valid eventid to registerVisitorToEvent method
		 * and assert the value
		 */	
		EventDAO e=new EventDAO();
		Visitor v1=new Visitor();
		String username="admin";
		String password="admin";
		visitor=visitorDAO.searchUser(username, password);
		visitorDAO.registerVisitorToEvent(visitor, 1003, 10003);
		assertTrue(e.checkEventsofVisitor(visitor, 1003, 10003));
	}	

	/**
	 * Test case for method registeredEvents
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testRegisteredEvents() throws ClassNotFoundException, SQLException {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Pass this visitor object and valid eventid to registeredEvents method
		 * and assert the value
		 */	
		
		
		Event e=new Event();
		ArrayList<Object[]> e1=new ArrayList<Object[]>();		
		String username="admin";
		String password="admin";
		
		visitor=visitorDAO.searchUser(username, password);
		visitorDAO.registeredEvents(visitor);
		ArrayList<Object[]> even=new ArrayList<Object[]>();
		even=visitorDAO.registeredEvents(visitor);
		assertEquals(1, even.size());
		
	}

	/**
	 * Test case for method updateVisitor
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testUpdateVisitor() throws ClassNotFoundException, SQLException {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Update the value in this visitor object
		 * Pass this visitor object to updateVisitor method
		 * and assert the value of changed value
		 */	
		
		visitor=visitorDAO.searchUser("admin", "admin");
		visitor.setFirstName("hi");
		visitor.setLastName("he");
		visitorDAO.updateVisitor(visitor);
		assertEquals("hi",visitor.getFirstName() );
	}

	/**
	 * Test case for method registeredEvents
	 * @throws Exception 
	 */
	@Test
	public void testUnregisterEvent() throws Exception {
		/**
		 * @TODO: Fetch visitor object by calling searchUser for valid values of username and password
		 * Pass this visitor object and valid eventid to unregisterEvent method
		 * and assert the value
		 */	
		EventDAO e=new EventDAO();
		visitor=visitorDAO.searchUser("admin", "admin");
		visitorDAO.unregisterEvent(visitor, 1003, 10003);
		
		assertEquals(false,e.checkEventsofVisitor(visitor, 1003, 10003) );

	}
	
	
	@Test
	public void testChangePassword_VisitorNull() {
		try {
			visitor = null;
			visitorDAO.changePassword(visitor);
		} catch (SQLException exception) {
			fail("SQL Exception");
		} catch (ClassNotFoundException exception) {
			fail("Class Not Found Exception");
		} catch (Exception exception) {
			fail("NULL Exception");
		}
	}

}