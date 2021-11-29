package com.cnkitzmann.chess.pieces;

import com.cnkitzmann.chess.PiecesHandler;
import static java.lang.Math.abs;

public class Knight extends Piece {
    public Knight(int x, int y, boolean isWhite, PiecesHandler ph) {
        super(x, y, isWhite, 'n', ph);
    }

    protected void generateMoves() {
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if ((abs(i) + abs(j)) == 3) {
                    newMove(i, j);
                }
            }
        }
    }
}
