package loderunner.contrat;

import loderunner.decorator.CharacterDecorator;
import loderunner.services.Cell;
import loderunner.services.CharacterService;
import loderunner.services.EnvironmentService;
import loderunner.services.ScreenService;

public class CharacterContrat extends CharacterDecorator{

	public CharacterContrat(CharacterService delegate) {
		super(delegate);
	}
	
	
	public void checkInvariant() {
		//	inv : getEnvi().getCellNature(getWdt(),getHgt()) in {MLT,HOL,LAD,HDR}
	
		Cell cell = getEnvi().getCellNature(getWdt(),getHgt()) ;
		if(!(cell == Cell.MTL || cell == Cell.HOL || cell == Cell.LAD || cell == Cell.HDR)) {
			
			throw new InvariantError("cell nature error");
		}
	
	
	}
	
	
	public void init(ScreenService s, int x,int y) {
		
		//	pre : getEnvi().getCellNature(x,y) == EMP
		if(getEnvi().getCellNature(x,y) != Cell.EMP) {
			throw new PreconditionError("getEnvi().getCellNature(x,y) != Cell.EMP");

		}
		
		super.init(s,x,y);
		
		//	post : getHgt = y 
		if(!(getHgt()==y)) {
			throw new PostconditionError("getHgt()!=y");
		}
		
		//	post : getWdt = x
		if(!(getWdt()==x)) {
			throw new PostconditionError("getWdt()!=x");
		}		
	}
	
	public void goLeft() {
		
		
		//Capture
		int getHgt_pre = getHgt();
		int getWdt_pre = getWdt();
		
		super.goLeft();
		
		//	post : getHgt() == getHgt()@pre
		if(!(getHgt() == getHgt_pre)) {
			throw new PostconditionError("getHgt() != getHgt_pre");
		}
		
		//	post : getWdt()==0 -> getWdt() == getWdt()@pre
		if(!( getWdt()!=0 || getWdt() == getWdt_pre)) {
			throw new PostconditionError("getWdt()==0 && getWdt() != getWdt_pre");
		}
		
		//	post : getEnvi().getCellNature(getWdt()-1,getHgt()) \in {MLT,PLT,LAD} -> getWdt() == getWdt()@pre
		//	post : getEnvi().getCellNature(getWdt()-1,getHgt()) \notin {LAD,HDR} &&
		//	getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL} &&
		//	* 		  c:Character \notexists \in getEnvi().getCellNature(getWdt()-1,getHgt())
		//	 *        ->
		
	}
	
}
