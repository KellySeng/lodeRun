package loderunner.map;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import loderunner.impl.EditableScreenImpl;
import loderunner.impl.EngineImpl;
import loderunner.impl.PlayerImpl;
import loderunner.impl.ScreenImpl;
import loderunner.services.Cell;
import loderunner.services.EditableScreenService;
import loderunner.services.EngineService;
import loderunner.services.Pair;
import loderunner.services.PlayerService;
import loderunner.services.ScreenService;

public class DrawMap {

	public static void drawmap(EditableScreenService s) {
		List<String> l = new ArrayList<String>();
		Path source = Paths.get("src/loderunner/map/map1.txt");

		try {
			l = Files.readAllLines(source, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("fail with readAllLines");
			System.out.println(e.getMessage());
		}

		
		int width = l.get(l.size()-1).length();
		int height = l.size();

		System.out.println("height = "+ height+"width = "+ width);
		s.init(height, width);
		System.out.println("getWidth = "+ s.getWidth());
		System.out.println("getheight = "+ s.getHeight());


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

					default : 
						break;
					}
				}
			}

		}
		
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				System.out.print(s.getCellNature(i, j));
			}
			System.out.println();
			
		}
		



	}

}
