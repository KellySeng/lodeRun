package loderunner.impl;


import java.awt.Point;
import java.util.HashMap;

import loderunner.services.Cell;
import loderunner.services.ScreenService;

public class ScreenImpl implements ScreenService{

	private int height,width;
	
	public ScreenImpl(int h, int w) {
		init(h,w);
	}
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public Cell getCellNature(int x, int y) {
		return null;
	}

	@Override
	public void init(int h, int w) {
		this.height =h;
		this.width =w;
		
		
		
		
	}

	@Override
	public void dig(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fill(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
