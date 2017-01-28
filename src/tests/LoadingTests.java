package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import model.Battle;
import model.BattleUnit;
import model.Map;

public class LoadingTests {
	@Test
	public void testMapLoad() {
		String path = "testmap";
		
		Map map = null;
		
		try {
			map = Map.loadMap(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(map.getTile(2, 2).toString());
	}
	
	@Test
	public void testUnitParse() {
		String unitString = "";
		
		unitString = "stats=random x=2 y=3";
		//System.out.println(BattleUnit.parseBattleUnit(unitString));
		
		unitString = "stats=50,50,50,50,50,50 x=2 y=3";
		//System.out.println(BattleUnit.parseBattleUnit(unitString));
	}
	
	@Test
	public void testBattleLoad() {
		try {
			Battle b = Battle.loadBattle("testbattle");
			b.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}