package loderunner.decorator;

import java.util.HashSet;

import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.EnvironmentService;

public class EnvironmentDecorator implements EnvironmentService{
	private EnvironmentService delegate;

	EnvironmentDecorator(EnvironmentService s){
		this.delegate = s;
	}
	public int getHeight() {
		return delegate.getHeight();
	}

	public HashSet<CellContent>  cellContent(int x, int y) {
		return delegate.cellContent(x, y);
	}

	public int getWidth() {
		return delegate.getWidth();
	}

	public Cell getCellNature(int x, int y) {
		return delegate.getCellNature(x, y);
	}

	public void init(int h, int w) {
		delegate.init(h, w);
	}

	public void dig(int x, int y) {
		delegate.dig(x, y);
	}

	public void fill(int x, int y) {
		delegate.fill(x, y);
	}
}
