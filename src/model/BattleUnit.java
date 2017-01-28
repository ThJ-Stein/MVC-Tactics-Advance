package model;

public class BattleUnit {
	private Unit unit;
	
	private int xPos;
	private int yPos;

	public BattleUnit(Unit unit) {
		this.unit = unit;
	}
	
	public void setPosition(int x, int y) {
		xPos = x;
		yPos = y;
	}

}
