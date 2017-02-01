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
	
	private LinkedBlockingQueue<Command> commandQueue;
	
	
	private HashMap<String, CommandHandler> commandMap;
	
	private boolean running;
	
	
	public Controller(Model model) {
		commandQueue = new LinkedBlockingQueue<Command>();
		commandMap = new HashMap<String, CommandHandler>();
		
		this.model = model;
		
		childController = null;
		
		running = true;
	}
	
	public abstract void init();
	
	public abstract void destroy();
	
	protected void addCommandHandler(String command, CommandHandler handler) {
		
		commandMap.put(command, handler);
	}
	
	protected void executeCommand(Command command) {
		if (commandMap.containsKey(command.getType())) {
			System.out.println(CommandHandler.NONE.getClass());
			
			
			//A CommandHandler must use a type that extends Controller
			//That means casting to CommandHandler<Contoller> is always okay

			CommandHandler handler = commandMap.get(command.getType());
			
			handler.execute(this, command);
		}
	}
	
	@Override
	public void run() {
		running = true;
		while (running) {
			try {
				Command command = commandQueue.take();
				//String[] args = command.split(" ");
				
				if (childController == null) {
					executeCommand(command);
				} else if (childController.isRunning()) {
					childController.executeCommand(command);
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

	
	//TODO extend command functionality
	public synchronized void addCommand(String command) {
		commandQueue.add(new Command(command));
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
