
public class Roey extends Player
{

	public Roey(PlayerType symbol)
	{
		super(symbol);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setName()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public BoardMove playTurn(Board board)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 1|2|3 4|5|6 7|8|9
	 */
	public int getMove(Board board)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (board.arr[i][j] == PlayerType.EMPTY)
				{
					Board newBoard = new Board(board);
					newBoard.arr[i][j] = symbol;
					if (isTheWinner(board))
					{

					}
				}
			}
		}
	}

	private boolean isTheWinner(Board board)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (board.winner())
				{
					
				}
			}
		}
		return false;
	}

}
