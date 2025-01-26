package com.tictactoe.services;

import java.util.List;

import com.tictactoe.models.Board;
import com.tictactoe.models.PieceType;
import com.tictactoe.strategy.WinStrategy;

/**
 * TL:DR, This is just Strategy Pattern.
 * This file can also be named as {@code WinningStrategy.java}.
 * 
 * The {@code WinningService} class is responsible for checking if a player
 * has won the game by evaluating a set of win strategies. It delegates the
 * win-checking logic to a list of provided {@link WinStrategy} implementations.
 * 
 * This class implements the {@link WinStrategy} interface, making it compatible
 * with other strategies and allowing it to be used polymorphically.
 */
public class WinningService implements WinStrategy {
    private final List<WinStrategy> strategies;

    /**
     * Constructs a {@code WinningService} with a list of win strategies.
     * 
     * @param strategies a list of {@link WinStrategy} implementations to evaluate
     */
    public WinningService(List<WinStrategy> strategies) {
        this.strategies = strategies;
    }

    /**
     * Checks if the given player has won the game.
     * A player wins if they have their piece (X or O) in:
     * - Any complete row, or
     * - Any complete column, or
     * - Main diagonal (top-left to bottom-right), or
     * - Anti-diagonal (top-right to bottom-left)
     * 
     * @param row       the row index of the last move
     * @param col       the column index of the last move
     * @param pieceType the {@link PieceType} of the player making the move
     * @param board     the {@link Board} representing the current state of the game
     * @return {@code true} if any strategy detects a win; {@code false} otherwise
     */
    @Override
    public boolean checkWin(int row, int col, PieceType pieceType, Board board) {
        for (WinStrategy strategy : strategies) {
            if (strategy.checkWin(row, col, pieceType, board)) {
                return true;
            }
        }

        return false;
    }
}
