package com.snakeandladder.models;

/**
 * Snake is an enitity on the board that serves the game function
 */
public class Snake extends Entity {

    /**
     * Creates a snake object
     * 
     * @param start Start of positon of snake
     * @param end   End positon of snake
     */
    public Snake(Position start, Position end) {
        super(start, end);
        if (start.getPosition() <= end.getPosition()) {
            throw new IllegalArgumentException("Snake's head must be greater than its tail.");
        }
    }

    @Override
    public boolean isValid(int size) {
        return start.getPosition() < size && end.getPosition() < size &&
                start.getPosition() != end.getPosition();
    }

}
