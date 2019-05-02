package loderunner.contrat;

import java.util.ArrayList;
import java.util.HashSet;

import loderunner.decorator.EnvironmentDecorator;
import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.CharacterService;
import loderunner.services.EditableScreenService;
import loderunner.services.EnvironmentService;
import loderunner.services.ItemType;

public class EnvironmentContrat extends EnvironmentDecorator{

	public EnvironmentContrat(EnvironmentService s) {
		super(s);
	}
	
	
	private ArrayList<CellContent> getCharacterList(int w, int h) {
		ArrayList <CellContent> list = new ArrayList<>();
		
		
		if(getCellContent(w,h)!= null) {
			for(CellContent c : getCellContent(w,h)) {
				if(c instanceof CharacterService) list.add(c);
			}
		}
		return list;
	}
	
	private ArrayList<CellContent> getTreasureList(int w, int h) {
		ArrayList <CellContent> list = new ArrayList<>();	
		for(CellContent c : getCellContent(w,h)) {
			if(c instanceof ItemType && c == ItemType.Treasure) list.add(c);
		}
		return list;
	}
	
	public void checkInvariant() {
		
		
		
	//	inv : \forall(x:Integer,y : Integer) \in [0;getWidth()[ x [0;getHeight()[,
	//		  \forall c1: Character, c2 : Character in getCellContent(x,y), c1 = c2	
			
		for(int x = 0 ; x<getWidth();x++) {
			for(int y = 0 ; y<getHeight();y++) {
				ArrayList<CellContent> l = getCharacterList(x, y);
				if(!(l.size() == 0 || l.size() == 1)) {
					throw new InvariantError("plus d'un perso dans une case");
				}
			}
		}
		
		//	inv : \forall(x:Integer,y : Integer) \in [0;getWidth()[ x [0;getHeight()[,
		//      getCellNature(x,y) \in {MTL, PLR} -> getCellContent(x,y) == vide
		
		for(int x = 0 ; x<getWidth();x++) {
			for(int y = 0 ; y<getHeight();y++) {
				if(getCellNature(x,y) == Cell.MTL || getCellNature(x,y) == Cell.PLT) {
					if (getCellContent(x,y).size() != 0) {
						throw new InvariantError("getCellNature(x,y) \\in {MTL, PLR} not implies getCellContent(x,y) == null");

				}
				}
			}
		}
		
//		  inv : \forall(x:Integer,y : Integer) \in [0;getWidth()[ x [0;getHeight()[,
//			    \exists t:ItemType.Treasure \in getCellContent(x,y) -> getCellNature(x,y) == EMP && getCellNature(x,y-1) \in {MTL, PLT} 
		for(int x = 0 ; x<getWidth();x++) {
			for(int y = 0 ; y<getHeight();y++) {
				if(getTreasureList(x, y).size()!=0) {
					if(! (getCellNature(x,y) == Cell.EMP &&
						  getCellNature(x,y-1) == Cell.MTL &&
						  getCellNature(x,y) == Cell.PLT)) {
						throw new InvariantError("Treasure must be in a empty case and on MTL OR PLT");
					}
				}
			}
		}
	}
	

	public void init(EditableScreenService e) {
//		checkInvariant();
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
	
	
//	public HashSet<CellContent> getCellContent(int x,int y){
//		
//		//pre : 0 <= y && y <= getHeight() &&  0 <= x && x <= getWidth()   
//		if(!( 0 <= y && y <= getHeight() &&  0 <= x && x <= getWidth())) {
//			throw new PreconditionError(" ! (0<y<getHeight && 0<x<getWidth");
//		}
//		
//		
//		return super.getCellContent(x, y);
//		
//	}

}
