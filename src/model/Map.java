package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {
	// tiles is an array of ROWS. A vertical array of horizontal arrays. So it's Tile[y][x].
	private Tile[][] tiles = null;
	private List<BattleUnit> units = null;
	
	public Map(int width, int height) {
		tiles = new Tile[height][width];
		units = new ArrayList<BattleUnit>();
	}
	
	public void setTile(Tile tile, int x, int y) {
		tiles[y][x] = tile;
	}
	
	public void placeUnit(BattleUnit unit, int x, int y) {
		unit.setPosition(x, y);
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
	
	/* Reads a file and builds a map instance from it.
	 * Requires the path given to be a valid path.
	 */
	public static Map loadMap(String path) throws IOException {
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