package Pieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;

import Model.ChessModel;
import Model.Location;

public abstract class ChessPiece {
	public static final File BLACK_BISHOP = new File("resources/Black B.png");
	public static final File BLACK_ROOK = new File("resources/Black R.png");
	public static final File BLACK_KNIGHT = new File("resources/Black N.png");
	public static final File BLACK_KING = new File("resources/Black K.png");
	public static final File BLACK_QUEEN = new File("resources/Black Q.png");
	public static final File BLACK_PAWN = new File("resources/Black P.png");
	
	public static final File WHITE_BISHOP = new File("resources/White B.png");
	public static final File WHITE_ROOK = new File("resources/White R.png");
	public static final File WHITE_KNIGHT = new File("resources/White N.png");
	public static final File WHITE_KING = new File("resources/White K.png");
	public static final File WHITE_QUEEN = new File("resources/White Q.png");
	public static final File WHITE_PAWN = new File("resources/White P.png");

	protected BufferedImage image;
	private int x;
	private int y;
	private boolean white;
	protected ChessModel model;
	
	ChessPiece(int x, int y, ChessModel model, boolean white)
	{
		this.x = x;
		this.y = y;
		this.model = model;
		this.white = white;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}

	public Location getLocation()
	{
		return new Location(x,y);
	}
	
	public boolean isWhite()
	{
		return white;
	}
	
	public void move(Location l)
	{
		this.x = l.getX();
		this.y = l.getY();
	}
	
	public HashSet<Location> allCross()
	{
		HashSet<Location> moves = new HashSet<Location>();
		Location l;
		for(l = this.getLocation().up(); l.withinBounds() && model.isEmpty(l); l = l.up()) moves.add(l);
		movePossible(l, moves);
		for(l = this.getLocation().down(); l.withinBounds() && model.isEmpty(l); l = l.down()) moves.add(l);
		movePossible(l, moves);
		for(l = this.getLocation().left(); l.withinBounds() && model.isEmpty(l); l = l.left()) moves.add(l);
		movePossible(l, moves);
		for(l = this.getLocation().right(); l.withinBounds() && model.isEmpty(l); l = l.right()) moves.add(l);
		movePossible(l, moves);
		return moves;
	}
	
	public HashSet<Location> allDiagonal()
	{
		HashSet<Location> moves = new HashSet<Location>();
		Location l;
		for(l = this.getLocation().up().right(); l.withinBounds() && model.isEmpty(l); l = l.up().right()) moves.add(l);
		movePossible(l, moves);
		for(l = this.getLocation().up().left(); l.withinBounds() && model.isEmpty(l); l = l.up().left()) moves.add(l);
		movePossible(l, moves);
		for(l = this.getLocation().down().right(); l.withinBounds() && model.isEmpty(l); l = l.down().right()) moves.add(l);
		movePossible(l, moves);
		for(l = this.getLocation().down().left(); l.withinBounds() && model.isEmpty(l); l = l.down().left()) moves.add(l);
		movePossible(l, moves);
		return moves;
	}
	
	public void movePossible(Location l, HashSet<Location> moves)
	{
		if(l.withinBounds() && (model.isEnemy(l, this) || model.isEmpty(l))) moves.add(l);
	}
	
	public abstract HashSet<Location> getMoves();
}
