package loderunner.services;

public interface CharacterService extends CellContent{

	/*
	 * Invariants
	 * inv : getEnvi().getCellNature(getWdt(),getHgt()) in {MLT,HOL,LAD,HDR}
	 */
	
	
	
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
	 * 		  c:Character \notexists \in getEnvi().getCellNature(getWdt()-1,getHgt())
	 *        ->
	 *         
	 */
	
	public void goLeft();
	public void goRight();
	public void goUp();
	public void goDown();
	
}
