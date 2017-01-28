package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
	private List<Unit> unitList;
	
	private Battle activeBattle;
	
	public Model() {
		unitList = new ArrayList<Unit>();
	}
}
