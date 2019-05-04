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
import loderunner.map.DrawMap;
import loderunner.services.Cell;
import loderunner.services.Command;
import loderunner.services.EngineService;
import loderunner.services.GuardService;
import loderunner.services.Pair;

public class TestPlayer extends AbstractJeuTest{



	private EnvironmentContrat enviContrat;


	private EngineService engine ;
	@Override
	public void beforeTests() {

		setEngine(new EngineContrat(new EngineImpl()));
	}

	public void drawMap() {
		EditableScreenImpl screen = new EditableScreenImpl();
		EditableScreenContrat  screenContrat = new EditableScreenContrat(screen);
	
		DrawMap.drawmap(screenContrat);

		//créer un environment
		EnvironmentImpl	envi = new EnvironmentImpl();
		enviContrat = new EnvironmentContrat(envi);
		enviContrat.init(screenContrat.getHeight(),screenContrat.getWidth());
		enviContrat.init(screenContrat);

	}

	public void initialisation() {
		drawMap();
		
		//créer un player qui est en pos (4,2)
		PlayerImpl player = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(player);	
		playerContrat.init(enviContrat, 4, 2);


		//créer un guard qui est en pos (0,2)
		GuardImpl guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		guardContrat.init(100, 0, 2, enviContrat, playerContrat);
		ArrayList<GuardService> guardsContrat = new ArrayList<GuardService>();
		guardsContrat.add(guardContrat);	

		//créer un tresor en pos(6,2)
		List<Pair<Integer, Integer>> listTresors = new ArrayList<Pair<Integer, Integer>> ();
		listTresors.add(new Pair(6,2));

		//Initialiser engine
		engine = getEngine();
		engine.init(enviContrat,playerContrat, guardsContrat, listTresors);

	}
	@Test
	public void testInitPrePositif() {

		initialisation();
	}


	@Test
	public void testPlayerGoRightPLTPositif() {


		initialisation(); // player en position (4,2), un seul guard est en position(0, 2)
		engine.setCmd(Command.Right);
		engine.step();
		assertEquals(engine.getPlayer().getWdt(),5);
		assertEquals(engine.getPlayer().getHgt(),2);
		assertEquals(engine.getGuards().get(0).getWdt(), 1);
		assertEquals(engine.getGuards().get(0).getHgt(), 2);


	}
	
	@Test
	public void testPlayerGoRightHDRPositif() {


		initialisation(); // player en position (4,2), un seul guard est en position(0, 2)
		engine.setCmd(Command.Left);
		engine.step();
		engine.setCmd(Command.Up);
		engine.step();
		engine.setCmd(Command.Up);
		engine.step();
		engine.setCmd(Command.Up);
		engine.step();
		engine.setCmd(Command.Right);
		engine.step();
		
		engine.setCmd(Command.Right);
		engine.step();
		
		engine.setCmd(Command.Right);
		engine.step();
		System.out.println("x="+engine.getPlayer().getWdt()+"y = "+ engine.getPlayer().getHgt());
//
//		assertEquals(engine.getPlayer().getWdt(),5);
//		assertEquals(engine.getPlayer().getHgt(),2);
//		assertEquals(engine.getGuards().get(0).getWdt(), 1);
//		assertEquals(engine.getGuards().get(0).getHgt(), 2);


	}


	@Test
	public void testPlayerGoLeftPrePositif() {


		initialisation();// player en position (4,2), un seul guard est en position(0, 2)
		engine.setCmd(Command.Left);
		engine.step();
		assertEquals(engine.getPlayer().getWdt(),3);
		assertEquals(engine.getPlayer().getHgt(),2);
		assertEquals(engine.getGuards().get(0).getWdt(), 1);
		assertEquals(engine.getGuards().get(0).getHgt(), 2);


	}


	@Test
	public void testPlayerGoUpPrePositif() {

		// En initiale,le player en position (4,2), un seul guard est en position(0, 2)
		initialisation();
	
		
		engine.setCmd(Command.Left);
		engine.step();
		engine.setCmd(Command.Up);
		engine.step();
		assertEquals(engine.getPlayer().getHgt(),3);
		assertEquals(engine.getPlayer().getWdt(),3);
		assertEquals(engine.getGuards().get(0).getWdt(), 2);
		assertEquals(engine.getGuards().get(0).getHgt(), 2);


	}
	@Test
	public void testPlayerWillFallPrePositif() {
		
		// En initiale,le player en position (4,2), un seul guard est en position(0, 2)
		initialisation();

		engine.setCmd(Command.Left);
		engine.step();

		engine.setCmd(Command.Up);
		engine.step();

		engine.setCmd(Command.Up);
		engine.step();

		engine.setCmd(Command.Up);
		engine.step();

		engine.setCmd(Command.Up);
		engine.step();
		System.out.println("x="+engine.getPlayer().getWdt()+"y = "+ engine.getPlayer().getHgt());

		engine.setCmd(Command.Left);
		engine.step();
		System.out.println("x="+engine.getPlayer().getWdt()+"y = "+ engine.getPlayer().getHgt());

		engine.setCmd(Command.Left);
		engine.step();

		System.out.println("x="+engine.getPlayer().getWdt()+"y = "+ engine.getPlayer().getHgt());

		engine.setCmd(Command.Neutral);
		engine.step();
		assertEquals(engine.getPlayer().getHgt(),4);

	}

	@Test
	public void testPlayerGoDownPrePositif() {
		
		// En initiale,le player en position (4,2), un seul guard est en position(0, 2)
		initialisation();
		engine.setCmd(Command.Left);
		engine.step();
		engine.setCmd(Command.Up);
		engine.step();
		engine.setCmd(Command.Down);
		engine.step();
		
		assertEquals(engine.getPlayer().getHgt(),2);
		assertEquals(engine.getPlayer().getWdt(),3);
		

	}


	/**
	 * En initialisation, le player est  en position (4,2),un seul guard est en position(0, 2)
	 * le player va aller a gauche et fait DigL, donc le guard tombe dans le trou
	 */
	@Test
	public void testPlayerDigLPrePositif() {

		initialisation();
		engine.setCmd(Command.Left);
		engine.step();
		engine.setCmd(Command.DigL);
		engine.step();
		assertEquals(engine.getEnvironment().getCellNature(2, 1), Cell.HOL);

		
		engine.setCmd(Command.Neutral);
		engine.step();
		
		assertEquals(engine.getGuards().get(0).getWdt(), 2);
		assertEquals(engine.getGuards().get(0).getHgt(), 1);

	}
	@Test
	public void testPlayerDigRPrePositif() {

		initialisation();// player en position (4,2),un seul guard est en position(0, 2)
		engine.setCmd(Command.DigR);
		engine.step();
		assertEquals(engine.getEnvironment().getCellNature(5, 1), Cell.HOL);
		assertEquals(engine.getGuards().get(0).getWdt(), 1);
		assertEquals(engine.getGuards().get(0).getHgt(), 2);

	}

}
