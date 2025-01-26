package com.tictactoe.models;

import java.util.Arrays;

import com.tictactoe.exceptions.InvalidMoveException;

import lombok.Getter;

/**
 * Represents the game board for TicTacToe.
 * The board is a singleton to ensure there is only one instance in the game.
 * The board is initialized with a specified size and can be accessed globally.
 */
public class Board {
    private static Board instance;
    private static final int MIN_SIZE = 3;
    private static final int DEFAULT_SIZE = 3;

    @Getter
    private final int size;

    /**
     * Note to self:
     * Avoid using @Getter to return arrays directly, as it allows users to
     * access and modify the returned array, potentially breaking encapsulation.
     * Instead use a custom getter method which streams or return a read-only copy
     * of the array.
     * 
     */
    private final PieceType[][] board;

    /**
     * Private constructor to prevent direct instantiation.
     * Initializes the {@code Board} with the specified size.
     * Each cell is set to a {@link PieceType} enum of {@code SYMBOL_NONE}.
     *
     * @param size The size of the board (e.g., 3 for a 3x3 board).
     * @throws IllegalArgumentException if the size is less than the minimum size.
     */
    private Board(int size) {
        if (size < MIN_SIZE) {
            throw new IllegalArgumentException("Board size should be at least " + MIN_SIZE);
        }

        this.size = size;
        this.board = new PieceType[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = PieceType.SYMBOL_NONE;
            }
        }
    }

    /**
     * Returns the singleton instance of the Board with the default size.
     * If the instance does not exist, it creates a new instance with the default
     * size.
     *
     * @return The singleton instance of the {@link Board}.
     */
    public static Board getInstance() {
        return getInstance(DEFAULT_SIZE);
    }

    /**
     * Returns the singleton instance of the Board with the specified size.
     * If the instance does not exist, it creates a new instance with the specified
     * size.
     *
     * @param size The {@code size} of the board (e.g., 3 for a 3x3 board).
     * @return The singleton instance of the {@link Board}.
     */
    public static Board getInstance(int size) {
        if (instance == null) {
            instance = new Board(size);
        }

        return instance;
    }

    /**
     * Returns piece at [row][col].
     * 
     * @param r The row.
     * @param c The column.
     * @return The {@link PieceType} at [r][c].
     * @throws IllegalArgumentException if the row or column is out of bounds.
     */
    public PieceType getPieceAt(int r, int c) {
        if (r < 0 || c < 0 || r >= size || c >= size) {
            throw new IllegalArgumentException("Invalid row or column.");
        }

        return board[r][c];
    }

    /**
     * Set the board with a piece at [row][col].
     * 
     * @param r         The row.
     * @param c         The column.
     * @param pieceType The piece type (X, O, etc.).
     * @throws InvalidMoveException     If the move is invalid.
     * @throws IllegalArgumentException if the row or column is out of bounds.
     */
    public void setPieceAt(int r, int c, PieceType pieceType) throws InvalidMoveException {
        if (r < 0 || c < 0 || r >= size || c >= size) {
            throw new IllegalArgumentException("Invalid row or column.");
        }

        if (board[r][c] != PieceType.SYMBOL_NONE) {
            throw new InvalidMoveException("Cell is already occupied.");
        }

        board[r][c] = pieceType;
    }

    /**
     * Checks if the board is empty.
     *
     * @return {@code true} if board is empty, else returns {@code false}.
     */
    public boolean isEmpty() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != PieceType.SYMBOL_NONE) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if the board is full.
     *
     * @return boolean value, true if board is full, else false
     */
    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == PieceType.SYMBOL_NONE) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns a string representation of the Board.
     * This includes the board size and the current state of the {@link Board}.
     *
     * @return A string representation of the {@link Board}.
     */
    @Override
    public String toString() {
        return "Board [size=" + size + ", board=" + Arrays.toString(board) + "]";
    }

}
