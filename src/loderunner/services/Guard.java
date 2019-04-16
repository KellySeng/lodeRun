package loderunner.services;

public interface Guard extends CharacterService {
	
	/**
	 * Invariants
	 * inv : getEnvi().getCellNature(getWdt(),getHgt()) == LAD 
	 * 		&& getHgt() < getTarget().getHgt()
	 * 		&& (!(getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL}) 
	 * 			|| (\exists c:Character \in getEnvi().getCellContent(getWdt(),getHgt()-1)
	 * 			-> getTarget().getHgt() - getHgt() < |getTarget().getWdt() - getWdt()| )
	 * 		-> getBehavior() = Up
	 * 
	 * inv :  getEnvi().getCellNature(getWdt(),getHgt()) == LAD 
	 * 		&& getHgt() > getTarget().getHgt()
	 * 		&& (!(getEnvi().getCellNature(getWdt(),getHgt()-1) \in {PLT,MTL}) 
	 * 			|| (\exists c:Character \in getEnvi().getCellContent(getWdt(),getHgt()+1)
	 * 			-> getTarget().getHgt() + getHgt() > |getTarget().getWdt() - getWdt()| )
	 * 		-> getBehavior() = Down
	 */
	
	
	/*
	 * Observators
	 */
	
	public int getId();
	public Move getBehavior();
	public CharacterService getTarget();
	public int getTimeInHole();
	
	
	
	//Constructors 
	
	/**
	 * p
	 */
	public void init();
	
	//Operators
	
	/**
	 * pre : getEnvi().getCellNature(getWdt(),getHgt()) == HOL
	 * post : getWdt() ==0 -> getWdt() == getWdt()@pre  && getHgt() ==  get Hgt()@pre
	 * post : getEnvi().getCellNature(getWdt()-1,getHgt()+1) \in {MTL,PLT} -> getWdt() == getWdt()@pre && getHgt() == getHgt()@pre
	 * post : \exist c:Character \in getEnvi().getCellContent(getWdt()-1,getHgt()+1) -> getWdt() == getWdt()@pre && getHgt() == getHgt()@pre
	 * post : getWdt() == 0 &&  !(getEnvi().getCellNature(getWdt()-1,getHgt()+1) \in {MTL,PLT})  && !(\exist c:Character \in getEnvi().getCellContent(getWdt()-1,getHgt()+1)
	 * ) -> getWdt() == getWdt()@pre -1 && getHgt() == getHgt()@pre +1
	 * 
	 */
	public void climbLeft();
	
	/**
	 * pre : getEnvi().getCellNature(getWdt(),getHgt()) == HOL
	 * post : getWdt() ==0 -> getWdt() == getWdt()@pre  && getHgt() ==  get Hgt()@pre
	 * post : getEnvi().getCellNature(getWdt()+1,getHgt()+1) \in {MTL,PLT} -> getWdt() == getWdt()@pre && getHgt() == getHgt()@pre
	 * post : \exist c:Character \in getEnvi().getCellContent(getWdt()+1,getHgt()+1) -> getWdt() == getWdt()@pre && getHgt() == getHgt()@pre
	 * post : getWdt() == 0 &&  !(getEnvi().getCellNature(getWdt()+1,getHgt()+1) \in {MTL,PLT})  && !(\exist c:Character \in getEnvi().getCellContent(getWdt()-1,getHgt()+1)
	 * ) -> getWdt() == getWdt()@pre +1 && getHgt() == getHgt()@pre +1
	 * 
	 */
	public void climbRight();
	
	
	

}
