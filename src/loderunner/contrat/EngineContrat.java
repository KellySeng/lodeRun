package loderunner.contrat;

import loderunner.decorator.EngineDecorator;
import loderunner.services.EngineService;

class EngineContrat extends EngineDecorator{

	protected EngineContrat(EngineService s) {
		super(s);
	}

}
