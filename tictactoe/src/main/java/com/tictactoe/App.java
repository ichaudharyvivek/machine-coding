package com.tictactoe;

import com.tictactoe.services.GameService;
import com.tictactoe.services.GameStateManagerService;

/**
 * Main class that handles the TicTacToe game execution.
 * Processes input in the specified format and runs the game.
 */
public class App {
    public static void main(String[] args) {
        GameStateManagerService manager = new GameStateManagerService();
        GameService gameService = manager.initializeGame();
        gameService.startGameInteractive();
    }
}
