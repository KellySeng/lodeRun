package loderunner.impl;


import loderunner.services.Cell;
import loderunner.services.ScreenService;

public class ScreenImpl implements ScreenService{

	private int height,width;
	protected Cell[][] ecran;
	

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
		ecran = new Cell[w][h];	
		
		for(int i= 0;i<w;i++) {
			for(int j =0;j<h;j++) {
				
				ecran[i][j] = Cell.EMP;
			}
		}
	}

	@Override
	public void dig(int x, int y) {
		if(ecran[x][y] == Cell.PLT)	ecran[x][y] = Cell.HOL;
		
	}

	@Override
	public void fill(int x, int y) {
		if(ecran[x][y] == Cell.HOL)	ecran[x][y] = Cell.PLT;
	}

}
