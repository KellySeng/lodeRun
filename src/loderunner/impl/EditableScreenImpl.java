package loderunner.impl;

import loderunner.services.Cell;
import loderunner.services.EditableScreenService;

public class EditableScreenImpl extends ScreenImpl implements EditableScreenService{

	@Override
	public void init(int w,int h) {
		super.init(h, w);
		for (int i = 0; i < w; i++) ecran[i][0] = Cell.MTL;
	}
	
	@Override
	public boolean isPlayable() {
		
		for(int x=0;x<getWidth();x++) {
			for(int y=0;y<getHeight();y++) {
				if(!(getCellNature(x,y) != Cell.HOL)) {
					return false;
				}
			}
		}
		
		for(int x=0;x<getWidth();x++) {
				if(!(getCellNature(x,0) == Cell.MTL)) {
					return false;
				}
		}
		
		return true;
	}

	@Override
	public void setNature(int x, int y, Cell c) {
		ecran[x][y] =c;
	}



}
