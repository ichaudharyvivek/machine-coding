package com.snakeandladder.models;

import lombok.Getter;

/**
 * Represents the player in the Snake and Ladder game.
 * Each player has a name and a current position on the board.
 */
public class Player {
    private static final int DEFAULT_START_POSITION = 0;
    private static final int MINIMUM_POSITION = 0;

    @Getter
    private final String name;
    private Position position;

    /**
     * Creates a new player with the given name and initializes their position to
     * the default start position.
     *
     * @param name The name of the player
     * @throws IllegalArgumentException if the name is null or empty
     */
    public Player(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty.");
        }

        this.name = name.trim();
        position.setPosition(DEFAULT_START_POSITION);
    }

    /**
     * Updates the player's position on the board.
     *
     * @param position The new position of the player
     * @throws IllegalArgumentException if the position is less than the minimum
     *                                  allowed position
     */
    public void setPosition(int newPosition) {
        if (newPosition < MINIMUM_POSITION) {
            throw new IllegalArgumentException("Position cannot be less than " + MINIMUM_POSITION);
        }

        position.setPosition(newPosition);
    }

    /**
     * Resets the player's position to the default start position.
     */
    public void resetPosition() {
        position.setPosition(DEFAULT_START_POSITION);
    }

    /**
     * Returns a string representation of the player for debugging or display
     * purposes.
     *
     * @return A string containing the player's name and current position
     */
    @Override
    public String toString() {
        return String.format("Player[name= '%s', position= %d]", name, position.getPosition());
    }
}