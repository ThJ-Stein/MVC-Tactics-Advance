package app;

import controller.Controller;
import controller.DebugController;
import model.Model;
import view.View;

public class App {
	private Thread controllerThread;
	private Thread viewThread;
	
	private Model model;
	private Controller controller;
	private View view;

	public App() {
		model = new Model();
		controller = new DebugController(model);
		view = new View();
		
		controller.setView(view);
		view.setController(controller);
		
		controller.init();
		
		
		controllerThread = new Thread(controller);
		viewThread = new Thread(view);
	}
	
	public void init() {
		controllerThread.start();
		viewThread.start();
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.init();
	}
}