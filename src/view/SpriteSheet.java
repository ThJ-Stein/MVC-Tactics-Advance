package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	private int width;
	private int height;

	public SpriteSheet(BufferedImage sheet, int width, int height) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics2D g, int xPos, int yPos, int spriteCol, int spriteRow) {
		g.drawImage(sheet,
				xPos,
				yPos,
				xPos+width,
				yPos+height,
				spriteCol*width,
				spriteRow*height,
				(spriteCol+1)*width,
				(spriteRow+1)*height,
				null);
		
		//drawBorder(g, xPos, yPos);
	}
	
	public void drawBorder(Graphics2D g, int xPos, int yPos) {
		g.drawRect(xPos, yPos, width, height);
	}

	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}
