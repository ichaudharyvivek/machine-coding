package com.tictactoe.strategy;

import com.tictactoe.models.Board;
import com.tictactoe.models.PieceType;

/**
 * Represents a strategy for checking win conditions in a game.
 * Implementations define specific ways of determining a win
 * (e.g., horizontal, vertical, or diagonal).
 */
public interface WinStrategy {

    /**
     * Determines if the specified piece type has achieved a winning condition
     * on the board, starting from the given row and column.
     *
     * @param row       The row index of the last move
     * @param col       The column index of the last move
     * @param pieceType The type of piece to check for a win
     * @param board     The current state of the game board
     * @return {@code true} if the piece type satisfies the win condition, else
     *         {@code false}
     */
    public boolean checkWin(int row, int col, PieceType pieceType, Board board);
}
