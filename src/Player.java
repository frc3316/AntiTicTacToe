/**
 * Subclasses of this class can be players in an anti-tic-tac-toe game.
 * 
 * @author Ofir
 *
 */
public abstract class Player
{
	String name = ""; // the name of the player
	public final PlayerType symbol; // the name of the player (A or B)

	public Player(PlayerType symbol)
	{
		this.symbol = symbol;
	}

	/**
	 * Method that is called by the engine at the start of the game to set the
	 * name of the player.
	 */
	public abstract void setName();

	/**
	 * Method that is called each turn by the game engine
	 * 
	 * @param board
	 *            The current board state. This board is a copy of the current
	 *            board, so it can be changed without harming the game.
	 * @return The position where the player wants to play. A player can not
	 *         play in a position that is already filled (i.e. not 0).
	 */
	public abstract BoardMove playTurn(Board board);

	public PlayerType getSymbol()
	{
		return symbol;
	}

	public String toString()
	{
		return name;
	}
}
