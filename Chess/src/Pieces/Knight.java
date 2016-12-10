package Pieces;

import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;

import Model.ChessModel;
import Model.Location;

public class Knight extends ChessPiece{
	
	public Knight(boolean white, int x, int y, ChessModel model)
	{
		super(x, y, model, white);
		try
		{
			if(white)
			{
				image = ImageIO.read(ChessPiece.WHITE_KNIGHT);
			}
			else
			{
				image = ImageIO.read(ChessPiece.BLACK_KNIGHT);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public HashSet<Location> getMoves() 
	{
		HashSet<Location> moves = new HashSet<Location>();
		Location l = this.getLocation();
		movePossible(l.up().right().right(), moves);
		movePossible(l.up().left().left(), moves);
		movePossible(l.down().right().right(), moves);
		movePossible(l.down().left().left(), moves);
		movePossible(l.right().up().up(), moves);
		movePossible(l.right().down().down(), moves);
		movePossible(l.left().up().up(), moves);
		movePossible(l.left().down().down(), moves);
		return moves;
	}
}
