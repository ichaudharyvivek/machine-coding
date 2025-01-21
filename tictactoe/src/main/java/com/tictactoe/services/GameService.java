package com.tictactoe.services;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.tictactoe.models.Board;
import com.tictactoe.models.Player;
import com.tictactoe.models.GameLogger;
import com.tictactoe.models.Piece;

public class GameService {
    private final Board board;
    private final GameLogger logger;
    private final Deque<Player> turnDeque;

    /**
     * Constructs a GameService instance.
     *
     * @param board   The game board containing snakes, ladders, and the board size.
     * @param players The list of players participating in the game.
     */
    public GameService(Board board, List<Player> players) {
        this.board = board;
        this.turnDeque = new LinkedList<>(players);
        this.logger = GameLogger.getInstance(board);
    }

    /**
     * Starts the game in interactive mode
     * The game continues until a player wins or the board is full
     * 
     * The flow of the method is as follows:
     * - The current state of the board is printed before each move.
     * - The current player is prompted to enter their move as coordinates(r & c)
     * - The input is validated, and the move is applied to the board.
     * - Player re-enters move if its invalid.
     * - The game checks for a win or draw after every move:
     * ---- If the current player wins, the game prints the winner and terminates.
     * ---- If the board is full, the game declares a draw and terminates.
     * 
     * 
     * @throws IllegalArgumentException If the input coordinates are not valid
     *                                  integers or if the move is invalid
     *                                  (e.g., out of bounds or cell occupied).
     */
    public void startGameInteractive() {
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                logger.printBoard();

                Player currentPlayer = turnDeque.pollFirst();
                Piece currentPlayerPiece = currentPlayer.getPlayerPiece();

                System.out.print("Please enter coordinates - " + currentPlayer.getPlayerName() + ": ");
                String input = sc.nextLine().trim();
                try {

                    // Split the input and parse coordinates
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        System.out.println("Invalid input. Please enter coordinates as- <row> <col>");
                        turnDeque.offerFirst(currentPlayer);
                        continue;
                    }

                    int r = Integer.parseInt(parts[0]) - 1;
                    int c = Integer.parseInt(parts[1]) - 1;
                    board.setBoardPiece(r, c, currentPlayerPiece);

                    if (hasPlayerWon(r, c, currentPlayer)) {
                        logger.printBoard();
                        logger.printWinner(currentPlayer);
                        return;
                    }

                    if (board.isFull()) {
                        logger.printBoard();
                        logger.printGameDraw();
                        return;
                    }

                    turnDeque.offerLast(currentPlayer);

                } catch (IllegalArgumentException e) {
                    logger.printInvalidMove();
                    turnDeque.offerFirst(currentPlayer);

                } catch (Exception e) {
                    System.out.println("Exception occured: " + e);

                }
            }
        } finally {
            sc.close();
        }
    }

    /**
     * Checks if the given player has won the game.
     * A player wins if they have their piece (X or O) in:
     * - Any complete row, or
     * - Any complete column, or
     * - Main diagonal (top-left to bottom-right), or
     * - Anti-diagonal (top-right to bottom-left)
     * 
     * @param r      The row of current player
     * @param c      The column of current player
     * @param player The player to check for winning
     * @return true if the player has won, false otherwise
     */
    private boolean hasPlayerWon(int r, int c, Player player) {
        Piece playerPiece = player.getPlayerPiece();
        int size = board.getSize();

        // Check row
        boolean rowWin = true;
        for (int j = 0; j < size; j++) {
            if (board.getBoardPiece(r, j) != playerPiece) {
                rowWin = false;
                break;
            }
        }
        if (rowWin) {
            return true;
        }

        // Check columns
        boolean colWin = true;
        for (int i = 0; i < size; i++) {
            if (board.getBoardPiece(i, c) != playerPiece) {
                colWin = false;
                break;
            }
        }
        if (colWin) {
            return true;
        }

        // Check main diagonal (top-left to bottom-right) and
        // Check anti-diagnal (top-right to bottom-left)
        boolean mainDiagonalWin = true;
        boolean antiDialognalWin = true;
        for (int i = 0; i < size; i++) {
            if (board.getBoardPiece(i, i) != playerPiece) {
                mainDiagonalWin = false;
            }

            if (board.getBoardPiece(i, size - i - 1) != playerPiece) {
                antiDialognalWin = false;
            }
        }
        if (mainDiagonalWin || antiDialognalWin) {
            return true;
        }

        // If no winning condition is met
        return false;
    }

}
