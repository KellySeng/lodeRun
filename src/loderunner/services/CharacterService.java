package loderunner.services;

public interface CharacterService {

	/*
	 * Invariants
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
	 * pre  : getEnvi().getCellNature(x,y) == HOL
	 * post : getHgt = h 
	 * post : getCol = c
	 */
	public void init(int c, int h);

	
	/*
	 * Operators
	 */
	
	/**
	 * post : getHgt() == getHgt()@pre
	 * post : getWdt() -> getWdt() == getWdt()@pre
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
