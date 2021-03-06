package Pieces;

import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;

import Model.ChessModel;
import Model.Location;

public class King extends ChessPiece{

	public King(boolean white, int x, int y, ChessModel model)
	{
		super(x, y, model, white);
		try
		{
			if(white)
			{
				image = ImageIO.read(ChessPiece.WHITE_KING);
			}
			else
			{
				image = ImageIO.read(ChessPiece.BLACK_KING);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public HashSet<Location> getMoves() 
	{
		HashSet<Location> moves = new HashSet<Location>();
		Location l = this.getLocation();
		movePossible(l.up(), moves);
		movePossible(l.up().right(), moves);
		movePossible(l.right(), moves);
		movePossible(l.right().down(), moves);
		movePossible(l.down(), moves);
		movePossible(l.down().left(), moves);
		movePossible(l.left(), moves);
		movePossible(l.left().up(), moves);
		return moves;
	}
}
