package loderunner.services;

import java.util.Set;

public interface EngineService {

	/*
	 * Observators
	 */
	public EnvironmentService getEnvironment();
	
	public PlayerService getPlayer();
	
	public Set<Guard> getGuards();

	public Set<ItemService> getTreasures();
	
	public Command getNextCommand();
	
	
	/**
	 * pre : e!=null
	 * post : getEngine() == e
	 * @param e
	 */
	public void init(EditableScreenService screen);
	

	/*
	 * Operator 
	 */
	public void step();
}
