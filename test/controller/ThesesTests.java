package controller;


import static org.junit.Assert.*;
import static play.test.Helpers.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import play.libs.Yaml;
import play.mvc.Result;
import play.test.WithApplication;

import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;

public class ThesesTests extends WithApplication {

	
	@Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
        Ebean.save((List) Yaml.load("test-data.yml"));
    }
	
	
	@Test
	public void testCreateThesisFormWhenUnauthorized(){
		Result result = callAction(controllers.routes.ref.Application.newThesis(),
				fakeRequest().withFormUrlEncodedBody(
						ImmutableMap.of("type", "Bachelor",
										"startDate", "2013-05-19",
										"topic", "Entwicklung einer BPEL Compliance Test Suite in BPELUnit"))
				);
		
		assertEquals(303, status(result));
		assertEquals(controllers.routes.Application.login().url(), header("Location", result));
	}
	
	// einen Request absenden, in dem ein Fake-Formular geschickt wird
	// dann checken, ob man auf der richtigen Seite gelandet ist
	@Test
	public void testCreateThesisForm(){
		Result result = callAction(controllers.routes.ref.Application.newThesis(),
				fakeRequest().withSession("email", "bob@test.de").withFormUrlEncodedBody(
						ImmutableMap.of("type", "Bachelor",
										"startDate", "2013-05-19",
										"topic", "Entwicklung einer BPEL Compliance Test Suite in BPELUnit"))
				);
		
		assertEquals(303, status(result));
		assertEquals(controllers.routes.Application.index().url(), header("Location", result));
	}

	// + checken, ob man bei falschem datum auf eine badrequest response bekommt
	@Test
	public void testCreateThesisWithABadForm(){
		Result result = callAction(controllers.routes.ref.Application.newThesis(),
				fakeRequest().withSession("email", "bob@test.de").withFormUrlEncodedBody(
						ImmutableMap.of("type", "Bachelor",
										"startDate", "NotAValidDate",
										"topic", "Entwicklung einer BPEL Compliance Test Suite in BPELUnit"))
				);
		
		assertEquals(400, status(result));
		//und dann irgendwie testen, dass man auf das eigene Formular zurückkommt
	}
}
