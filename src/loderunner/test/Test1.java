package loderunner.test;
import loderunner.contrat.EditableScreenContrat;
import loderunner.contrat.EngineContrat;
import loderunner.contrat.EnvironmentContrat;
import loderunner.contrat.GuardContrat;
import loderunner.contrat.PlayerContrat;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import loderunner.impl.EditableScreenImpl;
import loderunner.impl.EngineImpl;
import loderunner.impl.EnvironmentImpl;
import loderunner.impl.GuardImpl;
import loderunner.impl.PlayerImpl;
import loderunner.services.Cell;
import loderunner.services.Command;
import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.Pair;
import loderunner.services.PlayerService;

public class Test1 extends AbstractJeuTest{




	@Override
	public void beforeTests() {

		setEngine(new EngineContrat(new EngineImpl()));
	}

	

	@Test
	public void testInitPrePositif() {

		EditableScreenImpl screen = new EditableScreenImpl();
		EditableScreenContrat  screenContrat = new EditableScreenContrat(screen);
		screenContrat.init(5, 5);
		for(int i = 0; i<5;i++)
			screenContrat.setNature(i, 0, Cell.MTL);
		
		
		//créer un environment
		EnvironmentImpl	envi = new EnvironmentImpl();
		EnvironmentContrat enviContrat = new EnvironmentContrat(envi);
		enviContrat.init(screenContrat.getHeight(),screenContrat.getWidth());
		enviContrat.init(screenContrat);
				
		//créer un player	
		PlayerImpl player = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(player);	
		playerContrat.init(enviContrat, 3, 1);
		

		//créer un guard
		GuardImpl guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);	
		guardContrat.init(enviContrat,1, 1);
		ArrayList<GuardService> guardsContrat = new ArrayList<GuardService>();
		guardsContrat.add(guardContrat);	
		
		//créer une liste de tresors 
		List<Pair<Integer, Integer>> listTresors = new ArrayList<Pair<Integer, Integer>> ();
		listTresors.add(new Pair(4,1));

		//Initialiser engine
		EngineService engine = getEngine();
		engine.init(enviContrat,playerContrat, guardsContrat, listTresors);

	}


	@Test
	public void testGoRightPrePositif() {

		EditableScreenImpl screen = new EditableScreenImpl();
		EditableScreenContrat  screenContrat = new EditableScreenContrat(screen);
		screenContrat.init(5, 5);
		for(int i = 0; i<5;i++)
			screenContrat.setNature(i, 0, Cell.MTL);
		
		
		//créer un environment
		EnvironmentImpl	envi = new EnvironmentImpl();
		EnvironmentContrat enviContrat = new EnvironmentContrat(envi);
		enviContrat.init(screenContrat.getHeight(),screenContrat.getWidth());
		enviContrat.init(screenContrat);
				
		//créer un player	
		PlayerImpl player = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(player);	
		playerContrat.init(enviContrat, 3, 1);
		

		//créer un guard
		GuardImpl guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);	
		guardContrat.init(enviContrat,1, 1);
		ArrayList<GuardService> guardsContrat = new ArrayList<GuardService>();
		guardsContrat.add(guardContrat);	
		
		//créer une liste de tresors 
		List<Pair<Integer, Integer>> listTresors = new ArrayList<Pair<Integer, Integer>> ();
		listTresors.add(new Pair(4,1));

		//Initialiser engine
		EngineService engine = getEngine();
		engine.init(enviContrat,playerContrat, guardsContrat, listTresors);

		engine.setCmd(Command.Right);
		engine.step();

	}


	@Test
	public void testGoLeftPrePositif() {

		EditableScreenImpl screen = new EditableScreenImpl();
		EditableScreenContrat  screenContrat = new EditableScreenContrat(screen);
		screenContrat.init(5, 5);
		for(int i = 0; i<5;i++)
			screenContrat.setNature(i, 0, Cell.MTL);
		
		
		//créer un environment
		EnvironmentImpl	envi = new EnvironmentImpl();
		EnvironmentService enviContrat = new EnvironmentContrat(envi);
		enviContrat.init(screenContrat.getHeight(),screenContrat.getWidth());
		enviContrat.init(screenContrat);
				
		//créer un player	
		PlayerImpl player = new PlayerImpl();
		PlayerService playerContrat = new PlayerContrat(player);	
		playerContrat.init(enviContrat, 3, 1);
		

		//créer un guard
		GuardImpl guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);	
		guardContrat.init(enviContrat,1, 1);
		ArrayList<GuardService> guardsContrat = new ArrayList<GuardService>();
		guardsContrat.add(guardContrat);	
		
		//créer une liste de tresors 
		List<Pair<Integer, Integer>> listTresors = new ArrayList<Pair<Integer, Integer>> ();
		listTresors.add(new Pair(4,1));

		//Initialiser engine
		EngineService engine = getEngine();
		engine.init(enviContrat,playerContrat, guardsContrat, listTresors);


		engine.setCmd(Command.Left);
		engine.step();

	}


	@Test
	public void testGoUpPrePositif() {

		EditableScreenImpl screen = new EditableScreenImpl();
		EditableScreenContrat  screenContrat = new EditableScreenContrat(screen);
		screenContrat.init(5, 5);
		for(int i = 0; i<5;i++)
			screenContrat.setNature(i, 0, Cell.MTL);
		
		screenContrat.setNature(3, 1, Cell.LAD);
		screenContrat.setNature(3, 2, Cell.LAD);
		screenContrat.setNature(3, 3, Cell.LAD);

		//créer un environment
		EnvironmentImpl	envi = new EnvironmentImpl();
		EnvironmentContrat enviContrat = new EnvironmentContrat(envi);
		enviContrat.init(screenContrat.getHeight(),screenContrat.getWidth());
		enviContrat.init(screenContrat);
				
		//créer un player	
		PlayerImpl player = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(player);	
		playerContrat.init(enviContrat, 3, 1);
		

		//créer un guard
		GuardImpl guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);	
		guardContrat.init(enviContrat,1, 1);
		ArrayList<GuardService> guardsContrat = new ArrayList<GuardService>();
		guardsContrat.add(guardContrat);	
		
		//créer une liste de tresors 
		List<Pair<Integer, Integer>> listTresors = new ArrayList<Pair<Integer, Integer>> ();
		listTresors.add(new Pair(4,1));

		//Initialiser engine
		EngineService engine = getEngine();
		engine.init(enviContrat,playerContrat, guardsContrat, listTresors);



		engine.setCmd(Command.Up);
		engine.step();

	}
	



}
