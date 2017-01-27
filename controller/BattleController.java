package controller;

public class BattleController extends Controller {

	@Override
	protected void handleCommand(String[] args) {
		switch (args[0]) {
		case "print":
			print(args);
		case "endBattle":
			setRunning(false);
		}
	}
}
