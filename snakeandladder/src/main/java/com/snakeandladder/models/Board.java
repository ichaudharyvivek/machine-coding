package com.snakeandladder.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game board for Snake and Ladder.
 * The board is a singleton to ensure there is only one instance in the game.
 */
public class Board {
    private static final int DEFAULT_SIZE = 100;
    private static final int MIN_SIZE = 10;
    private static Board instance;

    @Getter
    private final int size;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;

    /**
     * Private constructor to initialize the board.
     *
     * @param size The size of the board.
     */
    private Board(int size) {
        if (size < MIN_SIZE) {
            throw new IllegalArgumentException("Board size must be at least " + MIN_SIZE);
        }

        this.size = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the board with the default size.
     *
     * @return The singleton instance of the board.
     */
    public static Board getInstance() {
        return getInstance(DEFAULT_SIZE);
    }

    /**
     * Returns the singleton instance of the board.
     *
     * @param size The size of the board, used only during the first initialization.
     * @return The singleton instance of the board.
     */
    public static Board getInstance(int size) {
        if (instance == null) {
            instance = new Board(size);
        }

        return instance;
    }

    /**
     * Adds a snake to the board.
     *
     * @param snake The snake to add.
     * @throws IllegalArgumentException if the snake is invalid or overlaps with an
     *                                  existing entity.
     */
    public void addSnake(Snake snake) {
        if (!snake.isValid()) {
            throw new IllegalArgumentException(
                    "Invalid snake: Start should be greater than end and within board limits.");
        }

        if (isOverlapping(snake)) {
            throw new IllegalArgumentException("Snake overlaps with an existing entity.");
        }

        snakes.add(snake);
    }

    /**
     * Adds a ladder to the board.
     *
     * @param ladder The ladder to add.
     * @throws IllegalArgumentException if the ladder is invalid or overlaps with an
     *                                  existing entity.
     */
    public void addLadder(Ladder ladder) {
        if (!ladder.isValid()) {
            throw new IllegalArgumentException(
                    "Invalid ladder: End should be greater than start and within board limits.");
        }

        if (isOverlapping(ladder)) {
            throw new IllegalArgumentException("Ladder overlaps with an existing entity.");
        }

        ladders.add(ladder);
    }

    /**
     * Gets all the snakes on the board.
     *
     * @return List of snakes.
     */
    public List<Snake> getSnakes() {
        return new ArrayList<>(snakes); // Return a copy to preserve encapsulation
    }

    /**
     * Gets all the ladders on the board.
     *
     * @return List of ladders.
     */
    public List<Ladder> getLadders() {
        return new ArrayList<>(ladders); // Return a copy to preserve encapsulation
    }

    /**
     * Resets the board by clearing all entities (snakes and ladders).
     */
    public void resetBoard() {
        snakes.clear();
        ladders.clear();
    }

    /**
     * Checks if an entity overlaps with existing snakes or ladders on the board.
     *
     * @param entity The entity to check.
     * @return true if the entity overlaps, false otherwise.
     */
    private boolean isOverlapping(Entity entity) {
        for (Snake snake : snakes) {
            if (snake.getStart().getPosition() == entity.getStart().getPosition() ||
                    snake.getEnd().getPosition() == entity.getEnd().getPosition()) {
                return true;
            }
        }

        for (Ladder ladder : ladders) {
            if (ladder.getStart().getPosition() == entity.getStart().getPosition() ||
                    ladder.getEnd().getPosition() == entity.getEnd().getPosition()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a string representation of the board.
     *
     * @return A string representation of the board with its size, snakes, and
     *         ladders.
     */
    @Override
    public String toString() {
        return "Board [size= " + size + ", snakes= " + snakes.size() + ", ladders= " + ladders.size() + "]";
    }
}
