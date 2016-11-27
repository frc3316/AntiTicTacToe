import java.util.HashMap;

public class SmartBot extends Player {
	public static HashMap<Board, Boolean> boardConfigurations = new HashMap<>();
	public SmartBot(PlayerType symbol) {
		super(symbol);
	}

	@Override
	public void setName() {
		name = "SmartBot ^~^";
	}

	@Override
	public BoardMove playTurn(Board board) {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (board.arr[x][y] == PlayerType.EMPTY
						&& canWin(board, x, y)) {
					return new BoardMove(x, y, symbol);
				}
			}
		}
		return wasteTime(board);
	}
	private BoardMove wasteTime(Board board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 2; j > -1; j--) {
				if (board.arr[i][j] == PlayerType.EMPTY) {
					Board newBoard = new Board(board);
					newBoard.arr[i][j] = symbol;
					if (!newBoard.winner()) {
						return new BoardMove(i, j, symbol);
					}
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.arr[i][j] == PlayerType.EMPTY) {
					return new BoardMove(i, j, symbol);
				}
			}
		}

		return null;
	}

	private boolean canWin(Board board, int x, int y) {
		Board newBoard = new Board(board);
		newBoard.arr[x][y] = symbol;

		// if we already know the answer
		if (boardConfigurations.containsKey(newBoard)) {
			return boardConfigurations.get(newBoard);
		}

		// if this move will make you immediately lose
		if (newBoard.winner()) {
			if (!boardConfigurations.containsKey(newBoard)) {
				boardConfigurations.put(newBoard, false);
			}
			return false;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (newBoard.arr[i][j] == PlayerType.EMPTY) {
					// if the other player can make a winning move after
					// this turn
					if (canWin(newBoard, i, j)) {
						if (!boardConfigurations.containsKey(newBoard)) {
							boardConfigurations.put(newBoard, false);
						}
						return false;
					}
				}
			}
		}
		// if the other player can't make a winning move after this turn (this
		// is a winning move)
		if (!boardConfigurations.containsKey(newBoard)) {
			boardConfigurations.put(newBoard, true);
		}
		return true;
	}
}