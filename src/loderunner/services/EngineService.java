package loderunner.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface EngineService {

	/*
	 * Observators
	 */
	public EnvironmentService getEnvironment();
	
	public PlayerService getPlayer();
	
	public ArrayList<GuardService> getGuards();

	public Set<ItemService> getTreasures();

	public Status getStatus();
	
	public Command getNextCommand();
	
	
	/**
	 * pre : screen!=null
	 * post : getEnvironment() == screen
	 * post : getPlayer() = p
	 * post : getGuards() = g
	 * post : getTreasures() = t
	 * @param screen
	 */
	public void init(EnvironmentService screen, PlayerService p,
			ArrayList<GuardService> g, HashSet<ItemService> t);
	

	/*
	 * Operator 
	 */
	public void step();
}
