package loderunner.impl;


import java.util.HashSet;


import loderunner.services.CellContent;
import loderunner.services.EditableScreenService;
import loderunner.services.EnvironmentService;

public class EnvironmentImpl extends ScreenImpl  implements EnvironmentService{

	private HashSet<CellContent>[][] cell_content;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(int h,int w,EditableScreenService e) {

		super.init(h, w);
		
		cell_content = (HashSet<CellContent>[][]) new HashSet[w][h];
		for(int i = 0;i<w;i++) {
			
			for(int j=0;j<h;j++) {
				
				cell_content[i][j] = new HashSet<CellContent>();
			}
		}
		
		for(int i=0;i < e.getWidth();i++) {
			for(int j = 0;j<e.getHeight();j++) {
				ecran[i][j] = e.getCellNature(i, j);
			}
			
		}
		
	}

	@Override
	public HashSet<CellContent> getCellContent(int x, int y) {
		return cell_content[x][y];
	}

	
}
