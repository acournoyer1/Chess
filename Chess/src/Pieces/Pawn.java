package Pieces;

import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;

import Model.ChessModel;
import Model.Location;

public class Pawn extends ChessPiece{
	
	private boolean hasMoved = false;
	
	public Pawn(boolean white, int x, int y, ChessModel model)
	{
		super(x, y, model, white);
		try
		{
			if(white)
			{
				image = ImageIO.read(ChessPiece.WHITE_PAWN);
			}
			else
			{
				image = ImageIO.read(ChessPiece.BLACK_PAWN);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void move(Location l)
	{
		super.move(l);
		hasMoved = true;
	}
	
	public HashSet<Location> getMoves() 
	{
		HashSet<Location> moves = new HashSet<Location>();
		Location l = this.getLocation();
		if(isWhite())
		{
			if(l.down().withinBounds() && model.isEmpty(l.down()))
			{
				moves.add(l.down());
				if(!hasMoved && l.down().down().withinBounds() && model.isEmpty(l.down().down())) moves.add(l.down().down()); 
			}
			if(l.down().right().withinBounds() && model.isEnemy(l.down().right(), this)) moves.add(l.down().right());
			if(l.down().left().withinBounds() && model.isEnemy(l.down().left(), this)) moves.add(l.down().left());
		}
		else
		{
			if(l.up().withinBounds() && model.isEmpty(l.up()))
			{
				moves.add(l.up());
				if(!hasMoved && l.up().up().withinBounds() && model.isEmpty(l.up().up())) moves.add(l.up().up()); 
			}
			if(l.up().right().withinBounds() && model.isEnemy(l.up().right(), this)) moves.add(l.up().right());
			if(l.up().left().withinBounds() && model.isEnemy(l.up().left(), this)) moves.add(l.up().left());
		}
		return moves;
	}
}
