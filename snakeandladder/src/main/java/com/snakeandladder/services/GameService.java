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

    public GameService(Board board, List<Player> players, Dice dice) {
        this.dice = dice;
        this.board = board;
        this.turnQueue = new LinkedList<>(players);
    }

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

            for (Snake snake : board.getSnakes()) {
                newPosition = snake.checkEntity(newPosition);
            }

            for (Ladder ladder : board.getLadders()) {
                newPosition = ladder.checkEntity(newPosition);
            }

            System.out.printf("%s rolled a %d and moved from %d to %d%n",
                    currentPlayer.getName(), diceRoll, currentPosition.getPosition(), newPosition.getPosition());

            currentPlayer.setPosition(newPosition.getPosition());
            if (newPosition.getPosition() == board.getSize()) {
                System.out.printf("%s wins the game%n", currentPlayer.getName());
                break;
            }

            turnQueue.offer(currentPlayer);
        }
    }

}
