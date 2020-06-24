import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Board extends JPanel implements KeyListener
{
	private char[][] board;
	private boolean redTurn;
	private boolean gameWon;
	private String winner = "";
	public Board()
	{
		board = new char[][] {{' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' '}};
		addKeyListener(this);
		setFocusable(true);
		update();
	}
	
	
	public void paint(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillRect(0,0,700,600);
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				if(board[j][i] == ' ')
					g.setColor(new Color(0,0,128));
				else if(board[j][i] == 'r')
					g.setColor(new Color(255,0,0));
				else if(board[j][i] == 'y')
					g.setColor(new Color(255,255,0));
				g.fillOval(j*100 + 10, i*100 + 10,  80, 80);
			}
		}
		if(winner.equals("Yellow"))
		{
			g.setFont(new Font("Times Roman", 0, 50));
			g.setColor(Color.yellow);
			g.drawString(winner + " Wins!", 300,300);
		}
		else if(winner.equals("Red"))
		{
			g.setFont(new Font("Times Roman", 0, 50));
			g.setColor(Color.red);
			g.drawString(winner + " Wins!", 300,300);
		}
			
	}

	public void update()
	{
		this.setSize(716,640);
		this.setSize(715,640);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(!gameWon)
		{
			char piece;
			if(redTurn)
				piece = 'r';
			else
				piece = 'y';
			if(e.getKeyCode() < 56 && e.getKeyCode() > 48)
			{
				if(placePiece(e.getKeyCode()-48, piece))
				{
					update();
					gameWon = checkForWin();
					if(redTurn && gameWon)
					{
						winner = "Yellow";
					}
					else if(gameWon)
						winner = "Red";
				}
			}
		}
	}

	public boolean placePiece(int column, char piece)
	{
		for(int i = 5; i >= 0; i--)
		{
			if(board[column - 1][i] == ' ')
			{
				board[column - 1][i] = piece;
				redTurn = !redTurn;
				return true;
			}
		}
		return false;
	}
	public boolean checkForWin()
	{
		for(int i = 5; i >= 0; i--)
		{
			for(int j = 0; j < 4; j++)
			{
				if(board[j][i] == board[j+1][i] && board[j+1][i] == board[j+2][i] && board[j+2][i] == board[j+3][i] && board[j+1][i] != ' ')
				{
					return true;
				}
			}
		}
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				if(board[j][i] == board[j][i+1] && board[j][i+1] == board[j][i+2] && board[j][i+2] == board[j][i+3] && board[j][i+1] != ' ')
				{
					return true;
				}
			}
		}
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				if(board[j][i] == board[j+1][i+1] && board[j+1][i+1] == board[j+2][i+2] && board[j+2][i+2] == board[j+3][i+3] && board[j+3][i+3] != ' ')
				{
					return true;
			
				}
			}	
		}
		
		for(int i = 5; i >= 3; i--)
		{
			for(int j = 0; j < 3; j++)
			{
				if(board[i][j] == board[i-1][j+1] && board[i-1][j+1] == board[i-2][j+2] && board[i-2][j+2] == board[i-3][j+3] && board[i-3][j+3] != ' ')
				{
					return true;
				}
			}
		}
		return false;
		
		
	}
	
	
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
}
