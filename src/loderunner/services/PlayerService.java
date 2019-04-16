package loderunner.services;

public interface PlayerService extends CharacterService{
	
	/*
	 * Observators
	 */
	public EngineService getEngine();
	
	
	//Constructors
	
	
	/**
	 * pre : e!=null
	 * post : getEngine() == e
	 * @param e
	 */
	public void init(EngineService e);
	
	
	/*
	 * Operator 
	 */
	public void step();
	

}
