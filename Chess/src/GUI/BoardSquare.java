package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class BoardSquare extends Rectangle{
	Color color;
	
	public BoardSquare(int x, int y, int width, int height, Color color)
	{
		super(x, y, width, height);
		this.color = color;
	}
	
	public void paint(Graphics2D g)
	{
		g.setColor(color);
		g.fill(this);
	}
}
