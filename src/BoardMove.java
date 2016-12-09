 /**
 * An object holding x and y coordinates of a tile in the board.
 * @author Ofir
 *
 */
public class BoardMove
{
	public int x;
	public int y;
	public PlayerType symbol;
	
	public BoardMove (int x, int y, PlayerType symbol)
	{
		this.x = x;
		this.y = y;
		this.symbol = symbol;
	}
	
	public String toString ()
	{
		return x + ", " + y + ", " + symbol;
	}
}
