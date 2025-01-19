package com.snakeandladder.models;

import lombok.Getter;

/**
 * Represents a position on the Snake and Ladder board.
 * The position is a positive integer representing a specific location on the
 * board.
 */
public class Position {
    @Getter
    private int position;

    /**
     * Constructs a Position object with the given position value.
     * We use position class to make App dynamic. In future, if we need to introduce
     * new types of dimentions (e.g. row and column) then only this class needs to
     * be updated. All other class are only concered with this abstraction.
     * 
     * @param position The position value, must be >= 0.
     * @throws IllegalArgumentException if the position is < 0.
     */
    public Position(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Position must be greater than or equal to 0.");
        }

        this.position = position;
    }

    /**
     * Sets a new position on the board.
     * 
     * @param position The new position value, must be >= 0.
     * @throws IllegalArgumentException if the position is < 0.
     */
    public void setPosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Position must be greater than or equal to 0.");
        }
        this.position = position;
    }

    /**
     * Returns a string representation of the current position.
     * 
     * @return A string representation of the position.
     */
    @Override
    public String toString() {
        return "Position: " + position;
    }
}