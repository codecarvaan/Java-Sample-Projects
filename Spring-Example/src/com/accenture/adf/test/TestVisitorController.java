package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.adf.businesstier.controller.VisitorController;
import com.accenture.adf.businesstier.dao.VisitorDAO;
import com.accenture.adf.businesstier.entity.Visitor;

/**
 * Junit test case to test the class VisitorController
 * 
 */
public class TestVisitorController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;
	private ModelAndView modelAndView;
	private VisitorController controller;
	private VisitorDAO visitorDao;

	/**
	 * Set up initial methods required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		modelAndView = new ModelAndView();
		controller = new VisitorController();
		session = new MockHttpSession();
		response = new MockHttpServletResponse();
		visitorDao = new VisitorDAO();
	}

	/**
	 * Deallocate objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
		modelAndView = null;
		controller = null;
		session = null;
		response = null;
		visitorDao = null;
	}

	/**
	 * Positive test case to test the method newVisitor
	 */
	
	@Test
	public void testNewVisitor_Positive() {
		try {
			request = new MockHttpServletRequest("GET", "/newVistor.htm");

			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			request.setParameter("FIRSTNAME", "TestVFname");
			request.setParameter("LASTNAME", "lname");
			request.setParameter("EMAIL", "mail");
			request.setParameter("PHONENO", "11111");
			request.setParameter("ADDRESS", "testAddress");
			modelAndView = controller.newVisitor(request, response);
		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/registration.jsp", modelAndView.getViewName());
	}

	/**
	 * Negative test case to test the method newVisitor
	 */
	
	@Test
	public void testNewVisitor_Negative() {
		/**
		 * @TODO: Call newVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		
		try {
			modelAndView = controller.newVisitor(null, response);
		} catch(Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder"
					     ,e.getMessage());
		}
		
	}

	/**
	 * Positive test case to test the method searchVisitor
	 */
	
	@Test
	public void testSearchVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set request parameters for USERNAME and PASSWORD for valid values
		 * Call searchVisitor method and assert model view name 
		 */	
		
		try {
			request = new MockHttpServletRequest("GET","/searchVisitor.htm");
			request.setParameter("USERNAME", "admin");
			request.setParameter("PASSWORD", "admin");
			modelAndView = controller.searchVisitor(request, response);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("/visitormain.jsp",modelAndView.getViewName());
	}

	/**
	 * Negative test case of invalid user for method searchVisitor
	 */
	
	@Test
	public void testSearchVisitor_Negative_InvalidUser() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set request parameters for USERNAME and PASSWORD for invalid values
		 * Call searchVisitor method and assert model view name 
		 */	
		try {
			request = new MockHttpServletRequest("GET","/searchVisitor.htm");
			request.setParameter("USERNAME", "dheeraj");
			request.setParameter("PASSWORD", "12256");
			modelAndView = controller.searchVisitor(request, response);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("/index.jsp",modelAndView.getViewName());
	}

	/**
	 * Negative test case for method searchVisitor
	 */
	
	@Test
	public void testSearchVisitor_Negative() {
		/**
		 * @TODO: Call searchVisitor method by passing request object as null and 
		 * asserting the model view name
		 * 
		 */
		
		try {
			modelAndView = controller.searchVisitor(null, response);
		} catch(Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					e.getMessage());
		}
	}

	/**
	 * Positive test case for method registerVisitor
	 */
	
	@Test
	public void testRegisterVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for USERNAME and PASSWORD for valid values
		 * Call registerVisitor method and assert model view name 
		 */		
		
		try {
			request = new MockHttpServletRequest("GET","/eventreg.htm");
			String username = "usha";
			String password = "efgh1234";
			Visitor v = new Visitor();
			v = visitorDao.searchUser(username, password);
			session.setAttribute("VISITOR", v);
			request.setSession(session);
			request.setParameter("username", username);
			request.setParameter("password", password);
			modelAndView = controller.registerVisitor(request, response);
			assertEquals("/visitormain.jsp",modelAndView.getViewName());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}	

	/**
	 * Negative test case for method registerVisitor
	 */
	
	@Test
	public void testRegisterVisitor_Negative() {
		/**
		 * @TODO: Call registerVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		
		try {
			modelAndView = controller.registerVisitor(null, response);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * Positive test case for method updateVisitor
	 */

	
	@Test
	public void testUpdateVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for all valid user values
		 * Call updateVisitor method and assert model view name 
		 */	
		
		//PHONE NUMBER FROM SEARCH USER RETURNS NULL.
		
		try {
			request = new MockHttpServletRequest("GET","/updatevisitor.htm");
			Visitor v =null;
			String username = "usha";
			String password = "efgh1234";
		    v = visitorDao.searchUser(username, password);
		    System.out.println(v.getUserName());
		    System.out.println(v.getPassword());
		    System.out.println(v.getFirstName());
		    System.out.println(v.getLastName());
		    System.out.println("phone from visitor"+v.getPhoneNumber());
		    System.out.println(v.getEmail());
		    System.out.println(v.getAddress());
		    System.out.println(v.getAdmin());
		    request.setParameter("username", username);
		    request.setParameter("password", password);
		    request.setParameter("firstname", "Usha1");
		    request.setParameter("lastname", v.getLastName());
		    request.setParameter("email", v.getEmail());
		    request.setParameter("phonenumber", v.getPhoneNumber());
		    request.setParameter("address", v.getAddress());
		    request.setParameter("isadmin", "false");
		    session.setAttribute("VISITOR", v);
		    request.setSession(session);
		    modelAndView = controller.updateVisitor(request, response);
		    assertEquals("/updatevisitor.jsp",modelAndView.getViewName());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Negative test case for method updateVisitor
	 */
	
	@Test
	public void testUpdateVisitor_Negative() {
		/**
		 * @TODO: Call updateVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		
		try {
			modelAndView = controller.updateVisitor(null, response);
		}catch(Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder"
					     ,e.getMessage());
		}
	}

	/**
	 * Positive test case for method unregisterEvent
	 */
	//NOT UNREGISTERING.
	
	@Test
	public void testUnregisterEvent_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for all USERNAME, PASSWORD and eventId values
		 * Call unregisterEvent method and assert model view name 
		 */	
		
		try {
			request = new MockHttpServletRequest("GET","/eventunreg.htm");
			Visitor v = visitorDao.searchUser("usha", "efgh1234");
			session.setAttribute("VISITOR", v);
			request.setSession(session);
			request.setParameter("username", v.getUserName());
			request.setParameter("password", v.getPassword());
			request.setParameter("eventId", "1002");
			request.setParameter("eventsessionid", "10002");
			modelAndView = controller.unregisterEvent(request, response);
			assertEquals("/visitormain.jsp",modelAndView.getViewName());
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
	}

	/**
	 * Negative test case for method unregisterEvent
	 */
	
	@Test
	public void testUnregisterEvent_Negative() {
		/**
		 * @TODO: Call unregisterEvent method by passing request object as null and 
		 * asserting the model view name
		 */	
		
		try {
			modelAndView = controller.unregisterEvent(null, response);
		}catch(Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder"
					,e.getMessage());
		}
		
}

	/**
	 * Positive test case for search events by name
	 */
	
	@Test
	public void testSearchEventsByName_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for eventname
		 * Call searchEventsByName method and assert model view name 
		 */		
		

		try {
			request = new MockHttpServletRequest("GET","/searchEventByName.htm");
			Visitor v = visitorDao.searchUser("usha", "efgh1234");
			String eventName = "event2";
			session.setAttribute("VISITOR", v);
			request.setSession(session);
			request.setParameter("eventName", eventName);
			modelAndView = controller.searchEventsByName(request, response);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals("/visitormain.jsp",modelAndView.getViewName());
	}

	/**
	 * Positive test case for search events by name catalog
	 */
	
	@Test
	public void testSearchEventsByNameCatalog_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for eventname
		 * Call searchEventsByNameCatalog method and assert model view name 
		 */		
		

		try {
			request = new MockHttpServletRequest("GET","/searchEventByNameCatalog.htm");
			Visitor v = visitorDao.searchUser("usha", "efgh1234");
			String eventName = "event2";
			session.setAttribute("VISITOR", v);
			request.setSession(session);
			request.setParameter("eventName", eventName);
			modelAndView = controller.searchEventsByNameCatalog(request, response);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals("/eventCatalog.jsp",modelAndView.getViewName());
		
	}

	/**
	 * Test case for show events in asc order
	 */
	
	@Test
	public void testShowEventsAsc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsAsc method and assert model view name 
		 */		
		
		try {
			request = new MockHttpServletRequest("GET","/displayasc.htm");
			Visitor v = visitorDao.searchUser("usha", "efgh1234");
			modelAndView = controller.showEventsAsc(request, response);
			assertEquals("/visitormain.jsp",modelAndView.getViewName());
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	/**
	 * Test case for show events in desc order
	 */
	
	@Test
	public void testShowEventsDesc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsDesc method and assert model view name 
		 */		
		
		try {
			request = new MockHttpServletRequest("GET","/displaydesc.htm");
			Visitor v = visitorDao.searchUser("usha", "efgh1234");
			session.setAttribute("VISITOR", v);
			request.setSession(session);
			modelAndView = controller.showEventsDesc(request, response);
			assertEquals("/visitormain.jsp",modelAndView.getViewName());
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("/visitormain.jsp",modelAndView.getViewName());
		
	}

	/**
	 * Test case for show events catalog asc order
	 */
	
	@Test
	public void testShowEventsCatalogAsc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsCatalogAsc method and assert model view name 
		 */		
		

		try {
			request = new MockHttpServletRequest("GET","/displaycatalogasc.htm");
			Visitor v = visitorDao.searchUser("usha", "efgh1234");
			modelAndView = controller.showEventsAsc(request, response);
			assertEquals("/eventCatalog.jsp",modelAndView.getViewName());
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//assertEquals("/eventCatalog.jsp",modelAndView.getViewName());
		
		
	}

	/**
	 * Test case for show events catalog desc
	 */
	
	@Test
	public void testShowEventsCatalogDesc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsCatalogDesc method and assert model view name 
		 */	
		
		try {
			request = new MockHttpServletRequest("GET","/displaycatalogdesc.htm");
			Visitor v = visitorDao.searchUser("usha", "efgh1234");
			modelAndView = controller.showEventsAsc(request, response);
			assertEquals("/eventCatalog.jsp",modelAndView.getViewName());
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//assertEquals("/eventCatalog.jsp",modelAndView.getViewName());
		
		
	}

	/**
	 * Negative test case for search events by name
	 */
	
	@Test
	public void testSearchEventsByName_Negative() {
		/**
		 * @TODO: Call searchEventsByName method by passing request object as null and 
		 * asserting the model view name
		 */		
		
		try {
			modelAndView = controller.searchEventsByName(null, response);
		}catch(Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					e.getMessage());
		}
		
	}

	/**
	 * Negative test case for search events by name catalog
	 */
	
	@Test
	public void testSearchEventsByNameCatalog_Negative() {
		/**
		 * @TODO: Call searchEventsByNameCatalog method by passing request object as null and 
		 * asserting the model view name
		 */		
		
		try {
			modelAndView = controller.searchEventsByNameCatalog(null, response);
		}catch(Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					e.getMessage());
		}
		
	}

	/**
	 * Negative test case for show events in asc order
	 */
	
	@Test
	public void testShowEventsAsc_Negative() {
		/**
		 * @TODO: Call showEventsAsc method by passing request object as null and 
		 * asserting the model view name
		 */		
		
		try {
			modelAndView = controller.showEventsAsc(null, response);
		}catch(Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",e.getMessage());
		}
		
	}

	/**
	 * Negative test case for show events in desc order
	 * 
	 */
	
	@Test
	public void testShowEventsDesc_Negative() {
		/**
		 * @TODO: Call showEventsDesc method by passing request object as null and 
		 * asserting the model view name
		 */		
		
		try {
			modelAndView = controller.showEventsDesc(null, response);
		}catch(Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					e.getMessage());
		}
		
		
	}

	/**
	 * Negative test case for show events catalog in asc order
	 */
	
	@Test
	public void testShowEventsCatalogAsc_Negative() {
		/**
		 * @TODO: Call showEventsCatalogAsc method by passing request object as null and 
		 * asserting the model view name
		 */		
		
		try {
			modelAndView = controller.showEventsCatalogAsc(null, response);
		}catch(Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",e.getMessage());
		}
		
	}

	/**
	 * Negative test case for show events catalog in desc order
	 */
	
	@Test
	public void testShowEventsCatalogDesc_Negative() {
		/**
		 * @TODO: Call showEventsCatalogDesc method by passing request object as null and 
		 * asserting the model view name
		 */		
		
		try {
			modelAndView = controller.showEventsCatalogDesc(request, response);
		}catch(Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder",
					e.getMessage());
		}
		
		
	}
	
	
	/**
	 * Positive test case for change password
	 */
	/*@Test
	public void testChangePassword_Positive(){
		*//**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for password
		 * Call changePassword method and assert status as success
		 *//*		
	}
	
	*//**
	 * Negative test case for change password with password as null
	 *//*
	@Test
	public void testChangePassword_PasswordNull(){
		*//**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Do not set request parameters for password
		 * Call changePassword method and assert status as success
		 *//*	
	}
	
	*//**
	 * Negative test case for change password with visitor as null
	 *//*
	@Test
	public void testChangePassword_VisitorNull(){
		*//**
		 * @TODO: Create MockHttpServletRequest object 
		 * Do not set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for password
		 * Call changePassword method and assert status as success
		 *//*		
	}*/
	
	/**
	 * Positive test case for change password
	 */
	
	@Test
	public void testChangePassword_Positive(){
		try{
			request = new MockHttpServletRequest("GET", "/changePWD.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");	
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			request.setParameter("password", "password3");
			modelAndView = controller.changePassword(request, response);		
		}catch(Exception exception){
			fail("Exception");
		}
		assertEquals("success", modelAndView.getModelMap().get("status"));
		request.setParameter("password", "password");
		modelAndView = controller.changePassword(request, response);
	}
	
	/**
	 * Negative test case for change password with password as null
	 */
	
	@Test
	public void testChangePassword_PasswordNull(){
		try{
			request = new MockHttpServletRequest("GET", "/changePWD.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");			
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);			
			modelAndView = controller.changePassword(request, response);		
		}catch(Exception exception){
			fail("Exception");
		}
		assertEquals("error", modelAndView.getModelMap().get("status"));
	}
	
	/**
	 * Negative test case for change password with visitor as null
	 */
	
	@Test
	public void testChangePassword_VisitorNull(){
		try{
			request = new MockHttpServletRequest("GET", "/changePWD.htm");
			Visitor visitor = new Visitor();			
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			request.setParameter("password", "password");
			modelAndView = controller.changePassword(request, response);		
		}catch(Exception exception){
			fail("Exception");
		}
		assertEquals("error", modelAndView.getModelMap().get("status"));
	}
}