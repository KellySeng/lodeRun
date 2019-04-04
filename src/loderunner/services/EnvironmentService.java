package loderunner.services;

import java.util.HashMap;
import java.util.Set;

public interface EnvironmentService extends ScreenService {

	/*
	 * Invariants
	 */
	public HashMap<CharacterService,ItemService> cellContent(int x, int y);
}
