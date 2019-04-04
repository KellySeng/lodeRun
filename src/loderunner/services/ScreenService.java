package loderunner.services;

public interface ScreenService {
	
	/**
	 * Pas d'invariants
	 * @return
	 */

	/**
	 * Observators
	 */
	
	public int getHeight();
	public int getWidth();
	
	/*
	 * pre : 0<y<getHeight() && 0<x<getWidth()
	 */
	public Cell getCellNature(int x, int y);
	
	/**
	 * Constructors
	 */
	
	/**
	 * pre : 0 < h && 0< w 
	 * post : getHeight() == h
	 * post : getWidth() == w
	 * post : \forall (u:Integer,v: Integer) \in [0;getWidth()[ X [0;getHeight()[, getCellNature(x,y) == EMP
	 */
	public void init(int h, int w);
	
	/**
	 * Operators
	 * 
	 */
	
	/**
	 * pre : getCellNature(x,y) == PLT
	 * post : getCellNature(x,y) == HOL
	 * post : \forall (u:Integer, v:Integer) \in [0;getWidth()[ X [0;getHeight()[ && x!=u && y!=v  -> getCellNature(u,v) == getCellNature(u,v)@pre  
	 */
	public void dig(int x, int y);
	
	
	/**
	 * pre : getCellNature(x,y) == HOL
	 * post : getCellNature(x,y) == PLT
	 * post : \forall (u:Integer,v:Integer) \in [0;getWidth()[ X [0;getHeight()[ && x!=u && y!=v  -> getCellNature(u,v) == getCellNature(u,v)@pre  
	 */
	public void fill(int x, int y);
}
