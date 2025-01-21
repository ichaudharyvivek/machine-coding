package com.tictactoe.services;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.tictactoe.models.Board;
import com.tictactoe.models.Player;
import com.tictactoe.models.GameLogger;
import com.tictactoe.models.Piece;

public class GameService {
    private final Board board;
    private final GameLogger logger;
    private final Deque<Player> turnDeque;

    /**
     * Constructs a GameService instance.
     *
     * @param board   The game board containing snakes, ladders, and the board size.
     * @param players The list of players participating in the game.
     */
    public GameService(Board board, List<Player> players) {
        this.board = board;
        this.turnDeque = new LinkedList<>(players);
        this.logger = GameLogger.getInstance(board);
    }

    public void startGameInteractive() {
        return;
    }

}
