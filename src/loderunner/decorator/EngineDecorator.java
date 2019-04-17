package loderunner.decorator;

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

public class EngineDecorator implements EngineService{
	private EngineService delegate;

	protected EngineDecorator(EngineService s){
		this.delegate = s;
	}
	
	@Override
	public void init(EnvironmentService screen, PlayerService p, ArrayList<GuardService> g, HashSet<ItemService> t) {
		delegate.init(screen, p, g, t);
	}
	public EnvironmentService getEnvironment() {
		return delegate.getEnvironment();
	}

	public PlayerService getPlayer() {
		return delegate.getPlayer();
	}

	public ArrayList<GuardService> getGuards() {
		return delegate.getGuards();
	}

	public Set<ItemService> getTreasures() {
		return delegate.getTreasures();
	}

	public Command getNextCommand() {
		return delegate.getNextCommand();
	}

	public void step() {
		delegate.step();
	}

	@Override
	public Status getStatus() {
		return delegate.getStatus();
	}




}
