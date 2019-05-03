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




	private EngineService engine ;
	@Override
	public void beforeTests() {

		setEngine(new EngineContrat(new EngineImpl()));
	}

	

	public void initialisation() {
		
		EditableScreenImpl screen = new EditableScreenImpl();
		EditableScreenContrat  screenContrat = new EditableScreenContrat(screen);
		screenContrat.init(5, 5);
		for(int i = 0; i<5;i++)
			screenContrat.setNature(i, 0, Cell.MTL);
		
			

		screenContrat.setNature(0, 1, Cell.PLT);
		screenContrat.setNature(1, 1, Cell.PLT);
		screenContrat.setNature(2, 1, Cell.PLT);
		screenContrat.setNature(3, 1, Cell.PLT);
		screenContrat.setNature(4, 1, Cell.PLT);

		//créer un environment
		EnvironmentImpl	envi = new EnvironmentImpl();
		EnvironmentContrat enviContrat = new EnvironmentContrat(envi);
		enviContrat.init(screenContrat.getHeight(),screenContrat.getWidth());
		enviContrat.init(screenContrat);
				
		//créer un player	
		PlayerImpl player = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(player);	
		playerContrat.init(enviContrat, 3, 2);
		

		//créer un guard
		GuardImpl guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		guardContrat.init(100, 0, 2, enviContrat, playerContrat);
		ArrayList<GuardService> guardsContrat = new ArrayList<GuardService>();
		guardsContrat.add(guardContrat);	
		
		//créer une liste de tresors 
		List<Pair<Integer, Integer>> listTresors = new ArrayList<Pair<Integer, Integer>> ();
		listTresors.add(new Pair(4,2));

		//Initialiser engine
		engine = getEngine();
		engine.init(enviContrat,playerContrat, guardsContrat, listTresors);

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
		guardContrat.init(100, 1, 1, enviContrat, playerContrat);
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
	public void testPlayerGoRightPrePositif() {


		initialisation(); // player en position (3,2), guard est en position(0, 2)
		engine.setCmd(Command.Right);
		engine.step();
		assertEquals(engine.getPlayer().getWdt(),4);
		assertEquals(engine.getGuards().get(0).getWdt(), 1);

	}


	@Test
	public void testPlayerGoLeftPrePositif() {


		initialisation();// player en position (3,2)
		engine.setCmd(Command.Left);
		engine.step();
		assertEquals(engine.getPlayer().getWdt(),2);


	}


	@Test
	public void testPlayerGoUpPrePositif() {


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
		playerContrat.init(enviContrat, 2, 1);
		

		//créer un guard
		GuardImpl guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		guardContrat.init(100, 1, 1, enviContrat, playerContrat);
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

		engine.setCmd(Command.Up);
		engine.step();
		assertEquals(engine.getPlayer().getHgt(),2);
		System.out.println("hgt = "+ engine.getPlayer().getHgt());

	}
	@Test
	public void testPlayerWillFallPrePositif() {
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
		playerContrat.init(enviContrat, 3, 3);
		

		//créer un guard
		GuardImpl guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		guardContrat.init(100, 1, 1, enviContrat, playerContrat);
		ArrayList<GuardService> guardsContrat = new ArrayList<GuardService>();
		guardsContrat.add(guardContrat);	
		
		//créer une liste de tresors 
		List<Pair<Integer, Integer>> listTresors = new ArrayList<Pair<Integer, Integer>> ();
		listTresors.add(new Pair(4,1));

		//Initialiser engine
		engine = getEngine();
		engine.init(enviContrat,playerContrat, guardsContrat, listTresors);

		engine.setCmd(Command.Neutral);
		engine.step();
		assertEquals(engine.getPlayer().getHgt(),2);

	}
	
	@Test
	public void testPlayerGoDownPrePositif() {
		EditableScreenImpl screen = new EditableScreenImpl();
		EditableScreenContrat  screenContrat = new EditableScreenContrat(screen);
		screenContrat.init(5, 5);
		for(int i = 0; i<5;i++)
			screenContrat.setNature(i, 0, Cell.MTL);
		
			

		screenContrat.setNature(3, 3, Cell.LAD);
		screenContrat.setNature(3, 2, Cell.LAD);
		screenContrat.setNature(3, 1, Cell.LAD);
		//créer un environment
		EnvironmentImpl	envi = new EnvironmentImpl();
		EnvironmentContrat enviContrat = new EnvironmentContrat(envi);
		enviContrat.init(screenContrat.getHeight(),screenContrat.getWidth());
		enviContrat.init(screenContrat);
				
		//créer un player	
		PlayerImpl player = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(player);	
		playerContrat.init(enviContrat, 2, 1);
		

		//créer un guard
		GuardImpl guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		guardContrat.init(100, 1, 1, enviContrat, playerContrat);
		ArrayList<GuardService> guardsContrat = new ArrayList<GuardService>();
		guardsContrat.add(guardContrat);	
		
		//créer une liste de tresors 
		List<Pair<Integer, Integer>> listTresors = new ArrayList<Pair<Integer, Integer>> ();
		listTresors.add(new Pair(4,1));

		//Initialiser engine
		engine = getEngine();
		engine.init(enviContrat,playerContrat, guardsContrat, listTresors);	
		engine.setCmd(Command.Right);
		engine.step();
		engine.setCmd(Command.Up);
		engine.step();
		engine.setCmd(Command.Down);
		engine.step();
		assertEquals(engine.getPlayer().getHgt(),1);
		assertEquals(engine.getPlayer().getWdt(),3);

	}
	

	@Test
	public void testPlayerDigLPrePositif() {
	
		initialisation();// player en position (3,2)
		engine.setCmd(Command.DigL);
		engine.step();
		assertEquals(engine.getEnvironment().getCellNature(2, 1), Cell.HOL);

	}
	@Test
	public void testPlayerDigRPrePositif() {
	
		initialisation();// player en position (3,2)
		engine.setCmd(Command.DigR);
		engine.step();
		assertEquals(engine.getEnvironment().getCellNature(4, 1), Cell.HOL);

	}

}
