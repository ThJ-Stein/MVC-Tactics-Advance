package model;

public class Unit {
	protected Stats stats;
	
	public Unit(Stats stats) {
		this.stats = stats;
	}
	
	public Stats getStats() {
		return stats;
	}

	@Override
	public String toString() {
		return "Unit [stats=" + stats + "]";
	}
}
