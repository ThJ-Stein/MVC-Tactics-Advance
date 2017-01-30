package controller;

import java.io.IOException;

public abstract class CommandHandler<T extends Controller> {
	public abstract void execute(T c, String[] args);
	
	public static CommandHandler<Controller> PRINT = new CommandHandler<Controller>() {

		@Override
		public void execute(Controller c, String[] args) {
			c.print(args);
		}
		
	};
	
	public static CommandHandler<Controller> ENTER_BATTLE = new CommandHandler<Controller>() {

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

	public static CommandHandler<BattleController> MOVE_CURSOR = new CommandHandler<BattleController>() {

		@Override
		public void execute(BattleController c, String[] args) {
			switch (args[1]) {
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
			
		}
		
	};
	
	public static CommandHandler<BattleController> MOVE_ORIGIN = new CommandHandler<BattleController>() {

		@Override
		public void execute(BattleController c, String[] args) {
			switch (args[1]) {
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
		}
		
	};
	
	public static CommandHandler<BattleController> UNIT_DETAILS = new CommandHandler<BattleController>() {

		@Override
		public void execute(BattleController c, String[] args) {
			
			int x = c.getCursorX();
			int y = c.getCursorY();
			
			String details = c.getModel().getBattle().getMap().getTile(x, y).getUnit().toString();
			c.print(details);
			
		}
		
	};
	
	public static CommandHandler<BattleController> SCALE = new CommandHandler<BattleController>() {

		@Override
		public void execute(BattleController c, String[] args) {
			double scale = Double.parseDouble(args[1]);
			c.getBattlePainter().setScale(scale);
		}
		
	};
	
	public static CommandHandler<Controller> END_BATTLE = new CommandHandler<Controller>() {

		@Override
		public void execute(Controller c, String[] args) {
			c.setRunning(false);
		}
		
	};
	
//	public static CommandHandler<Controller> BOILERPLATE = new CommandHandler<Controller>() {
//
//		@Override
//		public void execute(Controller c, String[] args) {
//			
//		}
//		
//	};
}