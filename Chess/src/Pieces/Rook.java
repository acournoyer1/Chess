package Pieces;

import java.io.IOException;
import java.util.HashSet;
import javax.imageio.ImageIO;

import Model.ChessModel;
import Model.Location;

public class Rook extends ChessPiece{
	
	public Rook(boolean white, int x, int y, ChessModel model)
	{
		super(x, y, model, white);
		try
		{
			if(white)
			{
				image = ImageIO.read(ChessPiece.WHITE_ROOK);
			}
			else
			{
				image = ImageIO.read(ChessPiece.BLACK_ROOK);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public HashSet<Location> getMoves() 
	{
		return allCross();
	}
}
