package loderunner.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import loderunner.services.Command;
import loderunner.services.EditableScreenService;
import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.ItemService;
import loderunner.services.PlayerService;
import loderunner.services.Status;

public class EngineImpl implements EngineService {
	
	EnvironmentService envi;
	PlayerService player;
	ArrayList<GuardService> guards;
	HashSet<ItemService> treasures;
	Status status;
	
	@Override
	public void init(EnvironmentService screen, PlayerService p, ArrayList<GuardService> g, HashSet<ItemService> t) {
		this.envi = screen;
		this.guards = g;
		this.player =p;
		this.treasures = t;
		
	}
	
	
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
		// TODO Auto-generated method stub
		return null;
	}

	public Status getStatus() {
		return status;
	}

	
	@Override
	public void step() {

		player.step();
	}


	

}
