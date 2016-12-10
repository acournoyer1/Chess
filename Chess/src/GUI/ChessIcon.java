package GUI;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import Pieces.ChessPiece;

public class ChessIcon {
	private BufferedImage image;
	private ChessPiece piece;
	private int x;
	private int y;
	private int width = 100;
	private int height = 100;
	
	public ChessIcon(ChessPiece piece, int x, int y)
	{
		this.image = piece.getImage();
		this.piece = piece;
		this.x = x;
		this.y = y;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public ChessPiece getPiece()
	{
		return piece;
	}
	
	public void setCenter(Point p)
	{
		x = p.x - width/2;
		y = p.y - height/2;
	}
	
	public boolean contains(Point p)
	{
		return p.x > x && p.x < x + width && p.y > y && p.y < y + height;
	}
	
	public void paint(Graphics2D g)
	{
		g.drawImage(image, x, y, width, height, null);
	}
	
}
