package loderunner.main;

import loderunner.impl.EngineImpl;
import loderunner.services.EngineService;

public class MainClass {

	public static void main(String[] args) {
		
		EngineService engine = new EngineImpl();
		//engine.init(screen, player, listGuards, listTresors);
	}
}
