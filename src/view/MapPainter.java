package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.io.IOException;

import model.Map;

public class MapPainter implements Painter {
	private Point origin = new Point(0, 0);
	
	public static int TILE_HEIGHT = 50;
	public static int TILE_WIDTH = 80;
	public static int TILE_Z = 15;
	
	private double scale = 1;

	private Map map;

	public MapPainter(Map map) {
		this.map = map;
		origin.setLocation(250, 200);
	}

	@Override
	public void paint(Graphics2D g) {		
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				drawSquare(g, x, y, map.getTile(x, y).getHeight());
			}
		}
	}
	
	private void drawSquare(Graphics2D g, int x, int y, int z) {
		if (z < 0) {
			return;
		}
		
		Polygon surface = new Polygon();
		Polygon leftSide = new Polygon();
		Polygon rightSide = new Polygon();
		
		Point top = translate(x, y, z);
		Point left = translate(x, y+1, z);
		Point bottom = translate(x+1, y+1, z);
		Point right = translate(x+1, y, z);
		
		Point leftZ = translate(x, y+1, z-1);
		Point bottomZ = translate(x+1, y+1, z-1);
		Point rightZ = translate(x+1, y, z-1);
		
		surface.addPoint(top.x, top.y);
		surface.addPoint(left.x, left.y);
		surface.addPoint(bottom.x, bottom.y);
		surface.addPoint(right.x, right.y);
		
		leftSide.addPoint(left.x, left.y);
		leftSide.addPoint(leftZ.x, leftZ.y);
		leftSide.addPoint(bottomZ.x, bottomZ.y);
		leftSide.addPoint(bottom.x, bottom.y);
		
		rightSide.addPoint(right.x, right.y);
		rightSide.addPoint(rightZ.x, rightZ.y);
		rightSide.addPoint(bottomZ.x, bottomZ.y);
		rightSide.addPoint(bottom.x, bottom.y);
		
		g.setColor(Color.GREEN);
		g.fill(surface);		
		
		for (int i = 0; i < z; i++) {
			g.setColor(Color.BLUE);
			g.fill(leftSide);
			
			g.setColor(Color.CYAN);
			g.fill(rightSide);
			
			leftSide.translate(0, (int) (TILE_Z*scale));
			rightSide.translate(0, (int) (TILE_Z*scale));
		}
		
		g.setColor(Color.DARK_GRAY);
		g.draw(surface);
	}
	
	private Point translate(int x, int y, int z) {
		Point trans = new Point(origin);
		
		double dx = (x - y) * scale * TILE_WIDTH / 2;
		double dy = (x + y) * scale * TILE_HEIGHT / 2 - z*scale*TILE_Z;
		
		trans.translate((int) dx, (int) dy);
		
		return trans;
	}

	public void moveOrigin(int x, int y) {
		origin.translate(x, y);
	}
	
	public void setScale(double scale) {
		this.scale = scale;
	}
}
