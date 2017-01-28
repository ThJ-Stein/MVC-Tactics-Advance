package model;

import java.io.IOException;

public class Battle {
	private Map map;
	
	public Battle(String mapName) throws IOException {
		map = Map.loadMap(mapName);
		System.out.println(map);
	}
}
