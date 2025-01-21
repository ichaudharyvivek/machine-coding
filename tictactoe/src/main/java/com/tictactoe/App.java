package com.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tictactoe.models.Board;
import com.tictactoe.models.Player;
import com.tictactoe.models.piece_types.PieceType;
import com.tictactoe.services.GameService;

/**
 * Main class that handles the TicTacToe game execution.
 * Processes input in the specified format and runs the game.
 */
public class App {
    public static void main(String[] args) {
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

        sc.close();

        // Initialize board and start game
        Board board = Board.getInstance();
        GameService gameService = new GameService(board, players);
        gameService.startGameInteractive();

    }

    /**
     * Maps the given character to the corresponding PieceType.
     * 
     * @param symbol The character representing the symbol ('X', 'O', etc.).
     * @return The corresponding PieceType.
     */
    public static PieceType getPieceTypeBySymbol(char symbol) {
        switch (symbol) {
            case 'X':
                return PieceType.SYMBOL_X;

            case 'O':
                return PieceType.SYMBOL_O;

            default:
                throw new IllegalArgumentException("Invalid Symbol.");
        }
    }
}
