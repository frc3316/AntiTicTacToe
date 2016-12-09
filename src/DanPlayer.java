public class DanPlayer extends Player {
		
	public DanPlayer(PlayerType pt) {
		super(pt);
	}

	public void setName() {
		this.name = "Dan";
	}

	private int getNumOfSymbols(Board board){
		int count = 0;
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				if(board.arr[i][j]!=PlayerType.EMPTY)
					count++;
			}
		}
		return count;
	}

	private boolean moveThatLoses(Board board, BoardMove bm){
		Board tempBoard = new Board(board);
		tempBoard.insert(bm);
		if(board.arr[bm.x][bm.y]!=PlayerType.EMPTY || tempBoard.winner()){
			return false;
		}return true;
	}
	
	private boolean isStuck(Board board){
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				if(!moveThatLoses(board,new BoardMove(i, j, symbol)))
					return false;
			}
		}return true;
	}
	
	private BoardMove checkToStuck(Board board){
		Board tempBoard; 
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				tempBoard = new Board(board);
				if(tempBoard.arr[i][j]==PlayerType.EMPTY){
					tempBoard.insert(new BoardMove(i, j, symbol));
					if(isStuck(tempBoard))
						return new BoardMove(i, j, symbol);
				}
			}
		}return simpleMove(board);
	}
	
	private BoardMove simpleMove(Board board) {
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				if(moveThatLoses(board, new BoardMove(i, j, symbol)))
					return new BoardMove(i, j, symbol);
			}
		}
		return randomMove(board);
	}

	private BoardMove randomMove(Board board) {
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				if(board.arr[i][j]==PlayerType.EMPTY)
					return new BoardMove(i, j, symbol);
			}
		}return new BoardMove(2, 2, symbol);
		
	}
	
	private BoardMove func2(Board board) {
		if(board.arr[0][0]!=PlayerType.EMPTY||board.arr[0][2]!=PlayerType.EMPTY)return new BoardMove(2, 1, symbol);
		else if(board.arr[0][1]!=PlayerType.EMPTY||board.arr[1][2]!=PlayerType.EMPTY)return new BoardMove(2, 0, symbol);
		else if(board.arr[1][0]!=PlayerType.EMPTY||board.arr[2][1]!=PlayerType.EMPTY)return new BoardMove(0, 2, symbol);
		else return new BoardMove(0, 1, symbol);
	}

	public BoardMove playTurn(Board board){ 
		switch (getNumOfSymbols(board)) {
			case 0:
				return new BoardMove(1, 1, symbol);
			case 1:
				return simpleMove(board);
			case 2:
				return func2(board);
			case 3:
				return checkToStuck(board);
			default:
				return simpleMove(board);
						
		}
	}
	
}
