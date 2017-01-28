package controller;

import java.io.IOException;

import model.Model;

public class BattleController extends Controller {
	
	
	public BattleController(Model model, String mapName) throws IOException {
		super(model);
		
		startBattle(mapName);
	}

	@Override
	protected void handleCommand(String[] args) {
		switch (args[0]) {
		case "print":
			print(args);
		case "endBattle":
			setRunning(false);
		}
	}
	
	private void startBattle(String mapName) throws IOException {
		model.startBattle(mapName);
	}
}
