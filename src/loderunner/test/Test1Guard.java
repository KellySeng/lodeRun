package loderunner.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import loderunner.contrat.CharacterContrat;
import loderunner.contrat.GuardContrat;
import loderunner.contrat.PlayerContrat;
import loderunner.contrat.PreconditionError;
import loderunner.impl.CharacterImpl;
import loderunner.impl.EditableScreenImpl;
import loderunner.impl.EnvironmentImpl;
import loderunner.impl.GuardImpl;
import loderunner.services.Cell;
import loderunner.services.CharacterService;
import loderunner.services.EditableScreenService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.Move;
import loderunner.services.PlayerService;

public class Test1Guard {
	
	GuardService g1;
	GuardService g2;
	EnvironmentService env;
	CharacterService target;
	EditableScreenService es;
	private int h = 5;
	private int w = 5;
	
	
	@Before
	public void beforeTests() {
		g1 = new GuardContrat(new GuardImpl());
		g2 = new GuardContrat(new GuardImpl());
		env = new EnvironmentImpl();
		es = new EditableScreenImpl();
		target = new CharacterImpl();
		es.init(h, w);
		env.init(es.getWidth(), es.getHeight(),es);
		target.init(env, w-1, 1);
	}
	
	@After
	public void afterTests() {
		g1 = null;
		g2 = null;
		env = null;
		es = null;
		target = null;
	}
	

	

	@Test
	public void test1InitPrePositif() {
		try {
			g1.init(0, 1, env, target);
			assertTrue(true);
		}catch(PreconditionError e) {
			fail("error init");
			assertTrue(false);
		}
	}
	
	

	@Test
	public void test1InitPreNegatif() {
		try {
			es.setNature(0, 1, Cell.PLT);
			env.init(es.getWidth(), es.getHeight(),es);
			target.init(env, w-1, 1);
			g1.init(0, 1, env, target);
			assertTrue(false);
		}catch(PreconditionError e) {
			fail("error init");
			assertTrue(true);
		}
	}
	
	@Test
	public void test2InitPreNegatif() {
		try {
			es.setNature(0, 1, Cell.PLT);
			env.init(es.getWidth(), es.getHeight(),es);
			target.init(env, w-1, 1);
			g1.init(0, 1, env, null);
			assertTrue(false);
		}catch(PreconditionError e) {
			fail("error init");
			assertTrue(true);
		}
	}
	
	@Test
	public void test1InitPositif() {
		try {
			g1.init(0, 1, env, target);
			g2.init(2, 1, env, target);
			assertEquals(g1.getId(), 0);
			assertEquals(g1.getWdt(), 0);
			assertEquals(g1.getHgt(), 1);
			assertEquals(g2.getId(), 1);
			assertEquals(g2.getWdt(), 2);
			assertEquals(g2.getHgt(), 1);
		}catch(PreconditionError e) {
			fail("error init");
			assertTrue(false);
		}
	}
	
	@Test
	public void test1ClimbLeftPrePositif() {
		
		for (int i = 0; i < h; i++) {
			es.setNature(i, 1, Cell.PLT);
		}
		
		env.init(h, w, es);
		env.dig(1, 1);
	
		g1.init(2, 2, env, target);
		g1.goLeft();
		g1.goDown();
		g1.climbLeft();
		
		assertEquals(g1.getWdt(), 0);
		assertEquals(g1.getHgt(), 2);
	}
	
	@Test
	public void test2ClimbLeftPrePositif() {
		
		for (int i = 0; i < h; i++) {
			es.setNature(i, 1, Cell.PLT);
		}
		es.setNature(0, 2, Cell.PLT);
		env.init(h, w, es);
		env.dig(1, 1);
		g1.init(2, 2, env, target);
		g1.goLeft();
		g1.goDown();
		g1.climbLeft();
		assertEquals(g1.getWdt(), 1);
		assertEquals(g1.getHgt(), 1);
	}

	@Test
	public void test1ClimbLeftPreNegatif() {
		try {
			g1.init(1, 1, env, target);
			g1.climbLeft();
			assertTrue(false);
		} catch(PreconditionError e) {
			fail("error climbLeft");
			assertTrue(true);
		}
	}
	
	//climbRight
	
	@Test
	public void test1ClimbRighttPrePositif() {
		
		for (int i = 0; i < h; i++) {
			es.setNature(i, 1, Cell.PLT);
		}
		
		env.init(h, w, es);
		env.dig(1, 1);
	
		g1.init(0, 2, env, target);
		g1.goRight();
		g1.goDown();
		g1.climbRight();
		
		assertEquals(g1.getWdt(), 3);
		assertEquals(g1.getHgt(), 2);
	}
	
	@Test
	public void test2ClimbRightPrePositif() {
		
		for (int i = 0; i < h; i++) {
			es.setNature(i, 1, Cell.PLT);
		}
		es.setNature(2, 2, Cell.MTL);
		env.init(h, w, es);
		env.dig(1, 1);
		g1.init(0, 2, env, target);
		g1.goRight();
		g1.goDown();
		g1.climbRight();
		assertEquals(g1.getWdt(), 1);
		assertEquals(g1.getHgt(), 1);
	}
	
	@Test
	public void test3ClimbRightPrePositif() {
		
		for (int i = 0; i < h; i++) {
			es.setNature(i, 1, Cell.PLT);
		}
		es.setNature(2, 2, Cell.MTL);
		env.init(h, w, es);
		env.dig(1, 1);
		target.init(env, 2, 2);
		g1.init(0, 2, env, target);
		g1.goRight();
		g1.goDown();
		g1.climbRight();
		assertEquals(g1.getWdt(), 1);
		assertEquals(g1.getHgt(), 1);
	}

	@Test
	public void test1ClimbRightPreNegatif() {
		try {
			g1.init(0, 1, env, target);
			g1.climbRight();
			assertTrue(false);
		} catch(PreconditionError e) {
			fail("error climbLeft");
			assertTrue(true);
		}
	}
	
	//step
	
	@Test	
	public void test1StepPositif() {
		g1.init(0, 1, env, target); 
		g1.step();
		assertEquals(g1.getWdt(), 1);
		g1.step();
		assertEquals(g1.getWdt(), 2);
		g1.step();
		assertEquals(g1.getWdt(), 3);
		g1.step();
		assertEquals(g1.getWdt(), 3);
	}
	
	
	@Test	
	public void test2StepPositif() {
		for(int i = 1; i<w; i++) {
			es.setNature(1, i, Cell.LAD);
		}
		es.setNature(2, 3, Cell.MTL);
		env.init(h, w, es);
		target.init(env, 2, 4);
		g1.init(0, 1, env, target); 
		g1.step();
		g1.step();
		g1.step();
		g1.step();		
		assertEquals(g1.getWdt(), 1);
		assertEquals(g1.getHgt(), 4);
	}
	
	@Test
	public void test3StepPositif() {
		
		for (int i = 0; i < h; i++) {
			es.setNature(i, 1, Cell.PLT);
		}
		
		env.init(h, w, es);
		env.dig(1, 1);
		target.init(env, 4, 2);
		g1.init(0, 2, env, target);
		
		int cmp = 0;
		while(cmp <9) {
			System.out.println(g1.getWdt() + " " + g1.getHgt()+ " " + cmp);
			g1.step();
			cmp++;
			
		}
		
		assertEquals(g1.getWdt(), w-2);
		assertEquals(g1.getHgt(), 2);
	}

}
