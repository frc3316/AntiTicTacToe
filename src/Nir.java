
public class Nir extends Player
{

	int z;

	public Nir(PlayerType symbol)
	{
		super(symbol);
		z = 0;
	}

	@Override
	public void setName()
	{
		name = "Nir";
	}

	@Override
	public BoardMove playTurn(Board board)
	{
		BoardMove currMove = new BoardMove(0, 0, symbol);
		for (int i = 0; i < 9; i++)
		{
			if (board.arr[i / 3][i % 3] == PlayerType.EMPTY)
			{
				currMove = new BoardMove(i / 3, i % 3, symbol);
				if (isWinPlace(board, currMove))
				{
					return currMove;
				}
			}
		}
		if (z == 0)
		{
			currMove.x = 1;
			currMove.y = 0;
			z++;
		}
		else
		{
			if (board.arr[2][2] != PlayerType.EMPTY)
			{
				currMove.x = 2;
				currMove.y = 0;
			}
			else
			{
				currMove.x = 0;
				currMove.y = 0;
			}
		}
		return currMove;

	}

	private boolean isWinPlace(Board board, BoardMove move)
	{
		Board brdcpy = new Board(board);
		brdcpy.arr[move.x][move.y] = symbol;
		if (brdcpy.winner() == true)
			return false;
		// checking opponent's moves
		for (int i = 0; i < 9; i++)
		{
			if (brdcpy.arr[i / 3][i % 3] == PlayerType.EMPTY)
			{
				BoardMove currMove = new BoardMove(i / 3, i % 3, symbol);

				if (isWinPlace(brdcpy, currMove))
				{
					return false;
				}
			}
		}
		return true;
	}

}
