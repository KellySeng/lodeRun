package loderunner.contrat;

import java.util.ArrayList;

import loderunner.decorator.CharacterDecorator;
import loderunner.services.Cell;
import loderunner.services.CellContent;
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
		
		checkInvariant();
		
		super.init(s,x,y);
		
		
		checkInvariant();
		
		//	post : getHgt = y 
		if(!(getHgt()==y)) {
			throw new PostconditionError("getHgt()!=y");
		}
		
		//	post : getWdt = x
		if(!(getWdt()==x)) {
			throw new PostconditionError("getWdt()!=x");
		}		
	}
	
	private ArrayList<CellContent> getCharacterList(int w, int h) {
		ArrayList <CellContent> list = new ArrayList<>();	
		for(CellContent c : getEnvi().getCellContent(w,h)) {
			if(c instanceof CharacterService) list.add(c);
		}
		return list;
	}
	
	public void goLeft() {
		
		
		//Capture
		int getHgt_pre = getHgt();
		int getWdt_pre = getWdt();
		
		ArrayList <CellContent> character_list_wdt_minus_1 = getCharacterList(getWdt()-1,getHgt());	
		ArrayList <CellContent> character_list_hgt_minus_1 = getCharacterList(getWdt(),getHgt()-1);	
	
		checkInvariant();
		
		super.goLeft();
		
		checkInvariant();
		
		//	post : getHgt() == getHgt()@pre
		if(!(getHgt() == getHgt_pre)) {
			throw new PostconditionError("CharacterContrat post goLeft: getHgt() != getHgt_pre");
		}
		
		//	post : getWdt()==0 -> getWdt() == getWdt()@pre
		if(!( getWdt()!=0 || getWdt() == getWdt_pre)) {
			throw new PostconditionError("CharacterContrat post goLeft: getWdt()==0 && getWdt() != getWdt_pre");
		}
		
		
		//post : getEnvi().getCellNature(getWdt()-1,getHgt()) \in {MTL,PLT,LAD} -> getWdt() == getWdt()@pre
		if(getEnvi().getCellNature(getWdt()-1,getHgt()) == Cell.MTL ||
		   getEnvi().getCellNature(getWdt()-1,getHgt()) == Cell.PLT ||
		   getEnvi().getCellNature(getWdt()-1,getHgt()) == Cell.LAD ){
			if(!(getWdt() == getWdt_pre)) {
				throw new PostconditionError("CharacterContrat post goLeft: getWdt() != getWdt_pre");
			}
		}
		
	
		
		
		/*post : getEnvi().getCellNature(getWdt()-1,getHgt()) \notin {LAD,HDR} &&
			  getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL} &&
		 	  c:Character \not \exists \in getEnvi().getCellContent(getWdt()-1,getHgt())
		      -> getWdt() == getWdt()@pre */
		
		if((getEnvi().getCellNature(getWdt()-1,getHgt()) != Cell.LAD ||
		    getEnvi().getCellNature(getWdt()-1,getHgt()) != Cell.HDR ) &&
		   (getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.PLT || 
		    getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.MTL) &&
		   character_list_wdt_minus_1.size() == 0
		   ){
			if(!(getWdt() == getWdt_pre)) {
				throw new PostconditionError("CharacterContrat post goLeft: getWdt() != getWdt_pre");
			}
		}
		
		
		// post : c:Character \exists \in getEnvi.getCellContent(getWdt()-1,getHgt()) -> getWdt() == getWdt()@pre
		
		if(character_list_wdt_minus_1.size() != 0) {
			if(!(getWdt() == getWdt_pre)) {
				throw new PostconditionError("CharacterContrat post goLeft: getWdt() != getWdt_pre");
			}
		}
		
		
		/*
		post : getWdt() != 0  
			   && getEnvi().getCellNature(getWdt()-1,getHgt()) \notin  {MTL,PLT}
			   || getEnvi().getCellNature(getWdt(),getHgt()) \in {LAD,HDR}
			   || getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL,LAD}
			   || c:Character \exists \in getEnvi().getCellContent(getWdt(),getHgt()-1)
			   && \not(c:Character \exists \in getEnvi().getCellContent(getWdt()-1,getHgt())) -> getWdt() == getWdt()@pre -1
			   */
		
		if(getWdt() != 0 
		   && (getEnvi().getCellNature(getWdt()-1,getHgt()) !=  Cell.MTL || getEnvi().getCellNature(getWdt()-1,getHgt()) !=  Cell.PLT) 
		   || (getEnvi().getCellNature(getWdt(),getHgt()) ==  Cell.LAD || getEnvi().getCellNature(getWdt(),getHgt()) !=  Cell.HDR) 
		   || (getEnvi().getCellNature(getWdt(),getHgt()-1) !=  Cell.PLT || getEnvi().getCellNature(getWdt(),getHgt()-1) !=  Cell.MTL ||
			   getEnvi().getCellNature(getWdt(),getHgt()-1) !=  Cell.LAD ) 
		   || (character_list_hgt_minus_1.size() != 0)
		   && !(character_list_wdt_minus_1.size() !=0) ){
			if(!(getWdt() == getWdt_pre-1)) {
				throw new PostconditionError("CharacterContrat post goLeft: getWdt() != getWdt_pre");
			}
			
		}
	}
	
	public void goRight() {

		//Capture
		int getHgt_pre = getHgt();
		int getWdt_pre = getWdt();
		
		ArrayList <CellContent> character_list_wdt_plus_1 = getCharacterList(getWdt()+1,getHgt());	
		ArrayList <CellContent> character_list_hgt_minus_1 = getCharacterList(getWdt(),getHgt()-1);	
	
		checkInvariant();
		
		super.goLeft();
		
		checkInvariant();
		
		//	post : getHgt() == getHgt()@pre
		if(!(getHgt() == getHgt_pre)) {
			throw new PostconditionError("CharacterContrat post goRight: getHgt() != getHgt_pre");
		}
		
		//	post : getWdt()==getWdt()=getEnvi.getWidth()-> getWdt() == getWdt()@pre
		if(!( getWdt()!=getEnvi().getWidth() || getWdt() == getWdt_pre)) {
			throw new PostconditionError("CharacterContrat post goRight: getWdt()==0 && getWdt() != getWdt_pre");
		}
		
		
		//post : getEnvi().getCellNature(getWdt()-1,getHgt()) \in {MTL,PLT,LAD} -> getWdt() == getWdt()@pre
		if(getEnvi().getCellNature(getWdt()+1,getHgt()) == Cell.MTL ||
		   getEnvi().getCellNature(getWdt()+1,getHgt()) == Cell.PLT ||
		   getEnvi().getCellNature(getWdt()+1,getHgt()) == Cell.LAD ){
			if(!(getWdt() == getWdt_pre)) {
				throw new PostconditionError("CharacterContrat post goRight: getWdt() != getWdt_pre");
			}
		}
		
	
		
		
		/*post : getEnvi().getCellNature(getWdt()-1,getHgt()) \notin {LAD,HDR} &&
			  getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL} &&
		 	  c:Character \not \exists \in getEnvi().getCellContent(getWdt()-1,getHgt())
		      -> getWdt() == getWdt()@pre */
		
		if((getEnvi().getCellNature(getWdt()+1,getHgt()) != Cell.LAD ||
		    getEnvi().getCellNature(getWdt()+1,getHgt()) != Cell.HDR ) &&
		   (getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.PLT || 
		    getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.MTL) &&
		   character_list_wdt_plus_1.size() == 0
		   ){
			if(!(getWdt() == getWdt_pre)) {
				throw new PostconditionError("CharacterContrat post goRight: getWdt() != getWdt_pre");
			}
		}
		
		
		// post : c:Character \exists \in getEnvi.getCellContent(getWdt()-1,getHgt()) -> getWdt() == getWdt()@pre
		
		if(character_list_wdt_plus_1.size() != 0) {
			if(!(getWdt() == getWdt_pre)) {
				throw new PostconditionError("CharacterContrat post goRight: getWdt() != getWdt_pre");
			}
		}
		
		
		/*
		post : getWdt() != 0  
			   && getEnvi().getCellNature(getWdt()-1,getHgt()) \notin  {MTL,PLT}
			   || getEnvi().getCellNature(getWdt(),getHgt()) \in {LAD,HDR}
			   || getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL,LAD}
			   || c:Character \exists \in getEnvi().getCellContent(getWdt(),getHgt()-1)
			   && \not(c:Character \exists \in getEnvi().getCellContent(getWdt()-1,getHgt())) -> getWdt() == getWdt()@pre + 1
			   */
		
		if(getWdt() != 0 
		   && (getEnvi().getCellNature(getWdt()+1,getHgt()) !=  Cell.MTL || getEnvi().getCellNature(getWdt()+1,getHgt()) !=  Cell.PLT) 
		   || (getEnvi().getCellNature(getWdt(),getHgt()) ==  Cell.LAD || getEnvi().getCellNature(getWdt(),getHgt()) !=  Cell.HDR) 
		   || (getEnvi().getCellNature(getWdt(),getHgt()-1) !=  Cell.PLT || getEnvi().getCellNature(getWdt(),getHgt()-1) !=  Cell.MTL ||
			   getEnvi().getCellNature(getWdt(),getHgt()-1) !=  Cell.LAD ) 
		   || (character_list_hgt_minus_1.size() != 0)
		   && !(character_list_wdt_plus_1.size() !=0) ){
			if(!(getWdt() == getWdt_pre+1)) {
				throw new PostconditionError("CharacterContrat post goLeft: getWdt() != getWdt_pre");
			}
			
		}
	}

	public void goUp() {
	
		//Capture pour post-conditions
		int getHgt_pre = getHgt();
		int getWdt_pre = getWdt();
		ArrayList <CellContent> character_list_hgt_plus_1 = getCharacterList(getWdt(),getHgt()+1);	
		
		checkInvariant();
		
		super.goUp();
		
		checkInvariant();
		
		//post : getWdt() == getWdt()@pre
		if(!(getWdt() == getWdt_pre)) {
			throw new PostconditionError("CharacterContrat post goUp: getWdt() != getWdt_pre");
		}
		
		//getHgt()=getEnvi.getHeight() -> getHgt() == getHgt()@pre
		if((getHgt() == getEnvi().getHeight())) {
			if(!(getHgt() == getHgt_pre)) throw new PostconditionError("CharacterContrat post goUp: getHgt() != getHgt_pre");
		}
		
		//post : getEnvi().getCellNature(getWdt(),getHgt()+1) \in {MLT,PLT} -> getHgt() == getHgt()@pre
		if((getEnvi().getCellNature(getWdt(),getHgt()+1) == Cell.MTL || getEnvi().getCellNature(getWdt(),getHgt()+1) == Cell.PLT)) {
			if(!(getHgt() == getHgt_pre)) throw new PostconditionError("CharacterContrat post goUp: getHgt() != getHgt_pre");
		}
		
		//post: c:Character \exists \in getEnvi.getCellContent(getWdt(),getHgt()+1) -> getHgt() == getHgt()@pre
		if(character_list_hgt_plus_1.size() != 0) {
			if(!(getHgt() == getHgt_pre)) throw new PostconditionError("CharacterContrat post goUp: getHgt() != getHgt_pre");
		}
		
		/*
		 post : getHgt() != getEnvi.getHeight()
				&& getEnvi().getCellNature(getWdt(),getHgt()+1) == LAD
				&& \not(c:Character \exists \in getEnvi.getCellContent(getWdt(),getHgt()+1)) -> getHgt() == getHgt()@pre + 1
		*/
		
		if((getHgt() == getEnvi().getHeight()) && (getEnvi().getCellNature(getWdt(),getHgt()+1) == Cell.LAD)
			&& !(character_list_hgt_plus_1.size() != 0)) {
			if(!(getHgt() == getHgt_pre +1)) throw new PostconditionError("CharacterContrat post goUp: getHgt() != getHgt_pre +1 ");
		}
		
	}

	public void goDown() {
		
		int getHgt_pre = getHgt();
		int getWdt_pre = getWdt();
		ArrayList <CellContent> character_list_hgt_minus_1 = getCharacterList(getWdt(),getHgt()-1);	
		
		checkInvariant();
		
		super.goDown();
		
		checkInvariant();
		
		//post : getWdt() == getWdt()@pre
				if(!(getWdt() == getWdt_pre)) {
					throw new PostconditionError("CharacterContrat post goUp: getWdt() != getWdt_pre");
				}
				
				//getHgt()=getEnvi.getHeight() -> getHgt() == getHgt()@pre
				if(getHgt() == 0) {
					if(!(getHgt() == getHgt_pre)) throw new PostconditionError("CharacterContrat post goUp: getHgt() != getHgt_pre");
				}
				
				//post : getEnvi().getCellNature(getWdt(),getHgt()-1) \in {MLT,PLT} -> getHgt() == getHgt()@pre
				if((getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.MTL || getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.PLT)) {
					if(!(getHgt() == getHgt_pre)) throw new PostconditionError("CharacterContrat post goUp: getHgt() != getHgt_pre");
				}
				
				//post: c:Character \exists \in getEnvi.getCellContent(getWdt(),getHgt()-1) -> getHgt() == getHgt()@pre
				if(character_list_hgt_minus_1.size() != 0) {
					if(!(getHgt() == getHgt_pre)) throw new PostconditionError("CharacterContrat post goUp: getHgt() != getHgt_pre");
				}
				
				/*
				 post : getHgt() != 0
	 * 					  && (getEnvi().getCellNature(getWdt(),getHgt()-1) == LAD
	 * 					  || !(getEnvi().getCellNature(getWdt(),getHgt()-1) \in {MTL,PLT}))
	 *		 			 && \not(c:Character \exists \in getEnvi.getCellContent(getWdt(),getHgt()-1)) -> getHgt() == getHgt()@pre-1
				*/
				
				if((getHgt() == 0) && 
				   (getEnvi().getCellNature(getWdt(),getHgt()-1) == Cell.LAD || 
					getEnvi().getCellNature(getWdt(), getHgt()-1) != Cell.MTL ||
					getEnvi().getCellNature(getWdt(), getHgt()-1) != Cell.PLT )
					&& !(character_list_hgt_minus_1.size() != 0)) {
					if(!(getHgt() == getHgt_pre -1)) throw new PostconditionError("CharacterContrat post goUp: getHgt() != getHgt_pre - 1 ");
				}
				
	}
}
