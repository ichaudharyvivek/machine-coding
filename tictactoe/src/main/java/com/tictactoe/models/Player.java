package com.tictactoe.models;

import com.tictactoe.models.piece_types.PieceType;
import com.tictactoe.models.piece_types.PieceO;
import com.tictactoe.models.piece_types.PieceX;

import lombok.Getter;

/**
 * Represents the player in the tictactoe game.
 * Each player has a name and a playing piece on the board.
 */
public class Player {
    @Getter
    private String playerName;
    @Getter
    private Piece playerPiece;

    /**
     * Creates a new player with the given name and initializes their position to
     * the default start position.
     *
     * @param name      The name of the player
     * @param pieceType The valid enum piece types
     * @throws IllegalArgumentException if the name is null or empty
     */
    public Player(String name, PieceType pieceType) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty.");
        }

        this.playerName = name.trim();
        char symbol = pieceType.getSymbol();
        switch (symbol) {
            case 'X':
                this.playerPiece = new PieceX(pieceType);
                break;
            case 'O':
                this.playerPiece = new PieceO(pieceType);
                break;
            default:
                throw new IllegalArgumentException("Invalid piece type.");
        }
    }

    /**
     * Returns a string representation of the player for debugging or display
     * purposes.
     *
     * @return A string containing the player's name and current position
     */
    @Override
    public String toString() {
        return "Player [playerName=" + playerName + ", playerPiece=" + playerPiece.getSymbol() + "]";
    }

}
