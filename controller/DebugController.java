package controller;

public class DebugController extends Controller {

	@Override
	protected void handleCommand(String[] args) {
		switch (args[0]) {
		case "print":
			print(args);
		case "enterBattle":
			enterBattle();
		}
	}

	private void enterBattle() {
		setChild(new BattleController());
	}
}
