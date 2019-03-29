package loderunner.services;

public interface EditableScreenService extends ScreenService {

	/*
	 * Invariants
	 * 
	 * inv : \forall (x:Integer,y:Integer) \in [0; getWidth()[ X [0; getHeight()[, getCellNature(x,y) != HOL &&
	 * 		 \forall x:Integer \in [0; getWidth()[, getCellNature(x,0) == MTL
	 */
	
	/*
	 * Observators 
	 */
	public boolean isPlayable();
	
	/*
	 * Operators 
	 */
	
	/**
	 * pre : 0<y<getHeigth()
	 * pre : 0<x<getWidth()
	 * post : setNature(x,y,c).getCellNature() == c
	 * post : \forall (u:Integer,v:Integer) \in [0; getWidth()[ X [0; getHeight()[  && (u!=x) && (v!=y) -> setNature(x,y,c).getCellNature(u,v) = getCellNature(u,v)@pre
	 */
	public EditableScreenService setNature(int x, int y,Cell c);
	
}
