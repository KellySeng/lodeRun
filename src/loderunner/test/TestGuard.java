package loderunner.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import loderunner.contrat.EditableScreenContrat;
import loderunner.contrat.EngineContrat;
import loderunner.contrat.EnvironmentContrat;
import loderunner.contrat.GuardContrat;
import loderunner.contrat.PlayerContrat;
import loderunner.impl.EditableScreenImpl;
import loderunner.impl.EngineImpl;
import loderunner.impl.EnvironmentImpl;
import loderunner.impl.GuardImpl;
import loderunner.impl.PlayerImpl;
import loderunner.services.Cell;
import loderunner.services.Command;
import loderunner.services.EngineService;
import loderunner.services.GuardService;
import loderunner.services.Pair;

public class TestGuard extends AbstractJeuTest{




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
				
		//créer un player qui est en position (3,2)
		PlayerImpl player = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(player);	
		playerContrat.init(enviContrat, 3, 2);
		

		//créer un guard qui est en position (0,2)
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
	
	
	
	/**
	 * En initialisation, le player est en position (3,2), un seul guard est en position(0, 2)
	 * le player ne bouge pas,  le guard va aller a droite
	 */
	@Test
	public void testGuardGoRightPrePositif() {



		initialisation();
		engine.setCmd(Command.Neutral);
		engine.step();
		assertEquals(engine.getGuards().get(0).getWdt(), 1);
		assertEquals(engine.getGuards().get(0).getHgt(), 2);


	}
	
	/**
	 * En initialisation, le player est  en position (3,2),un seul guard est en position(0, 2)
	 * player fait DigL, et apres il fait rien pendant 2 step, le guard tombe dans le trou
	 */
	@Test
	public void testGuardTomber() {
	
		initialisation();
		engine.setCmd(Command.DigL);
		engine.step();
		engine.setCmd(Command.Neutral);
		engine.step();
		engine.setCmd(Command.Neutral);
		engine.step();
		assertEquals(engine.getEnvironment().getCellNature(2, 1), Cell.HOL);
		assertEquals(engine.getGuards().get(0).getWdt(), 2);
		assertEquals(engine.getGuards().get(0).getHgt(), 1);

	}
	
	/**
	 * En initialisation, le player est  en position (3,2),un seul guard est en position(0, 2)
	 * player fait DigL, et apres il fait rien pendant 2 step, le guard tombe dans le trou
	 * et puis apres 3 step, le guard fait un ClimbRight
	 */
	@Test
	public void testGuardClimbRight() {
	
		initialisation();
		engine.setCmd(Command.DigL);
		engine.step();
		engine.setCmd(Command.Right);
		engine.step();
		engine.setCmd(Command.Neutral);
		engine.step();
		assertEquals(engine.getEnvironment().getCellNature(2, 1), Cell.HOL);
		assertEquals(engine.getGuards().get(0).getWdt(), 2);
		assertEquals(engine.getGuards().get(0).getHgt(), 1);
		engine.setCmd(Command.Neutral);
		engine.step();
		engine.setCmd(Command.Neutral);
		engine.step();
		engine.setCmd(Command.Neutral);
		engine.step();
		engine.setCmd(Command.Neutral);
		engine.step();
		engine.setCmd(Command.Neutral);
		engine.step();
		
		engine.setCmd(Command.Neutral);
		engine.step();
		
		System.out.println(engine.getGuards().get(0).getTimeInHole());

		assertEquals(engine.getGuards().get(0).getWdt(), 3);
		assertEquals(engine.getGuards().get(0).getHgt(), 2);
		

	}



}
