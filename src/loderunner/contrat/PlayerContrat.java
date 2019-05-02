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
		
		if(!(getEngine() == engine)) {
			throw new PostconditionError("getEngine != engine");
		}
	}
	
	
	
	public void step() {

		
		//capture
		Set<CellContent> set =  getEnvi().getCellContent(getWdt(), getHgt()-1);
		boolean havePersonnageEnbas = false;
		for(CellContent c : set) {
			if(c instanceof CharacterService) {
				havePersonnageEnbas = true;
			}
		}

		Cell cell_bas_pre = getEnvi().getCellNature(getWdt(), getHgt()-1);
		Cell cell_bas_gauche_pre = getEnvi().getCellNature(getWdt()-1, getHgt()-1);
		Cell cell_bas_droite_pre = getEnvi().getCellNature(getWdt()+1, getHgt()-1);
		Cell cell_gauche_pre = getEnvi().getCellNature(getWdt()-1, getHgt());
		Cell cell_droite_pre = getEnvi().getCellNature(getWdt()+1, getHgt());
//		
//		PlayerService p_goLeft =null;
//		PlayerService p_goRight  =null;
//		PlayerService p_goDown  =null;
//		PlayerService p_goUp  =null;
//		try {
//			p_goLeft = (PlayerService) this.clone();
//			p_goLeft.goLeft();
//			
//			p_goRight = (PlayerService) this.clone();
//			p_goRight.goRight();
//			
//			p_goDown = (PlayerService) this.clone();
//			p_goDown.goDown();
//			
//			p_goUp = (PlayerService) this.clone();
//			p_goUp.goUp();
//			
//		} catch (CloneNotSupportedException e) {
//			e.printStackTrace();
//		}


		super.step();

		System.out.println("apres step : getWdt() = "+getWdt()+" getHgt()" + getHgt());

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

			if(getEnvi().getCellNature(getWdt()-1, getHgt()-1) != Cell.HOL) {
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

			if(getEnvi().getCellNature(getWdt()+1, getHgt()-1) != Cell.HOL) {
				throw new PostconditionError("DigR error");
			}
		}
		
//		//post : if getNextCommand() = Left
//		if(getEngine().getNextCommand() == Command.Left) {
//			if(!(getWdt() != p_goLeft.getWdt() && getHgt() != p_goLeft.getHgt())) {
//				throw new PostconditionError("goLeft error");
//			}
//		}
//		
//		//post : if getNextCommand() = Right
//		if(getEngine().getNextCommand() == Command.Right) {
//			if(!(getWdt() != p_goRight.getWdt() && getHgt() != p_goRight.getHgt())) {
//				throw new PostconditionError("goRight error");
//			}
//		}
//		//post : if getNextCommand() = Up
//		if(getEngine().getNextCommand() == Command.Up) {
//			if(!(getWdt() != p_goUp.getWdt() && getHgt() != p_goUp.getHgt())) {
//				throw new PostconditionError("goUp error");
//			}
//		}
//		//post : if getNextCommand() = Down
//		if(getEngine().getNextCommand() == Command.Down) {
//			if(!(getWdt() != p_goDown.getWdt() && getHgt() != p_goDown.getHgt())) {
//				throw new PostconditionError("goUp error");
//			}
//		}

	}

}