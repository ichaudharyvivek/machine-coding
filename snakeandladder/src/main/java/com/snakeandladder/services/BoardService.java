package com.snakeandladder.services;

import com.snakeandladder.models.*;

import java.util.List;

public class BoardService {
    private final Board board;

    public BoardService(int size, List<Snake> snakes, List<Ladder> ladders) {
        this.board = Board.getInstance(size);

        for (Snake snake : snakes) {
            board.addSnake(snake);
        }

        for (Ladder ladder : ladders) {
            board.addLadder(ladder);
        }
    }

    public Board getBoard() {
        return board;
    }
}
