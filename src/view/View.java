package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.Controller;

public class View extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5342987342023050045L;
	
	private static final int DEFAULT_HEIGHT = 500;
	private static final int DEFAULT_WIDTH = 500;
	
	private int height;
	private int width;
	
	private Controller controller;

	private ViewCanvas canvas;
	
	public View() {
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
	}
	
	public void init() {
		setSize(new Dimension(height, width));
		setLayout(new FlowLayout());
		
		addCanvas();
		addInputField();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setSize(new Dimension(height, width));
		pack();
		setVisible(true);
	}
	
	public void addCanvas() {
		canvas = new ViewCanvas();
		canvas.init();
		add(canvas);
	}
	
	public void addInputField() {
		JTextField input = new JTextField(50);
		add(input);
		
		input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (!event.getActionCommand().equals("")) {
					getController().addCommand(event.getActionCommand());
				}
				((JTextField) event.getSource()).setText("");
			}
		});
	}
	
	public ArrayList<Painter> getPainters() {
		return canvas.getPainters();
	}

	@Override
	public void run() {
		init();
		
		while (true) {
			canvas.repaint();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
