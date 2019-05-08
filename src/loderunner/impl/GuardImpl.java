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
	boolean isSpecial ;

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
		}
		
		//Si le garde est dans un trou sur un rail ou au-dessus d’une case non-libre 
		//ou au-dessus d’une case libre contenant un personnage
		// ou c'est un guard special, et il est au-dessus une case HOL
		if(getEnvi().getCellNature(wdt, hgt)==Cell.HOL				
			||getEnvi().getCellNature(wdt, hgt)==Cell.HDR
			||(getEnvi().getCellNature(wdt, hgt-1)==Cell.MTL 
				||getEnvi().getCellNature(wdt, hgt-1)==Cell.PLT
				||getEnvi().getCellNature(wdt, hgt-1)==Cell.LAD
				||haveCharacterEnBas)
			||( getEnvi().getCellNature(wdt, hgt-1)==Cell.HOL && isSpecial())			
			) {
			
			//Behaviour renvoie Left si la cible du garde se trouve strictement plus a gauche que lui
			if(target.getWdt() < wdt) {		
				return Move.Left;
			}
			
			//	Right si elle se trouve strictement plus a droite
			else if(target.getWdt() > wdt) {
				return Move.Right;
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
				}
			}else if (dis_vertical > dis_horizontal ) {
				if(target.getWdt() < wdt) {
					return Move.Left;
				}else if(target.getWdt() > wdt) {
					return Move.Right;
				}
			}
		}
		return Move.Neutral;
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


		Set<CellContent> setLeft = null;
		boolean hasGuardLeft = false;

		if(x>0) {
			setLeft =  getEnvi().getCellContent(x-1, y);
			for(CellContent c : setLeft) {
				if(c instanceof GuardService) {
					hasGuardLeft = true;
				}
			}	
		}
		
		Set<CellContent> setRight = null;
		boolean hasGuardRight = false;
		if(x+1<getEnvi().getWidth()) {
			setRight = getEnvi().getCellContent(x+1, y);
			for(CellContent c : setRight) {
				if(c instanceof GuardService) {
					hasGuardRight = true;
				}
			}	
		}
		
		Set<CellContent> setEnBas = null;
		boolean havePersonnageEnBas = false;
		if(y>0) {
			setEnBas =  getEnvi().getCellContent(x, y-1);
			for(CellContent c : setEnBas) {
				if(c instanceof CharacterService) {
					havePersonnageEnBas = true;
				}
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
				&& !havePersonnageEnBas
				//Si c'est un garde special,il peut passer au dessus des trous
				&& (!(getEnvi().getCellNature(x, y-1) == Cell.HOL
						&& isSpecial()))
				
				) {				 

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
			}//si il y a un joueur au dessus de trou //gerer Contact
			else if(target.getHgt() == y + 1 && target.getWdt() == x){
				System.out.println("guard go up to catch player");
				goUp();
			}

				// si c'est un garde special et il est au-dessus un trou 
		}else if(isSpecial() && getEnvi().getCellNature(x, y-1) == Cell.HOL) {
			if(getBehavior() == Move.Right
					&& env.getCellNature(getWdt()+1,getHgt()) !=  Cell.MTL 
					&& env.getCellNature(getWdt()+1,getHgt()) !=  Cell.PLT 
					&& wdt!= env.getWidth()-1
					&& !hasGuardRight) {
				env.getCellContent(wdt, hgt).remove(this);
				wdt = wdt+1;
				env.getCellContent(wdt, hgt).add(this);
			}
			if(getBehavior() == Move.Left
					&& env.getCellNature(getWdt()-1,getHgt()) !=  Cell.MTL 
					&& env.getCellNature(getWdt()-1,getHgt()) !=  Cell.PLT 
					&& wdt!= 0
					&& !hasGuardLeft) {
				env.getCellContent(wdt, hgt).remove(this);
				wdt = wdt-1;
				env.getCellContent(wdt, hgt).add(this);
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
	public void init(int x, int y, EnvironmentService env, CharacterService target, boolean estSpecial) {
	
		hgt = y;
		wdt = x;
		this.env = env;
		this.target = target;
		this.id = cmp;
		isSpecial = estSpecial;
		cmp++;
	}

	@Override
	public boolean isSpecial() {
		return isSpecial;
	}


	
	

	



}
