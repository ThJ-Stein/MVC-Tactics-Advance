package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.Controller;

public class View extends JFrame implements Runnable, KeyListener {
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
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent event) {
				event.getComponent().requestFocus();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		addKeyListener(this);
        setFocusable(true);
        //setFocusTraversalKeysEnabled(false);
        
        //setSize(new Dimension(height, width));
      	pack();
      	setVisible(true);
      	
      	//debug code
      	getController().addCommand("enterBattle testbattle");
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
		int frames = 0;
		while (true) {
			canvas.update(frames++);
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

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		case 37:
			getController().addCommand("press left");
			break;
		case 38:
			getController().addCommand("press up");
			break;
		case 39:
			getController().addCommand("press right");
			break;
		case 40:
			getController().addCommand("press down");
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		
	}
}
