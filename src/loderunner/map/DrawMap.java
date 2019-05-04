package loderunner.main;

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
import loderunner.services.PlayerService;
import loderunner.services.ScreenService;

public class Main {

	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		Path source = Paths.get("filename");
		int i, j;
		try {
			l = Files.readAllLines(source, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("fail with readAllLines");
			System.out.println(e.getMessage());
		}
		
		EditableScreenService s = new EditableScreenImpl();
		s.init(l.size(), l.get(0).length());
		for (i = 0; i < l.size(); i++) {
			for (j = 0; j < l.get(0).length(); j++) {
				switch ((l.get(i).charAt(j))) {
					case 'E' :  
						s.setNature(i, j, Cell.EMP); 
						break;
					
					case 'P' :
						s.setNature(i, j, Cell.PLT);
						break;
						
					case 'H' : 
						s.setNature(i, j, Cell.HOL);
						break;
					
					case 'L' : 
						s.setNature(i, j, Cell.LAD);
						break;
					
					case 'h' : 
						s.setNature(i, j, Cell.HDR);
						break;
					
					case 'M' :
						s.setNature(i, j, Cell.MTL);	
						break;
					
					default : break;
				}
			}

		}
		
		PlayerService player = new PlayerImpl();
		EngineService engine = new EngineImpl();
		//engine.init(s, x, y, listGuards, listTresors);
		player.init(s,1,3);
		
		
		
	}
	
}
