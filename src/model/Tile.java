package model;

public class Tile {
	private int height = 0;
	private BattleUnit unit;
	
	public Tile(int height) {
		this.height = height;
		unit = null;
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

	public BattleUnit getUnit() {
		return unit;
	}

	public void setUnit(BattleUnit unit) {
		this.unit = unit;
	}
	
	public boolean isOccupied() {
		return unit != null;
	}
}
