package controller;
//package controller;
//
//import static org.junit.Assert.*;
//import models.User;
//import org.junit.*;
//
//import com.google.common.collect.ImmutableMap;
//
//import play.libs.F;
//import play.mvc.Result;
//import play.mvc.Results.Redirect;
//import play.test.*;
//import static play.test.Helpers.*;
//
//public class LoginTests extends WithApplication {
//
//	@Before
//	public void setUp() {
//		start(fakeApplication(inMemoryDatabase()));
//		// Map<String,List<Object>> data =
//		// (Map<String,List<Object>>)Yaml.load("initial-data.yml");
//		// Ebean.save(data);
//
//		// User.createUser("Stephan.Test@fast.test.de", "secret");
//		// User.createUser("Raphael.Test@fast.test.de", "secret");
//		// User.createUser("Anna.Test@fast.test.de", "secret");
//	}
//
//	// just checking if basic insert operations function on memDB
//	@Test
//	public void sanityCheck() {
//		User.createUser("newPerson@inf.uni-hannover.de", "secret");
//		User newUser = User.findByEmail("newPerson@inf.uni-hannover.de");
//		assertNotNull(newUser);
//		assertEquals("secret", newUser.password);
//	}
//
//	// normal login, data already in db, login, do not rewrite user to db
//	@Test
//	public void normalLoginLogout() {
//
//		// Create the user and put him in the db.
//		User.createUser("stephan.test@fast.test.de", "secret");
//
//		// call login-Authentication and get a FOUND-Result
//		Result result = callAction(
//				controllers.routes.ref.Application.authenticateUser(),
//				fakeRequest().withFormUrlEncodedBody(
//						ImmutableMap.of("email", "stephan.test@fast.test.de",
//								"password", "secret")));
//		assertEquals("stephan.test@fast.test.de", session(result).get("email"));
//		assertEquals(303, status(result));
//
//		// Log him out and get a FOUND-Result
//		Result result2 = callAction(
//				controllers.routes.ref.Application.logout(),
//				fakeRequest().withFormUrlEncodedBody(
//						ImmutableMap.of("email", "stephan.test@fast.test.de",
//								"password", "secret")));
//		assertNull(session(result2).get("email"));
//		assertEquals(303, status(result2));
//	}
//
//	// normal Login, wrong password.
//	@Test
//	public void wrongPassword() {
//
//		// Create the user and put him in the db.
//		User.createUser("Stephan.Test@fast.test.de", "secret");
//
//		Result result = callAction(
//				controllers.routes.ref.Application.authenticate(),
//				fakeRequest().withFormUrlEncodedBody(
//						ImmutableMap.of("email", "stephan.test@fast.test.de",
//								"password", "badpassword")));
//		assertEquals(400, status(result));
//		assertNull(session(result).get("email"));
//		assertNull(session(result).get("password"));
//	}
//
//	// normal Login
//	@Test
//	public void normalLogin2() {
//		User.createUser("Stephan.Test@fast.test.de", "secret");
//
//		// call first page - be redirected to loginUser
//		Result result = callAction(controllers.routes.ref.Application.index());
//		assertEquals(303, status(result));
//		assertTrue(headers(result).get("Location").contains("/loginUser"));
//
//		
//		
//	}
//
//	// Login for the first time, not yet in DB, write to DB, login, logout,
//	// login again
//
//	// Login for first time, no pw given, do not login, do not write to db
//
//	// Login for first time, no email given, do not login, do not write to db
//
//	// Login for first time, nothing given, do not login, do not write to db
//
//	//
//}
