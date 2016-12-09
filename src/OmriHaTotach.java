import java.util.LinkedList;


public class OmriHaZaian extends Player{

	private boolean start;
	private Board oldBoard;
	public static int counter = 0;
	
	public OmriHaZaian(PlayerType symbol) {
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
	private boolean isHorse(int x1, int y1, int x2, int y2){
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
	private int[] getOppMove(Board board){
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
	private LinkedList<BoardMove> horseMoves(Board board){
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
	private BoardMove getNoLooseMove(Board board, LinkedList<BoardMove> moves){
		if(moves != null && moves.size() > 0){
			BoardMove bm = moves.get(0);
			for (BoardMove boardMove : moves) {
				oldBoard.insert(boardMove);
				if(!oldBoard.winner()){
					bm = boardMove;
				}
				oldBoard.arr[boardMove.y][boardMove.x] = PlayerType.EMPTY;
			}
			return bm;
		}
		return null;
	}
	
	//generates all possible moves
	private LinkedList<BoardMove> genMoves(Board board){
		LinkedList<BoardMove> moves = new LinkedList<>();
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				if(board.arr[i][j] == PlayerType.EMPTY)
					moves.add(new BoardMove(i, j, symbol));
			}
		}
		return moves;
	}
	
	//checks if the other player can even play anywhere
	private boolean willWin(Board board, BoardMove bm){
		Board b = new Board(board);
		LinkedList<BoardMove> moves = genMoves(board);
		for (BoardMove boardMove : moves) {
			b = new Board(board);
			b.insert(bm);
			b.insert(boardMove);
			if(!b.winner()){
				return false;
			}
		}
		return true;
	}
	
	//gets all possible reflection moves
	private LinkedList<BoardMove> reflectionMoves(Board board){
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
	
	private LinkedList<BoardMove> getWillWin(Board board){
		LinkedList<BoardMove> moves = new LinkedList<BoardMove>();
		int[] op = getOppMove(board);
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr[i].length; j++) {
				if(willWin(board, new BoardMove(i, j, symbol)))
					moves.add(new BoardMove(i, j, symbol));
			}
		}
		return moves;
	}
	
	//When the player goes second he goes to a reflection tactics 
	private BoardMove seconder(Board board){
		if(board.arr[1][1] == PlayerType.EMPTY){
			return getNoLooseMove(board, reflectionMoves(board));
		}
		else{
			BoardMove move = getNoLooseMove(board, getWillWin(board));
			if(move == null){
				move = getNoLooseMove(board, genMoves(board));
				if(move == null){
					move = new BoardMove(0, 0, symbol);
				}
			}
			return move;
		}
	}
	
	//When the player goes first
	//wins all the time
	private BoardMove starter(Board board){
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
	private boolean isStarting(Board board){
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
