import java.util.LinkedList;


public class OmriHaTotach extends Player{

	public OmriHaTotach(PlayerType symbol) {
		super(symbol);
	}

	@Override
	public void setName() {
		this.name = "Omri lo yail aval tov";
	}

	private int[] getBestMove(Board board, PlayerType cp){
		if(board.winner()){
			int scores[] = new int[1];
			scores[0] = (cp == symbol) ? 10 : -10;
			return scores;
		} 
		
		LinkedList<BoardMove> moves = getMoves(board);
		int scores[] = new int[moves.size()];
		
		for (int i = 0; i < moves.size(); i++) {
			Board b = new Board(board);
			moves.get(i).symbol = cp;
			b.insert(moves.get(i));
			PlayerType np = (cp == PlayerType.A) ? PlayerType.B : PlayerType.A;
			scores[i] = getSum(getBestMove(b, np));
		}
		System.out.println(board);
		return scores;
	}
	
	private int getSum(int arr[]){
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return sum;
	}
	
	private LinkedList<BoardMove> getMoves(Board board){
		LinkedList<BoardMove> boardMoves = new LinkedList<BoardMove>();
		for (int i = 0; i < board.arr.length; i++) {
			for (int j = 0; j < board.arr.length; j++) {
				if(board.arr[i][j] == PlayerType.EMPTY){
					boardMoves.add(new BoardMove(i, j, symbol));
				}
			}
		}
		return boardMoves;
	}
	
	private int getBiggest(int[] arr){
		int max = arr[0];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max){
				max = arr[i];
				index = i;
			}
		}
		return index;
	}
	
	@Override
	public BoardMove playTurn(Board board) {
		LinkedList<BoardMove> moves = getMoves(board);
		int scores[] = getBestMove(board, symbol);
		return moves.get(getBiggest(scores));
	}

}
