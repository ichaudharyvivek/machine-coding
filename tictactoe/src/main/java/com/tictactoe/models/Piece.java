package com.tictactoe.models;

import com.tictactoe.models.piece_types.PieceType;

import lombok.Getter;

/**
 * Represents a piece in the Tic-Tac-Toe game.
 * Each piece has a specific type that determines its symbol (e.g., X, O, or _).
 */
public abstract class Piece {
    @Getter
    private final PieceType pieceType;

    /**
     * Constructs a Piece with the specified type.
     *
     * @param pieceType The type of the piece of enum PieceType
     */
    public Piece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    /**
     * Retrieves the character symbol associated with the piece type.
     * Examples include 'X', 'O', or '_' for empty cells.
     *
     * @return The character symbol of the piece type.
     */
    public char getSymbol() {
        return pieceType.getSymbol();
    }
}
