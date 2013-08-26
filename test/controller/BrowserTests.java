package controller;

import java.sql.SQLException;

import models.Person;

import org.junit.*;

import controllers.Application;
import static org.junit.Assert.*;

import play.db.*;
import play.db.ebean.Model;

import play.libs.F;

import play.test.*;
import static play.test.Helpers.*;

public class BrowserTests extends WithApplication{

	// @Test
	// public void testInServer() {
	// running(testServer(3333), new Callback() {
	// public void invoke() {
	// assertThat(WS.url("http://localhost:3333").get().get().status)
	// .isEqualTo(OK);
	// }
	// });
	// }

	private void navigateToIndex(TestBrowser browser) {
		// navigation zur Index-Seite - automatische Weiterleitung zu
		// /loginUser; checken, dass wir uns auf der LoginSeite befinden
		browser.goTo("http://localhost:3333");
		assertEquals("http://localhost:3333/login", browser.url());
		assertTrue(browser.$("title").getTexts().get(0)
				.equals("SuperVizard"));
		assertEquals("Bitte loggen Sie sich ein.", browser.$("#LoginText")
				.getTexts().get(0));
	}

	// an unknown User enters an E-Mail-Address but leaves the PW field
	// blank,
	// no cookies stored.
	@Test
	public void unknownUserNoPW() {
		running(testServer(3333), FIREFOX, new F.Callback<TestBrowser>() {
			public void invoke(TestBrowser browser) throws InterruptedException {

				navigateToIndex(browser);

				// eMail-Adresse eintragen, Submit-Button drücken
				browser.$("#eMailTextfield").text("test@test.de");
				browser.$("#LoginButton").click();

				// wird die Login-Seite wieder geladen? Mit Fehlermeldung? Und
				// eingetragener E-Mail-Adresse? Ohne Password?
				assertEquals("http://localhost:3333/login", browser.url());
				assertEquals("Ungültiger Benutzername oder Passwort.", browser
						.$("#errorMessage").getTexts().get(0));
				assertTrue(browser.$("title").getTexts().get(0)
						.contains("SuperVizard"));
				assertEquals("test@test.de",
						browser.$("#eMailTextfield").getValue());
				assertEquals("", browser.$("#passwordTextfield").getValue());
			}
		});
	}

	// an unknown User enters a password, but no E-Mail-Adress, no cookies
	// stored
	@Test
	public void unknownUserNoEMail() {
		running(testServer(3333), FIREFOX, new F.Callback<TestBrowser>() {
			public void invoke(TestBrowser browser) throws InterruptedException {

				navigateToIndex(browser);

				// eMail-Adresse eintragen, Submit-Button drücken
				browser.$("#password").text("secret");
				browser.$("#loginButton").click();

				// wird die Login-Seite wieder geladen? Mit Fehlermeldung? PW
				// und eMail-Feld sollen nun leer sein.
				assertEquals("http://localhost:3333/login", browser.url());
				assertEquals("Ungültiger Benutzername oder Passwort.", browser
						.$("#errorMessage").getTexts().get(0));
				assertTrue(browser.$("title").getTexts().get(0)
						.contains("SuperVizard"));
				assertEquals("", browser.$("#eMailTextfield").getValue());
				assertEquals("", browser.$("#passwordTextfield").getValue());
			}
		});
	}

