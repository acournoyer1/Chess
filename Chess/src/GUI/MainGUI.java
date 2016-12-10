package GUI;

import java.awt.Dimension;

import javax.swing.JFrame;

import Model.ChessModel;

@SuppressWarnings("serial")
public class MainGUI extends JFrame{
	private ChessPane board;
	
	public MainGUI()
	{
		ChessModel m = new ChessModel();
		board = new ChessPane(m);
		this.add(board);
		this.getContentPane().setPreferredSize(new Dimension(1300, 900));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main(String args[])
	{
		new MainGUI();
	}
}
