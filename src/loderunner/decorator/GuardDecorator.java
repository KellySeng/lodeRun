package loderunner.decorator;

import loderunner.services.CharacterService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.Move;
import loderunner.services.ScreenService;

public class GuardDecorator implements GuardService {
	private GuardService delegate;

	

	protected GuardDecorator(GuardService s){
		this.delegate = s;
	}
	
	public EnvironmentService getEnvi() {
		return delegate.getEnvi();
	}

	public int getId() {
		return delegate.getId();
	}

	public int getHgt() {
		return delegate.getHgt();
	}

	public int getWdt() {
		return delegate.getWdt();
	}

	public void init(ScreenService screen, int w, int h) {
		delegate.init(screen, w, h);
	}

	public void goLeft() {
		delegate.goLeft();
	}

	public Move getBehavior() {
		return delegate.getBehavior();
	}

	public CharacterService getTarget() {
		return delegate.getTarget();
	}

	public int getTimeInHole() {
		return delegate.getTimeInHole();
	}

	public void climbLeft() {
		delegate.climbLeft();
	}

	public void goRight() {
		delegate.goRight();
	}

	public void climbRight() {
		delegate.climbRight();
	}

	public void step() {
		delegate.step();
	}

	public void goUp() {
		delegate.goUp();
	}

	public void goDown() {
		delegate.goDown();
	}

	@Override
	public void init(int x, int y, EnvironmentService env, CharacterService target) {
		delegate.init(x, y, env, target);
		
	}


}
