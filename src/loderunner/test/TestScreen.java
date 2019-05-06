package loderunner.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import loderunner.contrat.PostconditionError;
import loderunner.contrat.PreconditionError;
import loderunner.contrat.ScreenContrat;

import loderunner.impl.ScreenImpl;
import loderunner.services.ScreenService;

public class TestScreen {

	private ScreenService s ;
	private int h = 5;
	private int w = 5;
	
	@Before
	public void beforeTests() {
		s = new ScreenContrat(new ScreenImpl());
		s.init(h, w);
	}
	
	@After
	public void afterTests() {
		s = null;
	}
	
	// INIT
	
	@Test
	public void  test1InitPrePositif() {
		try{
			s.init(5, 5);
			assertTrue(true);
		}catch(PreconditionError e) {
			fail("init error");
		}	
	}
	
	@Test
	public void  test1InitPreNegatif() {
		try{
			s.init(5, -5);
		}catch(PreconditionError e) {
			assertTrue(false);
		}	
	}
	
	@Test
	public void  test2InitPreNegatif() {
		try{
			s.init(-5, 5);
		}catch(PreconditionError e) {
			fail("init error");
			assertTrue(false);
		}	
	}
	
	@Test
	public void  test3InitPreNegatif() {
		try{
			s.init(-5, -5);
			
		}catch(PreconditionError e) {
			fail("init error");
			assertTrue(false);
		}	
	}
	
	@Test
	public void  testInitPositif() {
			s.init(6, 6);
			assertEquals(s.getWidth(),6);
			assertEquals(s.getHeight(),6);
	}
	
	// PRE CELL NATURE
	
	@Test
	public void  test1CellNaturePrePositif() {
		try{
			s.getCellNature(0, 0);
			assertTrue(true);
		}catch(PreconditionError e) {
			
		}	
	}
	
	@Test
	public void  test1CellNaturePreNegatif() {
		try{
			s.getCellNature(0, w+2);
		}catch(PreconditionError e) {
			assertTrue(false);
		}	
	}
	
	//PRE DIG / FILL : pas de tests car on ne peut modifier la nature d'un Cell dans ce service
}
