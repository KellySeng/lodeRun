package loderunner.services;

import java.util.HashSet;
import java.util.List;

public interface EnvironmentService extends ScreenService {

	
	
	
	/**
	 * Invariants
	 * inv : \forall(x:Integer,y : Integer) \in [0;getWidth()[ x [0;getHeight()[,
	 *		 \forall c1: Character, c2 : Character in getCellContent(x,y), c1 = c2	
	 *
	 * inv : \forall(x:Integer,y : Integer) \in [0;getWidth()[ x [0;getHeight()[,
	 *	getCellNature(x,y) \in {MTL, PLR} -> getCellContent(x,y) == null
	 *
	 * inv : \forall(x:Integer,y : Integer) \in [0;getWidth()[ x [0;getHeight()[,
	 * \exists t:ItemType.Treasure \in getCellContent(x,y) -> getCellNature(x,y) == EMP && getCellNature(x,y-1) \in {MTL, PLR} 
	 *
	 **/
	 
	
	/**
	 * pre : 0 <= y && y <= getHeight() &&  0 <= x && x <= getWidth()   
	 **/
	public HashSet<CellContent> getCellContent(int x,int y);

	
	
	/**
	 * post :  \forall(x:Integer,y : Integer) \in [0;getWidth()[ x [0;getHeight()[,
	 * 		getCellNature(x,y) == e.getCellNature(x,y)
	 **/
	public void init (EditableScreenService e);
}
