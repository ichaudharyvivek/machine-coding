package com.snakeandladder.models;

/**
 * Ladder is an enitity on the board that serves the game function
 */
public class Ladder extends Entity {

    /**
     * Creates a snake object
     * 
     * @param start Start of positon of snake
     * @param end   End positon of snake
     */
    public Ladder(Position start, Position end) {
        super(start, end);
    }

    @Override
    public boolean isValid() {
        // TODO: Work on validation and check for 0th and boards last positon
        // if (!(start.getPosition() > 0 && end.getPosition() > 0) &&
        // (start.getPosition() < 100
        // && end.getPosition() < 100)) {

        // }
        return true;
    }
}
