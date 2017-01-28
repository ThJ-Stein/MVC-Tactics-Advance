package controller;

import java.io.IOException;

import model.Model;
import view.MapPainter;
import view.Painter;

public class BattleController extends Controller {
	
	
	private MapPainter mapPainter;

	public BattleController(Model model, String battleFile) throws IOException {
		super(model);
		
		startBattle(battleFile);
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
			mapPainter.setScale(Double.parseDouble(args[1]));
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
			mapPainter.moveOrigin(0, 3);
			break;
		case "down":
			mapPainter.moveOrigin(0, -3);
			break;
		case "left":
			mapPainter.moveOrigin(3, 0);
			break;
		case "right":
			mapPainter.moveOrigin(-3, 0);
			break;
		}
	}

	private void startBattle(String path) throws IOException {
		model.startBattle(path);
	}
	
	private void connectView() {
		mapPainter = new MapPainter(model.getBattle().getMap());
		view.getPainters().add(mapPainter);
	}
	
	private void disconnectView() {
		view.getPainters().remove(mapPainter);
	}

	@Override
	public void init() {
		connectView();
	}
}
