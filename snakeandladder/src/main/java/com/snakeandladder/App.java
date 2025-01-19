package com.snakeandladder;

import com.snakeandladder.models.*;
import com.snakeandladder.services.*;

import java.util.*;

/**
 * Main class that handles the Snake and Ladder game execution.
 * Processes input in the specified format and runs the game.
 */
public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfSnakes = sc.nextInt();
        List<Snake> snakes = new ArrayList<>();
        for (int i = 0; i < numberOfSnakes; i++) {
            int head = sc.nextInt();
            int tail = sc.nextInt();
            try {
                snakes.add(new Snake(new Position(head), new Position(tail)));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid snake position: " + e.getMessage());
            }
        }

        int numberOfLadders = sc.nextInt();
        List<Ladder> ladders = new ArrayList<>();
        for (int i = 0; i < numberOfLadders; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            try {
                ladders.add(new Ladder(new Position(start), new Position(end)));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid snake position: " + e.getMessage());
            }
        }

        int numberOfPlayers = sc.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            String name = sc.next();
            players.add(new Player(name));
        }

        sc.close();

        // Initialize the game component
        BoardService boardService = new BoardService(100, snakes, ladders);
        Board board = boardService.getBoard();
        Dice dice = new Dice();

        // Start game
        GameService gameService = new GameService(board, players, dice);
        gameService.startGame();
    }
}
