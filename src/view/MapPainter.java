package view;

import java.awt.Graphics2D;
import java.io.IOException;

import model.Map;

public class MapPainter implements Painter {

	private Map map;

	public MapPainter(Map map) {
		this.map = map;
	}

	@Override
	public void paint(Graphics2D g) {		
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				g.drawString(map.getTile(x, y).getHeight() + "", x*20, y*20);
			}
		}
	}

}
