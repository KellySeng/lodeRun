package loderunner.decorator;

import loderunner.services.Cell;
import loderunner.services.ScreenService;

public class ScreenDecorator implements ScreenService{
	
	private ScreenService delegate;

	protected ScreenDecorator(ScreenService s) {
		this.delegate =s;
	}

	@Override
	public int getHeight() {
		return delegate.getHeight();
	}

	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	@Override
	public Cell getCellNature(int x, int y) {
		return delegate.getCellNature(x, y);
	}

	@Override
	public void init(int h, int w) {
		delegate.init(h, w);
	}

	@Override
	public void dig(int x, int y) {
		delegate.dig(x, y);		
	}

	@Override
	public void fill(int x, int y) {
		delegate.fill(x, y);
	}

}
