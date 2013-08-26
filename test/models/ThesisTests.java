package models;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeGlobal;
import static play.test.Helpers.inMemoryDatabase;

import java.util.Date;
import java.util.List;

import org.junit.*;

import play.libs.Yaml;
import play.test.WithApplication;

import com.avaje.ebean.Ebean;

public class ThesisTests extends WithApplication {
	
	@Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
        Ebean.save((List) Yaml.load("test-data.yml"));
    }
	
	@Test
	public void testSaveThesis() {
		Date date = new Date();
		new Thesis("Bachelor", date, "Entwicklung einer BPEL Compliance Test Suite in BPELUnit").save();
		
		List<Thesis> theses = Thesis.find.all();
		assertEquals(1, theses.size());
		assertEquals("Entwicklung einer BPEL Compliance Test Suite in BPELUnit", theses.get(0).topic);
		assertEquals("Bachelor", theses.get(0).type);
		assertEquals(date, theses.get(0).startDate);
	}
	
	@Test
	public void testCreateThesis() {
		Date date = new Date();
		Thesis.create(new Thesis("Bachelor", date, "Entwicklung einer BPEL Compliance Test Suite in BPELUnit"));
		
		List<Thesis> theses = Thesis.find.all();
		assertEquals(1, theses.size());
		assertEquals("Entwicklung einer BPEL Compliance Test Suite in BPELUnit", theses.get(0).topic);
		assertEquals("Bachelor", theses.get(0).type);
		assertEquals(date, theses.get(0).startDate);
	}
	
	
	
}
