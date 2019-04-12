package loderunner.impl;

import loderunner.contrat.InvariantError;
import loderunner.services.Cell;
import loderunner.services.EditableScreenService;

public class EditableScreenImpl extends ScreenImpl implements EditableScreenService{

	public EditableScreenImpl(int h, int w) {
		super(h, w);
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
