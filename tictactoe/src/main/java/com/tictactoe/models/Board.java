package com.tictactoe.models;

import java.util.Arrays;

import com.tictactoe.models.piece_types.PieceNone;
import com.tictactoe.models.piece_types.PieceType;

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
    
    @Getter
    private final Piece[][] board;

    /**
     * Private constructor to prevent direct instantiation.
     * Initializes the board with the specified size.
     * Each cell is set to a Piece of type SYMBOL_NONE.
     *
     * @param size The size of the board (e.g., 3 for a 3x3 board).
     * @throws IllegalArgumentException if the size is less than the minimum size.
     */
    private Board(int size) {
        if (size < MIN_SIZE) {
            throw new IllegalArgumentException("Board size should be at least " + MIN_SIZE);
        }

        this.size = size;
        this.board = new Piece[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new PieceNone(PieceType.SYMBOL_NONE);
            }
        }
    }

    /**
     * Returns the singleton instance of the Board with the default size.
     * If the instance does not exist, it creates a new instance with the default
     * size.
     *
     * @return The singleton instance of the Board.
     */
    public static Board getInstance() {
        return getInstance(DEFAULT_SIZE);
    }

    /**
     * Returns the singleton instance of the Board with the specified size.
     * If the instance does not exist, it creates a new instance with the specified
     * size.
     *
     * @param size The size of the board (e.g., 3 for a 3x3 board).
     * @return The singleton instance of the Board.
     * @throws IllegalArgumentException if the size is less than the minimum size.
     */
    public static Board getInstance(int size) {
        if (instance == null) {
            instance = new Board(size);
        }
        return instance;
    }

    /**
     * Returns piece at [row][col]
     * 
     * @param r The row
     * @param c The column
     * @return The Piece at [r][c]
     * @throws IllegalArgumentException if the row or column is out of bounds
     */
    public Piece getBoardPiece(int r, int c) {
        if (r < 0 || c < 0 || r >= size || c >= size) {
            throw new IllegalArgumentException("Invalid row or column.");
        }
        return board[r][c];
    }

    /**
     * Set's the board with piece at [row][col]
     * 
     * @param r     The row
     * @param c     The column
     * @param piece The piece (X, O, etc.)
     * @throws IllegalArgumentException if the row or column is out of bounds
     */
    public void setBoardPiece(int r, int c, Piece piece) {
        if (r < 0 || c < 0 || r >= size || c >= size) {
            throw new IllegalArgumentException("Invalid row or column.");
        }

        if (board[r][c].getPieceType() != PieceType.SYMBOL_NONE) {
            throw new IllegalArgumentException("Invalid row or column");
        }

        board[r][c] = piece;
    }

    /**
     * Checks if the board is full.
     *
     * @return boolean value, true if board is full, else false
     */
    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getPieceType() == PieceType.SYMBOL_NONE) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns a string representation of the Board.
     * This includes the board size and the current state of the board.
     *
     * @return A string representation of the Board.
     */
    @Override
    public String toString() {
        return "Board [size=" + size + ", board=" + Arrays.toString(board) + "]";
    }

}
