package com.tictactoe.exceptions;

/**
 * Exception thrown when an invalid move is attempted in the Tic-Tac-Toe game.
 */
public class InvalidMoveException extends Exception {
    /**
     * Constructs a new InvalidMoveException with a specific error message.
     * 
     * @param message Detailed description of the invalid move
     */
    public InvalidMoveException(String message) {
        super(message);
    }
}