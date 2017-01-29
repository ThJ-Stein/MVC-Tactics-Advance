package model;

import java.util.Arrays;

import model.exceptions.StatException;

public class BattleUnit {
	private Unit unit;
	
	private int xPos;
	private int yPos;

	public BattleUnit(Unit unit, int x, int y) {
		this.unit = unit;
		setPosition(x, y);
	}
	
	public void setPosition(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}

	
	@Override
	public String toString() {
		return "BattleUnit [unit=" + unit + ", xPos=" + xPos + ", yPos=" + yPos + "]";
	}

	public static BattleUnit parseBattleUnit(String data) {
		Stats stats = null;
		int x = -1;
		int y = -1;
		
		String[] args = data.split(" ");
		for (int i = 0; i <  args.length; i++) {
			String[] arg = args[i].split("=");
			
			switch (arg[0]) {
			case "stats":
				if (arg[1].equals("random")) {
					stats = Stats.randomizeStats();
				} else {
					String[] statStrings = arg[1].split(",");
					int[] statArray = new int[statStrings.length];
					
					try {
						for (int j = 0; j < statStrings.length; j++) {
							statArray[j] = Integer.parseInt(statStrings[j]);
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					
					try {
						stats = new Stats(statArray);
					} catch (StatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
				
			case "x":
				x = Integer.parseInt(arg[1]);
				break;
				
			case "y":
				y = Integer.parseInt(arg[1]);
				break;
			}
		}
		
		Unit unit = new Unit(stats);
		BattleUnit bUnit = new BattleUnit(unit, x, y);
		
		return bUnit;
	}
}
