package com.tictactoe.models.piece_types;

import com.tictactoe.models.Piece;

/**
 * Construct a concrete class for no piece represente by '_'
 */
public class PieceNone extends Piece {

    /**
     * Constructs a Piece with the specified type.
     *
     * @param pieceType The type of the piece of enum PieceType
     */
    public PieceNone(PieceType pieceType) {
        super(pieceType);
    }
}
