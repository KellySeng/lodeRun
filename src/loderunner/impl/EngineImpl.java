package loderunner.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import loderunner.services.Cell;
import loderunner.services.Command;
import loderunner.services.EditableScreenService;
import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.ItemService;
import loderunner.services.ItemType;
import loderunner.services.Pair;
import loderunner.services.PlayerService;
import loderunner.services.Status;
import loderunner.services.Triplet;


public class EngineImpl implements EngineService {
	
	EnvironmentService envi;
	PlayerService player;
	ArrayList<GuardService> guards;
	HashSet<ItemService> treasures;
	Status status;
	EditableScreenService screen;
	int x; 
	int y;
	List<Pair<Integer, Integer>> listGuards;
	List<Pair<Integer, Integer>> listTresors;
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
	public ArrayList<GuardService> getGuards() {
		return guards;
	}

	@Override
	public Set<ItemService> getTreasures() {

		return treasures;
	}

	@Override
	public Command getNextCommand() {
		return null;
	}

	public Status getStatus() {
		return status;
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
			
			//Au moment où un trou est rebouché, si le joueur était dedans, le jeu est perdu.
			if(t==15 && player.getWdt()==h.getFirst() && player.getHgt()==h.getSecond()) {
				status = Status.Loss;
			}
			else if(t ==15) screen.setNature(h.getFirst(), h.getSecond(), Cell.PLT);
		}
		
		// Au moment où un trou est rebouché, si un garde était dedans, il revient à sa position initiale.
		
		player.step();
		
		for(GuardService guard : guards) {
			guard.step();
			
		}
		
	}

	@Override
	public ArrayList<Triplet<Integer, Integer, Integer>> getHoles() {
		
		return holes;
	}

	@Override
	public void init(EditableScreenService screen, int x, int y, List<Pair<Integer, Integer>> listGuards,
		List<Pair<Integer, Integer>> listTresors) {
		
		int id =0;
	
		player = new PlayerImpl();
		player.init(screen, x, y);
		for(Pair<Integer,Integer> l : listGuards) {
			// pas de gardes 
		}
		
		for(Pair<Integer,Integer> l : listTresors) {
			treasures.add(new ItemImpl(id, ItemType.Treasure, l.getL(), l.getR()));
			id++;
		}
		
		
		
	}
	

}
