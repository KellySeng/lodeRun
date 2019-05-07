package loderunner.contrat;


import loderunner.decorator.EditableScreenDecorator;
import loderunner.services.Cell;
import loderunner.services.EditableScreenService;

public class EditableScreenContrat extends EditableScreenDecorator{

	public EditableScreenContrat(EditableScreenService s) {
		super(s);
	}
	
	public void checkInvariant() {
		
		// inv : Playable() =  \forall (x:Integer,y:Integer) \in [0; getWidth()[ X [0; getHeight()[, getCellNature(x,y) != HOL
		// && \forall x:Integer \in [0; getWidth()[, getCellNature(x,0) == MTL
		
		boolean Playable = true;
		for(int x=0;x<getWidth();x++) {
			for(int y=0;y<getHeight();y++) {
				if(!(getCellNature(x,y) != Cell.HOL)) {
					Playable = false;
				}
			}
		}
		
		for(int x=0;x<getWidth();x++) {
				if(!(getCellNature(x,0) == Cell.MTL)) {
					Playable = false;
				}
		}
		
		if(Playable != super.isPlayable()) {
			throw new InvariantError("EditableScreenContrat inv error ");
		}
	}
	
	@Override
	public void init(int h,int w) {
		
		super.init(h, w);
		
		for(int x=0;x<getWidth();x++) {
			if(!(getCellNature(x,0) == Cell.MTL)) {
				throw new PostconditionError("lowest cells must be all metals");
			}
		}
		
		checkInvariant();
		
		
	}
	
	
	
	
	public void setNature(int x, int y,Cell c) {
		//pre : 0<y<getHeigth()
		
		if(!(y>=0 && y<getHeight())) {
			throw new PreconditionError("EditableScreenContrat setNature pre : y pas entre 0 et getHeight() ");
		}
		
		//pre : 0<x<getWidth()
		
		System.out.println("width = "+getWidth() );
		if(!(x>=0 && x<getWidth())) {
			throw new PreconditionError("EditableScreenContrat setNature pre : x = "+x+" pas entre 0 et getWidth() ");
		}
		
		checkInvariant();
		
		//capure pour post-conditions
		Cell [][] cellNature_atpre = new Cell [getWidth()][getHeight()];
		for(int u =0; u<getWidth();u++) {
			for(int v=0; v<getHeight();v++) {
				cellNature_atpre[u][v] = getCellNature(u,v);
			}
		}
		
		super.setNature(x, y, c);
		
		checkInvariant();
		
		//post :  setNature(x,y,c).getCellNature() == c
		
		if(!(getCellNature(x,y) == c)) {
			throw new PostconditionError("EditableScreenContrat setNature post : getCellNature(x,y) != c");
		}
		
		//post : \forall (u:Integer,v:Integer) \in [0; getWidth()[ X [0; getHeight()[  && (u!=x) && (v!=y) 
		//        -> setNature(x,y,c).getCellNature(u,v) = getCellNature(u,v)@pre
		
		for(int u =0; u<getWidth();u++) {
			for(int v=0; v<getHeight();v++) {
				if(x!=u && y!=v) {
					if(!(getCellNature(u,v) == cellNature_atpre[u][v])) {
						throw new PostconditionError("EditableScreenContrat setNature post "
								+ ": getCellNature("+u+","+v+") != cellNature_atpre["+u+"]["+ v+"]");
					}
				}
			}
		}
		
		
		
	}
	
	
	

}
