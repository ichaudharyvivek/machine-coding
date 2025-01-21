package com.tictactoe.models.piece_types;

/**
 * Enum representing the different types of pieces used in a Tic-Tac-Toe game.
 * Each piece is associated with a specific character symbol.
 * This can be extended with other symbols as required.
 */
public enum PieceType {
    SYMBOL_NONE('_'),
    SYMBOL_X('X'),
    SYMBOL_O('O');

    private final char symbol;

    /**
     * Constructor to associate a symbol with each piece type.
     *
     * @param symbol The character representation of the piece.
     */
    private PieceType(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Retrieves the character symbol associated with the piece type.
     *
     * @return The character symbol.
     */
    public char getSymbol() {
        return symbol;
    }

}
