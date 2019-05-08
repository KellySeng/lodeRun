package loderunner.main;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import loderunner.contrat.EditableScreenContrat;
import loderunner.contrat.EngineContrat;
import loderunner.contrat.EnvironmentContrat;
import loderunner.impl.EditableScreenImpl;
import loderunner.impl.EngineImpl;
import loderunner.impl.EnvironmentImpl;
import loderunner.impl.PlayerImpl;
import loderunner.impl.ScreenImpl;
import loderunner.map.DrawMap;
import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.EditableScreenService;
import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.ItemService;
import loderunner.services.Pair;
import loderunner.services.PlayerService;
import loderunner.services.ScreenService;
import loderunner.services.Status;
import loderunner.services.Triplet;


public class MainClass {

	public static void main(String[] args) {
		
		EngineService engine = new EngineImpl();
		EditableScreenService screen =  new EditableScreenContrat(new EditableScreenImpl());
		DrawMap.drawmap(screen, "mapTestEngine.txt");	
	
		EnvironmentService env = new EnvironmentContrat(new EnvironmentImpl());
		env.init( screen.getHeight(),screen.getWidth(), screen);
		
		List<Triplet<Integer,Integer,Boolean>> listGuards = new ArrayList<Triplet<Integer,Integer,Boolean>>();
		List<Pair<Integer,Integer>> listTresors = new ArrayList<Pair<Integer,Integer>>();
		
		listGuards.add(new Triplet<Integer,Integer,Boolean>(0,2,false));
	//	listGuards.add(new Pair(46,1));
		
		listTresors.add(new Pair<Integer,Integer>(6,2));
		
		Pair<Integer,Integer> player = new Pair<Integer,Integer>(4,2);
		engine.init(env, player, listGuards, listTresors);
		
		while(engine.getStatus() == Status.Playing) {
			print(engine.getEnvironment());
			System.out.println("Entrer une commande");
			engine.step();
		}
		
		if(engine.getStatus() == Status.Loss) {
			System.out.println("Fin de la partie. Vous avez perdu");
		}
		
		if(engine.getStatus() == Status.Win) {
			System.out.println("Fin de la partie. Vous avez gagn�");
		}
	}
	

		public static void print(EnvironmentService env) {
			for (int i =  env.getHeight()-1; i >= 0; i--) {
				for (int j = 0 ; j < env.getWidth(); j++) {
					for(CellContent c : env.getCellContent(j, i)) {
						if(c instanceof PlayerService) {
							System.out.print("@");break;
						}
						else if(c instanceof GuardService) {
							System.out.print("!");break;
						}
						else if(c instanceof ItemService) {
							System.out.print("?");break;
						}
					}
					
					if(env.getCellContent(j, i).size() ==0) {
						switch (env.getCellNature(j, i)) {
						case HDR :  System.out.print("^");
							break;
						case MTL :  System.out.print("-");
							break;
						case PLT :  System.out.print("~");
							break;
						case LAD :  System.out.print("+"); 
							break;
						default:  System.out.print(" ");
							break;
						}
					}
				}
				System.out.println();
		}
	}
}
