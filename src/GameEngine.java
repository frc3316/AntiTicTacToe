public class GameEngine
{
	int player1Score = 0, player2Score = 0, totalGames = 0;
	PlayerType playerStarts = PlayerType.EMPTY;

	// Change the number of games here:
	final int maxGames = 100;

	public void runGame()
	{
		/*
		 * Players to be configured here
		 */
		Player player1 = new Roey(PlayerType.A);
		Player player2 = new RandomPlayer(PlayerType.B);

		player1.setName();
		player2.setName();

		totalGames++;

		playerStarts = playerStarts == PlayerType.A ? PlayerType.B : PlayerType.A;

		Board board = new Board();

		System.out.println("\nGame #" + totalGames + ":");
		System.out.println("\tHi I'm " + player1 + " and hi I'm " + player2);

		Player currentPlayer = player1.getSymbol() == playerStarts ? player1 : player2;
		System.out.println("\t" + currentPlayer + " is starting");

		currentPlayer = currentPlayer.equals(player1) ? player2 : player1; // Switch
																			// the
																			// player,
																			// so
																			// it
																			// could
																			// be
																			// changed
																			// in
																			// the
																			// while
																			// loop
																			// again

		System.out.println(board);

		while (!board.winner() && !board.isFull())
		{
			currentPlayer = currentPlayer.equals(player1) ? player2 : player1;

			BoardMove next = currentPlayer.playTurn(new Board(board));

			if (next.x > 2 || next.y > 2 || next.x < 0 || next.y < 0)
			{
				System.out.println("\t" + currentPlayer + " lost because he tried playing outside of the board");
				if (currentPlayer.getSymbol() == PlayerType.A)
					player2Score++;
				else
					player1Score++;
				break;
			}
			else if (board.arr[next.x][next.y] != PlayerType.EMPTY)
			{
				System.out.println("\t" + currentPlayer + " lost because he tried playing in an already taken space");
				if (currentPlayer.getSymbol() == PlayerType.A)
					player2Score++;
				else
					player1Score++;
				break;
			}
			else if (next.symbol != currentPlayer.symbol)
			{
				System.out.println(
						"\t" + currentPlayer + " lost because he tried cheating - placing a symbol that is not his");
				if (currentPlayer.getSymbol() == PlayerType.A)
					player1Score++;
				else
					player2Score++;
				break;
			}
			else
			{
				board.insert(next);
			}
			System.out.println("\tIt's " + currentPlayer + "'s turn ->");
			System.out.println(board);
		}

		if (board.winner())
		{
			Player winnerPlayer = currentPlayer.getSymbol() == PlayerType.A ? player2 : player1;
			System.out.println("\t" + winnerPlayer + " wins!");
			if (winnerPlayer.getSymbol() == PlayerType.A)
				player1Score++;
			else
				player2Score++;
		}

		if (totalGames < maxGames)
		{
			runGame();
		}
		else
		{
			int scoreTie = totalGames - (player1Score + player2Score);

			System.out.println("\n" + totalGames + (totalGames != 1 ? " games were played" : " game was played") + ":");
			System.out
					.println("\t" + player1.name + " won in " + player1Score + (player1Score != 1 ? " games" : " game")
							+ " - " + ((double) player1Score / totalGames * 100) + "% of all the games.");
			System.out
					.println("\t" + player2.name + " won in " + player2Score + (player2Score != 1 ? " games" : " game")
							+ " - " + ((double) player2Score / totalGames * 100) + "% of all the games.");
			System.out.println("\tIt was a tie in " + scoreTie + (scoreTie != 1 ? " games" : " game") + " - "
					+ ((double) scoreTie / totalGames * 100) + "% of all the games.");
		}
	}

	public static void main(String[] args)
	{
		new GameEngine().runGame();
	}
}
