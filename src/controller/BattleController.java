package controller;

import java.io.IOException;

import model.Model;
import view.MapPainter;
import view.Painter;

public class BattleController extends Controller {
	
	
	private Painter mapPainter;

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
			disconnectView();
			setRunning(false);
		}
	}
	
	private void startBattle(String path) throws IOException {
		model.startBattle(path);
	}
	
	public void connectView() {
		mapPainter = new MapPainter(model.getBattle().getMap());
		view.getPainters().add(mapPainter);
	}
	
	public void disconnectView() {
		view.getPainters().remove(mapPainter);
	}
}
