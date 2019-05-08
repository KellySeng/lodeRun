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
		
		EngineService engine = new EngineContrat(new EngineImpl());
		EditableScreenService screen =  new EditableScreenContrat(new EditableScreenImpl());
		String filename = args[0];
		DrawMap.drawmap(screen, filename);	
	
		EnvironmentService env = new EnvironmentContrat(new EnvironmentImpl());
		env.init( screen.getHeight(),screen.getWidth(), screen);
		
		List<Triplet<Integer,Integer,Boolean>> listGuards = new ArrayList<Triplet<Integer,Integer,Boolean>>();
		List<Pair<Integer,Integer>> listTresors = new ArrayList<Pair<Integer,Integer>>();
		Pair<Integer,Integer> player =null;	
		switch(filename) {
			case "mapTestEngine.txt" :  
				listGuards.add(new Triplet<Integer,Integer,Boolean>(0,2,false));
				listTresors.add(new Pair<Integer, Integer>(6,2));
				player = new Pair<Integer, Integer>(4, 2);
				break;
			case "mapTestPlayer.txt" :  
				listGuards.add(new Triplet<Integer,Integer,Boolean>(0,2,false));
				listTresors.add(new Pair<Integer, Integer>(6,2));
				player = new Pair<Integer, Integer>(4, 2);
				break;
			case "lvl1.txt" :
				listGuards.add(new Triplet<Integer,Integer,Boolean>(44,1,false));
				listGuards.add(new Triplet<Integer,Integer,Boolean>(1,1,true));
				listTresors.add(new Pair<Integer, Integer>(46,1));
				player = new Pair<Integer, Integer>(5, 1);
				break;
		}
		
		
		
		engine.init(env, player, listGuards, listTresors);
		
		while(engine.getStatus() == Status.Playing) {
			print(engine.getEnvironment());
			System.out.println("Score :" + engine.getPlayer().getScore() + " Vies : "+engine.getPlayer().getVie());
			System.out.println("Entrer une commande");
			engine.step();
		}
		
		if(engine.getStatus() == Status.Loss) {
			System.out.println("Fin de la partie. Vous avez perdu");
			System.out.println("Score final :" + engine.getPlayer().getScore()); 
		}
		
		if(engine.getStatus() == Status.Win) {
			System.out.println("Fin de la partie. Vous avez gagné");
			System.out.println("Score final :" + engine.getPlayer().getScore()); 
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
							if(((GuardService) c).isSpecial()) {
								System.out.print("&");break;
							}
							else {
								System.out.print("!");break;
							}
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