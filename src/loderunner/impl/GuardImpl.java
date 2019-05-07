package loderunner.impl;

import java.util.Set;

import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.CharacterService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.Move;

public class GuardImpl extends CharacterImpl implements GuardService {


	private static int cmp = 0;
	CharacterService target;
	int timeInHole = 1;
	int id;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Move getBehavior() {
		
		Set<CellContent> set =  getEnvi().getCellContent(wdt, hgt-1);
		boolean haveCharacterEnBas = false;
		for(CellContent c : set) {
			if(c instanceof CharacterService) {
				haveCharacterEnBas = true;
			}
		}

		//	Si le garde est dans une echelle
		if(this.getEnvi().getCellNature(wdt, hgt)==Cell.LAD) {
			//	Behaviour renvoie Up si la cible du garde se trouve strictement plus haut que lui
			if(target.getHgt() > hgt) {
				return Move.Up;
			}
			//Down si elle se trouve strictement plus basse
			else if(target.getHgt() < hgt) {
				return Move.Down;
			}
			else {//	 Neutral sinon
				return Move.Neutral;
			}	
		}
		
		//Si le garde est dans un trou sur un rail ou au-dessus d’une case non-libre 
		//ou au-dessus d’une case libre contenant un personnage
		if(getEnvi().getCellNature(wdt, hgt)==Cell.HOL				
			||getEnvi().getCellNature(wdt, hgt)==Cell.HDR
			||
				(getEnvi().getCellNature(wdt, hgt-1)==Cell.MTL 
				||getEnvi().getCellNature(wdt, hgt-1)==Cell.PLT
				||getEnvi().getCellNature(wdt, hgt-1)==Cell.LAD
				||haveCharacterEnBas
					)
			) {
			
			//Behaviour renvoie Left si la cible du garde se trouve strictement plus a gauche que lui
			if(target.getWdt() < wdt) {
				return Move.Left;
			}
			
			//	Right si elle se trouve strictement plus a droite
			else if(target.getWdt() > wdt) {
				return Move.Right;
			} 
			else {
				return Move.Neutral;
			}
			
			
		}
		
		
		//Si le garde est a la fois dans une echelle et au-dessus d’une case non-libre
		if(getEnvi().getCellNature(wdt, hgt)==Cell.LAD
			  &&(getEnvi().getCellNature(wdt, hgt-1)==Cell.MTL 
						||getEnvi().getCellNature(wdt, hgt-1)==Cell.PLT
						||getEnvi().getCellNature(wdt, hgt-1)==Cell.LAD
						||haveCharacterEnBas
				)		
			) {
			int dis_vertical= Math.abs(target.getHgt() - hgt);
			int dis_horizontal= Math.abs(target.getWdt() - wdt);
			

			if(dis_vertical < dis_horizontal ) {
				if(target.getHgt() > hgt) {
					return Move.Up;
				}else if(target.getHgt() < hgt) {
					return Move.Down;
				}else {
					return Move.Neutral;
				}	
			}else if (dis_vertical > dis_horizontal ) {
				if(target.getWdt() < wdt) {
					return Move.Left;
				}else if(target.getWdt() > wdt) {
					return Move.Right;
				}else {
					return Move.Neutral;
				}
			}else {
				return Move.Neutral;
			}
		}
		return null;
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
//		System.out.println("guard climbLeft");

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
		//System.out.println("guard climbRight");
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

		//Si le garde se trouve dans une case qui nâ€™est ni une echelle,ni un rail, ni un trou et
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

			System.out.println("le garde tombe ");

		}
		//Si le garde se trouve dans un trou et que TimeInHole est strictement infeÌ�rieur a 5	 
		else if(getEnvi().getCellNature(x, y) == Cell.HOL && timeInHole<5) {
			timeInHole = timeInHole +1;

		}
		//Si le garde se trouve dans un trou, que TimeInHole vaut 5
		else if(getEnvi().getCellNature(x, y) == Cell.HOL && timeInHole == 5 ) {
			if(getBehavior() == Move.Left) {

				climbLeft();
			}else if(getBehavior() == Move.Right) {
				climbRight();
			}

		}else {		
			switch(getBehavior()) {

				case Right :
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

	

	@Override
	public void init(int x, int y, EnvironmentService env, CharacterService target) {
		hgt = y;
		wdt = x;
		this.env = env;
		this.target = target;
		this.id = cmp;
		cmp++;
	}

	



}
