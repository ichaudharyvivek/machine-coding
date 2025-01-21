package com.tictactoe.models.piece_types;

import com.tictactoe.models.Piece;

/**
 * Construct a concrete class for piece 'X'
 */
public class PieceX extends Piece {

    /**
     * Constructs a Piece with the specified type.
     *
     * @param pieceType The type of the piece of enum PieceType
     */
    public PieceX(PieceType pieceType) {
        super(pieceType);
    }
}
