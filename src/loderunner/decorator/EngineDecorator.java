package loderunner.decorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import loderunner.services.Command;
import loderunner.services.EditableScreenService;
import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.ItemService;
import loderunner.services.Pair;
import loderunner.services.PlayerService;
import loderunner.services.Status;
import loderunner.services.Triplet;

public class EngineDecorator implements EngineService{
	private EngineService delegate;

	

	protected EngineDecorator(EngineService s){
		this.delegate = s;
	}
	
	
	public EnvironmentService getEnvironment() {
		return delegate.getEnvironment();
	}

	public PlayerService getPlayer() {
		return delegate.getPlayer();
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
	public ArrayList<GuardService> getGuards() {
		return delegate.getGuards();
	}

	@Override
	public ArrayList<Triplet<Integer, Integer, Integer>> getHoles() {
		return delegate.getHoles();
	}


	@Override
	public Status getStatus() {
		return delegate.getStatus();
	}


	@Override
	public void setCmd(Command c) {
		delegate.setCmd(c);
		
	}


	@Override
	public void init(EnvironmentService envi, Pair<Integer, Integer> player, List<Pair<Integer, Integer>> listGuards,
			List<Pair<Integer, Integer>> listTresors) {
		delegate.init(envi, player, listGuards, listTresors);
	}


	@Override
	public void setEnTestMode() {
		delegate.setEnTestMode();		
	}


	

}
