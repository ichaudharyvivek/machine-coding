package com.tictactoe.strategy;

import com.tictactoe.models.Board;
import com.tictactoe.models.PieceType;

public class VerticalWinStrategy implements WinStrategy {

    @Override
    public boolean checkWin(int row, int col, PieceType pieceType, Board board) {
        int size = board.getSize();
        boolean colWin = true;

        // Check columns
        for (int i = 0; i < size; i++) {
            if (board.getPieceAt(i, col) != pieceType) {
                colWin = false;
                break;
            }
        }

        return colWin;
    }

}
