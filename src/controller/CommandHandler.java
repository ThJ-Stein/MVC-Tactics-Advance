package controller;

import java.io.IOException;

public enum CommandHandler {
	NONE((Controller c, Command command) -> {
		
	}),
	PRINT((Controller c, Command command) -> {
		c.print(command.getBody());
	}),
	ENTER_BATTLE((Controller c, Command command) -> {
		BattleController bc;
		try {
			bc = new BattleController(c.getModel(), command.getArg(1));
			c.setChild(bc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}),
	MOVE_CURSOR((BattleController c, Command command) -> {
		switch (command.getArg(1)) {
		case "up":
			c.moveCursor(0,-1);
			break;
		case "down":
			c.moveCursor(0,1);
			break;
		case "left":
			c.moveCursor(-1,0);
			break;
		case "right":
			c.moveCursor(1,0);
			break;
		}
	}),
	
	MOVE_ORIGIN((BattleController c, Command command) -> {
		switch (command.getArg(1)) {
		case "up":
			c.moveCursor(0,3);
			break;
		case "down":
			c.moveCursor(0,-3);
			break;
		case "left":
			c.moveCursor(3,0);
			break;
		case "right":
			c.moveCursor(-3,0);
			break;
		}
	}),
	UNIT_DETAILS((BattleController c, Command command) -> {
		int x = c.getCursorX();
		int y = c.getCursorY();
		String details = c.getModel().getBattle().getMap().getTile(x, y).getUnit().toString();
		c.print(details);
	}),
	SCALE((BattleController c, Command command) -> {
		double scale = Double.parseDouble(command.getArg(1));
		c.getBattlePainter().setScale(scale);
	}),
	END_BATTLE((Controller c, Command command) -> {
		c.setRunning(false);
	});

	private Action action;

	private CommandHandler(Action<? extends Controller> action) {
		this.action = action;
	}
	
	public <C extends Controller> void execute(C controller, Command command) {
		action.execute(controller, command);
	}
	
	private interface Action<C extends Controller> {
		public void execute(C c, Command command);
	}
}
