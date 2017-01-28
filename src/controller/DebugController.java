package controller;

import java.io.IOException;

import model.Model;

public class DebugController extends Controller {

	public DebugController(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleCommand(String[] args) {
		switch (args[0]) {
		case "print":
			print(args);
		case "enterBattle":
			enterBattle(args[1]);
		}
	}

	private void enterBattle(String mapName) {
		System.out.println(mapName);
		try {
			setChild(new BattleController(model, mapName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
