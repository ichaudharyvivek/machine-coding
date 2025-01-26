package com.tictactoe.strategy;

import com.tictactoe.models.Board;
import com.tictactoe.models.PieceType;

public class HorizontalWinStrategy implements WinStrategy {

    @Override
    public boolean checkWin(int row, int col, PieceType pieceType, Board board) {
        int size = board.getSize();
        boolean rowWin = true;

        // Check row
        for (int j = 0; j < size; j++) {
            if (board.getPieceAt(row, j) != pieceType) {
                rowWin = false;
                break;
            }
        }

        return rowWin;
    }

}
