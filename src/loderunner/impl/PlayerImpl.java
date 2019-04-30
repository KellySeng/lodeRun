package loderunner.impl;

import java.util.Set;

import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.CharacterService;
import loderunner.services.EngineService;
import loderunner.services.PlayerService;
import loderunner.services.Triplet;

public class PlayerImpl extends CharacterImpl implements PlayerService {

	EngineService engine;
	


	@Override
	public EngineService getEngine() {
		return engine;
	}

	@Override
	public void init(EngineService e) {
		engine = e;
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
		
		//	Le joueur tombe (comme un garde) quand il ne se trouve pas dans une echelle ou un rail et que la case en ´
		//	dessous de lui est libre et ne contient pas de personnage.	
		 if(getEnvi().getCellNature(x, y) != Cell.LAD
			&&  getEnvi().getCellNature(x, y) != Cell.HDR
			&& ( getEnvi().getCellNature(x, y-1) == Cell.EMP ||
				getEnvi().getCellNature(x, y-1) == Cell.LAD ||
				getEnvi().getCellNature(x, y-1) == Cell.HDR ||
				getEnvi().getCellNature(x, y-1) == Cell.HOL )
			&& !havePersonnageEnBas ) {
			 
			 getEnvi().getCellContent(wdt, hgt).remove(this);

			 hgt = hgt-1;
			 getEnvi().getCellContent(wdt, hgt).add(this);

			 System.out.println("tomber ");
			 
		 }
		
		
		switch(engine.getNextCommand()) {
			

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
			case DigL :
				
				if( (getEnvi().getCellNature(x, y-1) == Cell.MTL 
					|| getEnvi().getCellNature(x, y-1) == Cell.PLT 
					|| havePersonnageEnBas  )  //la case a sa gauche est libre
					&& ( getEnvi().getCellNature(x-1, y) == Cell.EMP ||
							getEnvi().getCellNature(x-1, y) == Cell.LAD ||
									getEnvi().getCellNature(x-1, y) == Cell.HDR ||
											getEnvi().getCellNature(x-1, y) == Cell.HOL )
					&& getEnvi().getCellNature(x-1, y-1) == Cell.PLT
					) {
				
					getEnvi().dig(x-1, y-1);	
					getEngine().getHoles().add(new Triplet(x-1,y-1,0));
				
				}
							
				break;
			case DigR:
				if( (getEnvi().getCellNature(x, y-1) == Cell.MTL 
				|| getEnvi().getCellNature(x, y-1) == Cell.PLT 
				|| havePersonnageEnBas  )  //la case a sa droite est libre
				&& ( getEnvi().getCellNature(x+1, y) == Cell.EMP ||
						getEnvi().getCellNature(x+1, y) == Cell.LAD ||
								getEnvi().getCellNature(x+1, y) == Cell.HDR ||
										getEnvi().getCellNature(x+1, y) == Cell.HOL )
				&& getEnvi().getCellNature(x+1, y-1) == Cell.PLT
				) {
			
					getEnvi().dig(x+1, y-1);	
					getEngine().getHoles().add(new Triplet(x+1,y-1,0));

			
				}
			
				break;
			
		
		
		
		}
		
	}

	
}
