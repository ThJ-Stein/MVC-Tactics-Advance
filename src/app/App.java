package app;

import controller.Controller;
import controller.DebugController;
import model.Model;
import view.View;

public class App {
	private Thread controllerThread;
	private Thread viewThread;

	public App() {
		Model model = new Model();
		Controller controller = new DebugController(model);
		View view = new View();
		
		controller.setView(view);
		view.setController(controller);
		
		
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