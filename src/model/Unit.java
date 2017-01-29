package model;

public class Unit {
	protected Stats stats;
	
	public Unit(Stats stats) {
		this.stats = stats;
	}
	
	public Unit(Job job) {
		this.stats = Stats.randomizeStats(job);
	}
	
	public boolean canHaveJob(Job job) {
		return job.validStats(stats);
	}
	
	public Stats getStats() {
		return stats;
	}

	@Override
	public String toString() {
		return "Unit [stats=" + stats + "]";
	}
}
