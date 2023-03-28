package com.example.logic;

/**
 * A Tic Tac Toe board.
 */
public class Board {

    // Represent the board status as an array of Player using null for empty
    // places. Index i corresponds to row i / 3 and column i % 3
    // This variable is final since the array is created once (final variables
    // cannot be reassigned but the object they refer can mutate).
    // The public interface does not leak null or any detail about the
    // internal representation of the board.
    private final Player[] board;
    private final int size = 3;

    /**
     * Class constructor, creates an empty board
     */
    public Board() {
        // each element of the array is initialised with null by default
        board = new Player[size * size];
    }

    /**
     * Prints the current board on stdout
     */
    public void print() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] != null) {
                System.out.print(board[i].name());
            } else {
                System.out.print(' ');
            }
            if (i % 3 < 2)
                System.out.print('|');
            else if (i < board.length - 1)
                System.out.println("\n-+-+-");
            else
                System.out.println();
        }
    }

    /**
     * Returns the player that plays the next move (if any)
     */
    public Player nextPlayer() {
        int delta = 0;
        for (Player p : board)
            if (Player.X == p)
                delta += 1;
            else if (Player.O == p)
                delta -= 1;
        if (delta > 0)
            return Player.O;
        else
            return Player.X;
    }

    /**
     * Checks if the given square is free
     *
     * @param square an integer between 1 and 9 (included)
     */
    public boolean isFree(int square) {
        // While debugging, we use an assertion to check that square is in the legal
        // range
        assert 1 <= square && square <= 9 : square;
        return board[square - 1] == null;
    }

    /**
     * Make the next player play on the given square. The square must be free.
     *
     * @see nextPlayer()
     * @see isFree(int)
     * @param square an integer corresponding to a free square.
     */
    public void play(int square) {
        // While debugging, we use an assertion to check that square is free
        assert isFree(square) : square;
        board[square - 1] = nextPlayer();
    }

    /**
     * Checks if the game ended with a tie.
     */
    public boolean tied() {
        int free = 0;
        for (int i = 0; i < board.length; i++)
            if (board[i] == null)
                free = free + 1;
        return (free == 0) && !won();
    }

    /**
     * Checks if the game ended with a victory of the player tha played the last
     * move.
     */
    public boolean won() {
        Player player = (nextPlayer() == Player.X) ? Player.O : Player.X;
        boolean notWon = true;
        // check row and col wins
        int i = 0;
        while (notWon && i < 3) {
            notWon = !(wonRow(player, i) || wonCol(player, i));
            i = i + 1;
        }
        // check diagonal win (unless notWon is false)
        notWon = notWon && !wonDiagonal(player);
        // check anti-diagonal win (unless notWon is false)
        notWon = notWon && !wonAntiDiagonal(player);
        return !notWon;
    }

    // auxiliary methods for won()
    private boolean wonRow(Player player, int row) {
        int i = 0;
        boolean won = true;
        while (won && i < 3) {
            won = player == board[row * 3 + i];
            i = i + 1;
        }
        return won;
    }

    private boolean wonCol(Player player, int col) {
        int i = 0;
        boolean won = true;
        while (won && i < 3) {
            won = player == board[i * 3 + col];
            i = i + 1;
        }
        return won;
    }

    private boolean wonDiagonal(Player player) {
        int i = 0;
        boolean won = true;
        while (won && i < 3) {
            won = player == board[i * 3 + i];
            i = i + 1;
        }
        return won;
    }

    private boolean wonAntiDiagonal(Player player) {
        int i = 0;
        boolean won = true;
        while (won && i < 3) {
            won = player == board[i * 3 + 2 - i];
            i = i + 1;
        }
        return won;
    }

    public int size() {
        return this.size;
    }

}