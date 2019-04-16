package loderunner.decorator;

import java.util.Set;

import loderunner.services.Command;
import loderunner.services.EditableScreenService;
import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.Guard;
import loderunner.services.ItemService;
import loderunner.services.PlayerService;

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

	public Set<Guard> getGuards() {
		return delegate.getGuards();
	}

	public Set<ItemService> getTreasures() {
		return delegate.getTreasures();
	}

	public Command getNextCommand() {
		return delegate.getNextCommand();
	}

	public void init(EditableScreenService screen, int x, int y) {
		delegate.init(screen, x, y);
	}

	public void step() {
		delegate.step();
	}

}
