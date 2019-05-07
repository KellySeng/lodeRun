package loderunner.impl;

import java.util.ArrayList;

import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.CharacterService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.PlayerService;
import loderunner.services.ScreenService;

public class CharacterImpl implements CharacterService{

	protected int hgt;
	protected int wdt;
	protected	EnvironmentService env;
	
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

	public void setPos(int x,int y) {

		getEnvi().getCellContent(wdt, hgt).remove(this);

		this.hgt = y;
		this.wdt = x;
		getEnvi().getCellContent(x, y).add(this);

	}
	@Override
	public void init(ScreenService screen,int x, int y) {
		hgt = y;
		wdt = x;
		env = (EnvironmentService)screen;
		env.getCellContent(x, y).add(this);
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
		
		ArrayList <CellContent> character_list_wdt_minus_1 = new ArrayList<CellContent>();	
		ArrayList <CellContent> character_list_hgt_minus_1 = new ArrayList<CellContent>();	
		
		if(getWdt() >0) character_list_wdt_minus_1 = getCharacterList(getWdt()-1,getHgt());	
		if(getHgt() >0) character_list_hgt_minus_1 = getCharacterList(getWdt(),getHgt()-1);	
		
		
		/*quand il y a un guard a la case gauche, si c'est un player, il peut aller a gauche, sinon il peut pas 
		 * car une case ne peut contenir plus d’un garde,*/
		boolean hasGuardAGuache = false;
		for(CellContent c :character_list_wdt_minus_1) {
			if(c instanceof GuardService) {
				hasGuardAGuache = true;
			}
			
		}
		boolean canGoLeft = true;
		if(hasGuardAGuache) {
			if(this instanceof GuardService) {
				canGoLeft = false;
			}
		}
		
		
		if( wdt!= 0 
			&& env.getCellNature(getWdt()-1,getHgt()) !=  Cell.MTL 
			&& env.getCellNature(getWdt()-1,getHgt()) !=  Cell.PLT 
			&&(	(env.getCellNature(getWdt(),getHgt()) ==  Cell.LAD || env.getCellNature(getWdt(),getHgt()) ==  Cell.HDR) 
				   || (env.getCellNature(getWdt(),getHgt()-1) ==  Cell.PLT || env.getCellNature(getWdt(),getHgt()-1) ==  Cell.MTL 
				   		||
					   env.getCellNature(getWdt(),getHgt()-1) ==  Cell.LAD) 
				   || (character_list_hgt_minus_1.size() > 0)
			   )
		    && canGoLeft) {
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
		
		ArrayList <CellContent> character_list_wdt_plus_1 = new ArrayList<CellContent>();	
		ArrayList <CellContent> character_list_hgt_minus_1 = new ArrayList<CellContent>();	
		
		if(getWdt() < env.getWidth()-1 ) character_list_wdt_plus_1 = getCharacterList(getWdt()+1,getHgt());	
		if(getHgt() >0) character_list_hgt_minus_1 = getCharacterList(getWdt(),getHgt()-1);	
		
		/*quand il y a un guard a la case droite, si c'est un player, il peut aller a droite, sinon il peut pas 
		 * car une case ne peut contenir plus d’un garde,*/
		boolean hasGuardAdroite = false;
		for(CellContent c :character_list_wdt_plus_1) {
			if(c instanceof GuardService) {
				hasGuardAdroite = true;
			}
			
		}
		boolean canGoRight = true;
		if(hasGuardAdroite) {
			if(this instanceof GuardService) {
				canGoRight = false;
			}
		}
				
		if( wdt!= env.getWidth()-1
			&& env.getCellNature(getWdt()+1,getHgt()) !=  Cell.MTL 
			&& env.getCellNature(getWdt()+1,getHgt()) !=  Cell.PLT 
			&&(	(env.getCellNature(getWdt(),getHgt()) ==  Cell.LAD || env.getCellNature(getWdt(),getHgt()) ==  Cell.HDR) 
				   || (env.getCellNature(getWdt(),getHgt()-1) ==  Cell.PLT || env.getCellNature(getWdt(),getHgt()-1) ==  Cell.MTL 
				   		||
					   env.getCellNature(getWdt(),getHgt()-1) ==  Cell.LAD) 
				   || (character_list_hgt_minus_1.size() > 0)
			   )
		    && canGoRight) {
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
		
		ArrayList <CellContent> character_list_hgt_plus_1 = new ArrayList<CellContent>();	
		if(getHgt() < env.getHeight()-1) character_list_hgt_plus_1 = getCharacterList(getWdt(),getHgt()+1);	

		/*quand il y a un guard a la case en haut, si c'est un player, il peut aller en haut, sinon il peut pas 
		 * car une case ne peut contenir plus d’un garde,*/
		boolean hasGuardEnHaut = false;
		for(CellContent c :character_list_hgt_plus_1) {
			if(c instanceof GuardService) {
				hasGuardEnHaut = true;
			}
			
		}
		boolean canGoUp = true;
		if(hasGuardEnHaut) {
			if(this instanceof GuardService) {
				canGoUp = false;
			}
		}
		if(hgt < env.getHeight() -1
		   && env.getCellNature(wdt,hgt) == Cell.LAD
		   && (env.getCellNature(wdt,hgt+1) != Cell.MTL 
		   	  && env.getCellNature(wdt,hgt+1) != Cell.PLT)
		   && canGoUp) {
			
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
		
		/*quand il y a un guard a la case en bas, si this est un player, il peut aller en bas, sinon il peut pas 
		 * car une case ne peut contenir plus d’un garde,*/
		boolean hasGuardEnBas = false;
		for(CellContent c :character_list_hgt_minus_1) {
			if(c instanceof GuardService) {
				hasGuardEnBas = true;
			}
			
		}
		boolean canGoDown = true;
		if(hasGuardEnBas) {
			if(this instanceof GuardService) {
				canGoDown = false;
			}
		}
		
		if((getHgt() != 0) 
		  && (getEnvi().getCellNature(getWdt(), getHgt()-1) != Cell.MTL &&
			getEnvi().getCellNature(getWdt(), getHgt()-1) != Cell.PLT )
		    && canGoDown) {
			env.getCellContent(wdt, hgt).remove(this);
			hgt = hgt-1;
			env.getCellContent(wdt, hgt).add(this);
		}
		else {
			return;
		}
	}

}
