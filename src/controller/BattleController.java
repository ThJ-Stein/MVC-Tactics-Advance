package controller;

import java.io.IOException;

import model.Model;

public class BattleController extends Controller {
	
	
	public BattleController(Model model, String battleFile) throws IOException {
		super(model);
		
		startBattle(battleFile);
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
	
	private void startBattle(String path) throws IOException {
		model.startBattle(path);
	}
}
