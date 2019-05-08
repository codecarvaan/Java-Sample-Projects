package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.businesstier.service.EventServiceImpl;
import com.accenture.adf.businesstier.service.VisitorServiceImpl;

/**
 * Junit test class for VisitorServiceImpl
 *
 */
public class TestVisitorServiceImpl {

	private List<Event> visitorList;	
	private Visitor visitor;
	private VisitorServiceImpl visitorServiceImpl;
	EventServiceImpl eventServiceImpl;

	/**
	 * Set up the initial methods 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {		
		visitorServiceImpl = new VisitorServiceImpl();
		eventServiceImpl=new EventServiceImpl(); 
		visitor = new Visitor();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
	}

	/**
	 * Test case for method createVisitor
	 */
	@Test
	public void testCreateVisitor() {
		visitor.setAddress("address");
		
		visitor.setEmail("email");
		visitor.setFirstName("firstName");
		visitor.setLastName("lastName");
		visitor.setPassword("password");
		visitor.setUserName("userName");
		visitor.setPhoneNumber("98452158");
		visitor.setAdmin(false);
		boolean b=visitorServiceImpl.createVisitor(visitor);
		assertEquals(true, b);
		/**
		 * @TODO: Set the appropriate values for visitor object and
		 * call the method createVisitor by passing an argument of this visitor 
		 * object and then asserting the returned type of this method
		 */		
	}

	/**
	 * Test case for method createVisitor
	 */
	@Test
	public void testSearchVisitor() {
		
		visitor=visitorServiceImpl.searchVisitor("userName", "password");
		assertEquals("userName", visitor.getUserName());
		
		/**
		 * @TODO: Call searchVisitor method by passing the appropriate arguments 
		 * and then asserting the returned type visitor username with the argument passed
		 */		
	}

	/**
	 * Test case for method RegisterVisitor
	 */
	@Test
	public void testRegisterVisitor() {
		
		visitor=visitorServiceImpl.searchVisitor("admin", "admin");
		
		visitorServiceImpl.RegisterVisitor(visitor, 1003, 10003);
		
		boolean b=eventServiceImpl.checkEventsofVisitor(visitor,1003,10003);
		
		assertEquals(true, b);
		/**
		 * @TODO: Call RegisterVisitor method by passing visitor object which 
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of RegisterVisitor method 
		 */		
	}

	/**
	 * Test case for method showRegisteredEvents
	 */
	@Test
	public void testShowRegisteredEvents() {
		visitor=visitorServiceImpl.searchVisitor("admin", "admin");
		List<Object[]> ls=visitorServiceImpl.showRegisteredEvents(visitor);
		
		assertEquals(ls.get(0)[0], 0);
		
		/**
		 * @TODO: Call showRegisteredEvents method by passing visitor object which 
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of showRegisteredEvents method 
		 */		
	}

	/**
	 * Test case for method updateVisitorDetails
	 */
	@Test
	public void testUpdateVisitorDetails() {
		
		visitor=visitorServiceImpl.searchVisitor("admin", "admin");
		visitor.setAddress("delhi");
		int row=visitorServiceImpl.updateVisitorDetails(visitor);
		
		assertEquals(row, 1);
		
		/**
		 * @TODO: Call updateVisitorDetails method by passing the visitor object which
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of updateVisitorDetails
		 */		
	}

	/**
	 * Test case for method unregisterEvent
	 */
	@Test
	public void testUnregisterEvent() {
		
		visitor=visitorServiceImpl.searchVisitor("admin", "admin");
		visitor.setAddress("delhi");
		
		visitorServiceImpl.unregisterEvent(visitor, 1003, 10003);
		boolean b=eventServiceImpl.checkEventsofVisitor(visitor,1003,10003);
		
		assertEquals(false, b);
		/*
		 * 
		 * @TODO: Call unregisterEvent method by passing the visitor object which can be
		 * retrieved using searchVisitor method and then asserting the returned type 
		 * of unregisterEvent
		 */		
	}
	

}
