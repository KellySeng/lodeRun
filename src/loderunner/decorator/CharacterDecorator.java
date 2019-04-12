package loderunner.decorator;

import loderunner.services.CharacterService;
import loderunner.services.EnvironmentService;
import loderunner.services.ScreenService;

public class CharacterDecorator implements CharacterService{

	private CharacterService delegate;

	protected CharacterDecorator(CharacterService s){
		this.delegate = s;
	}
	public void init(ScreenService screen, int w, int h) {
		delegate.init(screen, w, h);
	}

	public EnvironmentService getEnvi() {
		return delegate.getEnvi();
	}

	public int getHgt() {
		return delegate.getHgt();
	}

	public int getWdt() {
		return delegate.getWdt();
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

	

}
