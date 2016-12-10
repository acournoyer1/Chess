package Pieces;

import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;

import Model.ChessModel;
import Model.Location;

public class Queen extends ChessPiece{
	
	public Queen(boolean white, int x, int y, ChessModel model)
	{
		super(x, y, model, white);
		try
		{
			if(white)
			{
				image = ImageIO.read(ChessPiece.WHITE_QUEEN);
			}
			else
			{
				image = ImageIO.read(ChessPiece.BLACK_QUEEN);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public HashSet<Location> getMoves() 
	{
		HashSet<Location> moves = allCross();
		moves.addAll(allDiagonal());
		return moves;
	}
}
