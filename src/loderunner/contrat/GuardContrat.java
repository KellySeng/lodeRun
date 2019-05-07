package loderunner.contrat;

import java.util.HashSet;
import java.util.Set;

import loderunner.decorator.GuardDecorator;
import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.CharacterService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.Move;

public class GuardContrat extends GuardDecorator{

	public GuardContrat(GuardService s) {
		super(s);
	}

	public void checkInvariant() {
	
			Set<CellContent> set_bas = new HashSet<CellContent>();
			Set<CellContent> set_haut = new HashSet<CellContent>();
			Set<CellContent> set_left = new HashSet<CellContent>();
			Set<CellContent> set_right =  new HashSet<CellContent>();
			
			
			
			if(getHgt() != 0) set_bas =  getEnvi().getCellContent(getWdt(), getHgt()-1);
			boolean haveCharacter_bas = false;
			for(CellContent c : set_bas) {
				if(c instanceof CharacterService) {
					haveCharacter_bas = true;
				}
			}
	
	
			if(getHgt() != getEnvi().getHeight()-1) set_haut =  getEnvi().getCellContent(getWdt(), getHgt()+1);
			boolean haveCharacter_haut = false;
			for(CellContent c : set_haut) {
				if(c instanceof CharacterService) {
					haveCharacter_haut = true;
				}
			}
			
			if(getWdt() != 0) set_left =  getEnvi().getCellContent(getWdt()-1, getHgt());
			boolean haveCharacter_left = false;
			for(CellContent c : set_left) {
				if(c instanceof CharacterService) {
					haveCharacter_left = true;
				}
			}
			
			if(getWdt() != getEnvi().getWidth()-1) set_right =  getEnvi().getCellContent(getWdt()+1, getHgt());
			boolean haveCharacter_right = false;
			for(CellContent c : set_right) {
				if(c instanceof CharacterService) {
					haveCharacter_right = true;
				}
			}
		
		
	
			// inv : getEnvi().getCellNature(getWdt(),getHgt()) == LAD 
			//	  		&& getHgt() < getTarget().getHgt()
			//	  		&& (!(getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL}) 
			//	  			|| (\exists c:Character \in getEnvi().getCellContent(getWdt(),getHgt()-1)
			//	  			-> getTarget().getHgt() - getHgt() < |getTarget().getWdt() - getWdt()| )
			//	  		-> getBehavior() = Up
			if(getEnvi().getCellNature(getWdt(),getHgt()) == Cell.LAD
					&& getHgt() < getTarget().getHgt()
					&& ( !(( getEnvi().getCellNature(getWdt(),getHgt()-1)!= Cell.PLT && getEnvi().getCellNature(getWdt(),getHgt()-1)!= Cell.MTL) 
							|| haveCharacter_bas)			
							|| getTarget().getHgt() - getHgt() < Math.abs(getTarget().getWdt() - getWdt()))
					) {
				if(!( getBehavior() == Move.Up)) {
					throw new InvariantError("getBehavior() != Move.Up");
	
				}
	
			}
	
			// inv :  getEnvi().getCellNature(getWdt(),getHgt()) == LAD 
			//	  		&& getHgt() > getTarget().getHgt()
			//		 		&& (!(getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL}) 
			//		  			|| (\exists c:Character \in getEnvi().getCellContent(getWdt(),getHgt()+1)
			//		  			-> getTarget().getHgt() - getHgt() < |getTarget().getWdt() - getWdt()| )
			//		  		-> getBehavior() = Down
			
			if(getEnvi().getCellNature(getWdt(),getHgt()) == Cell.LAD
					&& getHgt() > getTarget().getHgt()
					&&( !(( getEnvi().getCellNature(getWdt(),getHgt()-1)!= Cell.PLT && getEnvi().getCellNature(getWdt(),getHgt()-1)!= Cell.MTL) 
							|| haveCharacter_bas)			
							||  getHgt() - getTarget().getHgt() < Math.abs(getTarget().getWdt() - getWdt()))
					) {
				if(!( getBehavior() == Move.Down)) {
					throw new InvariantError("getBehavior() != Move.Down");
	
				}
			}
			
	
			// inv :  getEnvi().getCellNature(getWdt(),getHgt()) == LAD 
			//	  		&& getWdt() > getTarget().Wdt()
			//		 		&& (!(getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL}) 
			//		  			|| (\exists c:Character \in getEnvi().getCellContent(getWdt(),getHgt()+1)
			//		  			-> getTarget().getHgt() - getHgt() > |getTarget().getWdt() - getWdt()| )
			//		  		-> getBehavior() = Left
			//	
			if(getEnvi().getCellNature(getWdt(),getHgt()) == Cell.LAD
					&& getWdt() > getTarget().getWdt()
					&&( !(( getEnvi().getCellNature(getWdt(),getHgt()-1)!= Cell.PLT && getEnvi().getCellNature(getWdt(),getHgt()-1)!= Cell.MTL) 
							|| haveCharacter_bas)			
							||  getHgt() - getTarget().getHgt() > Math.abs(getTarget().getWdt() - getWdt()))
					) {
				if(!( getBehavior() == Move.Left)) {
					throw new InvariantError("getBehavior() != Move.Left");
	
				}
			}
			
			// inv :  getEnvi().getCellNature(getWdt(),getHgt()) == LAD 
			//	  		&& getWdt() < getTarget().Wdt()
			//		 		&& (!(getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL}) 
			//		  			|| (\exists c:Character \in getEnvi().getCellContent(getWdt(),getHgt()+1)
			//		  			-> getTarget().getHgt() - getHgt() > |getTarget().getWdt() - getWdt()| )
			//		  		-> getBehavior() = Right
			//	
			if(getEnvi().getCellNature(getWdt(),getHgt()) == Cell.LAD
					&& getWdt() < getTarget().getWdt()
					&&( !(( getEnvi().getCellNature(getWdt(),getHgt()-1)!= Cell.PLT && getEnvi().getCellNature(getWdt(),getHgt()-1)!= Cell.MTL) 
							|| haveCharacter_bas)			
							||  getHgt() - getTarget().getHgt() > Math.abs(getTarget().getWdt() - getWdt()))
					) {
				if(!( getBehavior() == Move.Right)) {
					throw new InvariantError("getBehavior() != Move.Right");
	
				}
			}
			
		
			if(((getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HOL ||
			   getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HDR)  ||
				(getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.MTL ||
				getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.PLT || 
				getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.LAD) ||
				haveCharacter_bas) 
					&& 
				getWdt() > getTarget().getWdt()  &&(getEnvi().getCellNature(getWdt()-1, getHgt()) != Cell.MTL ||
						getEnvi().getCellNature(getWdt()-1, getHgt()) != Cell.PLT) && !haveCharacter_left
				) {
				if(!( getBehavior() == Move.Left)) {
					throw new InvariantError("getBehavior() != Move.Left");
				}
			}
	
		
			if(((getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HOL ||
				   getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HDR)  ||
					(getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.MTL ||
					getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.PLT || 
					getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.LAD) ||
					haveCharacter_bas) 
						&& 
					getWdt() < getTarget().getWdt()  &&(getEnvi().getCellNature(getWdt()+1, getHgt()) != Cell.MTL ||
							getEnvi().getCellNature(getWdt()+1, getHgt()) != Cell.PLT) && !haveCharacter_right
					) {
					if(!( getBehavior() == Move.Right)) {
						throw new InvariantError("getBehavior() != Move.Right");
					}
				}
		
			if(((getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HOL ||
				   getEnvi().getCellNature(getWdt(), getHgt()) == Cell.HDR)  ||
					(getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.MTL ||
					getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.PLT || 
					getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.LAD) ||
					haveCharacter_bas) 
						&& 
					getWdt() == getTarget().getWdt()  &&(getEnvi().getCellNature(getWdt()+1, getHgt()) != Cell.MTL ||
							getEnvi().getCellNature(getWdt()+1, getHgt()) != Cell.PLT)
					) {
					if(!( getBehavior() == Move.Neutral)) {
						throw new InvariantError("getBehavior() != Move.Neutral");
					}
				}
			
			if(getEnvi().getCellNature(getWdt(), getHgt()) == Cell.LAD && getHgt() < getTarget().getHgt()) {
				if(!( getBehavior() == Move.Up)) {
					throw new InvariantError("getBehavior() != Move.Up");
				}
			}
			
			if(getEnvi().getCellNature(getWdt(), getHgt()) == Cell.LAD && getHgt() > getTarget().getHgt()) {
				if(!( getBehavior() == Move.Down)) {
					throw new InvariantError("getBehavior() != Move.Down");
				}
			}
			
			if(getEnvi().getCellNature(getWdt(), getHgt()) == Cell.LAD && getHgt() == getTarget().getHgt()) {
				if(!( getBehavior() == Move.Neutral)) {
					throw new InvariantError("getBehavior() != Move.Neutral");
				}
			}
		
		}


