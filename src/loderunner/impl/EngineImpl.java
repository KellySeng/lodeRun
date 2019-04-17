package loderunner.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import loderunner.services.Command;
import loderunner.services.EditableScreenService;
import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.Guard;
import loderunner.services.ItemService;
import loderunner.services.Pair;
import loderunner.services.PlayerService;
import loderunner.services.Status;
import loderunner.services.Triplet;


public class EngineImpl implements EngineService {
	
	EnvironmentService envi;
	PlayerService player;
	ArrayList<Guard> guards;
	Status status;
	ArrayList<Triplet<Integer,Integer,Integer>> holes;
	
	@Override
	public EnvironmentService getEnvironment() {
		return envi;
	}

	@Override
	public PlayerService getPlayer() {
		return player;
	}

	@Override
	public ArrayList<Guard> getGuards() {
		return guards;
	}

	@Override
	public Set<ItemService> getTreasures() {
		return null;
	}

	@Override
	public Command getNextCommand() {
		return null;
	}

	public Status getStatus() {
		return status;
	}
	@Override
	public void init(EditableScreenService screen, int x, int y, List<Pair<Integer,Integer>> listGuards,List<Pair<Integer,Integer>> listTresors ) {
		
		

	
	}

	@Override
	public void step() {
		
		//	le temps de chaque trou est incrementee
		for(Triplet<Integer, Integer, Integer> h : holes) {
			int t = h.getThird();
			if(t<15) {
				h.setThird(t+1);
			}
		}
		
		//	tous les trous dont la troisieme coordonnees vaut 15 sont rebouches.
		
		for(Triplet<Integer, Integer, Integer> h : holes) {
			int t = h.getThird();
			if(t==15) {
				
			}
		}
		
		player.step();
		
		for(Guard guard : guards) {
			guard.step();
			
		}
		
	}

	@Override
	public ArrayList<Triplet<Integer, Integer, Integer>> getHoles() {
		
		return holes;
	}

	
	

}
