
import java.util.LinkedList;

public class Stav extends Player {
	public void setName() {
		this.name = "Stav";
	}

	public Stav(PlayerType symbol) {
		super(symbol);
	}

	@Override
	public BoardMove playTurn(Board board) {
		LinkedList<BoardMove> availableMoves = generateAllMoves(board, symbol);
		if (availableMoves.size() == 9)
			return new BoardMove(1, 1, symbol);
		int[] scores = new int[availableMoves.size()];
		scores = minimax(board, symbol, 0);
		return availableMoves.get(maximalValuePosition(scores));
	}

	private int score(Board board, PlayerType currentPlayer, int depth) {
		if (board.winner() && currentPlayer == symbol) {
			return 10 - depth;
		}

		return depth - 10;
	}

	private LinkedList<BoardMove> generateAllMoves(Board board, PlayerType symbol) {
		LinkedList<BoardMove> toReturn = new LinkedList<>();

		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[0].length; j++) {
				if (board.arr[i][j] == PlayerType.EMPTY) {
					toReturn.add(new BoardMove(i, j, symbol));
				}
			}
		}

		return toReturn;
	}

	private int[] minimax(Board board, PlayerType currentPlayer, int depth) {

		if (board.winner()) {
			int[] scores = new int[1];
			scores[0] = score(board, currentPlayer, depth);
			return scores;
		}

		LinkedList<BoardMove> availableMoves = generateAllMoves(board, currentPlayer);

		int[] scores = new int[availableMoves.size()];

		depth++;

		for (int i = 0; i < availableMoves.size(); i++) {
			BoardMove possibleMove = availableMoves.get(i);
			Board newBoard = new Board(board);
			insertPossibleBoardMove(newBoard, possibleMove);
			PlayerType nextPlayer = currentPlayer == PlayerType.A ? PlayerType.B : PlayerType.A;
			scores[i] = arraySum(minimax(newBoard, nextPlayer, depth));
		}
		return scores;

	}

	private Board insertPossibleBoardMove(Board board, BoardMove boardmove) {
		Board newBoard = board;
		newBoard.arr[boardmove.x][boardmove.y] = boardmove.symbol;
		return newBoard;
	}

	private int arraySum(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}

		return sum;
	}

	private int maximalValuePosition(int[] array) {
		int maxValue = array[0];
		int maxValuePosition = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > maxValue) {
				maxValue = array[i];
				maxValuePosition = i;
			}

		}
		return maxValuePosition;
	}

	private void printArray(int[] array) {
		String str = "[";
		for (int i = 0; i < array.length; i++) {

			str += i != array.length - 1 ? array[i] + ", " : array[i];
		}
		str += "]";
		System.out.println(str);
	}
}
