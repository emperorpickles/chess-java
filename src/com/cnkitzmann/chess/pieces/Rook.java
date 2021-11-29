package com.cnkitzmann.chess.pieces;

import com.cnkitzmann.chess.PiecesHandler;

public class Rook extends Piece {
    public Rook(int x, int y, boolean isWhite, PiecesHandler ph) {
        super(x, y, isWhite, 'r', ph);
    }

    protected void generateMoves() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if (newMove(lines[i].x * j, lines[i].y * j)) break;
            }
        }
    }
}
