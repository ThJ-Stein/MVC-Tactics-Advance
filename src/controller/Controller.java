package controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

import model.Model;
import view.View;

public abstract class Controller implements Runnable {
	protected View view;
	private Model model;
	
	private Controller childController;
	
	private LinkedBlockingQueue<String> commandQueue;
	
	
	private HashMap<String, CommandHandler<? extends Controller>> commandMap;
	
	private boolean running;
	
	
	public Controller(Model model) {
		commandQueue = new LinkedBlockingQueue<String>();
		commandMap = new HashMap<String, CommandHandler<? extends Controller>>();
		
		this.model = model;
		
		childController = null;
		
		running = true;
	}
	
	public abstract void init();
	
	public abstract void destroy();
	
	protected void addCommandHandler(String command, CommandHandler<? extends Controller> handler) {
		
		commandMap.put(command, handler);
	}
	
	protected void executeCommand(String[] args) {
		if (commandMap.containsKey(args[0])) {
			System.out.println(CommandHandler.NONE.getClass());
			
			
			//A CommandHandler must use a type that extends Controller
			//That means casting to CommandHandler<Contoller> is always okay
			@SuppressWarnings("unchecked")
			CommandHandler<Controller> handler = (CommandHandler<Controller>) commandMap.get(args[0]);
			
			handler.execute(this, args);
		}
	}
	
	@Override
	public void run() {
		running = true;
		while (running) {
			try {
				String command = commandQueue.take();
				String[] args = command.split(" ");
				
				if (childController == null) {
					executeCommand(args);
				} else if (childController.isRunning()) {
					childController.executeCommand(args);
					if (!childController.isRunning()) {
						childController.destroy();
						childController = null;
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public Model getModel() {
		return model;
	}

	public boolean isRunning() {
		return running;
	}
	
	protected void setRunning(boolean running) {
		this.running = running;
	}

	protected void setChild(Controller controller) {
		childController = controller;
		childController.setView(view);
		childController.init();
	}

	public synchronized void addCommand(String command) {
		commandQueue.add(command);
	}
	
	protected void print(String[] args) {
		String toPrint = "";
		
		Iterator<String> iterator = Arrays.asList(args).iterator();
		iterator.next();
		while (iterator.hasNext()) {
			toPrint += iterator.next();
			if (iterator.hasNext()) {
				toPrint += " ";
			}
		}
		
		System.out.print(this.getClass() + ": ");
		System.out.println(toPrint);
	}
	
	protected void print(String toPrint) {
		System.out.print(this.getClass() + ": ");
		System.out.println(toPrint);
	}
}
