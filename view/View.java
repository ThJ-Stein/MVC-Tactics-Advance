package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public View(Controller controller) {
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		
		this.controller = controller;
	}
	
	public void init() {
		setSize(new Dimension(height, width));
		setLayout(new FlowLayout());
		
		addCanvas();
		addInputField();
		
		//setSize(new Dimension(height, width));
		pack();
		setVisible(true);
	}
	
	public void addCanvas() {
		ViewCanvas canvas = new ViewCanvas();
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
					controller.addCommand(event.getActionCommand());
				}
				((JTextField) event.getSource()).setText("");
			}
		});
	}

	@Override
	public void run() {
		init();
		
		while (true) {
			
		}
	}
}
