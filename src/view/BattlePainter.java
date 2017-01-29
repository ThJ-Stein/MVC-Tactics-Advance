package view;

import java.awt.Graphics2D;
import java.awt.Point;

import model.Battle;
import model.Map;

public class BattlePainter extends MapPainter {

	private int cursorX = 0;
	private int cursorY = 0;
	
	private int cursorFrame = 0;

	public BattlePainter(Battle	battle) {
		super(battle.getMap());
		// TODO Auto-generated constructor stub
		
		
	}

	
	public void moveCursor(int dx, int dy) {
		cursorX += dx;
		cursorY += dy;
	}
	
	@Override
	protected void handleTile(Graphics2D g, int x, int y) {
		super.handleTile(g, x, y);
		
		if (x == cursorX && y == cursorY) {
			drawCursor(g, x, y, map.getTile(x, y).getHeight());
		}
	}


	private void drawCursor(Graphics2D g, int x, int y, int height) {
		String s = "v";
		
		Point p = translate(x, y, height);
		p.translate(-g.getFontMetrics().stringWidth(s) /2, -5);
		
		g.drawString(s, (int) p.getX(), (int) p.getY() - (cursorFrame*5));
	}
	
	public void update(int frames) {
		if (frames % 30 == 0) {
			cursorFrame = (cursorFrame + 1) % 2;
		}
	}
}
