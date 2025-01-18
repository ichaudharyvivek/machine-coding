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
    }

    @Override
    public boolean isValid(int size) {
        if ((start.getPosition() > 0 && end.getPosition() > 0) &&
                (start.getPosition() < size && end.getPosition() < size)) {
            return true;
        }

        return false;
    }

}
