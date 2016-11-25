import java.util.Collections;
import java.util.LinkedList;


public class RandomPlayer extends Player
{
	public RandomPlayer(PlayerType alignment) 
	{
		super(alignment);
	}

	@Override
	public void setName() 
	{
		this.name = "Random"; // Set your player name
	}

	@Override
	public BoardMove playTurn(Board board) 
	{
		LinkedList<BoardMove> allMoves = generateAllMoves(board, symbol);
		Collections.shuffle(allMoves);
		return allMoves.stream().findAny().get();
	}

	
	private LinkedList <BoardMove> generateAllMoves (Board board, PlayerType symbol)
	{
		LinkedList<BoardMove> toReturn = new LinkedList<>();
		
		for (int i = 0; i < board.arr.length; i++)
		{
			for (int j = 0; j < board.arr[0].length; j++)
			{
				if (board.arr[i][j] == PlayerType.EMPTY)
				{
					toReturn.add(new BoardMove(i, j, symbol));
				}
			}
		}
		
		return toReturn;
	}
}
