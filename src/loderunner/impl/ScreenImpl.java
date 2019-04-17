package loderunner.impl;


import loderunner.services.Cell;
import loderunner.services.ScreenService;

public class ScreenImpl implements ScreenService{

	private int height,width;
	protected Cell[][] ecran;
	
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
		return ecran[x][y];
	}

	@Override
	public void init(int h, int w) {
		this.height =h;
		this.width =w;
		ecran = new Cell[h][w];	
		
		for(int i= 0;i<h;i++) {
			for(int j =0;j<w;j++) {
				ecran[i][j] = Cell.EMP;
			}
		}
	}

	@Override
	public void dig(int x, int y) {
		ecran[x][y] = Cell.HOL;
		
	}

	@Override
	public void fill(int x, int y) {
		ecran[x][y] = Cell.PLT;
	}

}
