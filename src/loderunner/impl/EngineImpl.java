package loderunner.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import loderunner.services.CellContent;
import loderunner.services.Command;
import loderunner.services.EditableScreenService;
import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.ItemService;
import loderunner.services.ItemType;
import loderunner.services.Pair;
import loderunner.services.PlayerService;
import loderunner.services.Status;
import loderunner.services.Triplet;


public class EngineImpl implements EngineService {

	EnvironmentService envi;
	PlayerService player;
	ArrayList<GuardService> guards;
	HashSet<ItemService> treasures;
	Status status;
	EditableScreenService screen;
	List<Pair<Integer, Integer>> listTresors;
	ArrayList<Triplet<Integer,Integer,Integer>> holes;

	
 	List<Pair<Integer, Integer>> listGuardsInitiaux;
 	int xPlayerInit;
 	int yPlayerInit;
	Command nextCommand;



	/*
	 * Pour le test  
	 */
	public void setCmd(Command c) {
		nextCommand = c;
	}

	@Override
	public EnvironmentService getEnvironment() {
		return envi;
	}

	@Override
	public PlayerService getPlayer() {
		return player;
	}

	@Override
	public ArrayList<GuardService> getGuards() {
		return guards;
	}

	@Override
	public Set<ItemService> getTreasures() {

		return treasures;
	}
	
	public void reinitialisePos() {
		player.setPos(xPlayerInit, yPlayerInit);

		for(GuardService guard : guards) {

			int index = guards.indexOf(guard);
			int guardX_init = listGuardsInitiaux.get(index).getL();
			int guardY_init = listGuardsInitiaux.get(index).getR();
			guard.setPos(guardX_init, guardY_init);
		}
	}

	@Override
	public Command getNextCommand() {
		//		String command ;
		//		Scanner scanIn = new Scanner(System.in);
		//		
		//		command = scanIn.nextLine();
		//		
		//		switch(command) {
		//		case "d" : return Command.Right;
		//		case "q" : return Command.Left;
		//		case "z" : return Command.Up;
		//		case "s" : return Command.Down;
		//		case "o" : return Command.DigL;
		//		case "p" : return Command.DigR;
		//		default :  return Command.Neutral;
		//		}
		//		
		return nextCommand;

	}

	public Status getStatus() {
		return status;
	}



	@Override
	public void step() {

		int x = player.getWdt();
		int y = player.getHgt();


		//Si au debut dâ€™un tour, le joueur se trouve sur une case contenant un tresor,
		//ce tresor disparait		
		Set<CellContent> set = envi.getCellContent(x, y);

		Set<CellContent> tresorARemove = new HashSet<CellContent>();
		for(CellContent c :set) {
			if (c instanceof ItemService ) {
				tresorARemove.add(c);
				treasures.remove(c);
				player.increScore();
			}
		}
		set.removeAll(tresorARemove);


		//	le temps de chaque trou est incrementee
		for(Triplet<Integer, Integer, Integer> h : holes) {
			int t = h.getThird();
			if(t<15) {
				h.setThird(t+1);
			}
		}


		player.step();

		for(GuardService guard : guards) {
			guard.step();

		}

		//	tous les trous dont la troisieme coordonnees vaut 15 sont rebouches.

		for(Triplet<Integer, Integer, Integer> h : holes) {
			int t = h.getThird();
			if(t==15) {


				envi.fill(h.getFirst(), h.getSecond());
				//	si le joueur etait dedans
				if(h.getFirst() == player.getWdt() && h.getSecond() == player.getHgt()) {
					player.decreVie();
					reinitialisePos() ;
					
					//	le jeu est perdu
					if(player.getVie()==0) {
						status = Status.Loss;
						System.out.println("le joueur eÌ�tait dedans quand un trou est rebouche, le jeu est perdu." );
					}
				}

				//si un garde Ã©tait dedans, il revient a sa position initiale
				for(GuardService guard : guards) {	
					if(h.getFirst() == guard.getWdt() && h.getSecond() == guard.getHgt()) {
						System.out.println("le trou est rebouché, le guard revient à la position initiale");
					
						int index = guards.indexOf(guard);
						int guardX_init = listGuardsInitiaux.get(index).getL();
						int guardY_init = listGuardsInitiaux.get(index).getR();
						guard.setPos(guardX_init, guardY_init);
						
					}

				}

			}
		}



		//	Le jeu est gagnÃ© quand il nâ€™y a plus de trÃ©sors.
		if(treasures.size() == 0) {	
			System.out.println("Le jeu est gagné");
			status = Status.Win;
		}


		//Si au debut dâ€™un tour, un garde est dans la meme case que le joueur, le jeu est perdu 		
				for(GuardService guard : guards) {
					if(guard.getWdt() == x && guard.getHgt() == y){
						System.out.println("player est attaqué par un guard");
						player.decreVie();	
						reinitialisePos();
						//	le jeu est perdu
						if(player.getVie()==0) {
							status = Status.Loss;
						}
					}
				}
				

		
		

	}

	@Override
	public ArrayList<Triplet<Integer, Integer, Integer>> getHoles() {

		return holes;
	}


	@Override
	public void init(EnvironmentService screen, PlayerService joueur, ArrayList<GuardService> listGuards,
			List<Pair<Integer, Integer>> listTresors) {
		holes = new ArrayList<Triplet<Integer,Integer,Integer>>();
		int id =0;

		envi = screen;

		xPlayerInit =  joueur.getWdt();
		yPlayerInit = joueur.getHgt();
		player = joueur;
		player.init(envi, joueur.getWdt(), joueur.getHgt(), this);


		guards = listGuards;

		//capture les pos initials pour les guards
	  listGuardsInitiaux = new 	ArrayList<Pair<Integer, Integer>>();
	  for(GuardService g :listGuards) {
		  listGuardsInitiaux.add(new Pair(g.getWdt(),g.getHgt()));
	  }

		for(GuardService guard : guards) {
			envi.getCellContent(guard.getWdt(), guard.getHgt()).add(guard);

		}


		treasures = new HashSet<ItemService>();
		for(Pair<Integer,Integer> l : listTresors) {
			ItemImpl tresor = new ItemImpl(id, ItemType.Treasure, l.getL(), l.getR());
			treasures.add(tresor);
			id++;
			envi.getCellContent(l.getL(), l.getR()).add(tresor);
		}

		holes = new ArrayList<Triplet<Integer,Integer,Integer>>();
		status = Status.Playing;


	}




}
