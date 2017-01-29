package model;

import java.util.Arrays;
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
	
	public static Stats randomizeStats(Job job) {
		boolean found = false;
		
		Random r = new Random();
		
		int[] statArray = new int[STAT_COUNT];
		int sum = 0;

		for (int i = 0; i < STAT_COUNT; i++) {
			int max = job.getMax()[i];
			int min = job.getMin()[i];
			
			statArray[i] = (max == min) ? min : r.nextInt(max - min) + min;
			sum += statArray[i];
		}
		
		while (sum < 300) {
			int index = r.nextInt(STAT_COUNT-1);
			
			statArray[index]++;
			if (job.validStats(statArray)) {
				sum++;
			} else {
				statArray[index]--;
			}
		}
		
		while (sum > 300) {
			int index = r.nextInt(STAT_COUNT-1);
			
			statArray[index]--;
			if (job.validStats(statArray)) {
				sum--;
			} else {
				statArray[index]++;
			}
		}
		
		Stats stats = null;
		
		try {
			stats = new Stats(statArray);
		} catch (StatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stats;
	}
	
	public static Stats randomizeStats() {
		return randomizeStats(Job.MERCHANT);
	}
}