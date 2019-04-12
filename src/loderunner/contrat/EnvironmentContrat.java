package loderunner.contrat;

import java.util.HashSet;

import loderunner.decorator.EnvironmentDecorator;
import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.EditableScreenService;
import loderunner.services.EnvironmentService;
import loderunner.services.ItemType;

public class EnvironmentContrat extends EnvironmentDecorator{

	public EnvironmentContrat(EnvironmentService s) {
		super(s);
	}
	
	public void checkInvariant() {
		
//	inv : \forall(x:Integer,y : Integer) \in [0;getWidth()[ x [0;getHeight()[,
//		  \forall c1: Character, c2 : Character in getCellContent(x,y), c1 = c2	
		
		for(int x = 0 ; x<getWidth();x++) {
			for(int y = 0 ; y<getHeight();y++) {
		
			}
		}
		
//		  inv : \forall(x:Integer,y : Integer) \in [0;getWidth()[ x [0;getHeight()[,
//           getCellNature(x,y) \in {MTL, PLR} -> getCellContent(x,y) == null
		
		for(int x = 0 ; x<getWidth();x++) {
			for(int y = 0 ; y<getHeight();y++) {
				if(!(getCellNature(x,y) == Cell.MTL || getCellNature(x,y) == Cell.PLT) || (getCellContent(x,y) == null)) {
					throw new InvariantError("getCellNature(x,y) \\in {MTL, PLR} not implies getCellContent(x,y) == null");
				}
			}
		}
		
//		  inv : \forall(x:Integer,y : Integer) \in [0;getWidth()[ x [0;getHeight()[,
//			    \exists t:ItemType.Treasure \in getCellContent(x,y) -> getCellNature(x,y) == EMP && getCellNature(x,y) \in {MTL, PLR} 
		

		for(int x = 0 ; x<getWidth();x++) {
			for(int y = 0 ; y<getHeight();y++) {
		
			}
		}
	}
	

	public void init(EditableScreenService e) {
		checkInvariant();
		super.init(e);
		checkInvariant();
		
//		  post :  \forall(x:Integer,y : Integer) \in [0;getWidth()[ x [0;getHeight()[,
// 		getCellNature(x,y) == e.getCellNature(x,y)
		for(int x = 0 ; x<getWidth();x++) {
			for(int y = 0 ; y<getHeight();y++) {
				if(!(getCellNature(x,y) == e.getCellNature(x, y))) {
					throw new PostconditionError("!(getCellNature(x,y) == e.getCellNature(x, y))");
				}
			}
		}
	}
	
	
	public HashSet<CellContent> getCellContent(int x,int y){
		
		//pre : 0 <= y && y <= getHeight() &&  0 <= x && x <= getWidth()   
		if(!( 0 <= y && y <= getHeight() &&  0 <= x && x <= getWidth())) {
			throw new PreconditionError(" ! (0<y<getHeight && 0<x<getWidth");
		}
		
		checkInvariant();
		
		return super.getCellContent(x, y);
		
	}

}
