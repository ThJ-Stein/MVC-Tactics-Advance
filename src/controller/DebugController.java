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

	private void enterBattle(String path) {
		System.out.println(path);
		try {
			setChild(new BattleController(model, path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
