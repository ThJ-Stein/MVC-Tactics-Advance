package controller;

import model.Model;

public class DebugController extends Controller {

	public DebugController(Model model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		addCommandHandler("print", CommandHandler.PRINT);
		addCommandHandler("enterBattle", CommandHandler.ENTER_BATTLE);
	}
	
	@Override
	public void destroy() {
		
	}
}
