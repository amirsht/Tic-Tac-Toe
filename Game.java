

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Game extends JFrame implements ActionListener 
{
	JPanel north = new JPanel();
	JPanel center = new JPanel();
	State current_turn = State.X;
	JLabel xname = new JLabel(" X wins: 0");
	JLabel oname = new JLabel(" O wins: 0");
	JButton [][] button = new JButton [3][3];
	Board board = new Board();
	static int xcount = 0;
	static int ocount = 0;
	
	public Game ()	
	{
		setSize(400,400);
		setResizable(false);
		
		
		setLayout(new BorderLayout());
		
		// Center panel
		center.setLayout(new GridLayout(3,3));
		
		// Init the buttons 
		for(int i=0 ; i<3 ; i++) 
		{
			for(int j=0 ; j<3 ; j++) 
			{
				button[i][j] = new JButton();
				button[i][j].setFont(new Font("Tahoma", Font.BOLD, 100));
				button[i][j].setBackground(Color.BLACK);
				center.add(button[i][j]);
				button[i][j].addActionListener(this);
			}
		}
		add(center,BorderLayout.CENTER);
		
		
		// North panel
		north.setLayout(new GridLayout(1,2));
		north.add(xname);
		north.add(oname);
		add(north,BorderLayout.NORTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public void resetGame()
	{
		board.initialize();
		for(int i=0 ; i<3 ; i++) 
		{
			for(int j=0 ; j<3 ; j++) 
			{
				button[i][j].setText("");
			}
		}
	}
	
	
	public static void main(String[] args) 	
	{
		
		new Game();
		
	}




	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		JButton current = (JButton) e.getSource();
		int i=0;
		int j=0;
		
		
		
		// Check the index of the clicked button
		for (int row = 0; row < 3; row++) 
		{
		    for (int col = 0; col < 3; col++) 
		    {
		       if (button[row][col] == e.getSource()) 
		       {
		    	   i = row;
		    	   j = col;
		       }
		    }
		}
		
		
		// check: if not a valid move -> error message!
		if(board.check_valid_move(i, j) == false) 
		{
		    JOptionPane.showMessageDialog(null, "Please choose empty location!", "Error", JOptionPane.ERROR_MESSAGE);
		    return;
		}
			
		
		if(current_turn == State.X) 
		{
			current.setForeground(Color.GREEN);
			current.setText("X");
			board.move(i,j,State.X);
			if(board.is_winner(State.X))
			{
				JOptionPane.showMessageDialog(null, "CONGRATULATIONS! X PLAYER WIN!", "Error", JOptionPane.INFORMATION_MESSAGE);
				xcount++;
				xname.setText("X wins: "+ xcount );
				//resetGame();
			}
				
			current_turn = State.O;
		}
		
		else
		{
			current.setForeground(Color.RED);
			current.setText("O");
			board.move(i,j,State.O);
			if(board.is_winner(State.O))
			{
				JOptionPane.showMessageDialog(null, "CONGRATULATIONS! O PLAYER WIN!", "Error", JOptionPane.INFORMATION_MESSAGE);
				ocount++;
				oname.setText("O wins: "+ ocount );
				//resetGame();
			}
			
			current_turn = State.X;
		}
		
		
		
	}

}

