package controller;

import java.io.IOException;

import model.Model;
import view.BattlePainter;

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
		
		addCommandHandler("print", CommandHandler.PRINT);
		addCommandHandler("press", CommandHandler.MOVE_CURSOR);
		addCommandHandler("select", CommandHandler.UNIT_DETAILS);
		addCommandHandler("scale", CommandHandler.SCALE);
		addCommandHandler("end", CommandHandler.END_BATTLE);
	}
	
	@Override
	public void destroy() {
		disconnectView();
	}

	private void startBattle(String path) throws IOException {
		getModel().startBattle(path);
	}
	
	private void connectView() {
		battlePainter = new BattlePainter(getModel().getBattle());
		view.getPainters().add(battlePainter);
	}
	
	protected void disconnectView() {
		view.getPainters().remove(battlePainter);
	}
	
	protected BattlePainter getBattlePainter() {
		return battlePainter;
	}
	
	protected int getCursorX() {
		return cursorX;
	}
	
	protected int getCursorY() {
		return cursorY;
	}
	
	protected void moveCursor(int dx, int dy) {
		while (getModel().getBattle().getMap().isInBounds(cursorX+dx, cursorY+dy)
				&& !getModel().getBattle().getMap().isWalkable(cursorX + dx, cursorY + dy)) {
			dx += Integer.signum(dx);
			dy += Integer.signum(dy);
		}
		
		if (getModel().getBattle().getMap().isInBounds(cursorX+dx, cursorY+dy)) {
			cursorX += dx;
			cursorY += dy;
			
			battlePainter.moveCursor(dx, dy);
		}
	}
}