	// an unknown User enters nothing, no cookie stored
	@Test
	public void unknownUserEntersNothing() {
		running(testServer(3333), FIREFOX, new F.Callback<TestBrowser>() {
			public void invoke(TestBrowser browser) throws InterruptedException {

				navigateToIndex(browser);

				// eMail-Adresse eintragen, Submit-Button drücken
				browser.$("#loginButton").click();

				// wird die Login-Seite wieder geladen? Mit Fehlermeldung? PW
				// und eMail-Feld sollen nun leer sein.
				assertEquals("http://localhost:3333/login", browser.url());
				assertEquals("Ungültiger Benutzername oder Passwort.", browser
						.$("#errorMessage").getTexts().get(0));
				assertTrue(browser.$("title").getTexts().get(0)
						.contains("SuperVizard"));
				assertEquals("", browser.$("#eMailTextfield").getValue());
				assertEquals("", browser.$("#passwordTextfield").getValue());
			}
		});
	}
	// Raphaels mess
//	// an unknown User enters eMail and pw, no cookie stored. This account is
//	// created in db and the user is taken to generalInformation-page
//	@Test
//	public void unknownUserEntersNewCredentials() {
//		running(testServer(3333), FIREFOX, new F.Callback<TestBrowser>() {
//			public void invoke(TestBrowser browser) throws InterruptedException {
//
//				navigateToIndex(browser);
//
//				// eMail-Adresse eintragen, Submit-Button drücken
//				browser.$("#eMailTextfield").text("NeuerUser@Neuland.de");
//				browser.$("#passwordTextfield").text("mySecret");
//				browser.$("#loginButton").click();
//
//				// wird die Login-Seite wieder geladen? Mit Fehlermeldung? PW
//				// und eMail-Feld sollen nun leer sein.
//				assertEquals("http://localhost:3333/general_information",
//						browser.url());
//				assertTrue(browser.$("title").getTexts().get(0)
//						.equals("SWP Registrierung - Angaben zur Person"));
//
//				// find the user in the db
//				assertNotNull(User.findByEmail("NeuerUser@Neuland.de"));
//
//				// TODO
//				// check if all textfields are empty - there should be no values
//				// in it.
//
//				assertEquals("", browser.$("#name").getValue());
//				assertEquals("", browser.$("#prename").getValue());
//				assertEquals("", browser.$("#matriculationNumber").getValue());
//				assertEquals("", browser.$("#course").getValue());
//				assertEquals(false, browser.findFirst("#certificate")
//						.isSelected());
//			}
//		});
//	}

//	// a partially known User (only user account exits, no other info given)
//	// enters eMail and pw, no cookie stored. The user is taken to
//	// generalInformation-page - not to overview page: only fully known users
//	// (have entered all infos)
//	// are taken to overview page (displaying their full information)
//	@Test
//	public void knownUserEntersNewCredentials() {
//		running(testServer(3333), FIREFOX, new F.Callback<TestBrowser>() {
//			public void invoke(TestBrowser browser)
//					throws InterruptedException, SQLException {
//
//				// create the user before navigating with the browser
//				User.createUser("NeuerUser@Neuland.de", "mySecret");
//				assertNotNull(User.findByEmail("NeuerUser@Neuland.de"));
//
//				navigateToIndex(browser);
//
//				// eMail-Adresse eintragen, Submit-Button drücken
//				browser.$("#eMailTextfield").text("NeuerUser@Neuland.de");
//				browser.$("#passwordTextfield").text("mySecret");
//				browser.$("#loginButton").click();
//
//				// TODO ###################################################
//				// no database writing should take place in this step - the user
//				// was registered before, the app does not need to write him to
//				// the db again.
//				assertNotNull(User.findByEmail("NeuerUser@Neuland.de"));
//
//				// wird die Login-Seite wieder geladen? Mit Fehlermeldung? PW
//				// und eMail-Feld sollen nun leer sein.
//				assertEquals("http://localhost:3333/general_information",
//						browser.url());
//				assertTrue(browser.$("title").getTexts().get(0)
//						.equals("SWP Registrierung - Angaben zur Person"));
//
//				// check if all textfields are empty - there should be no values
//				// in it.
//				assertEquals("", browser.$("#name").getValue());
//				assertEquals("", browser.$("#prename").getValue());
//				assertEquals("", browser.$("#matriculationNumber").getValue());
//				assertEquals("", browser.$("#course").getValue());
//				assertEquals(false, browser.findFirst("#certificate")
//						.isSelected());
//			}
//		});
//	}

}
