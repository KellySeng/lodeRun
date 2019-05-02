package loderunner.impl;


import java.util.HashSet;


import loderunner.services.CellContent;
import loderunner.services.EditableScreenService;
import loderunner.services.EnvironmentService;

public class EnvironmentImpl extends ScreenImpl  implements EnvironmentService{

	private EditableScreenService e;
	private HashSet<CellContent>[][] cell_content;

	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(EditableScreenService e) {
		
		int h = e.getHeight();
		int w = e.getWidth();
		
		cell_content = (HashSet<CellContent>[][]) new HashSet[h][w];
		for(int i = 0;i<h;i++) {
			
			for(int j=0;j<w;j++) {
				
				cell_content[j][i] = new HashSet<CellContent>();
			}
		}
		
		for(int i=0;i < h;i++) {
			for(int j = 0;j<w;j++) {
				ecran[i][j] = e.getCellNature(i, j);
			}
			
		}
		this.e=e;
	}

	@Override
	public HashSet<CellContent> getCellContent(int x, int y) {
		return cell_content[x][y];
	}

	@Override
	public void addCellContent(int x, int y, CellContent cell) {
		cell_content[x][y].add(cell);		
	}
	

}
