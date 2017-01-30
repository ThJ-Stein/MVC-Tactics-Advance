package controller;

import java.io.IOException;

public abstract class CommandHandler {
	public abstract void execute(Controller c, String[] args);
	
	public static CommandHandler PRINT = new CommandHandler() {

		@Override
		public void execute(Controller c, String[] args) {
			c.print(args);
		}
		
	};
	
	public static CommandHandler ENTER_BATTLE = new CommandHandler() {

		@Override
		public void execute(Controller c, String[] args) {
			BattleController bc;
			try {
				bc = new BattleController(c.getModel(), args[1]);
				c.setChild(bc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	};

	public static CommandHandler MOVE_CURSOR = new CommandHandler() {

		@Override
		public void execute(Controller c, String[] args) {
			switch (args[1]) {
			case "up":
				((BattleController) c).moveCursor(0,-1);
				break;
			case "down":
				((BattleController) c).moveCursor(0,1);
				break;
			case "left":
				((BattleController) c).moveCursor(-1,0);
				break;
			case "right":
				((BattleController) c).moveCursor(1,0);
				break;
			}
			
		}
		
	};
	
	public static CommandHandler MOVE_ORIGIN = new CommandHandler() {

		@Override
		public void execute(Controller c, String[] args) {
			switch (args[1]) {
			case "up":
				((BattleController) c).moveCursor(0,3);
				break;
			case "down":
				((BattleController) c).moveCursor(0,-3);
				break;
			case "left":
				((BattleController) c).moveCursor(3,0);
				break;
			case "right":
				((BattleController) c).moveCursor(-3,0);
				break;
			}
		}
		
	};
	
	public static CommandHandler UNIT_DETAILS = new CommandHandler() {

		@Override
		public void execute(Controller c, String[] args) {
			
			int x = ((BattleController) c).getCursorX();
			int y = ((BattleController) c).getCursorY();
			
			String details = c.getModel().getBattle().getMap().getTile(x, y).getUnit().toString();
			c.print(details);
			
		}
		
	};
	
	public static CommandHandler SCALE = new CommandHandler() {

		@Override
		public void execute(Controller c, String[] args) {
			double scale = Double.parseDouble(args[1]);
			((BattleController) c).getBattlePainter().setScale(scale);
		}
		
	};
	
	public static CommandHandler END_BATTLE = new CommandHandler() {

		@Override
		public void execute(Controller c, String[] args) {
			c.setRunning(false);
		}
		
	};
	
//	public static CommandHandler BOILERPLATE = new CommandHandler() {
//
//		@Override
//		public void execute(Controller c, String[] args) {
//			
//		}
//		
//	};
}