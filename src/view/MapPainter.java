package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.BattleUnit;
import model.Map;

public class MapPainter implements Painter {
	private Point origin = new Point(0, 0);
	
	public static int TILE_HEIGHT = 50;
	public static int TILE_WIDTH = 80;
	public static int TILE_Z = 15;
	
	private double scale = 1;

	protected Map map;

	private BufferedImage im;

	public MapPainter(Map map) {
		this.map = map;
		origin.setLocation(250, 200);
		
//		try {
//			im = ImageIO.read(new File("resources/images/battlebackground.jpg"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void paint(Graphics2D g) {
//		g.drawImage(im, 0, 0, null);
		
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				handleTile(g, x, y);
			}
		}
	}
	
	protected void handleTile(Graphics2D g, int x, int y) {
		drawSquare(g, x, y, map.getTile(x, y).getHeight());
		
		if (map.getTile(x, y).isOccupied()) {
			drawUnit(g, map.getTile(x, y).getUnit(), map.getTile(x, y).getHeight());
		}
	}

	private void drawUnit(Graphics2D g, BattleUnit unit, int height) {
		String s = "@";
		
		Font font = new Font("TimesRoman", Font.CENTER_BASELINE, (int) (40*scale));
		g.setFont(font);
		int charWidth = g.getFontMetrics().stringWidth(s);
		
		
		Point p = translate(unit.getX() + .5, unit.getY() + .5, height);
		p.translate(-charWidth / 2, 0);
		
		Shape shadow = new Ellipse2D.Double(p.getX(), p.getY() - 5, charWidth, 10);
		
		g.setColor(Color.GRAY);
		g.fill(shadow);
		
		
		g.setColor(Color.BLACK);
		
		g.drawString(s, (int) p.getX(), (int) p.getY() - 5);	
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
	
	protected Point translate(double x, double y, double z) {
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

	@Override
	public void update(int frames) {
		//
		
	}
}
