package loderunner.test;
import loderunner.contrat.EngineContrat;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import loderunner.impl.EditableScreenImpl;
import loderunner.impl.EngineImpl;
import loderunner.services.Cell;
import loderunner.services.Command;
import loderunner.services.EditableScreenService;
import loderunner.services.EngineService;
import loderunner.services.Pair;

public class Test1 extends AbstractJeuTest{

	
	
	
	@Override
	public void beforeTests() {
		
		setEngine(new EngineContrat(new EngineImpl()));
	}
	
	
	@Test
	public void testInitPrePositif() {
		
		
		EditableScreenService screen = new EditableScreenImpl(5,5);
		for(int i = 0; i<5;i++)
			screen.setNature(i, 0, Cell.PLT);
		 List<Pair<Integer, Integer>> listGuards = new ArrayList<Pair<Integer, Integer>>();
		 listGuards.add(new Pair(1,1));
		List<Pair<Integer, Integer>> listTresors = new ArrayList<Pair<Integer, Integer>> ();
		listTresors.add(new Pair(4,1));
		
		EngineService engine = getEngine();
	
			engine.init(screen, 2, 1, listGuards, listTresors);
		
	}
	

	@Test
	public void testStepPreNegatif() {
		
		
		EditableScreenService screen = new EditableScreenImpl(5,5);
		for(int i = 0; i<5;i++) {
			screen.setNature(i, 0, Cell.MTL);
			
		}
		 List<Pair<Integer, Integer>> listGuards = new ArrayList<Pair<Integer, Integer>>();
		 listGuards.add(new Pair(1,1));
		List<Pair<Integer, Integer>> listTresors = new ArrayList<Pair<Integer, Integer>> ();
		listTresors.add(new Pair(4,1));
		
		EngineService engine = getEngine();
		engine.init(screen, 2, 1, listGuards, listTresors);
		
		engine.setCmd(Command.Right);
		engine.step();
		
	}
	
	
	
	

}
