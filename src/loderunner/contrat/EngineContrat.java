package loderunner.contrat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import loderunner.decorator.EngineDecorator;
import loderunner.services.Cell;
import loderunner.services.CellContent;
import loderunner.services.EditableScreenService;
import loderunner.services.EngineService;
import loderunner.services.EnvironmentService;
import loderunner.services.GuardService;
import loderunner.services.ItemService;
import loderunner.services.ItemType;
import loderunner.services.Pair;
import loderunner.services.PlayerService;
import loderunner.services.Status;

public class EngineContrat extends EngineDecorator{

	public EngineContrat(EngineService s) {
		super(s);
	}

	public void checkInvariant() {

		//	l’observateur Environment doit être synchronisé avec les observateurs 'Guards', Player et Treasure. Ainsi
		//	si G ∈ Guards(E) est tel que Hgt(G)=4 et Col(G)=3, alors G ∈ CellContent(Environment(E),3,4).

		if( getEnvironment()!=null) {

			if(getPlayer()!=null) {

				HashSet<CellContent> player_cell_content = getEnvironment().getCellContent(getPlayer().getWdt(), getPlayer().getHgt());			
				boolean containsPlayer = false;
				for(CellContent c:  player_cell_content) {
						if(c instanceof PlayerService) {
							containsPlayer = true;
						}
					}
				if(!containsPlayer) {
					throw new InvariantError("player not synchronized with env");
				}
			}


			for(ItemService t : getTreasures()) {

				HashSet<CellContent> treasure_cell_content = getEnvironment().getCellContent(t.getCol(), t.getHgt());
				if(!(treasure_cell_content.contains(t))) {
					throw new InvariantError("treasure not synchronized with env");
				}
			}

			for(GuardService g : getGuards()) {
				HashSet<CellContent> guard_cell_content = getEnvironment().getCellContent(g.getWdt(), g.getHgt());
				boolean containsGuard = false;
				int id_guard = -1 ;
				for(CellContent c:  guard_cell_content) {
						if(c instanceof GuardService) {
							containsGuard = true;
							id_guard = ((GuardService) c).getId();
						}
					}
				
				
				if(!(containsGuard && g.getId() ==id_guard )) {
					throw new InvariantError("treasure not synchronized with env");
				}
			}

			if(getTreasures().size() == 0) {
				if(!(getStatus() == Status.Win)) {
					throw new InvariantError("game is already won");
				}
			}

		}
	}

	public void init(EditableScreenService screen, int x, int y, List<Pair<Integer, Integer>> listGuards,
			List<Pair<Integer, Integer>> listTresors) {

		if(screen == null || !screen.isPlayable() || ! (0<=x && x<screen.getWidth()) || !(0<=y && y<screen.getHeight()) 
				|| listGuards == null || listTresors == null) {
			throw new PreconditionError("init error");
		}


		checkInvariant();
		super.init(screen, x, y, listGuards, listTresors);


		checkInvariant();


	}

	@Override
	public void init(EnvironmentService envi, PlayerService player, ArrayList<GuardService> listGuards,
			List<Pair<Integer, Integer>> listTresors) {
		
		
		// pre : envi.getCellNature(player.getWdt(), player.getHgt()) == Cell.EMP
		if(envi.getCellNature(player.getWdt(), player.getHgt())!= Cell.EMP) {
			throw new PreconditionError("Engine init error , la case de player n'est pas empty");
		}
		
		//pre : forall g in listGuards, envi.getCellNature(g.getWdt(), g.getHgt()) == Cell.EMP
		for(GuardService guard : listGuards) {
			if(envi.getCellNature(guard.getWdt(),guard.getHgt())!=Cell.EMP) {
				throw new PreconditionError("Engine init error , la case du guard n'est pas empty");

			}
		}
		
		// pre : forall t in listTresors, envi.getCellNature(t.getWdt(), t.getHgt()-1) == Cell.PLT
		for(Pair<Integer, Integer> t :listTresors ) {
			if(!(envi.getCellNature(t.getL(),t.getR()-1)== Cell.PLT
					||envi.getCellNature(t.getL(),t.getR()-1)== Cell.MTL) ) {
				throw new PreconditionError("Engine init error , la case dessous de tresor  est libre");

			}
		}
		checkInvariant();

		super.init(envi, player, listGuards, listTresors);
		checkInvariant();

		
	}

	public void step() {

		checkInvariant();

		//capture
		HashSet<CellContent> t_cell_content_atpre = getEnvironment().getCellContent(getPlayer().getWdt(), getPlayer().getHgt());

		super.step();


		checkInvariant();
		//Si au début d’un tour, le joueur se trouve sur une case contenant un trésor, ce trésor disparait.
		for(CellContent t : t_cell_content_atpre) {
			if(t== ItemType.Treasure) {
				if(getTreasures().contains(t)) {
					throw new PostconditionError("treasure must dissapear");
				}
			}
		}

		if(getTreasures().isEmpty()) {
			if(!(getStatus() == Status.Loss)) {
				throw new PostconditionError("Game should be lost");
			}
		}
		
//		Si au début d’un tour, un garde est dans la même case que le joueur, le jeu est perdu (cette règle nécessite de
			//	modifier la régle qui interdit à une case de contenir plus d’un personnage, par exemple, en établissant qu’une
			//	case ne peut contenir plus d’un garde, cette modification n’est pas demandée dans l’examen).
		for(GuardService g : getGuards()) {
			if(g.getHgt() == getPlayer().getHgt() && g.getWdt() == getPlayer().getWdt()) {
				if(!(getStatus() == Status.Loss)) {
					throw new PostconditionError("Game should be lost");
				}
			}
		}




	}

}