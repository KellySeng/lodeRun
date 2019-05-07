package loderunner.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import loderunner.contrat.CharacterContrat;
import loderunner.contrat.PreconditionError;
import loderunner.impl.CharacterImpl;
import loderunner.impl.EditableScreenImpl;
import loderunner.impl.EnvironmentImpl;
import loderunner.services.Cell;
import loderunner.services.CharacterService;
import loderunner.services.EditableScreenService;
import loderunner.services.EnvironmentService;

public class TestCharacter {
	
	CharacterService c;
	EnvironmentService env;
	EditableScreenService es;
	private int h = 5;
	private int w = 5;
	
	
	@Before
	public void beforeTests() {
		c = new CharacterContrat(new CharacterImpl());
		env = new EnvironmentImpl();
		es = new EditableScreenImpl();
		es.init(h, w);
		env.init(es.getWidth(), es.getHeight(),es);
	}
	
	@After
	public void afterTests() {
		c = null;
		env = null;
		es = null;
	}
	

	//init
	
	@Test
	public void test1InitPrePositif() {
		try {
			c.init(env, 0,1);
			assertTrue(true);
		}
		catch(PreconditionError e) {
			fail("init error");
			
		}
	}
	
	
	@Test
	public void test1InitPreNegatif() {
		try {
			c.init(env, 0,0);
			assertTrue(true);
		}
		catch(PreconditionError e) {
			fail("init error");
			assertTrue(false);
		}
	}
	
	@Test
	public void test1InitPositif() {
		c.init(env,0,1);
		assertEquals(c.getEnvi(), env);
		assertEquals(c.getWdt(), 0);
		assertEquals(c.getHgt(), 1);
	}
	
	//GoLeft
	@Test
	public void test1GoLeftPositif() {
		c.init(env, 0, 1);
		assertEquals(c.getWdt(), 0);
		assertEquals(c.getHgt(), 1);
		c.goLeft();
		assertEquals(c.getWdt(), 0);
		assertEquals(c.getHgt(), 1);
	}

	
	@Test
	public void test2GoLeftPositif() {
		c.init(env, 1, 1);
		assertEquals(c.getWdt(), 1);
		assertEquals(c.getHgt(), 1);
		c.goLeft();
		assertEquals(c.getWdt(), 0);
		assertEquals(c.getHgt(), 1);
	}
	
	@Test
	public void test3GoLeftPositif() {
		es.setNature(0, 1, Cell.PLT);
		env.init(h,w,es);
		c.init(env, 1, 1);
		c.goLeft();
		assertEquals(c.getWdt(), 1);
		assertEquals(c.getHgt(), 1);
	}
	
	@Test
	public void test4GoLeftPositif() {
		c.init(env, 2, 2);
		c.goLeft();
		assertEquals(c.getWdt(), 2);
		assertEquals(c.getHgt(), 2);
	}
	
	
	@Test
	public void test5GoLeftPositif() {
		env.getCellContent(2,1).add(new CharacterImpl());
		c.init(env, 2, 2);
		c.goLeft();
		assertEquals(c.getWdt(), 1);
		assertEquals(c.getHgt(), 2);
	}
	
	
	@Test
	public void test6GoLeftPositif() {
		es.setNature(3, 2, Cell.LAD);
		es.setNature(4, 1, Cell.PLT);
		env.init(h,w,es);
		c.init(env, 4, 2);
		c.goLeft();
		assertEquals(c.getWdt(), 3);
		assertEquals(c.getHgt(), 2);
		c.goLeft();
		assertEquals(c.getWdt(), 2);
		assertEquals(c.getHgt(), 2);
	}
	
	@Test
	public void test7GoLeftPositif() {
		es.setNature(3, 2, Cell.HDR);
		es.setNature(4, 1, Cell.PLT);
		env.init(h,w,es);
		c.init(env, 4, 2);
		c.goLeft();
		assertEquals(c.getWdt(), 3);
		assertEquals(c.getHgt(), 2);
		c.goLeft();
		assertEquals(c.getWdt(), 2);
		assertEquals(c.getHgt(), 2);
	}
	
	
	
	//goRight

		@Test
		public void test1GoRightPositif() {
			c.init(env, w-1, 1);
			c.goRight();
			assertEquals(c.getWdt(), w-1);
			assertEquals(c.getHgt(), 1);
		}
		

		@Test
		public void test2GoRightPositif() {
			c.init(env, 0, 1);
			c.goRight();
			assertEquals(c.getWdt(), 1);
			assertEquals(c.getHgt(), 1);
		}
		
		@Test
		public void test3GoRightPositif() {
			es.setNature(1, 1, Cell.PLT);
			env.init(h,w,es);
			c.init(env, 0, 1);
			c.goRight();
			assertEquals(c.getWdt(), 0);
			assertEquals(c.getHgt(), 1);
		}
		
		@Test
		public void test4GoRightPositif() {
			c.init(env, 2, 2);
			c.goRight();
			assertEquals(c.getWdt(), 2);
			assertEquals(c.getHgt(), 2);
		}
		
		
		@Test
		public void test5GoRightPositif() {
			env.getCellContent(2,1).add(new CharacterImpl());
			c.init(env, 2, 2);
			c.goRight();
			assertEquals(c.getWdt(), 3);
			assertEquals(c.getHgt(), 2);
		}
		
		@Test
		public void test6GoRightPositif() {
			es.setNature(3, 2, Cell.LAD);
			es.setNature(2, 1, Cell.PLT);
			env.init(h,w,es);
			c.init(env, 2, 2);
			c.goRight();
			assertEquals(c.getWdt(), 3);
			assertEquals(c.getHgt(), 2);
			c.goRight();
			assertEquals(c.getWdt(), 4);
			assertEquals(c.getHgt(), 2);
		}
		
		@Test
		public void test7GoRightPositif() {
			es.setNature(3, 2, Cell.HDR);
			es.setNature(2, 1, Cell.PLT);
			env.init(h,w,es);
			c.init(env, 2, 2);
			c.goRight();
			assertEquals(c.getWdt(), 3);
			assertEquals(c.getHgt(), 2);
			c.goRight();
			assertEquals(c.getWdt(), 4);
			assertEquals(c.getHgt(), 2);
		}
		
		
		//goUp
		
		@Test
		public void test1GoUpPositif() {
			c.init(env,0, 1);
			c.goUp();
			assertEquals(c.getWdt(), 0);
			assertEquals(c.getHgt(), 1);
		}
		
		@Test
		public void test2GoUpPositif() {
			es.setNature(0, 1, Cell.LAD);
			es.setNature(0, 2, Cell.LAD);
			es.setNature(0, 3, Cell.LAD);
			env.init(h,w,es);
			c.init(env, 1, 1);
			c.goLeft();
			c.goUp();//2
			c.goUp();//3
			c.goUp();//4
			c.goUp(); //ne peut plus monter
			assertEquals(c.getWdt(), 0);
			assertEquals(c.getHgt(), 4);
		}
		
		
		@Test
		public void test3GoUpPositif() {
			es.setNature(0, 1, Cell.LAD);
			es.setNature(0, 2, Cell.LAD);
			es.setNature(0, 3, Cell.PLT);
			env.init(h,w,es);
			c.init(env, 1, 1);
			c.goLeft();
			c.goUp();//2
			c.goUp();//3
			assertEquals(c.getWdt(), 0);
			assertEquals(c.getHgt(), 2);
		}
}
