package com.snakeandladder.services;

import com.snakeandladder.models.*;

import java.util.List;

/**
 * Service class to manage the game board, including its size, snakes, and
 * ladders.
 */
public class BoardService {
    private final Board board;

    /**
     * Constructs a BoardService and initializes the board with the given size,
     * snakes, and ladders.
     *
     * @param size    The size of the board.
     * @param snakes  The list of snakes to add to the board.
     * @param ladders The list of ladders to add to the board.
     */
    public BoardService(int size, List<Snake> snakes, List<Ladder> ladders) {
        this.board = Board.getInstance(size);

        snakes.forEach(i -> board.addSnake(i));
        ladders.forEach(i -> board.addLadder(i));
    }

    /**
     * Retrieves the board instance managed by this service.
     *
     * @return The board instance.
     */
    public Board getBoard() {
        return board;
    }
}
