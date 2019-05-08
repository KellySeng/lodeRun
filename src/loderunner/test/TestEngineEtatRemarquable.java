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
import loderunner.map.DrawMap;
import loderunner.services.Cell;
import loderunner.services.Command;
import loderunner.services.EngineService;
import loderunner.services.GuardService;
import loderunner.services.Pair;
import loderunner.services.Status;

public class TestEngineEtatRemarquable extends AbstractJeuTest{



	private EnvironmentContrat enviContrat;

	
	private EngineService engine ;
	@Override
	public void beforeTests() {

		setEngine(new EngineContrat(new EngineImpl()));

		EditableScreenImpl screen = new EditableScreenImpl();
		EditableScreenContrat  screenContrat = new EditableScreenContrat(screen);

		DrawMap.drawmap(screenContrat,"mapTestEngine.txt");

		//créer un environment
		EnvironmentImpl	envi = new EnvironmentImpl();
		enviContrat = new EnvironmentContrat(envi);
		enviContrat.init(screenContrat.getHeight(),screenContrat.getWidth(),screenContrat);

	}
	

	
	public void initialisation() {
		
		//créer un player qui est en position (3,2)
		PlayerImpl player = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(player);	
		playerContrat.init(enviContrat, 3, 2);
		

		//créer un guard qui est en position (0,2)
		GuardImpl guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		guardContrat.init( 0, 2, enviContrat, playerContrat);
		ArrayList<GuardService> guardsContrat = new ArrayList<GuardService>();
		guardsContrat.add(guardContrat);	
		
		//créer un tresor qui est en pos (4,2)
		List<Pair<Integer, Integer>> listTresors = new ArrayList<Pair<Integer, Integer>> ();
		listTresors.add(new Pair(4,2));

		//Initialiser engine
		engine = getEngine();
		engine.init(enviContrat,playerContrat, guardsContrat, listTresors);

	}
	
	
	

}
