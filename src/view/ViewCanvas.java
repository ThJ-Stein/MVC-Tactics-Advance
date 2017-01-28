package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ViewCanvas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -507658317440229450L;
	
	private static final int DEFAULT_HEIGHT = 500;
	private static final int DEFAULT_WIDTH = 500;
	
	private Color backgroundColor = Color.WHITE;
	
	private int height;
	private int width;
	
	public ViewCanvas() {
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
	}
	
	public void init() {
		setBackground(backgroundColor);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}
