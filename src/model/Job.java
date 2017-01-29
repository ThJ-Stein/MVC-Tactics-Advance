package model;

public enum Job {
	MERCHANT(new int[]{0,0,0,0,0,0}, new int[]{100, 100, 100, 100, 100, 100}),
	SOLDIER(new int[]{50,50,50,0,0,0}, new int[]{100, 100, 100, 50, 50, 100}),
	LIBRA(new int[]{50,50,50,50,50,50}, new int[]{50,50,50,50,50,50});
	
	private int[] max;
	private int[] min;

	private Job(int[] min, int[] max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean validStats(Stats stats) {
		int[] statArray = stats.asArray();
		
		for (int i = 0; i < statArray.length; i++) {
			if (!(statArray[i] >= min[i] && statArray[i] <= max[i])) {
				return false;
			}
		}
		return true;
	}
	
	public boolean validStats(int[] stats) {
		int[] statArray = stats;
		
		for (int i = 0; i < statArray.length; i++) {
			if (!(statArray[i] >= min[i] && statArray[i] <= max[i])) {
				return false;
			}
		}
		return true;
	}
	
	public int[] getMax() {
		return max;
	}
	
	public int[] getMin() {
		return min;
	}
}
