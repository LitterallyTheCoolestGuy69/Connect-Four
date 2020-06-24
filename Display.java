import javax.swing.JFrame;

public class Display{
	public static void main(String[] args)
	{
		JFrame screen = new JFrame();
		screen.setSize(715,640);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);
		Board board = new Board();
		screen.addKeyListener(board);
		screen.add(board);
	}

}
