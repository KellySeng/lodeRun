package loderunner.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import loderunner.services.CellContent;
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
		
		//Si au debut d’un tour, le joueur se trouve sur une case contenant un tresor,
		//ce tresor disparait
		int x = player.getWdt();
		int y = player.getHgt();
		
		Set<CellContent> set = envi.getCellContent(x, y);
		for(CellContent c :set) {
			if (c instanceof ItemService ) {
				set.remove(c);
			}
		}
		
		//	le temps de chaque trou est incrementee
		for(Triplet<Integer, Integer, Integer> h : holes) {
			int t = h.getThird();
			if(t<15) {
				h.setThird(t+1);
			}
		}
		
	
		player.step();
		
		for(Guard guard : guards) {
			guard.step();
			
		}
		
	//	tous les trous dont la troisieme coordonnees vaut 15 sont rebouches.
		
		for(Triplet<Integer, Integer, Integer> h : holes) {
			int t = h.getThird();
			if(t==15) {
				//	si le joueur etait dedans
				if(h.getFirst() == player.getWdt() && h.getSecond() == player.getHgt()) {
					//	le jeu est perdu
					status = Status.Loss;
				}
				

				for(Guard guard : guards) {
					
					//si un garde ´ etait dedans, il revient a sa position initiale
					if(h.getFirst() == guard.getWdt() && h.getSecond() == guard.getHgt()) {
						
					}
					
				}
				
			}
		}
		
		
	}

	@Override
	public ArrayList<Triplet<Integer, Integer, Integer>> getHoles() {
		
		return holes;
	}

	
	

}
