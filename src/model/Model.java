package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {
	private List<Unit> unitList;
	
	private Battle activeBattle;
	
	public Model() {
		unitList = new ArrayList<Unit>();
		
		activeBattle = null;
	}
	
	public void startBattle(String mapName) throws IOException {
		this.activeBattle = new Battle(mapName);
	}
}
