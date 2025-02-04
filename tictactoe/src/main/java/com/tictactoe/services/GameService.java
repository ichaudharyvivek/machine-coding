package com.tictactoe.services;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.tictactoe.exceptions.InvalidMoveException;
import com.tictactoe.models.Board;
import com.tictactoe.models.GameLogger;
import com.tictactoe.models.PieceType;
import com.tictactoe.models.Player;
import com.tictactoe.strategy.WinStrategy;

/**
 * A service class that manages the core game logic for a board game.
 * This class handles player turns, move validation, win conditions, and game
 * state management.
 */
public class GameService {
    private final Board board;
    private final GameLogger logger;
    private final Deque<Player> turnDeque;
    private final WinningService winningService;

    /**
     * Constructs a GameService instance.
     *
     * @param board   The game board containing snakes, ladders, and the board size.
     * @param players The list of players participating in the game.
     */
    public GameService(Board board, List<Player> players, List<WinStrategy> strategies) {
        this.board = board;
        this.turnDeque = new LinkedList<>(players);
        this.logger = GameLogger.getInstance(board);
        this.winningService = new WinningService(strategies);
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
     * 
     * @throws InvalidMoveException     If the move is invalid (e.g., out of bounds
     *                                  or cell occupied).
     */
    public void startGameInteractive() {
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                logger.printBoard();

                Player currentPlayer = turnDeque.pollFirst();
                PieceType currentPlayerPieceType = currentPlayer.getPlayerPiece();

                logger.printPrompt(currentPlayer.getPlayerName());
                String input = sc.nextLine().trim();
                try {
                    // The input is in the form <row> <col>
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        logger.printInvalidInput();
                        turnDeque.offerFirst(currentPlayer);
                        continue;
                    }

                    int r = Integer.parseInt(parts[0]) - 1;
                    int c = Integer.parseInt(parts[1]) - 1;
                    board.setPieceAt(r, c, currentPlayerPieceType);

                    if (winningService.checkWin(r, c, currentPlayerPieceType, board)) {
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

                } catch (IllegalArgumentException | InvalidMoveException e) {
                    logger.printError(e);
                    turnDeque.offerFirst(currentPlayer);
                }
            }

        } catch (Exception e) {
            logger.printError(e);

        } finally {
            sc.close();

        }
    }
}
