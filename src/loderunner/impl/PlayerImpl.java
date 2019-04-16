package loderunner.impl;

import java.util.Set;

import loderunner.contrat.PostconditionError;
import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.CharacterService;
import loderunner.services.Command;
import loderunner.services.EngineService;
import loderunner.services.PlayerService;

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

		Set<CellContent> set =  getEngine().getEnvironment().getCellContent(getWdt(), getHgt()-1);
		boolean havePersonnageEnBas = false;
		for(CellContent c : set) {
			if(c instanceof CharacterService) {
				havePersonnageEnBas = true;
			}
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
				
				if( (getEngine().getEnvironment().getCellNature(getWdt(), getHgt()-1) == Cell.MTL 
					|| getEngine().getEnvironment().getCellNature(getWdt(), getHgt()-1) == Cell.PLT 
					|| havePersonnageEnBas  )  //la case a sa gauche est libre
					&& ( getEngine().getEnvironment().getCellNature(getWdt()-1, getHgt()) == Cell.EMP ||
						 getEngine().getEnvironment().getCellNature(getWdt()-1, getHgt()) == Cell.LAD ||
						 getEngine().getEnvironment().getCellNature(getWdt()-1, getHgt()) == Cell.HDR ||
						 getEngine().getEnvironment().getCellNature(getWdt()-1, getHgt()) == Cell.HOL )
					&& getEngine().getEnvironment().getCellNature(getWdt()-1, getHgt()-1) == Cell.PLT
					) {
				
					getEngine().getEnvironment().dig(getWdt()-1, getHgt()-1);				
				
				}
							
				break;
			case DigR:
				if( (getEngine().getEnvironment().getCellNature(getWdt(), getHgt()-1) == Cell.MTL 
				|| getEngine().getEnvironment().getCellNature(getWdt(), getHgt()-1) == Cell.PLT 
				|| havePersonnageEnBas  )  //la case a sa droite est libre
				&& ( getEngine().getEnvironment().getCellNature(getWdt()+1, getHgt()) == Cell.EMP ||
					 getEngine().getEnvironment().getCellNature(getWdt()+1, getHgt()) == Cell.LAD ||
					 getEngine().getEnvironment().getCellNature(getWdt()+1, getHgt()) == Cell.HDR ||
					 getEngine().getEnvironment().getCellNature(getWdt()+1, getHgt()) == Cell.HOL )
				&& getEngine().getEnvironment().getCellNature(getWdt()+1, getHgt()-1) == Cell.PLT
				) {
			
				getEngine().getEnvironment().dig(getWdt()+1, getHgt()-1);				
			
				}
			
				break;
			
		
		
		
		}
		
	}

	
}
