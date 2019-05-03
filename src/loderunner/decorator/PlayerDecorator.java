package loderunner.decorator;

import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.PlayerService;
import loderunner.services.ScreenService;

public class PlayerDecorator implements PlayerService{
	private PlayerService delegate;

	
	protected PlayerDecorator(PlayerService s){
		this.delegate = s;
	}
	
	public EnvironmentService getEnvi() {
		return delegate.getEnvi();
	}

	public EngineService getEngine() {
		return delegate.getEngine();
	}

	

	public int getHgt() {
		return delegate.getHgt();
	}

	public int getWdt() {
		return delegate.getWdt();
	}

	public void step() {
		delegate.step();
	}

	public void init(ScreenService screen, int w, int h) {
		delegate.init(screen, w, h);
	}

	public void goLeft() {
		delegate.goLeft();
	}

	public void goRight() {
		delegate.goRight();
	}

	public void goUp() {
		delegate.goUp();
	}

	public void goDown() {
		delegate.goDown();
	}

	@Override
	public void init(EnvironmentService env, int x, int y, EngineService e) {
		delegate.init(env, x, y, e);
		
	}

}
