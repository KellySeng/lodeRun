package loderunner.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import loderunner.contrat.EnvironmentContrat;
import loderunner.contrat.PreconditionError;
import loderunner.impl.EditableScreenImpl;
import loderunner.impl.EnvironmentImpl;
import loderunner.impl.ItemImpl;
import loderunner.services.EditableScreenService;
import loderunner.services.EnvironmentService;
import loderunner.services.ItemType;


public class TestEnvironment {
	
	EnvironmentService env;
	private int h = 6;
	private int w = 6;

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
	public void test1InitPrePositif() {
		EditableScreenService es = new EditableScreenImpl();
		es.init(h, w);
		env.init(es.getWidth(), es.getHeight(),es);
	}
	
	@Test
	public void test1InitPreNegatif() {
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
	public void test1CellContentPrePositif() {
		try {
			EditableScreenService es = new EditableScreenImpl();
			es.init(h, w);
			env.init(es.getWidth(), es.getHeight(),es);
			env.getCellContent(w-1, h-1);
		} catch(PreconditionError e) {
			fail("CellContent error");
		}
	}
	
	@Test
	public void test1CellContentPreNegatif() {
		try {
			EditableScreenService es = new EditableScreenImpl();
			es.init(h, w);
			env.init(es.getWidth(), es.getHeight(),es);
			env.getCellContent(w-1, h);
		} catch(PreconditionError e) {
			fail("CellContent error");
		}
	}
	
	
	@Test
	public void test1CellContent() {
		EditableScreenService es = new EditableScreenImpl();
		es.init(h, w);
		env.init(es.getWidth(), es.getHeight(),es);
		assertEquals(env.getCellContent(0, 0).size(),0);
	}
	
	@Test
	public void test2CellContent() {
		EditableScreenService es = new EditableScreenImpl();
		es.init(h, w);
		env.init(es.getWidth(), es.getHeight(),es);
		assertEquals(env.getCellContent(0, 2).size(),0);
		env.getCellContent(0, 2).add( new ItemImpl(0, ItemType.Treasure, 0, 2));
		assertEquals(env.getCellContent(0, 2).size(),1);
	}
	
	@Test
	public void test3CellContent() {
		EditableScreenService es = new EditableScreenImpl();
		es.init(h, w);
		env.init(es.getWidth(), es.getHeight(),es);
		assertEquals(env.getCellContent(0, 2).size(),0);
		env.getCellContent(0, 2).add( new ItemImpl(0, ItemType.Treasure, 0, 2));
		assertEquals(env.getCellContent(0, 2).size(),1);
		env.getCellContent(0, 2).clear();
		assertEquals(env.getCellContent(0, 2).size(),0);
	}
	
	

}
