package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
	// tiles is an array of ROWS. A vertical array of horizontal arrays. So it's Tile[y][x].
	private Tile[][] tiles = null;
	private List<BattleUnit> units = null;
	
	public static String FOLDER = "resources/maps/";
	
	public Map(int width, int height) {
		tiles = new Tile[height][width];
		units = new ArrayList<BattleUnit>();
	}
	
	public void setTile(Tile tile, int x, int y) {
		tiles[y][x] = tile;
	}
	
	public void placeUnit(BattleUnit unit, int x, int y) {
		unit.setPosition(x, y);
		getTile(x, y).setUnit(unit);
		units.add(unit);
	}
	
	public Tile getTile(int x, int y) {
		return tiles[y][x];
	}
	
	public int getHeight() {
		return tiles.length;
	}
	
	public int getWidth() {
		return tiles[0].length;
	}
	
	public boolean isInBounds(int x, int y) {
		return (x >= 0 && x < getWidth() && y >= 0 && y < getHeight());
	}
	
	public boolean isWalkable(int x, int y) {
		return (isInBounds(x, y) && getTile(x, y).isWalkable());
	}
	
	public String getStringRepresentation() {
		String mapString = "\n";
		
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				int tile = getTile(x, y).getHeight();
				mapString += (tile > -1) ? tile : "_";
				if (x + 1 < getWidth()) {
					mapString += " ";
				}
			}
			mapString += "\n";
		}
		return mapString;
	}

	/* Reads a file and builds a map instance from it.
	 * Requires the path given to be a valid path.
	 */
	public static Map loadMap(String mapName) throws IOException {
		String path = FOLDER + mapName;
		
		BufferedReader mapReader = new BufferedReader(new FileReader(path));
		
		List<String[]> lines = new ArrayList<String[]>();
		
		String line = null;
		do {
			line = mapReader.readLine();
			if (line != null) {
				lines.add(line.split(" "));
			}
		} while (line != null);
		
		mapReader.close();
		
		Map map = new Map(lines.get(0).length, lines.size());
		
		for (int y = 0; y < lines.size(); y++) {
			for (int x = 0; x < lines.get(y).length; x++) {
				Tile tile = null;
				
				String data = lines.get(y)[x];
				switch (data) {
					case ".":
						tile = new Tile(-1);
						break;
					default:
						tile = new Tile(Integer.parseInt(data));
						break;
				}
				
				map.setTile(tile, x, y);
			}
		}
				
		return map;
	}
}