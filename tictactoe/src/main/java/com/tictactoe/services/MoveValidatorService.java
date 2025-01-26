package com.tictactoe.services;

import com.tictactoe.exceptions.InvalidMoveException;
import com.tictactoe.models.Board;

/**
 * The {@code MoveValidatorService} class provides a utility method to validate
 * moves in a Tic Tac Toe game. It ensures that a move is within the bounds of
 * the board and that the target cell is unoccupied.
 * 
 * This class helps enforce the rules of the game by preventing invalid moves.
 */
public class MoveValidatorService {

    /**
     * Validates a player's move on the Tic Tac Toe board.
     * 
     * @param row   the row index of the move
     * @param col   the column index of the move
     * @param board the {@link Board} representing the current state of the game
     * @throws InvalidMoveException if the move is out of bounds or the cell is
     *                              already occupied
     */
    public static void validateMove(int row, int col, Board board) throws InvalidMoveException {
        // Check if the move is within bounds
        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            throw new InvalidMoveException("Move is out of bounds.");
        }

        // Check if the cell is already occupied
        if (!board.isEmpty()) {
            throw new InvalidMoveException("Cell is already occupied.");
        }
    }
}
