package controller;

import java.io.IOException;

import model.Model;
import view.BattlePainter;
import view.Painter;

public class BattleController extends Controller {
	private BattlePainter battlePainter;
	
	private int cursorX;
	private int cursorY;

	public BattleController(Model model, String battleFile) throws IOException {
		super(model);
		
		startBattle(battleFile);
	}

	@Override
	public void init() {
		connectView();
	}

	@Override
	protected void handleCommand(String[] args) {
		switch (args[0]) {
		case "print":
			print(args);
			break;
		case "press":
			handlePress(args[1]);
			break;
		case "scale":
			battlePainter.setScale(Double.parseDouble(args[1]));
			break;
		case "endBattle":
			disconnectView();
			setRunning(false);
			break;
		}
	}
	
	private void handlePress(String key) {
		switch (key) {
		case "up":
			//battlePainter.moveOrigin(0, 3);
			moveCursor(0,-1);
			break;
		case "down":
			//battlePainter.moveOrigin(0, -3);
			moveCursor(0,1);
			break;
		case "left":
			//battlePainter.moveOrigin(3, 0);
			moveCursor(-1,0);
			break;
		case "right":
			//battlePainter.moveOrigin(-3, 0);
			moveCursor(1,0);
			break;
		}
	}

	private void startBattle(String path) throws IOException {
		model.startBattle(path);
	}
	
	private void connectView() {
		battlePainter = new BattlePainter(model.getBattle());
		view.getPainters().add(battlePainter);
	}
	
	private void disconnectView() {
		view.getPainters().remove(battlePainter);
	}
	
	private void moveCursor(int dx, int dy) {
		if (model.getBattle().getMap().isWalkable(cursorX + dx, cursorY + dy)) {
			cursorX += dx;
			cursorY += dy;
			
			battlePainter.moveCursor(dx, dy);
		}
	}
}
