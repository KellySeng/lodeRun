package loderunner.impl;

import java.util.HashSet;

import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.EnvironmentService;

public class EnvironmentImpl implements EnvironmentService{

	private int height,width;
	private Cell[][] ecran;
//	private HashSet<CellContent>[][] ecran_content;
	
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
//		ecran_content = new HashSet<CellContent>[h][w] ;
	}

	@Override
	public void dig(int x, int y) {
		ecran[x][y] = Cell.HOL;
		
	}

	@Override
	public void fill(int x, int y) {
		ecran[x][y] = Cell.PLT;
		
	}

	@Override
	public HashSet<CellContent> cellContent(int x, int y) {
		return null;
	}
	

}
