package loderunner.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import loderunner.contrat.EditableScreenContrat;
import loderunner.contrat.InvariantError;
import loderunner.contrat.PostconditionError;
import loderunner.contrat.PreconditionError;
import loderunner.impl.EditableScreenImpl;
import loderunner.services.Cell;
import loderunner.services.EditableScreenService;

public class TestEditableScreen {
	
	EditableScreenService es;
	private int h = 5;
	private int w = 5;

	@Before
	public void beforeTests() {
		es = new EditableScreenContrat(new  EditableScreenImpl());
		es.init(h, w);
	}
	
	@After
	public void afterTests() {
		es = null;
	}
	
	//init
	@Test
	public void test1InitPositif() {
		es.init(h, w);
	}
	
	@Test
	public void test1InitNegatif() {
		try {
		es.init(h, -w);
		}catch (PreconditionError  e) {
			fail("init error");
		}
		catch (Exception  e) {
			fail("init error");
		}
	}
	
	//isPlayable
	@Test
	public void test1IsPlayablePositif() {
		assertTrue(es.isPlayable());
	}
	
	@Test
	public void test2IsPlayablePositif() {
		es.setNature(1, 1, Cell.MTL);
		assertTrue(es.isPlayable());
	}
	
	@Test
	public void test1IsPlayableNegatif() {
			es.setNature(1, 0, Cell.HDR);
			assertTrue(es.isPlayable());
		
	}
	
	@Test
	public void test2IsPlayableNegatif() {
			es.setNature(w-1, h-1, Cell.HOL);
			assertTrue(es.isPlayable());
	}
	
	
	//setNature

	@Test
	public void test1SetNaturePrePositif() {
		try {
			es.setNature(0, h-1, Cell.LAD);
			}catch (Exception e) {
				fail("init error");
			}
	}
	
	@Test
	public void test1SetNaturePreNegatif() {
		try {
			es.setNature(-w, h-1, Cell.LAD);
		}catch (PreconditionError e) {
				fail("init error");
			}
	}
	

	@Test
	public void test1SetNaturePositif() {
		es.setNature(w-1, h-1, Cell.MTL);
		assertEquals(es.getCellNature(w-1, h-1), Cell.MTL);
	}
	
	@Test
	public void test1SetNatureNegatif() {
		es.setNature(w-1, h-1, Cell.HOL);
		assertEquals(es.getCellNature(w-1, h-1), Cell.PLT);
	}
	
	
	//dig
	@Test
	public void test1DigPositif() {
			es.setNature(0,1, Cell.PLT);
			es.dig(0, 1);	
			assertEquals(es.getCellNature(0, 1), Cell.HOL);
	}
	
	@Test
	public void test2DigPositif() {
		es.setNature(w-1,h-1, Cell.PLT);
		es.dig(w-1, h-1);	
		assertEquals(es.getCellNature(w-1, h-1), Cell.HOL);
}
	
	@Test
	public void test1DigNegatif() {
		es.setNature(0,1, Cell.LAD);
		es.dig(0, 1);	
		assertEquals(es.getCellNature(0, 1), Cell.HOL);
	}
	
	
	@Test
	public void test2DigNegatif() {
		es.dig(0, 0);	
		assertEquals(es.getCellNature(0, 0), Cell.HOL);
	}

	//fill
	@Test
	public void test1FillPositif() {
			es.setNature(0,1, Cell.HOL);
			es.fill(0, 1);	
			assertEquals(es.getCellNature(0, 1), Cell.PLT);
	}
	
	@Test
	public void test2FillPositif() {
		es.setNature(w-1,h-1, Cell.HOL);
		es.fill(w-1, h-1);	
		assertEquals(es.getCellNature(w-1, h-1), Cell.PLT);
}
	
	@Test
	public void test1FillNegatif() {
		es.setNature(0,1, Cell.MTL);
		es.fill(0, 1);	
		assertEquals(es.getCellNature(0, 1), Cell.PLT);
	}
	
	
	@Test
	public void test2FillNegatif() {
		es.fill(0, 0);	
		assertEquals(es.getCellNature(0, 0), Cell.PLT);
	}

	
	
}

