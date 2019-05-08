package loderunner.test;

import loderunner.services.EngineService;

import org.junit.After;
import org.junit.Before;


public abstract class AbstractJeuTest {

	private EngineService engine;

	
	
	protected AbstractJeuTest() {
		engine = null;
	}
	
	
	protected final void setEngine(EngineService engine) {
		this.engine = engine;
	}
	
	protected final EngineService getEngine() {
		return engine;
	}
	
	
	@Before
	public abstract void beforeTests(); 

	@After
	public abstract void afterTests();
	
}
