

public class Board 
{
    static final int BOARD_SIZE = 3;
	private State[][] board;
	

	
	public Board ()	
	{
		board = new State[BOARD_SIZE][BOARD_SIZE];
		//private HashSet<Integer> movesAvailable;
		initialize();
	}
	
	
	public void initialize() 	
	{
		for (int row = 0; row < BOARD_SIZE; row++) 
		{
            for (int col = 0; col < BOARD_SIZE; col++) 
            {
                board[row][col] = State.Blank;
            }
        }
	}
	
	public boolean check_valid_move(int i,int j) 
	{
		return board[i][j] == State.Blank;
	}
	
	
	public int move(int i,int j,State newstate) 
	{
		
		if(check_valid_move(i,j)) {
			board[i][j] = newstate;
			return 1;
		}
		
		// If this position is not blank - return fail (0)
		return 0;
	}
	
	
	public boolean is_winner(State player) 
	{
		
		for(int index = 0 ; index < 3 ; index++)
		{
			//Check if there is 3 in a row
			if(board[index][0] == player && board[index][1] == player && board[index][2] == player)
				return true;

			//Check if there is 3 in a col
			if(board[0][index] == player && board[1][index] == player && board[2][index] == player)
				return true;
		}
		
		// Check if there is 3 in diagonal 
		if(board[0][0] == player && board[1][1] == player && board[2][2] == player)
			return true;
		
		else if(board[0][2] == player && board[1][1] == player && board[2][0] == player)
			return true;

		return false;
	}
	

}
