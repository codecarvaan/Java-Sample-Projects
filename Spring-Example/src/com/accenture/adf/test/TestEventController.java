package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.adf.businesstier.controller.EventController;

/**
 * Junit test class for EventController
 * 
 */
public class TestEventController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private ModelAndView modelAndView;
	private EventController controller;

	/**
	 * Sets up initial objects required in other methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		modelAndView = new ModelAndView();
		controller = new EventController();
		response = new MockHttpServletResponse();		
	}

	/**
	 * Deallocate the objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		controller=null;
		modelAndView=null;
		response=null;
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
	}

	/**
	 * Test case to test the positive scenario for getAvailableEvents method
	 */
	@Test
	public void testGetAvailableEvents_Positive() {

		try {
			request = new MockHttpServletRequest("GET", "/catalog.htm");
			modelAndView = controller.getAvailableEvents(request, response);
		} catch (Exception exception) {
			//fail("Exception");
		}
		assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
	}

	/**
	 * Executes the negative scenario for getAvailableEvents method
	 */
	@Test
	public void testGetAvailableEvents_Negative() { //done
		
		try {
			
			modelAndView = controller.displayEvent(null, response);
		} catch (Exception exception) {
			System.out.println("not working ");
			fail("Exception");
		}
		assertEquals("/addEvent.jsp", modelAndView.getViewName());
		/**
		 * @TODO: Call getAvailableEvents methods  by passing request as null
		 * and assert it for appropriate model view name
		 */	
	}
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	public void testDisplayEvent_Positive() { //done
		
		try {
			request = new MockHttpServletRequest("GET", "/displayEvent.htm");
			request.setParameter("eventId", "1001");
			request.setParameter("sessionId", "10001");
			modelAndView = controller.displayEvent(request, response);
			assertEquals("/addEvent.jsp", modelAndView.getViewName());
		} catch (Exception exception) {
			System.out.println("not working ");
			fail("Exception");
		}
		
		/**
		 * @TODO: Call displayEvent methods and assert
		 * it for appropriate model view name
		 */	
	}

	/**
	 * Executes the negative scenario for displayEvent method
	 */
	@Test
	public void testDisplayEvent_Negative() { //done
		try {
			request = new MockHttpServletRequest("GET", "/displayEvent.htm");
			modelAndView = controller.displayEvent(null, response);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			fail("Exception");
		}
		
		assertEquals(null, modelAndView.getViewName());
		
	
		
		/**
		 * @TODO: Call displayEvent methods  by passing request as null
		 * and assert it for appropriate model view name
		 */	
	}	
	
	/**
	 * Test case to test the positive scenario for updateEvent method
	 */
	@Test
	public void testUpdateEvent_Positive() {
		try {
			request = new MockHttpServletRequest("GET", "/updateEvent.htm");
			
			request.setParameter("eventId","1001");
			request.setParameter("sessionId","10001");
			request.setParameter("eventName","Rose Parade");
			request.setParameter("desc","hello");
			request.setParameter("place","delhi");
			request.setParameter("duration","121-23");
			request.setParameter("eventType","diwali");
			request.setParameter("isAdd","false");
			request.setParameter("ticket","23");
			request.setParameter("coordinator","23");
			modelAndView = controller.updateEvent(request, response);
		} catch (Exception exception) {
			System.out.println("hello"+exception.getMessage());
			fail("Exception");
		}
		assertEquals("/addEvent.jsp", modelAndView.getViewName());
		
		
		/**
		 * @TODO: Call updateEvent methods and assert
		 * it for appropriate model view name
		 */	
	}

	/**
	 * Executes the negative scenario for updateEvent method
	 */
	@Test
	public void testUpdateEvent_Negative() {
		try {

			modelAndView = controller.updateEvent(null, response);
		} catch (Exception exception) {
			System.out.println("hello"+exception.getMessage());
			fail("Exception");
		}
		assertEquals("/addEvent.jsp", modelAndView.getViewName());
		
		
		/**
		 * @TODO: Call updateEvent methods  by passing request as null
		 * and assert it for appropriate model view name
		 */	
	}
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	public void testDeleteEvent_Positive() { //already deleted
		
		 request=new MockHttpServletRequest("GET","/deleteEvent.htm");
		 try {
			 request.setParameter("eventId", "1004");
			 request.setParameter("sessionId", "10004");
			modelAndView=controller.deleteEvent(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 assertEquals("/eventCatalog.jsp",modelAndView.getView());
		/**
		 * @TODO: Call deleteEvent methods and assert
		 * it for appropriate model view name
		 */
	}

	/**
	 * Executes the negative scenario for displayEvent method
	 */
	
	@Test
	public void testDeleteEvent_Negative() {
		/**
		 * @TODO: Call deleteEvent methods  by passing request as null
		 * and assert it for appropriate model view name
		 */	
		 request=new MockHttpServletRequest("GET","/deleteEvent.htm");
		 try {
			
			modelAndView=controller.deleteEvent(null, response);
			assertEquals("/eventCatalog.jsp",modelAndView.getViewName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception");
		}
	}		

}
