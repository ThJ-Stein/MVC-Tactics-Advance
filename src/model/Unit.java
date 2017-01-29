package model;

public class Unit {
	protected Stats stats;
	private Job job;
	
	public Unit(Stats stats) {
		this.stats = stats;
	}
	
	public Unit(Job job) {
		this.job = job;
		this.stats = Stats.randomizeStats(job);
	}
	
	//TODO should check if stats/job combo is legal?
	public Unit(Job job, Stats stats) {
		this.job = job;
		this.stats = stats;
	}
	
	public boolean canHaveJob(Job job) {
		return job.validStats(stats);
	}
	
	public Stats getStats() {
		return stats;
	}

	@Override
	public String toString() {
		return "Unit [stats=" + stats + ", job=" + job + "]";
	}
}
