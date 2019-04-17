package loderunner.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface EngineService {

	
	public void setCmd(Command c) ;
	/*
	 * Observators
	 */
	public EnvironmentService getEnvironment();
	
	public PlayerService getPlayer();
	
	public ArrayList<GuardService> getGuards();

	public Set<ItemService> getTreasures();

	public Status getStatus();
	
	public Command getNextCommand();
	
	public ArrayList<Triplet<Integer,Integer,Integer>> getHoles(); 
	
	
	/**
	 * pre : screen!=null
	 * post : getEnvironment() == screen
	 * post : getPlayer() = p
	 * post : getGuards() = g
	 * post : getTreasures() = t
	 * @param screen
	 */
	public void init(EditableScreenService screen, int x, int y, List<Pair<Integer,Integer>> listGuards,List<Pair<Integer,Integer>> listTresors );	


	/*
	 * Operator 
	 */
	public void step();
}