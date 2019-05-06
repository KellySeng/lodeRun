package loderunner.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import loderunner.contrat.EnvironmentContrat;
import loderunner.impl.EnvironmentImpl;
import loderunner.services.EnvironmentService;


public class TestEnvironment {
	
	EnvironmentService es;
	private int h = 5;
	private int w = 5;

	@Before
	public void beforeTests() {
		es = new EnvironmentContrat(new EnvironmentImpl());
		es.init(h, w);
	}
	
	@After
	public void afterTests() {
		es = null;
	}
	

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
