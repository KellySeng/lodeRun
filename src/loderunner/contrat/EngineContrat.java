package loderunner.contrat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import loderunner.decorator.EngineDecorator;
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
			
				if(!(player_cell_content.contains(getPlayer()))) {
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
				if(!(guard_cell_content.contains(g))) {
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

		if(screen == null  || ! (0<=x && x<screen.getWidth()) || !(0<=y && y<screen.getHeight()) 
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


		//	Si au début d’un tour, un garde est dans la même case que le joueur, le jeu est perdu (cette règle nécessite de
		//	modifier la régle qui interdit à une case de contenir plus d’un personnage, par exemple, en établissant qu’une
		//	case ne peut contenir plus d’un garde, cette modification n’est pas demandée dans l’examen).





	}

}