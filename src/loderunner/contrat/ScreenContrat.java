package loderunner.contrat;

import loderunner.decorator.ScreenDecorator;
import loderunner.services.Cell;
import loderunner.services.ScreenService;

public class ScreenContrat extends ScreenDecorator {

	public ScreenContrat(ScreenService s) {
		super(s);
	}
	
	/*
	 * pas d'invariant
	 */

	public void init(int h, int w) {
		
		//pre: h>0 && w>0 
		
		if(!(h>0 && w>0)) {
			throw new PreconditionError("init : h<0 || w<0");
		}
		
		//traitement
		super.init(h, w);
		
		//post: getHeight() == h
		
		if(!(getHeight()==h)){
			throw new PostconditionError("init : getHeight() != h");
		}
		
		//post : getWidth() == w
		if(!(getWidth()==w)){
			throw new PostconditionError("init : getWidth() != w");
		}
		
		//post : \forall (x:Integer,y: Integer) \in [0;getWidth()[ X [0;getHeight()[, getCellNature(x,y) == EMP
		
		for(int x =0; x<getWidth();x++) {
			for(int y = 0 ; y<getHeight();y++) {
				
				if(!(getCellNature(x, y) == Cell.EMP)) {
					throw new PostconditionError("init : getCellNature("+x+","+y+") != EMP");
				}
			}
		}
	}
	
	public void dig(int x, int y) {
		
		// pre : getCellNature(x,y) == PLT
		if(!(getCellNature(x, y) == Cell.PLT)) {
			throw new PreconditionError("dig : getCellNature("+x+","+y+") != PLT");
		}
		
		
		//capture pour post-conditions
		
		Cell [][] cellNature_atpre = new Cell [getWidth()][getHeight()];
		for(int u =0; u<getWidth();u++) {
			for(int v=0; v<getHeight();v++) {
				cellNature_atpre[u][v] = getCellNature(u,v);
			}
		}
		
		super.dig(x, y);
		
		// post : getCellNature(x,y) == HOL
		if(!(getCellNature(x, y) == Cell.HOL)){
			throw new PostconditionError("dig : getCellNature("+x+","+y+") != HOL");
		}
		
		/*
		 *  \forall (u:Integer, v:Integer) \in [0;getWidth()[ X [0;getHeight()[ && x!=u && y!=v
		 * 	 -> getCellNature(u,v) == getCellNature(u,v)@pre  	
		 */
		
		for(int u =0; u<getWidth();u++) {
			for(int v=0; v<getHeight();v++) {
				if(x!=u && y!=v) {
					if(!(getCellNature(u,v) == cellNature_atpre[u][v])) {
						throw new PostconditionError("dig : getCellNature("+u+","+v+") != cellNature_atpre["+u+"]["+ v+"]");
					}
				}
			}
		}
		
	}
	
	
	public void fill(int x, int y) {
		
		// pre : getCellNature(x,y) == HOL
		if(!(getCellNature(x, y) == Cell.HOL)) {
			throw new PreconditionError("dig : getCellNature("+x+","+y+") != HOL");
		}
		
		
		//capture pour post-conditions
		
		Cell [][] cellNature_atpre = new Cell [getWidth()][getHeight()];
		for(int u =0; u<getWidth();u++) {
			for(int v=0; v<getHeight();v++) {
				cellNature_atpre[u][v] = getCellNature(u,v);
			}
		}
		
		super.dig(x, y);
		
		// post : getCellNature(x,y) == PLT
		if(!(getCellNature(x, y) == Cell.PLT)){
			throw new PostconditionError("dig : getCellNature("+x+","+y+") != PLT");
		}
		
		/*
		 *  \forall (u:Integer, v:Integer) \in [0;getWidth()[ X [0;getHeight()[ && x!=u && y!=v
		 * 	 -> getCellNature(u,v) == getCellNature(u,v)@pre  	
		 */
		
		for(int u =0; u<getWidth();u++) {
			for(int v=0; v<getHeight();v++) {
				if(x!=u && y!=v) {
					if(!(getCellNature(u,v) == cellNature_atpre[u][v])) {
						throw new PostconditionError("dig : getCellNature("+u+","+v+") != cellNature_atpre["+u+"]["+ v+"]");
					}
				}
			}
		}
		
	}
}
