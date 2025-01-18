package com.snakeandladder.models;

import java.util.Random;
import lombok.Getter;

/**
 * Represents one or more dice with a fixed number of faces.
 * Each die can be rolled to generate a random number.
 */
public class Dice {
    private static final int NUMBER_OF_FACES = 6;
    private static final int DEFAULT_NUMBER_OF_DICE = 1;
    private static final int MINIMUM_DICE = 1;

    @Getter
    private final int numberOfDice;
    private final Random random;

    /**
     * Creates a single die with the default number of face.
     */
    public Dice() {
        this(DEFAULT_NUMBER_OF_DICE);
    }

    /**
     * Creates multiple dice with the default number of faces.
     * 
     * @param numberOfDice The number of dice to create
     * @throws IllegalArgumentException if numberOfDice is less than 1
     */
    public Dice(int numberOfDice) {
        if (numberOfDice < MINIMUM_DICE) {
            throw new IllegalArgumentException("Number of dice must be at least " + MINIMUM_DICE);
        }

        this.numberOfDice = numberOfDice;
        this.random = new Random();
    }

    /**
     * Rolls all dice and returns the sum of their values.
     * 
     * @return The sum of all dice rolls,
     *         between numberOfDice and numberOfDice * NUMBER_OF_FACES
     */
    public int rollDice() {
        int sum = 0;
        for (int i = 0; i < numberOfDice; i++) {
            sum += random.nextInt(NUMBER_OF_FACES) + 1;
        }

        return sum;
    }

}
