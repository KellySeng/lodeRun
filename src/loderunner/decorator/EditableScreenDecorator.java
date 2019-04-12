package loderunner.decorator;

import loderunner.services.Cell;
import loderunner.services.EditableScreenService;

public class EditableScreenDecorator implements EditableScreenService{

	private EditableScreenService delegate;
	
	protected EditableScreenDecorator(EditableScreenService s) {
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

	@Override
	public boolean isPlayable() {
		return delegate.isPlayable();
	}

	@Override
	public void setNature(int x, int y, Cell c) {
		delegate.setNature(x, y, c);
	}

}
