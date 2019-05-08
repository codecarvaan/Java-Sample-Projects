package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.accenture.adf.businesstier.dao.VisitorDAO;
import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.EventCoordinator;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.businesstier.service.EventServiceImpl;
import com.accenture.adf.exceptions.FERSGenericException;

/**
 * Junit test case to test class EventServiceImpl
 * 
 */
public class TestEventServiceImpl {

	private List<Object[]> eventList;
	private Visitor visitor;
	private EventServiceImpl eventServiceImpl;

	/**
	 * Set up the objects required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventServiceImpl = new EventServiceImpl();
		visitor = new Visitor();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
		
		eventServiceImpl = null;
		visitor = null;
	}

	/**
	 * Test case to test the method getAllEvents
	 */
	
	@Test
	public void testGetAllEvents() {
		/**
		 * @TODO: Call getAllEvents method and assert it for the size of returned array
		 */	
		
		eventList = eventServiceImpl.getAllEvents();
		assertEquals(16,eventList.size());
		
	}

	/**
	 * Test case to test the method checkEventsofVisitor
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	
	@Test
	public void testCheckEventsofVisitor() throws ClassNotFoundException, Exception {
		/**
		 * @TODO: Call checkEventsofVisitor and assert the returned type of this method
		 * for appropriate return type
		 */
		
		VisitorDAO v = new VisitorDAO();
		boolean status = false;
		visitor = v.searchUser("jfrancois", "password");
		status = eventServiceImpl.checkEventsofVisitor(visitor, 1004, 10004);
		assertTrue(status);
		
		
	}

	/**
	 * Test case to test the method updateEventDeletions
	 */

	@Test
	public void testUpdateEventDeletions() {
		/**
		 * @TODO: Call updateEventDeletions and assert the return type of this method
		 */	
		
		Event e=eventServiceImpl.getEvent(1004, 10004);
		System.out.println("sf"+e.getSeatsavailable());
		int seats=Integer.parseInt(e.getSeatsavailable());
		
		eventServiceImpl.updateEventDeletions(e.getEventid(), e.getSessionId());
		e=eventServiceImpl.getEvent(1004,10004);
		e.getSeatsavailable();
		seats=seats+1;
		assertEquals(seats,Integer.parseInt(e.getSeatsavailable()));
		
		
	}

	/**
	 * Junit test case for getEventCoordinator
	 */
	@Test
	public void testGetEventCoordinator() {
		/**
		 * @TODO: Call getAllEventCoordinators and assert the size of return type of this method
		 */	
		
		List<EventCoordinator> eventCordList = new ArrayList<EventCoordinator>();
		eventCordList = eventServiceImpl.getAllEventCoordinators();
		assertEquals(5,eventCordList.size());
		
		
	}

	/**
	 * Junit test case for getEvent
	 */

	@Test
	public void testGetEvent() {
		/**
		 * @TODO: Call getEvent and assert the event id of this event with 
		 * passed event id 
		 */		
		
		Event e = eventServiceImpl.getEvent(1004, 10004);
		assertEquals(1004,e.getEventid());
		
	}

	/**
	 * Junit test case for updateEvent
	 */
	
	@Test
	public void testInsertEvent() {
		/**
		 * @TODO: Call insertEvent
		 * Create event object by setting appropriate values
		 * Assert the status of insertEvent method
		 */		
		
		Event e = new Event();
		
		e.setName("event2");
		e.setDescription("desc_event4");
		e.setPlace("place4");
		e.setDuration("1300-1400");
		e.setEventtype("event_type4");
		e.setEventSession(1);
		e.setEventCoordinatorId(105);
		e.setSeatsavailable("450");
		int status = eventServiceImpl.insertEvent(e);
		System.out.println(status);
		assertEquals(1,status);
	}

	/**
	 * Junit test case for updateEvent
	 */
	
	@Test
	public void testUpdateEvent() {
		/**
		 * @TODO: Fetch Event object by calling getAllEvents method 
		 * Update event object by setting appropriate values
		 * Call updateEvent method
		 * Assert the status of updateEvent method
		 */	
		
		//ArrayList<Object[]> eventList = new ArrayList<Object[]>();
		//eventList = eventServiceImpl.getAllEvents("event3");
	}

	/**
	 * Junit test case for deleteEvent
	 */
	@Ignore
	@Test
	public void testDeleteEvent() {
		
		
		eventServiceImpl.deleteEvent(1004, 10004);
		Event event = eventServiceImpl.getEvent(104,10004);
		assertEquals(null,event);
		/**
		 * @TODO: Fetch Event object by calling getAllEvents method 
		 * Update event object by setting appropriate values
		 * Call deleteEvent method
		 * Assert the status of deleteEvent method
		 */	
	}

}