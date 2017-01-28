package controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

import model.Model;

public abstract class Controller implements Runnable {
	private LinkedBlockingQueue<String> commandQueue;
	
	protected Model model;
	
	private Controller childController;
	
	private boolean running;
	
	public Controller(Model model) {
		commandQueue = new LinkedBlockingQueue<String>();
		
		this.model = model;
		
		childController = null;
		
		running = true;
	}
	
	protected abstract void handleCommand(String[] args);
	
	@Override
	public void run() {
		running = true;
		while (running) {
			try {
				String command = commandQueue.take();
				String[] args = command.split(" ");
				
				if (childController == null) {
					handleCommand(args);
				} else if (childController.isRunning()) {
					childController.handleCommand(args);
				} else {
					childController = null;
					handleCommand(args);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	
	protected void setRunning(boolean running) {
		this.running = running;
	}

	protected void setChild(Controller controller) {
		childController = controller;
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
