package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.JPanel;

import Model.ChessListener;
import Model.ChessModel;
import Model.Location;
import Pieces.ChessPiece;

@SuppressWarnings("serial")
public class ChessPane extends JPanel implements ChessListener{
	
	private BoardSquare[][] squares;
	private ChessModel model;
	private LinkedList<ChessIcon> icons;
	private LinkedList<BoardSquare> targets;
	private HashSet<Location> moves;
	private ChessIcon selectedIcon = null;
	
	public ChessPane(ChessModel model)
	{
		this.squares = new BoardSquare[8][8];
		this.model = model;
		this.targets = new LinkedList<BoardSquare>();
		this.icons = getIcons();
		this.paintSquares();
		this.addListeners();
		model.addListener(this);
	}
	
	public void addListeners()
	{
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				ChessIcon icon = getIcon(e.getPoint());
				if(icon != null && icon.getPiece().isWhite() == model.isWhiteTurn())
				{
					selectedIcon = icon;
					moves = icon.getPiece().getMoves();
					getTargets(moves);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				if(selectedIcon == null) return;
				targets.clear();
				Location l = getSquare(e.getPoint());
				if(l != null)
				{
					for(Location loc: moves)
					{
						if(l.equals(loc))
						{
							model.place(selectedIcon.getPiece(), l);
						}
					}
				}
				update();
				selectedIcon = null;
			}
		});
		
		this.addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				if(selectedIcon != null && selectedIcon.getPiece().isWhite() == model.isWhiteTurn())
				{
					selectedIcon.setCenter(e.getPoint());
					repaint();
				}
			}
		});
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		this.setBackground(new Color(139,69,19));
		Graphics2D g2 = (Graphics2D)g;
		this.drawDecorations(g2);
		for(BoardSquare[] row: squares){
			for(BoardSquare s: row)
			{
				s.paint(g2);
			}
		}
		for(BoardSquare b: targets)
		{
			b.paint(g2);
		}
		for(ChessIcon c: icons)
		{
			if(c != selectedIcon) c.paint(g2);
		}
		if(selectedIcon != null) selectedIcon.paint(g2);
		
	}
	
	public ChessIcon getIcon(Point p)
	{
		for(ChessIcon c: icons)
		{
			if(c.contains(p))
			{
				return c;
			}
		}
		return null;
	}
	
	public void drawDecorations(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		g.drawRect(241, 41, 830, 830);
		g.drawRect(246, 51, 820, 810);
		g.drawRect(251, 46, 810, 820);
		g.drawRect(236, 31, 840, 850);
		g.drawRect(231, 36, 850, 840);
		g.setStroke(new BasicStroke(3));
		g.drawRect(10, 10, this.getWidth()-20, this.getHeight()-20);
	}
	
	private LinkedList<ChessIcon> getIcons()
	{
		LinkedList<ChessIcon> icons = new LinkedList<ChessIcon>();
		ChessPiece[][] pieces = model.getPieces();
		for(int i = 0; i < pieces.length; i++)
		{
			for(int j = 0; j < pieces.length; j++)
			{
				if(pieces[i][j] != null)
				{
					icons.add(new ChessIcon(pieces[i][j], 256 + j*100, 56 + i*100));
				}
			}
		}
		return icons;
	}
	
	private void paintSquares()
	{
		for(int x = 256, i = 0; i < 8; x += 100, i++)
		{
			for(int y = 56, j = 0; j < 8; y += 100, j++)
			{
				Color c = (i + j)%2 != 0 ? Color.BLACK : Color.WHITE;
				squares[i][j] = new BoardSquare((int)x, (int)y, 100, 100, c);
			}
		}
	}
	
	public Location getSquare(Point p)
	{
		for(int i=0; i<squares.length; i++)
		{
			for(int j=0; j<squares[0].length; j++)
			{
				if(squares[i][j].contains(p)) return new Location(j, i);
			}
		}
		return null;
	}
	
	public Point getOffset(Location l)
	{
		return new Point(256 + l.getY()*100, 56 + l.getX()*100);
	}
	
	public void getTargets(HashSet<Location> locations)
	{
		 for(Location l: locations)
		 {
			 Point p = getOffset(l);
			 Color c = model.isEnemy(l, selectedIcon.getPiece()) ? Color.RED : Color.GREEN;
			 targets.add(new BoardSquare(p.x+10, p.y+10, 80, 80, c));
		 }
	}
	
	public void update()
	{
		icons = getIcons();
		repaint();
	}
}
