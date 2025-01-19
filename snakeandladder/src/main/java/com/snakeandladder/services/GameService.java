package com.snakeandladder.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.snakeandladder.models.*;

/**
 * Service class that manages the core game logic for Snake and Ladder.
 * Handles player turns, dice rolls, and movement on the board.
 */
public class GameService {
    private final Dice dice;
    private final Board board;
    private final Queue<Player> turnQueue;

    /**
     * Constructs a GameService instance.
     *
     * @param board   The game board containing snakes, ladders, and the board size.
     * @param players The list of players participating in the game.
     * @param dice    The dice object to be used for rolling.
     */
    public GameService(Board board, List<Player> players, Dice dice) {
        this.dice = dice;
        this.board = board;
        this.turnQueue = new LinkedList<>(players);
    }

    /**
     * Starts the game loop, allowing players to take turns rolling the dice,
     * moving their pieces, and encountering snakes or ladders.
     * The game ends when a player reaches the final position on the board.
     */
    public void startGame() {
        while (true) {
            Player currentPlayer = turnQueue.poll();
            int diceRoll = dice.rollDice();

            Position currentPosition = currentPlayer.getPosition();
            Position newPosition = new Position(currentPosition.getPosition() + diceRoll);
            if (newPosition.getPosition() > board.getSize()) {
                turnQueue.offer(currentPlayer);
                continue;
            }

            // Check encounter with game's components
            for (Snake snake : board.getSnakes()) {
                newPosition = snake.checkEncounter(newPosition);
            }

            for (Ladder ladder : board.getLadders()) {
                newPosition = ladder.checkEncounter(newPosition);
            }

            System.out.printf("%s rolled a %d and moved from %d to %d%n", currentPlayer.getName(), diceRoll,
                    currentPosition.getPosition(), newPosition.getPosition());

            currentPlayer.setPosition(newPosition.getPosition());
            if (newPosition.getPosition() == board.getSize()) {
                System.out.printf("%s wins the game%n", currentPlayer.getName());
                break;
            }

            turnQueue.offer(currentPlayer);
        }
    }

}
