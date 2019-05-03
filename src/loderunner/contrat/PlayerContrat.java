package loderunner.contrat;

import java.util.Set;

import loderunner.decorator.PlayerDecorator;
import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.CharacterService;
import loderunner.services.Command;
import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.PlayerService;

public class PlayerContrat extends PlayerDecorator {

	public PlayerContrat(PlayerService delegate) {

		super(delegate);
	}

	@Override
	public void init(EnvironmentService env, int x, int y, EngineService engine) {


		// pre : engine!=null
		if(engine==null) {
			throw new PreconditionError("engine est null");

		}

		//pre :getCellNature(x, y) == Cell.EMP
		if(getEnvi().getCellNature(x, y)!=Cell.EMP) {
			throw new PreconditionError("la case d'initialisation n'est pas empty");

		}
		super.init(env, x, y,engine );
		// post : getEngine() == engine

		if(!(getEngine() == engine)) {
			throw new PostconditionError("getEngine != engine");
		}

	}



	public void step() {


		//capture
		Set<CellContent> setBas =  getEnvi().getCellContent(getWdt(), getHgt()-1);
		boolean haveCharacterEnbas = false;
		for(CellContent c : setBas) {
			if(c instanceof CharacterService) {
				haveCharacterEnbas = true;
			}
		}


		Set<CellContent> setHaut =  getEnvi().getCellContent(getWdt(), getHgt()+1);
		boolean haveCharacterEnHaut = false;
		for(CellContent c : setHaut) {
			if(c instanceof CharacterService) {
				haveCharacterEnHaut = true;
			}
		}

		Set<CellContent> setGauche =  getEnvi().getCellContent(getWdt()-1, getHgt());
		boolean haveCharacterEnGauche = false;
		for(CellContent c : setGauche) {
			if(c instanceof CharacterService) {
				haveCharacterEnGauche = true;
			}
		}

		Set<CellContent> setDroite =  getEnvi().getCellContent(getWdt()-1, getHgt());
		boolean haveCharacterEnDroite = false;
		for(CellContent c : setDroite) {
			if(c instanceof CharacterService) {
				haveCharacterEnDroite = true;
			}
		}
		int hgt_pre = getHgt();
		int wdt_pre = getWdt();

		Cell cell_bas_pre = getEnvi().getCellNature(getWdt(), getHgt()-1);
		Cell cell_bas_gauche_pre = getEnvi().getCellNature(getWdt()-1, getHgt()-1);
		Cell cell_bas_droite_pre = getEnvi().getCellNature(getWdt()+1, getHgt()-1);
		Cell cell_gauche_pre = getEnvi().getCellNature(getWdt()-1, getHgt());
		Cell cell_droite_pre = getEnvi().getCellNature(getWdt()+1, getHgt());


		boolean willFall = (getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.EMP ||
				getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.HOL ) && !haveCharacterEnbas
				&&  getEnvi().getCellNature(getWdt(), getHgt()) != Cell.LAD 
				&&	getEnvi().getCellNature(getWdt(), getHgt()) != Cell.HDR ;

		boolean willGoUp =( getEngine().getNextCommand() == Command.Up 
				&& getHgt() < getEnvi().getHeight() 
				&& (getEnvi().getCellNature(getWdt(),getHgt()+1) == Cell.LAD || getEnvi().getCellNature(getWdt(),getHgt()+1) == Cell.EMP 
				|| getEnvi().getCellNature(getWdt(),getHgt()+1) == Cell.HDR || getEnvi().getCellNature(getWdt(),getHgt()+1) == Cell.HOL )
				& ! haveCharacterEnHaut ) ;

		boolean willGoDown = (getEngine().getNextCommand() == Command.Down
				&& getHgt() != 0
				&& (getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.LAD || getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.EMP 
				|| getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.HDR || getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.HOL )
				& ! haveCharacterEnbas);

		boolean willGoLeft = (getEngine().getNextCommand() == Command.Left
				&& getWdt()!=0
				&& getEnvi().getCellNature(getWdt()-1,getHgt()) != Cell.MTL
				&& getEnvi().getCellNature(getWdt()-1,getHgt()) != Cell.PLT
				&& (getEnvi().getCellNature(getWdt(),getHgt()) == Cell.LAD
				||getEnvi().getCellNature(getWdt(),getHgt()) == Cell.HDR
				|| getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.PLT
				|| getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.MTL
				|| getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.LAD
				|| haveCharacterEnbas				
						)
				&& !haveCharacterEnGauche
				);

		boolean willGoRight = (getEngine().getNextCommand() == Command.Right
				&& getWdt()< getEnvi().getWidth()
				&& getEnvi().getCellNature(getWdt()+1,getHgt()) != Cell.MTL
				&& getEnvi().getCellNature(getWdt()+1,getHgt()) != Cell.PLT
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

		//		System.out.println("apres step : getWdt() = "+getWdt()+" getHgt()" + getHgt());

		//post : if getNextCommand() = DigL  	
		if(getEngine().getNextCommand() == Command.DigL 			
				&& (  cell_bas_pre == Cell.MTL 
				|| cell_bas_pre == Cell.PLT 
				||haveCharacterEnbas  ) 
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
				||haveCharacterEnbas  ) 
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

		//Post: if willFall
		if(willFall) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre-1)) {
				throw new PostconditionError("willFall error");
			}
		}
		//post : if getNextCommand() = Left
		if(willGoLeft) {
			if(!(getWdt() == wdt_pre-1 && getHgt() == hgt_pre)) {
				throw new PostconditionError("goLeft error");
			}
		}

		//post : if getNextCommand() = Right
		if(willGoRight) {
			if(!(getWdt() == wdt_pre+1 && getHgt() == hgt_pre)) {
				throw new PostconditionError("goRight error");
			}
		}
		//post : if getNextCommand() = Up
		if(willGoUp) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre+1)) {
				throw new PostconditionError("goUp error");
			}
		}
		//post : if getNextCommand() = Down
		if(willGoDown) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre-1)) {
				throw new PostconditionError("goDown error");
			}
		}
		
		

	}

}