	public void climbLeft() {

		// pre : getEnvi().getCellNature(getWdt(),getHgt()) == HOL
		if(getEnvi().getCellNature(getWdt(),getHgt()) != Cell.HOL) {
			throw new PreconditionError("getEnvi().getCellNature(getWdt(),getHgt()) != Cell.HOL");
		}


		//capture
		int wdt_pre = getWdt();
		int hgt_pre = getHgt();

		Set<CellContent> set =  getEnvi().getCellContent(wdt_pre-1, hgt_pre+1);
		boolean haveCharacter_pre = false;
		for(CellContent c : set) {
			if(c instanceof CharacterService) {
				haveCharacter_pre = true;
			}
		}

		Cell cellNature_gauche_haut = getEnvi().getCellNature(wdt_pre-1, hgt_pre+1);

		checkInvariant();
		super.climbLeft();
		checkInvariant();


		// post : getWdt() ==0 -> getWdt() == getWdt()@pre  && getHgt() ==  getHgt()@pre
		if(wdt_pre==0) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre)) {
				throw new PostconditionError("wdt_pre==0, climbLeft error,le guard ne doit pas bouger  ");
			}

		}

		// post : getEnvi().getCellNature(getWdt()-1,getHgt()+1) \in {MTL,PLT} -> getWdt() == getWdt()@pre && getHgt() == getHgt()@pre

		if( cellNature_gauche_haut == Cell.MTL || cellNature_gauche_haut == Cell.PLT) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre)) {
				throw new PostconditionError("Guard ne doit pas bouger !  ");
			}
		}
		// post : \exist c:Character \in getEnvi().getCellContent(getWdt()-1,getHgt()+1) -> getWdt() == getWdt()@pre && getHgt() == getHgt()@pre
		if(haveCharacter_pre) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre)) {
				throw new PostconditionError("Guard ne doit pas bouger !  ");
			}
		}
		// post : getWdt() != 0 &&  !(getEnvi().getCellNature(getWdt()-1,getHgt()+1) \in {MTL,PLT})  &&
		//	!(\exist c:Character \in getEnvi().getCellContent(getWdt()-1,getHgt()+1)
		// ) -> getWdt() == getWdt()@pre -1 && getHgt() == getHgt()@pre +1
		if(wdt_pre != 0 && cellNature_gauche_haut !=Cell.MTL && cellNature_gauche_haut != Cell.PLT && !haveCharacter_pre ) {
			if(!(getWdt() == wdt_pre -1  && getHgt() == hgt_pre + 1)) {
				throw new PostconditionError("Guard climb left error ");
			}

		}



	}

	public void climbRight() {
		// pre : getEnvi().getCellNature(getWdt(),getHgt()) == HOL
		if(getEnvi().getCellNature(getWdt(),getHgt()) != Cell.HOL) {
			throw new PreconditionError("getEnvi().getCellNature(getWdt(),getHgt()) != Cell.HOL");
		}

		//capture
		int wdt_pre = getWdt();
		int hgt_pre = getHgt();

		Set<CellContent> set =  getEnvi().getCellContent(wdt_pre+1, hgt_pre+1);
		boolean haveCharacter_pre = false;
		for(CellContent c : set) {
			if(c instanceof CharacterService) {
				haveCharacter_pre = true;
			}
		}

		Cell cellNature_droite_haut = getEnvi().getCellNature(wdt_pre+1, hgt_pre+1);

		checkInvariant();

		super.climbRight();
		checkInvariant();

		// post : getWdt() ==getEnvi().getWidth() -> getWdt() == getWdt()@pre  && getHgt() ==  getHgt()@pre
		if(wdt_pre == getEnvi().getWidth()) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre)) {
				throw new PostconditionError("wdt_pre==getEnvi().getWidth(), climbRight error,le guard ne doit pas bouger  ");
			}

		}

		// post : getEnvi().getCellNature(getWdt()+1,getHgt()+1) \in {MTL,PLT} -> getWdt() == getWdt()@pre && getHgt() == getHgt()@pre

		if( cellNature_droite_haut == Cell.MTL || cellNature_droite_haut == Cell.PLT) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre)) {
				throw new PostconditionError("Guard ne doit pas bouger !  ");
			}
		}
		// post : \exist c:Character \in getEnvi().getCellContent(getWdt()+1,getHgt()+1) -> getWdt() == getWdt()@pre && getHgt() == getHgt()@pre
		if(haveCharacter_pre) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre)) {
				throw new PostconditionError("Guard ne doit pas bouger !  ");
			}
		}
		// post : getWdt() != getEnvi().getWidth() &&  !(getEnvi().getCellNature(getWdt()+1,getHgt()+1) \in {MTL,PLT})  &&
		//	!(\exist c:Character \in getEnvi().getCellContent(getWdt()+1,getHgt()+1)
		// ) -> getWdt() == getWdt()@pre -1 && getHgt() == getHgt()@pre +1
		if(wdt_pre != getEnvi().getWidth() && cellNature_droite_haut !=Cell.MTL && cellNature_droite_haut != Cell.PLT && !haveCharacter_pre ) {
			if(!(getWdt() == wdt_pre +1  && getHgt() == hgt_pre + 1)) {
				throw new PostconditionError("Guard climb right error ");
			}

		}

	}
	public void step() {

		//capture
		Set<CellContent> setBas = null;
		boolean haveCharacterEnbas = false;

		if(getHgt()>0) {
			setBas =  getEnvi().getCellContent(getWdt(), getHgt()-1);
			for(CellContent c : setBas) {
				if(c instanceof CharacterService) {
					haveCharacterEnbas = true;
				}
			}
		}


		Set<CellContent> setHaut =  getEnvi().getCellContent(getWdt(), getHgt()+1);
		boolean haveCharacterEnHaut = false;
		for(CellContent c : setHaut) {
			if(c instanceof CharacterService) {
				haveCharacterEnHaut = true;
			}
		}

		Set<CellContent> setGauche  = null;
		boolean haveCharacterEnGauche = false;

		if(getWdt()>0) {
			 setGauche =  getEnvi().getCellContent(getWdt()-1, getHgt());
			for(CellContent c : setGauche) {
				if(c instanceof CharacterService) {
					haveCharacterEnGauche = true;
				}
			}
		}

		Set<CellContent> setDroite =  getEnvi().getCellContent(getWdt()+1, getHgt());
		boolean haveCharacterEnDroite = false;
		for(CellContent c : setDroite) {
			if(c instanceof CharacterService) {
				haveCharacterEnDroite = true;
			}
		}
		int hgt_pre = getHgt();
		int wdt_pre = getWdt();


		boolean willFall = (getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.EMP ||
				getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.HOL ) && !haveCharacterEnbas
				&&  getEnvi().getCellNature(getWdt(), getHgt()) != Cell.LAD 
				&&	getEnvi().getCellNature(getWdt(), getHgt()) != Cell.HDR ;

		boolean willGoUp =(getBehavior() == Move.Up 
				&& getHgt() < getEnvi().getHeight() 
				&& (getEnvi().getCellNature(getWdt(),getHgt()+1) == Cell.LAD || getEnvi().getCellNature(getWdt(),getHgt()+1) == Cell.EMP 
				|| getEnvi().getCellNature(getWdt(),getHgt()+1) == Cell.HDR || getEnvi().getCellNature(getWdt(),getHgt()+1) == Cell.HOL )
				& ! haveCharacterEnHaut ) ;

		boolean willGoDown = (getBehavior() == Move.Down
				&& getHgt() != 0
				&& (getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.LAD || getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.EMP 
				|| getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.HDR || getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.HOL )
				& ! haveCharacterEnbas);

		boolean willGoLeft = (getBehavior() == Move.Left
				&& getWdt()!=0
				&& getEnvi().getCellNature(getWdt()-1,getHgt()) != Cell.MTL
				&& getEnvi().getCellNature(getWdt()-1,getHgt()) != Cell.PLT
				&& getEnvi().getCellNature(getWdt(),getHgt()) != Cell.HOL
				&& (getEnvi().getCellNature(getWdt(),getHgt()) == Cell.LAD
				||getEnvi().getCellNature(getWdt(),getHgt()) == Cell.HDR
				|| getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.PLT
				|| getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.MTL
				|| getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.LAD
				|| haveCharacterEnbas				
						)
				&& !haveCharacterEnGauche
				);

		boolean willGoRight = (getBehavior() == Move.Right
				&& getWdt()< getEnvi().getWidth()
				&& getEnvi().getCellNature(getWdt()+1,getHgt()) != Cell.MTL
				&& getEnvi().getCellNature(getWdt()+1,getHgt()) != Cell.PLT
				&& getEnvi().getCellNature(getWdt(),getHgt()) != Cell.HOL
				&& (getEnvi().getCellNature(getWdt(),getHgt()) == Cell.LAD
				||getEnvi().getCellNature(getWdt(),getHgt()) == Cell.HDR
				|| getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.PLT
				|| getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.MTL
				|| getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.LAD
				|| haveCharacterEnbas				
						)
				&& !haveCharacterEnDroite
				);


		super.step();
		
		//Post: if willFall
		if(willFall) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre-1)) {
				throw new PostconditionError("willFall error");
			}
		}
		//post : if willGoLeft
		if(willGoLeft) {
			if(!(getWdt() == wdt_pre-1 && getHgt() == hgt_pre)) {
				throw new PostconditionError("guard goLeft error");
			}
		}

		//post : if willGoRight
		if(willGoRight) {
			if(!(getWdt() == wdt_pre+1 && getHgt() == hgt_pre)) {
				throw new PostconditionError("guard goRight error");
			}
		}
		//post : if willGoUp
		if(willGoUp) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre+1)) {
				throw new PostconditionError("guard goUp error");
			}
		}
		//post : if willGoDown
		if(willGoDown) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre-1)) {
				throw new PostconditionError("guard goDown error");
			}
		}
		
	}

	@Override
	public void init(int x, int y, EnvironmentService env, CharacterService target) {
		
		if(!(env.getCellNature(x, y) == Cell.EMP)) {
			throw new PreconditionError("error init precondition");
		}
		
		super.init(x, y, env, target);
		
		
		if(!(getTarget() == target)) {
			throw new PostconditionError("error init post");
		}
	}
}
