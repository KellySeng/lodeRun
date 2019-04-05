package loderunner.services;

import java.util.HashSet;

public interface EnvironmentService extends ScreenService {

	/*
	 * Invariants
	 */
	public HashSet<CellContent>  cellContent(int x, int y);
}
