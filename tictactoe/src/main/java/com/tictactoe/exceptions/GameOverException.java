package com.tictactoe.exceptions;

/**
 * Exception thrown when an action is attempted after the game has ended.
 */
public class GameOverException extends Exception {
    /**
     * Constructs a new GameOverException with a specific error message.
     * 
     * @param message Detailed description of why the game is over
     */
    public GameOverException(String message) {
        super(message);
    }
}