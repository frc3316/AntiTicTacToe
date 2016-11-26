
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
		name = "Roey ^~^";
	}

	@Override
	public BoardMove playTurn(Board board)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (board.arr[i][j] == PlayerType.EMPTY)
				{
					Board newBoard = new Board(board);
					newBoard.arr[i][j] = symbol;
					PlayerType reverseSymbol;

					if (symbol == PlayerType.A)
					{
						reverseSymbol = PlayerType.B;
					}
					else
					{
						reverseSymbol = PlayerType.A;
					}
					if (!isTheWinner(newBoard, reverseSymbol))
					{
						return new BoardMove(i, j, symbol);
					}
				}
			}
		}
		System.out.println("I'm losing :(");
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (board.arr[i][j] == PlayerType.EMPTY)
				{
					return new BoardMove(i, j, symbol);
				}
			}
		}
		System.out.println("SHOULDN'T EVER SEE THIS LINE");
		return null;
	}

	private boolean isTheWinner(Board board, PlayerType symbol)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (board.arr[i][j] == PlayerType.EMPTY)
				{
					Board newBoard = new Board(board);
					newBoard.arr[i][j] = symbol;
					if (!newBoard.winner())
					{
						PlayerType reverseSymbol;

						if (symbol == PlayerType.A)
						{
							reverseSymbol = PlayerType.B;
						}
						else
						{
							reverseSymbol = PlayerType.A;
						}
						return !isTheWinner(newBoard, reverseSymbol);
					}
				}
			}
		}
		return false;
	}

}
