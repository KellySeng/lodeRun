package loderunner.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import loderunner.contrat.EnvironmentContrat;
import loderunner.impl.EditableScreenImpl;
import loderunner.impl.EnvironmentImpl;
import loderunner.impl.ItemImpl;
import loderunner.services.EditableScreenService;
import loderunner.services.EnvironmentService;
import loderunner.services.ItemType;


public class TestEnvironment {
	
	EnvironmentService env;
	private int h = 5;
	private int w = 5;

	@Before
	public void beforeTests() {
		env = new EnvironmentContrat(new EnvironmentImpl());
	}
	
	@After
	public void afterTests() {
		env = null;
	}
	
	//init
	

	@Test
	public void test1InitPositif() {
		EditableScreenService es = new EditableScreenImpl();
		es.init(h, w);
		env.init(es.getWidth(), es.getHeight(),es);
	}
	
	@Test
	public void test1InitNegatif() {
		EditableScreenService es = new EditableScreenImpl();
		try {
			env.init(5,2,es);
		}
		catch(Exception e) {
			assertTrue(false);
		}
	}
	
	//getCellContent
	
	@Test
	public void test1CellContentPositif() {
		EditableScreenService es = new EditableScreenImpl();
		es.init(h, w);
		env.init(es.getWidth(), es.getHeight(),es);
		assertEquals(env.getCellContent(0, 0).size(),0);
	}
	
	@Test
	public void test1CellContentNegatif() {
		EditableScreenService es = new EditableScreenImpl();
		es.init(h, w);
		env.init(es.getWidth(), es.getHeight(),es);
		env.addCellContent(0, 0, new ItemImpl(0, ItemType.Treasure, 0, 0));
		assertEquals(env.getCellContent(0, 0).size(),0);
	}
	
	

}
