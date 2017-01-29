package controller;

import java.io.IOException;

import model.Model;

public class DebugController extends Controller {

	public DebugController(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
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
			BattleController controller = new BattleController(model, path);
			setChild(controller);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
