/**
 * Class representing an anti-tic-tac-toe board of 3x3
 * 
 * @author Ofir
 *
 */
public class Board {
	public PlayerType[][] arr;

	/**
	 * Constructor of an empty board.
	 */
	public Board() {
		arr = new PlayerType[3][3]; // A 3x3 size board

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = PlayerType.EMPTY;
			}
		}
	}

	/**
	 * Copy constructor - creates a new board that it identical to other.
	 * 
	 * @param other
	 *            the board to copy.
	 */
	public Board(Board other) {
		arr = new PlayerType[3][3];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = other.arr[i][j];
			}
		}
	}

	public void insert(BoardMove bm) {
		arr[bm.x][bm.y] = bm.symbol;
	}

	/**
	 * @return Whether the board is full (has no empty spaces). A player calling
	 *         this method will always receive false.
	 */
	public boolean isFull() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == PlayerType.EMPTY) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * @return The symbol number of the winner. If there is currently no winner,
	 *         this method will return EMPTY. A player calling this method will
	 *         always receive false.
	 */
	public boolean winner() {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][0] != PlayerType.EMPTY && arr[i][1] != PlayerType.EMPTY && arr[i][2] != PlayerType.EMPTY) {
				return true;
			}
		}

		for (int i = 0; i < arr[0].length; i++) {
			if (arr[0][i] != PlayerType.EMPTY && arr[1][i] != PlayerType.EMPTY && arr[2][i] != PlayerType.EMPTY) {
				return true;
			}
		}

		if (arr[0][0] != PlayerType.EMPTY && arr[1][1] != PlayerType.EMPTY && arr[2][2] != PlayerType.EMPTY) {
			return true;
		}

		if (arr[2][0] != PlayerType.EMPTY && arr[1][1] != PlayerType.EMPTY && arr[0][2] != PlayerType.EMPTY) {
			return true;
		}

		return false;
	}

	/**
	 * Return the string representation of this board.
	 */
	public String toString() {
		String toReturn = "";
		toReturn += "\tBoard containing:\n\t";

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == PlayerType.EMPTY)
					toReturn += "EMPTY";
				else toReturn += "  X  ";

				if (j != (arr[0].length - 1)) {
					toReturn += "\t";
				}
			}

			if (i != (arr.length - 1)) {
				toReturn += "\n\t";
			}
		}

		return toReturn;
	}
}