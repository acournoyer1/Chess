package Model;

import java.util.LinkedList;

import Pieces.ChessPiece;
import Pieces.*;

public class ChessModel {
	
	private ChessPiece[][] board;
	private LinkedList<ChessListener> listeners;
	private LinkedList<ChessPiece> deadPieces;
	private boolean whiteTurn = true;
	
	public ChessModel()
	{
		listeners = new LinkedList<ChessListener>();
		deadPieces = new LinkedList<ChessPiece>();
		board = new ChessPiece[8][8];
		setUpBoard();
	}
	
	public void addListener(ChessListener c)
	{
		listeners.add(c);
	}
	
	public ChessPiece[][] getPieces()
	{
		return board;
	}
	
	public boolean isWhiteTurn()
	{
		return whiteTurn;
	}
	
	public void update()
	{
		for(ChessListener l: listeners)
		{
			l.update();
		}
	}
	
	public void setUpBoard()
	{
		board[0][0] = new Rook(true, 0, 0, this);
		board[0][1] = new Knight(true, 0, 1, this);
		board[0][2] = new Bishop(true, 0, 2, this);
		board[0][3] = new King(true, 0, 3, this);
		board[0][4] = new Queen(true, 0, 4, this);
		board[0][5] = new Bishop(true, 0, 5, this);
		board[0][6] = new Knight(true, 0, 6, this);
		board[0][7] = new Rook(true, 0, 7, this);
		board[7][0] = new Rook(false, 7, 0, this);
		board[7][1] = new Knight(false, 7, 1, this);
		board[7][2] = new Bishop(false, 7, 2, this);
		board[7][3] = new King(false, 7, 3, this);
		board[7][4] = new Queen(false, 7, 4, this);
		board[7][5] = new Bishop(false, 7, 5, this);
		board[7][6] = new Knight(false, 7, 6, this);
		board[7][7] = new Rook(false, 7, 7, this);
		for(int i = 0; i < board.length; i++)
		{
			board[1][i] = new Pawn(true, 1, i, this);
		}
		for(int i = 0; i < board.length; i++)
		{
			board[6][i] = new Pawn(false, 6, i, this);
		}
		update();
	}
	
	public boolean isEmpty(Location l)
	{
		return board[l.getX()][l.getY()] == null;
	}
	
	public boolean isEnemy(Location l, ChessPiece p)
	{
		if(isEmpty(l)) return false;
		return board[l.getX()][l.getY()].isWhite() != p.isWhite();
	}
	
	public void place(ChessPiece p, Location l)
	{
		int x = l.getX();
		int y = l.getY();
		int oldX = p.getLocation().getX();
		int oldY = p.getLocation().getY();
		if(isEmpty(l))
		{
			board[x][y] = p;
			board[oldX][oldY] = null;
			p.move(l);
		}
		else if(isEnemy(l, p))
		{
			deadPieces.add(board[x][y]);
			board[x][y] = p;
			board[oldX][oldY] = null;
			p.move(l);
		}
		nextTurn();
		update();
	}
	
	public void nextTurn()
	{
		whiteTurn = !whiteTurn;
	}
}
