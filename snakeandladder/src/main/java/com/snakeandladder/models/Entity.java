package com.snakeandladder.models;

import lombok.Getter;

/**
 * Represents a generic entity on the board (e.g., Snake, Ladder).
 */
public abstract class Entity {
    @Getter
    protected final Position start;

    @Getter
    protected final Position end;

    /**
     * Creates a new Entity with start and end
     * 
     * @param start Start position for the Entity
     * @param end   End position for the Entity
     * @throws IllegalArgumentException if start and end are not positive integers
     */
    public Entity(Position start, Position end) {
        if (start.getPosition() < 0 || end.getPosition() < 0) {
            throw new IllegalArgumentException("Start and end positions must be positive.");
        }

        this.start = start;
        this.end = end;
    }

    /**
     * Checks if the player has encountered this entity and returns the new
     * position.
     *
     * @param current The player's current position.
     * @return The new position if the player encounters the entity; otherwise,
     *         returns the current position.
     */
    public Position checkEncounter(Position current) {
        if (current.getPosition() == start.getPosition()) {
            return end;
        }

        return current;
    }

    /**
     * Custom check entity for its validity
     * 
     * @return true if the entity meets the validity conditions, false otherwise
     */
    public abstract boolean isValid(int size);
}
