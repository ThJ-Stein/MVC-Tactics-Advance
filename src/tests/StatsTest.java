package tests;

import org.junit.Before;
import org.junit.Test;

import model.Stats;

public class StatsTest {

	@Before
	public void setup() {
		
	}
	
	@Test
	public void test() {
		for (int i = 0; i < 100000; i++) {	
			Stats stats = Stats.randomizeStats();
			assert(stats.getTotal() == 300);
			System.out.println(stats.toString());
		}
	}
}
