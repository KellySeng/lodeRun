package loderunner.contrat;

import java.util.Set;

import loderunner.decorator.GuardDecorator;
import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.CharacterService;
import loderunner.services.GuardService;

public class GuardContrat extends GuardDecorator{

	public GuardContrat(GuardService s) {
		super(s);
	}
	
	public void climbLeft() {
		
		// pre : getEnvi().getCellNature(getWdt(),getHgt()) == HOL
		if(getEnvi().getCellNature(getWdt(),getHgt()) != Cell.HOL) {
			throw new PreconditionError("getEnvi().getCellNature(getWdt(),getHgt()) != Cell.HOL");
		}
		
		//capture
		int wdt_pre = getWdt();
		int hgt_pre = getHgt();

		super.climbLeft();
		
		// post : getWdt() ==0 -> getWdt() == getWdt()@pre  && getHgt() ==  getHgt()@pre
		if(wdt_pre==0) {
			if(!(getWdt() == wdt_pre && getHgt() == hgt_pre)) {
				throw new PostconditionError("wdt_pre==0, climbLeft error ");
			}
			
		}
		
		// post : getEnvi().getCellNature(getWdt()-1,getHgt()+1) \in {MTL,PLT} -> getWdt() == getWdt()@pre && getHgt() == getHgt()@pre

		

	}

	public void climbRight() {
		super.climbRight();
	}
	public void step() {
		
		//capture
		Set<CellContent> set =  getEnvi().getCellContent(getWdt(), getHgt()-1);
		boolean haveCharacterEnbas = false;
		for(CellContent c : set) {
			if(c instanceof CharacterService) {
				haveCharacterEnbas = true;
			}
		}
	
		boolean willFall = (getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.EMP ||
				getEnvi().getCellNature(getWdt(), getHgt()-1) == Cell.HOL ) && !haveCharacterEnbas
				&&  getEnvi().getCellNature(getWdt(), getHgt()) != Cell.LAD 
				&&	getEnvi().getCellNature(getWdt(), getHgt()) != Cell.HDR ;
		
		super.step();
	}
}
