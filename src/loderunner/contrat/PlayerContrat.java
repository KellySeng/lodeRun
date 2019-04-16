package loderunner.contrat;

import java.util.Set;

import loderunner.decorator.PlayerDecorator;
import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.CharacterService;
import loderunner.services.Command;
import loderunner.services.EngineService;
import loderunner.services.PlayerService;

public class PlayerContrat extends PlayerDecorator {
	
	public PlayerContrat(PlayerService delegate) {
		
		super(delegate);
	}
	
	
	public void init(EngineService engine) {
		
		// pre : engine!=null
		if(engine==null) {
			throw new PreconditionError("engine est null");

		}
		super.init(engine);
		// post : getEngine() == engine
		
	}
	
	
	
	public void step() {

		//capture
		Set<CellContent> set =  getEngine().getEnvironment().getCellContent(getWdt(), getHgt()-1);
		boolean havePersonnageEnbas = false;
		for(CellContent c : set) {
			if(c instanceof CharacterService) {
				havePersonnageEnbas = true;
			}
		}

		Cell cell_bas_pre = getEngine().getEnvironment().getCellNature(getWdt(), getHgt()-1);
		Cell cell_bas_gauche_pre = getEngine().getEnvironment().getCellNature(getWdt()-1, getHgt()-1);
		Cell cell_bas_droite_pre = getEngine().getEnvironment().getCellNature(getWdt()+1, getHgt()-1);
		Cell cell_gauche_pre = getEngine().getEnvironment().getCellNature(getWdt()-1, getHgt());
		Cell cell_droite_pre = getEngine().getEnvironment().getCellNature(getWdt()+1, getHgt());


		super.step();

		//post : if getNextCommand() = DigL  	
		if(getEngine().getNextCommand() == Command.DigL 			
				&& (  cell_bas_pre == Cell.MTL 
				|| cell_bas_pre == Cell.PLT 
				||havePersonnageEnbas  ) 
				&& ( cell_gauche_pre == Cell.EMP ||  //la case a sa gauche est libre
				cell_gauche_pre == Cell.LAD ||
				cell_gauche_pre == Cell.HDR ||
				cell_gauche_pre == Cell.HOL )
				&& cell_bas_gauche_pre == Cell.PLT
				) {

			if(getEngine().getEnvironment().getCellNature(getWdt()-1, getHgt()-1) != Cell.HOL) {
				throw new PostconditionError("DigL error");
			}


		}


		//post : if getNextCommand() = DigR  	
		if(getEngine().getNextCommand() == Command.DigR			
				&& (  cell_bas_pre == Cell.MTL 
				|| cell_bas_pre == Cell.PLT 
				||havePersonnageEnbas  ) 
				&& ( cell_droite_pre == Cell.EMP ||  //la case a sa droite est libre
				cell_droite_pre == Cell.LAD ||
				cell_droite_pre == Cell.HDR ||
				cell_droite_pre == Cell.HOL )
				&& cell_bas_droite_pre == Cell.PLT
				) 
		{

			if(getEngine().getEnvironment().getCellNature(getWdt()+1, getHgt()-1) != Cell.HOL) {
				throw new PostconditionError("DigL error");
			}


		}

	}

}
