package com.cnkitzmann.chess.pieces;

import com.cnkitzmann.chess.PiecesHandler;

public class Bishop extends Piece {
    public Bishop(int x, int y, boolean isWhite, PiecesHandler ph) {
        super(x, y, isWhite, 'b', ph);
    }

    protected void generateMoves() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if (newMove(diagonals[i].x * j, diagonals[i].y * j)) break;
            }
        }
    }
}
