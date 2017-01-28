package model;

public class Tile {
	private int height = 0;
	
	public Tile(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isWalkable() {
		return height < 0;
	}
	
	public String toString() {
		return "Tile height: " + height;
	}
}
