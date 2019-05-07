package loderunner.decorator;

import java.util.HashSet;

import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.EditableScreenService;
import loderunner.services.EnvironmentService;

public class EnvironmentDecorator implements EnvironmentService{
	private EnvironmentService delegate;

	public EnvironmentDecorator(EnvironmentService s){
		this.delegate =s;
	}

	@Override
	public HashSet<CellContent> getCellContent(int x, int y) {
		return delegate.getCellContent(x, y);
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
	public void init(int h, int w, EditableScreenService e) {
		delegate.init(h, w, e);
	}

}
