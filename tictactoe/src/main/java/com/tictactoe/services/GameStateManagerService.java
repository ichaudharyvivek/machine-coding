package com.tictactoe.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tictactoe.models.Board;
import com.tictactoe.models.PieceType;
import com.tictactoe.models.Player;
import com.tictactoe.strategy.DiagonalWinStrategy;
import com.tictactoe.strategy.HorizontalWinStrategy;
import com.tictactoe.strategy.VerticalWinStrategy;
import com.tictactoe.strategy.WinStrategy;

/**
 * The {@code GameStateManagerService} class is responsible for initializing a
 * new game.
 * It acts as a utility and handles the creation of the game board, the setup of
 * winning strategies, and the management of player (incl. names and symbols).
 */
public class GameStateManagerService {

    /**
     * Initializes the game by setting up the board, gathering player information,
     * and configuring the necessary winning strategies.
     *
     * @return A new instance of {@link GameService} with all data.
     */
    public GameService initializeGame() {
        Board board = Board.getInstance();
        List<WinStrategy> strategies = getAllWinningStrategies();

        Scanner sc = new Scanner(System.in);
        List<Player> players = new ArrayList<>();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.isEmpty())
                break;

            String[] parts = input.split(" ");
            String name = parts[1];
            PieceType symbol = getPieceTypeBySymbol(parts[0].charAt(0));
            Player player = new Player(name, symbol);
            players.add(player);
        }

        // This is closed in GameService when the app terminates
        // sc.close();
        return new GameService(board, players, strategies);
    }

    /**
     * Maps the given character to the corresponding {@link PieceType}.
     * 
     * @param symbol The character representing the symbol ('X', 'O', etc.).
     * @return The corresponding PieceType.
     */
    private static PieceType getPieceTypeBySymbol(char symbol) {
        switch (symbol) {
            case 'X':
                return PieceType.SYMBOL_X;

            case 'O':
                return PieceType.SYMBOL_O;

            default:
                throw new IllegalArgumentException("Invalid Symbol.");
        }
    }

    /**
     * Returns a list of all winning strategies.
     * 
     * @return A list of {@link WinStrategy} implementations.
     */
    private static List<WinStrategy> getAllWinningStrategies() {
        return List.of(new VerticalWinStrategy(), new HorizontalWinStrategy(), new DiagonalWinStrategy());
    }
}
