package com.tictactoe.strategy;

import com.tictactoe.models.Board;
import com.tictactoe.models.PieceType;

public class DiagonalWinStrategy implements WinStrategy {

    @Override
    public boolean checkWin(int row, int col, PieceType pieceType, Board board) {
        int size = board.getSize();
        boolean mainDiagonalWin = true;
        boolean antiDialognalWin = true;

        // Check main diagonal (top-left to bottom-right) and
        // Check anti-diagnal (top-right to bottom-left)
        for (int i = 0; i < size; i++) {
            if (board.getPieceAt(i, i) != pieceType) {
                mainDiagonalWin = false;
            }

            if (board.getPieceAt(i, size - i - 1) != pieceType) {
                antiDialognalWin = false;
            }
        }

        return mainDiagonalWin || antiDialognalWin;
    }

}
