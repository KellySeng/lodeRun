package loderunner.map;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import loderunner.services.Cell;
import loderunner.services.EditableScreenService;

public class DrawMap {


	public static void drawmap(EditableScreenService s, String filename) {
		List<String> l = new ArrayList<String>();
		Path source = Paths.get("src/loderunner/map/"+filename);

		try {
			l = Files.readAllLines(source, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("fail with readAllLines");
			System.out.println(e.getMessage());
		}

		
		int width = l.get(l.size()-1).length();
		int height = l.size();

		s.init(height, width);
	
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < l.get(j).length(); i++) {
				if(!l.get(j).isEmpty()) {
					switch ((l.get(j).charAt(i))) {
					case 'P' :
						s.setNature(i, height-1-j, Cell.PLT);
						break;

					case 'L' : 
						s.setNature(i, height-1-j, Cell.LAD);
						break;

					case 'H' : 
						s.setNature(i, height-1-j, Cell.HDR);
						break;

					case 'M' :
						s.setNature(i, height-1-j, Cell.MTL);	
						break;
						
					case 'E' :
						s.setNature(i, height-1-j, Cell.EMP);	
						break;

					default : 
						break;
					}
					
				}
			}
		}
		
//		for (int j = 0; j < height; j++) {
//			for (int i = 0; i < width; i++) {
//				System.out.print(s.getCellNature(i, j));
//			}
//			System.out.println();
//		}
//		



	}

}


