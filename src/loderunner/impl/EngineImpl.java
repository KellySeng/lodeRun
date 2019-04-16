package loderunner.impl;

import java.util.ArrayList;
import java.util.Set;

import loderunner.services.Command;
import loderunner.services.EditableScreenService;
import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.Guard;
import loderunner.services.ItemService;
import loderunner.services.PlayerService;
import loderunner.services.Status;

public class EngineImpl implements EngineService {
	
	EnvironmentService envi;
	PlayerService player;
	ArrayList<Guard> guards;
	Status status;
	
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command getNextCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	public Status getStatus() {
		return status;
	}
	@Override
	public void init(EditableScreenService screen, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step() {
		player.step();
		
		for(Guard guard : guards) {
			
		}
		
	}
	

}
