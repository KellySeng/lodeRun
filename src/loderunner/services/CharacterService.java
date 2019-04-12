package loderunner.services;

public interface CharacterService extends CellContent{

	/**
	 * Invariants
	 * inv : getEnvi().getCellNature(getWdt(),getHgt()) in {MLT,HOL,LAD,HDR}
	 **/
	
	
	
	/*
	 * Observators
	 */
	public EnvironmentService getEnvi();
	public int getHgt();
	public int getWdt();
	
	/*
	 * Constructors
	 */
	
	/**
	 * pre  : getEnvi().getCellNature(x,y) == EMP
	 * post : getHgt = h 
	 * post : getWdt = w
	 */
	public void init(ScreenService screen,int w, int h);

	
	/*
	 * Operators
	 */
	
	/**
	 * 
	 * post : getHgt() == getHgt()@pre
	 * post : getWdt()=0 -> getWdt() == getWdt()@pre
	 * post : getEnvi().getCellNature(getWdt()-1,getHgt()) \in {MLT,PLT,LAD} -> getWdt() == getWdt()@pre
	 * post : getEnvi().getCellNature(getWdt()-1,getHgt()) \notin {LAD,HDR} &&
	 * 		  getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL} &&
	 * 		  c:Character \not \exists \in getEnvi().getCellNature(getWdt()-1,getHgt())
	 *        -> getWdt() == getWdt()@pre
	 * post : c:Character \exists \in getEnvi.getCellContent(getWdt()-1,getHgt()) -> getWdt() == getWdt()@pre
	 * post : getWdt() != 0  
	 * 		  && getEnvi().getCellNature(getWdt()-1,getHgt()) \notin  {MTL,PLT}
	 *		  || getEnvi().getCellNature(getWdt(),getHgt()) \in {LAD,HDR}
	 *		  || getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL,LAD}
	 *		  || c:Character \exists \in getEnvi().getCellNature(getWdt(),getHgt()-1)
	 *		  && \not(c:Character \exists \in getEnvi(getWdt()-1,getHgt())) -> getWdt() == getWdt()@pre -1
	 */
	
	public void goLeft();
	
	
	/**
	 * 
	 * post : getHgt() == getHgt()@pre
	 * post : getWdt()=getEnvi.getWidth() -> getWdt() == getWdt()@pre
	 * post : getEnvi().getCellNature(getWdt()+1,getHgt()) \in {MLT,PLT,LAD} -> getWdt() == getWdt()@pre
	 * post : getEnvi().getCellNature(getWdt()+1,getHgt()) \notin {LAD,HDR} &&
	 * 		  getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL} &&
	 * 		  c:Character \not \exists \in getEnvi().getCellNature(getWdt()+1,getHgt())
	 *        -> getWdt() == getWdt()@pre
	 * post : c:Character \exists \in getEnvi.getCellContent(getWdt()+1,getHgt()) -> getWdt() == getWdt()@pre
	 * post : getWdt() != getEnvi.getWidth()
	 * 		  && getEnvi().getCellNature(getWdt()+1,getHgt()) \notin  {MTL,PLT}
	 *		  || getEnvi().getCellNature(getWdt(),getHgt()) \in {LAD,HDR}
	 *		  || getEnvi().getCellNature(getWdt(),getHgt()+1) \in {PLT,MTL,LAD}
	 *		  || c:Character \exists \in getEnvi().getCellNature(getWdt(),getHgt()-1)
	 *		  && \not(c:Character \exists \in getEnvi(getWdt()+1,getHgt())) -> getWdt() == getWdt()@pre + 1
	 */
	public void goRight();
	
	/**
	 * post : getWdt() == getWdt()@pre
	 * post : getHgt()=getEnvi.getHeight() -> getHgt() == getHgt()@pre
	 * post : getEnvi().getCellNature(getWdt(),getHgt()+1) \in {MLT,PLT,LAD} -> getHgt() == getHgt()@pre
	 * post : getEnvi().getCellNature(getWdt(),getHgt()+1) \notin {LAD,HDR} &&
	 * 		  getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL} &&
	 * 		  c:Character \not \exists \in getEnvi().getCellNature(getWdt(),getHgt()+1)
	 *        -> getHgt() == getHgt()@pre
	 * post : c:Character \exists \in getEnvi.getCellContent(getWdt(),getHgt()+1) -> getWdt() == getWdt()@pre
	 * post : getHgt() != 0  
	 * 		  && getEnvi().getCellNature(getWdt(),getHgt()+1) \notin  {MTL,PLT}
	 *		  || getEnvi().getCellNature(getWdt(),getHgt()) \in {LAD,HDR}
	 *		  || getEnvi().getCellNature(getWdt(),getHgt()+1) \in {LAD}
	 *		  || c:Character \exists \in getEnvi().getCellNature(getWdt(),getHgt()-1)
	 *		  && \not(c:Character \exists \in getEnvi(getWdt(),getHgt()+1)) -> getHgt() == getHgt()@pre + 1
	 */
	public void goUp();

	/**
	 * post : getWdt() == getWdt()@pre
	 * post : getHgt()=0 -> getHgt() == getHgt()@pre
	 * post : getEnvi().getCellNature(getWdt(),getHgt()-1) \in {MLT,PLT,LAD} -> getHgt() == getHgt()@pre
	 * post : getEnvi().getCellNature(getWdt(),getHgt()-1) \notin {LAD,HDR} &&
	 * 		  getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL} &&
	 * 		  c:Character \not \exists \in getEnvi().getCellNature(getWdt(),getHgt()+1)
	 *        -> getHgt() == getHgt()@pre
	 * post : c:Character \exists \in getEnvi.getCellContent(getWdt(),getHgt()-1) -> getWdt() == getWdt()@pre
	 * post : getHgt() != getEnvi.getHeight() 
	 * 		  && getEnvi().getCellNature(getWdt(),getHgt()-1) \notin  {MTL,PLT}
	 *		  || getEnvi().getCellNature(getWdt(),getHgt()) \in {LAD,HDR}
	 *		  || getEnvi().getCellNature(getWdt(),getHgt()-1) \in {LAD}
	 *		  || c:Character \exists \in getEnvi().getCellNature(getWdt(),getHgt()+1)
	 *		  && \not(c:Character \exists \in getEnvi(getWdt(),getHgt()-1)) -> getWdt() == getWdt()@pre - 1
	 */
	public void goDown();
	
}
