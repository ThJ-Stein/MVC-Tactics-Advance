package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NotLinkException;
import java.util.ArrayList;
import java.util.Arrays;

import view.Painter;

public class Battle {
	public static final String FOLDER = "resources/battles/";
	
	private Map map;
	private ArrayList<BattleUnit> enemies;

	private int allowedUnits;
	
	private Battle(Map map, int allowedUnits, ArrayList<BattleUnit> enemies) {
		this.map = map;
		this.allowedUnits = allowedUnits;
		this.enemies = enemies;
	}

	@Override
	public String toString() {
		return "Battle [map=" + map.getStringRepresentation() + ", enemies=" + enemies + ", allowedUnits=" + allowedUnits + "]";
	}

	public static Battle loadBattle(String path) throws IOException {
		String fullPath = FOLDER + path;
		
		BufferedReader mapReader = new BufferedReader(new FileReader(fullPath));
		
		Map map = null;
		int allowedUnits = -1;
		ArrayList<BattleUnit> enemies = new ArrayList<BattleUnit>();
		
		while (mapReader.ready()) {
			
			
			String[] line = mapReader.readLine().split(" ");
			
			switch (line[0]) {
			case "map":
				try {
					map = Map.loadMap(line[1]);
				} catch (IOException e) {
					//Map cannot be loaded...
					e.printStackTrace();
				}
				break;
				
			case "allowed_units":
				try {
					allowedUnits = Integer.parseInt(line[1]);
				} catch (NumberFormatException e) {
					//Allowed_units must be followed by a number
					e.printStackTrace();
				}
				break;
				
			case "enemies":
				String unitData = mapReader.readLine();
				while (!unitData.equals("")) {
					enemies.add(BattleUnit.parseBattleUnit(unitData));
					unitData = mapReader.readLine();
				}
				break;
			}
		}
		
		mapReader.close();
		return new Battle(map, allowedUnits, enemies);
	}

	public Map getMap() {
		return map;
	}
}
