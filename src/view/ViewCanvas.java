package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Map;

public class ViewCanvas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -507658317440229450L;
	
	private static final int DEFAULT_HEIGHT = 500;
	private static final int DEFAULT_WIDTH = 500;
	
	private Color backgroundColor = Color.WHITE;
	
	private ArrayList<Painter> painters;
	
	private int height;
	private int width;
	
	public ViewCanvas() {
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		
		setPainters(new ArrayList<Painter>());
	}
	
	public void init() {
		setBackground(backgroundColor);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		for (Painter painter: getPainters()) {
			painter.paint(g2);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public ArrayList<Painter> getPainters() {
		return painters;
	}

	public void setPainters(ArrayList<Painter> painters) {
		this.painters = painters;
	}
}
