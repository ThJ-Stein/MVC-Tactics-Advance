package model;

import java.util.Random;

import model.exceptions.InvalidStatAmountException;
import model.exceptions.InvalidStatRangeException;
import model.exceptions.InvalidStatSumException;
import model.exceptions.StatException;

public class Stats {
	public static final int STAT_COUNT = 6;
	public static final int STAT_MAX = 100;
	public static final int STAT_SUM = STAT_COUNT * 50;
	
	public int hp;
	public int atk;
	public int def;
	public int spatk;
	public int spdef;
	public int speed;
	
	public Stats(int[] statArray) throws StatException {
		if (statArray.length != STAT_COUNT) {
			throw new InvalidStatAmountException();
		}
		
		for (int stat: statArray) {
			if (stat > STAT_MAX || stat < 0) {
				throw new InvalidStatRangeException();
			}
		}
		
		this.hp = statArray[0];
		this.atk = statArray[1];
		this.def = statArray[2];
		this.spatk = statArray[3];
		this.spdef = statArray[4];
		this.speed = statArray[5];
		
		if (getTotal() != STAT_SUM) {
			throw new InvalidStatSumException();
		}
	}

	public Stats(int hp, int atk, int def, int spatk, int spdef, int speed) throws StatException {
		for (int stat: new int[]{hp, atk, def, spatk, spdef, speed}) {
			if (stat > STAT_MAX || stat < 0) {
				throw new InvalidStatRangeException();
			}
		}
		
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spatk = spatk;
		this.spdef = spdef;
		this.speed = speed;
		
		if (getTotal() != STAT_SUM) {
			throw new InvalidStatSumException();
		}
	}

	public int getTotal() {
		return hp + atk + def + spatk + spdef + speed;
	}
	
	public int[] asArray() {
		return new int[]{hp, atk, def, spatk, spdef, speed};
	}
	
	@Override
	public String toString() {
		return "Stats [hp=" + hp + ", atk=" + atk + ", def=" + def + ", spatk=" + spatk + ", spdef=" + spdef
				+ ", speed=" + speed + ", getTotal()=" + getTotal() + "]";
	}
	
	public static Stats randomizeStats() {
		Stats stats = null;
		boolean found = false;
		
		Random r = new Random();
		
		while (!found) {
			double[] doubles = new double[STAT_COUNT];
			int[] ints = new int[STAT_COUNT];
			
			double total = 0;
			
			for (int i = 0; i < STAT_COUNT; i++) {
				doubles[i] = r.nextDouble();
				total += doubles[i];
			}
			
			double factor = STAT_SUM / total;
			
			int sum = 0;
			
			for (int i = 0; i < STAT_COUNT; i++) {
				double stat = doubles[i] * factor;
				ints[i] = (int) Math.round(stat);
				sum += ints[i];
			}
	
			while (sum < STAT_SUM) {
				ints[r.nextInt(6)]++;
				sum++;
			}
			
			while (sum > STAT_SUM) {
				ints[r.nextInt(6)]--;
				sum--;
			}
			
			try {
				stats = new Stats(ints);
				found = true;
			} catch (StatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
		}
		
		return stats;
	}
	
	public static Stats randomizeStats(Job job) {
		Stats stats;
		
		do {
			stats = randomizeStats();
		} while (!job.validStats(stats));
		
		return stats;
	}
}