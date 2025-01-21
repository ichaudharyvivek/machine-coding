package com.tictactoe.models;

/**
 * Logger class to handle logging board states or any other actions.
 * Implements the Singleton design pattern to ensure only one instance is
 * created.
 */
public class GameLogger {
    private static GameLogger instance;
    private final Board board;

    /**
     * Private constructor to prevent direct instantiation.
     * The Logger instance is initialized with a Board.
     *
     * @param board The Board to associate with this Logger instance.
     */
    private GameLogger(Board board) {
        this.board = board;
    }

    /**
     * Returns the singleton instance of Logger.
     * If the instance is not yet created, it initializes it.
     *
     * @param board The Board to associate with the Logger (used only for the first
     *              instantiation).
     * @return The singleton Logger instance.
     */
    public static GameLogger getInstance(Board board) {
        if (instance == null) {
            instance = new GameLogger(board);
        }
        return instance;
    }

    /**
     * Logs the current state of the board.
     * This could be used for debugging, displaying the state of the game, etc.
     */
    public void printBoard() {
        int size = board.getSize();
        Piece[][] pieceOnBoard = board.getBoard();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char piece = pieceOnBoard[i][j].getSymbol();
                System.out.print(piece + " ");
            }

            System.out.println();
        }
    }

    /**
     * Logs invalid move
     */
    public void printInvalidMove() {
        System.out.println("Invalid Move");
    }

    /**
     * Logs winner
     */
    public void printWinner(Player player) {
        System.out.printf("%s won the game%n", player.getPlayerName());
    }

    /**
     * Log game draw
     */
    public void printGameDraw() {
        System.out.println("Game Draw.");
    }

}