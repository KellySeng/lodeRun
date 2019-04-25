package loderunner.impl;

import java.util.Set;

import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.CharacterService;
import loderunner.services.GuardService;
import loderunner.services.Move;

public class GuardImpl extends CharacterImpl implements GuardService {


	int id = 0;
	CharacterService target;
	int timeInHole = 0;
	Move behaviour;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Move getBehavior() {
		return behaviour;
	}

	@Override
	public CharacterService getTarget() {
		return target;
	}

	@Override
	public int getTimeInHole() {
		return timeInHole;
	}



	@Override
	public void climbLeft() {
		int x = getWdt();
		int y = getHgt();

		Set<CellContent> set =  getEnvi().getCellContent(x-1, y+1);
		boolean haveCharacter = false;
		for(CellContent c : set) {
			if(c instanceof CharacterService) {
				haveCharacter = true;
			}
		}

		if(getWdt()>0 && getEnvi().getCellNature(x-1, y+1) != Cell.MTL 
				&& getEnvi().getCellNature(x-1, y+1) != Cell.PLT && !haveCharacter) {

			getEnvi().getCellContent(wdt,hgt).remove(this);
			wdt = wdt - 1;
			hgt = hgt + 1;
			getEnvi().getCellContent(wdt,hgt).add(this);

		}		
	}

	@Override
	public void climbRight() {
		int x = getWdt();
		int y = getHgt();

		Set<CellContent> set =  getEnvi().getCellContent(x+1, y+1);
		boolean haveCharacter = false;
		for(CellContent c : set) {
			if(c instanceof CharacterService) {
				haveCharacter = true;
			}
		}

		if(getWdt()<getEnvi().getWidth() && getEnvi().getCellNature(x+1, y+1) != Cell.MTL 
				&& getEnvi().getCellNature(x+1, y+1) != Cell.PLT && !haveCharacter) {

			getEnvi().getCellContent(wdt,hgt).remove(this);
			wdt = wdt + 1;
			hgt = hgt + 1;
			getEnvi().getCellContent(wdt,hgt).add(this);

		}

	}

	@Override
	public void step() {
		int x = getWdt();
		int y = getHgt();

		Set<CellContent> set =  getEnvi().getCellContent(x, y-1);
		boolean havePersonnageEnBas = false;
		for(CellContent c : set) {
			if(c instanceof CharacterService) {
				havePersonnageEnBas = true;
			}
		}		

		//Si le garde se trouve dans une case qui n’est ni une echelle,ni un rail, ni un trou et
		// que la case en dessous de lui est libre, le garde tombe.
		if(getEnvi().getCellNature(x, y) != Cell.LAD
				&&  getEnvi().getCellNature(x, y) != Cell.HDR
				&&  getEnvi().getCellNature(x, y) != Cell.HOL
				&& ( getEnvi().getCellNature(x, y-1) == Cell.EMP ||
				getEnvi().getCellNature(x, y-1) == Cell.HDR ||
				getEnvi().getCellNature(x, y-1) == Cell.HOL )
				&& !havePersonnageEnBas ) {				 

			getEnvi().getCellContent(wdt, hgt).remove(this);

			hgt = hgt-1;
			getEnvi().getCellContent(wdt, hgt).add(this);

			System.out.println("le guarde tomber ");

		}
		//Si le garde se trouve dans un trou et que TimeInHole est strictement inférieur a 5	 
		else if(getEnvi().getCellNature(x, y) == Cell.HOL && timeInHole<5) {
			timeInHole = timeInHole -1;

		}
		//Si le garde se trouve dans un trou, que TimeInHole vaut 5
		else if(getEnvi().getCellNature(x, y) == Cell.HOL && timeInHole == 5 ) {
			if(behaviour == Move.Left) {

				climbLeft();
			}else if(behaviour == Move.Right) {
				climbRight();
			}

		}else {		
			switch(behaviour) {

				case  Right :
					goRight();
					break;
				case Left :
					goLeft();
					break;
		
				case Up :
					goUp();
					break;
				case Down : 
					goDown();
					break;
				case Neutral :
					break;
			}

		}


	}

	



}
