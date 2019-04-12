package loderunner.impl;


import java.util.HashSet;


import loderunner.services.CellContent;
import loderunner.services.EditableScreenService;
import loderunner.services.EnvironmentService;

public class EnvironmentImpl extends ScreenImpl  implements EnvironmentService{

	private EditableScreenService e;
	
	public EnvironmentImpl(int h, int w) {
		super(h, w);
	}


	@Override
	public void init(EditableScreenService e) {
		this.e=e;
	}

	@Override
	public HashSet<CellContent> getCellContent(int x, int y) {
		HashSet<CellContent> l = new HashSet<CellContent>();
		for(int u = 0 ; u<getWidth();u++ ) {
			for(int v = 0; v<getHeight(); v++) {
				
			}
		}
		return null;
	}
	

}
