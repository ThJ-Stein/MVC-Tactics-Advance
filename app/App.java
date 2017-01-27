package app;

import controller.Controller;
import controller.DebugController;
import view.View;

public class App {
	private Thread controllerThread;
	private Thread viewThread;

	public App() {
		Controller controller = new DebugController();
		View view = new View(controller);
		
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