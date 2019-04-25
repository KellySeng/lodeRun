package loderunner.impl;

import java.util.ArrayList;

import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.CharacterService;
import loderunner.services.EnvironmentService;
import loderunner.services.ScreenService;

public class CharacterImpl implements CharacterService{

	protected int hgt;
	protected int wdt;
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
		hgt = y;
		wdt = x;
		env = (EnvironmentService)screen;
		
	}
	
	private ArrayList<CellContent> getCharacterList(int w, int h) {
		ArrayList <CellContent> list = new ArrayList<>();	
		for(CellContent c : getEnvi().getCellContent(w,h)) {
			

			if(c instanceof CharacterService) list.add(c);
		}
		return list;
	}

	@Override
	public void goLeft() {
		
		ArrayList <CellContent> character_list_wdt_minus_1 = getCharacterList(getWdt()-1,getHgt());	
		ArrayList <CellContent> character_list_hgt_minus_1 = getCharacterList(getWdt(),getHgt()-1);	
		
		if(wdt!= 0 
				   && (env.getCellNature(getWdt()-1,getHgt()) !=  Cell.MTL || env.getCellNature(getWdt()-1,getHgt()) !=  Cell.PLT) 
				   || (env.getCellNature(getWdt(),getHgt()) ==  Cell.LAD || env.getCellNature(getWdt(),getHgt()) !=  Cell.HDR) 
				   || (env.getCellNature(getWdt(),getHgt()-1) !=  Cell.PLT || env.getCellNature(getWdt(),getHgt()-1) !=  Cell.MTL ||
					   env.getCellNature(getWdt(),getHgt()-1) !=  Cell.LAD ) 
				   || (character_list_hgt_minus_1.size() != 0)
				   && !(character_list_wdt_minus_1.size() !=0) ) {
			env.getCellContent(wdt, hgt).remove(this);

			wdt = wdt-1;
			env.getCellContent(wdt, hgt).add(this);

		}
		else {
			return;
		}
	}

	@Override
	public void goRight() {
		

		ArrayList <CellContent> character_list_wdt_plus_1 = getCharacterList(getWdt()+1,getHgt());	
		ArrayList <CellContent> character_list_hgt_minus_1 = getCharacterList(getWdt(),getHgt()-1);	
		

		if(wdt != env.getWidth()
				   && (env.getCellNature(getWdt()+1,getHgt()) !=  Cell.MTL || env.getCellNature(getWdt()+1,getHgt()) !=  Cell.PLT) 
				   || (env.getCellNature(getWdt(),getHgt()) ==  Cell.LAD || env.getCellNature(getWdt(),getHgt()) !=  Cell.HDR) 
				   || (env.getCellNature(getWdt(),getHgt()-1) !=  Cell.PLT || env.getCellNature(getWdt(),getHgt()-1) !=  Cell.MTL ||
					   env.getCellNature(getWdt(),getHgt()-1) !=  Cell.LAD ) 
				   || (character_list_hgt_minus_1.size() != 0)
				   && !(character_list_wdt_plus_1.size() !=0) ) {
			
			env.getCellContent(wdt, hgt).remove(this);
			wdt = wdt+1;
			env.getCellContent(wdt, hgt).add(this);


			
			
		}
		else {
			return;
		}
		
	}

	@Override
	public void goUp() {
		
		ArrayList <CellContent> character_list_hgt_plus_1 = getCharacterList(getWdt(),getHgt()+1);	
		
		if(hgt == env.getHeight() 
		   && (env.getCellNature(wdt,hgt+1) == Cell.LAD)
		   && !(character_list_hgt_plus_1.size() != 0)) {
			env.getCellContent(wdt, hgt).remove(this);

			hgt = hgt+1;
			env.getCellContent(wdt, hgt).add(this);

		  }
		else {
			return;
		}
	}

	@Override
	public void goDown() {
		ArrayList <CellContent> character_list_hgt_minus_1 = getCharacterList(getWdt(),getHgt()-1);	
		
		if((getHgt() != 0) && 
		   (getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.LAD || 
			getEnvi().getCellNature(getWdt(), getHgt()-1) != Cell.MTL ||
			getEnvi().getCellNature(getWdt(), getHgt()-1) != Cell.PLT )
		    && !(character_list_hgt_minus_1.size() != 0)) {
			env.getCellContent(wdt, hgt).remove(this);

			hgt = hgt-1;
			env.getCellContent(wdt, hgt).add(this);

		}
		else {
			return;
		}
	}

}
