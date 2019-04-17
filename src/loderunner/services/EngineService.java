package loderunner.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface EngineService {

	/*
	 * Observators
	 */
	public EnvironmentService getEnvironment();
	
	public PlayerService getPlayer();
	
	public ArrayList<Guard> getGuards();

	public Set<ItemService> getTreasures();
	
	public Command getNextCommand();
	
	public ArrayList<Triplet<Integer,Integer,Integer>> getHoles(); 
	
	
	/**
	 * pre : screen!=null
	 * post : getEnvironment() == screen
	 * @param screen
	 */
	public void init(EditableScreenService screen, int x, int y, List<Pair<Integer,Integer>> listGuards,List<Pair<Integer,Integer>> listTresors );	

	/*
	 * Operator 
	 */
	public void step();
}
