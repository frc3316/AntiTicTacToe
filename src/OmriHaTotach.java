import java.util.LinkedList;


public class OmriHaTotach extends Player{

	private Board oldBoard;
	public static int counter = 0;
	
	public OmriHaTotach(PlayerType symbol) {
		super(symbol);
		oldBoard = new Board();
	}

	@Override
	public void setName() {
		this.name = "Tickle My Pickle 4 a Nickle";
	}
	
	//Generates all reflection moves
	public boolean isReflection(int x1, int y1, int x2, int y2){
		int dx = Math.abs(x1 - x2);
		int dy = Math.abs(y1 - y2);
		if(dx == 2 && dy == 0){
			return true;
		}
		else if(dx == 0 && dy == 2){
			return true;
		}
		else if(dx ==2 && dy == 2){
			return true;
		}
		return false;
	}
	public boolean isHorse(int x1, int y1, int x2, int y2){
		int dx = Math.abs(x1 - x2);
		int dy = Math.abs(y1 - y2);
		if(dx == 2 && dy == 1){
			return true;
		}
		else if(dx == 1 && dy == 2){
			return true;
		}
		return false;
	}

	//gets opponent's move
	public int[] getOppMove(Board board){
		int[] opmove = {0,0};
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				if(board.arr[i][j] != oldBoard.arr[i][j] && board.arr[i][j] != symbol){
					opmove[0] = i;
					opmove[1] = j;
				}
			}
		}
		return opmove;
	}
	
	//gets all possible horse moves
	public LinkedList<BoardMove> horseMoves(Board board){
		LinkedList<BoardMove> moves = new LinkedList<>();
		int[] op = getOppMove(board);
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				if(isHorse(op[0], op[1], i, j) && board.arr[i][j] == PlayerType.EMPTY)
					moves.add(new BoardMove(i, j, symbol));
			}
		}
		return moves;
	}
	
	//gets a list of moves and chooses the non loosing one
	public BoardMove getNoLooseMove(Board board, LinkedList<BoardMove> moves){
		BoardMove bm = moves.get(0);
		for (BoardMove boardMove : moves) {
			oldBoard.arr[boardMove.y][boardMove.x] = symbol;
			if(!oldBoard.winner()){
				bm = boardMove;
			}
			oldBoard.arr[boardMove.y][boardMove.x] = symbol;
		}
		return bm;
	}
	
	//generates all possible moves
	public LinkedList<BoardMove> genMoves(Board board){
		LinkedList<BoardMove> moves = new LinkedList<>();
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				if(board.arr[i][j] == PlayerType.EMPTY)
					moves.add(new BoardMove(i, j, symbol));
			}
		}
		return moves;
	}
	
	//gets all possible reflection moves
	public LinkedList<BoardMove> reflectionMoves(Board board){
		LinkedList<BoardMove> moves = new LinkedList<BoardMove>();
		int[] op = getOppMove(board);
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				if(isReflection(op[0], op[1], i, j) && board.arr[i][j] == PlayerType.EMPTY)
					moves.add(new BoardMove(i, j, symbol));
			}
		}
		return moves;
	}
	
	//When the player goes second he goes to a reflection tactics 
	public BoardMove seconder(Board board){
		if(board.arr[1][1] == PlayerType.EMPTY){
			return getNoLooseMove(board, reflectionMoves(board));
		}
		else{
			BoardMove move = getNoLooseMove(board, genMoves(board));
			if(move == null){
				return new BoardMove(0,0,symbol);
			}
			return move;
		}
	}
	
	//When the player goes first
	//wins all the time
	public BoardMove starter(Board board){
		if(board.arr[1][1] == PlayerType.EMPTY){
			return new BoardMove(1, 1, symbol);
		}
		else{
			return getNoLooseMove(board, horseMoves(board));
		}
	}
	
	/**
	 * checks if starting
	 * if the board is empty or the counter is zugi you start
	 * else the you are not starting*/
	public boolean isStarting(Board board){
		int counter = 0;
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				if(board.arr[i][j] != PlayerType.EMPTY){
					counter++;
				}
			}
		}
		if(counter%2 == 0){
			return true;
		}
		return false;
	}
	
	@Override
	public BoardMove playTurn(Board board) {
		BoardMove bm = null;
		if(isStarting(board)){
			bm = starter (board);
		}
		else{
			System.out.println("Not Starting look at me!!!!!!\n\n\n\n");
			bm = seconder(board);
		}
		this.oldBoard = board;
		return bm;
	}

}
