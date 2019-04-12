package loderunner.impl;

import loderunner.services.Cell;
import loderunner.services.CharacterService;
import loderunner.services.EnvironmentService;
import loderunner.services.ScreenService;

public class CharacterImpl implements CharacterService{

	private int hgt,wdt;
	private	EnvironmentService env;
	
	@Override
	public EnvironmentService getEnvi() {
		
		return env;
	}

	@Override
	public int getHgt() {
		return hgt;
	}

	@Override
	public int getWdt() {
		return wdt;
	}

	@Override
	public void init(ScreenService screen,int x, int y) {
		hgt = x;
		wdt = y;
		env = (EnvironmentService)screen;
		
	}

	@Override
	public void goLeft() {
		if(getWdt()==0 || env.getCellNature(getWdt()-1, getHgt())== Cell.MTL ||
				env.getCellNature(getWdt()-1, getHgt())== Cell.PLT ||
				env.getCellNature(getWdt()-1, getHgt())== Cell.LAD ||
				env.getCellNature(getWdt(), getHgt())!= Cell.PLT ||
				env.getCellNature(getWdt(), getHgt())!= Cell.MTL
				) {
			return;
		}else {
			wdt = getWdt()-1;
		}
	}

	@Override
	public void goRight() {
		if(getWdt()==env.getWidth() ||  env.getCellNature(getWdt()+1, getHgt())== Cell.MTL ||
				env.getCellNature(getWdt()+1, getHgt())== Cell.PLT ||
				env.getCellNature(getWdt()+1, getHgt())== Cell.LAD ||
				env.getCellNature(getWdt(), getHgt())!= Cell.PLT ||
				env.getCellNature(getWdt(), getHgt())!= Cell.MTL) {
			return;
			
		}else {
			wdt = getWdt()+1;
		}
		
	}

	@Override
	public void goUp() {
		// TODO Auto-generated method stub
		if() {
			
		}
		else {
			hgt = getHgt()+1;
		}
	}

	@Override
	public void goDown() {
		// TODO Auto-generated method stub
		if() {
			
		}
		else {
			hgt = getHgt()+1;
		}
	}

}
