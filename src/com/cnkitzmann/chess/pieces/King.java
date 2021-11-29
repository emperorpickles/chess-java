package com.cnkitzmann.chess.pieces;

import com.cnkitzmann.chess.PiecesHandler;

public class King extends Piece {
    public King(int x, int y, boolean isWhite, PiecesHandler ph) {
        super(x, y, isWhite, 'k', ph);
    }

    protected void generateMoves() {
//        basic king movement
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                newMove(i, j);
            }
        }

//        if king hasn't moved + rook hasn't moved + no pieces in between
//        short castles
        if (
                !moved &&
                        ph.getPieceAtCoord(gridPos.x + 3, gridPos.y) != null &&
                        !ph.getPieceAtCoord(gridPos.x + 3, gridPos.y).getMoved() &&
                        ph.getPieceAtCoord(gridPos.x + 2, gridPos.y) == null &&
                        ph.getPieceAtCoord(gridPos.x + 1, gridPos.y) == null
        ) {
            newMove(2, 0, 'K');
        }
//        long castles
        if (
                !moved &&
                        ph.getPieceAtCoord(gridPos.x - 4, gridPos.y) != null &&
                        !ph.getPieceAtCoord(gridPos.x - 4, gridPos.y).getMoved() &&
                        ph.getPieceAtCoord(gridPos.x - 3, gridPos.y) == null &&
                        ph.getPieceAtCoord(gridPos.x - 2, gridPos.y) == null &&
                        ph.getPieceAtCoord(gridPos.x - 1, gridPos.y) == null
        ) {
            newMove(-2, 0, 'Q');
        }

//        TODO -- Add logic for checks / checkmate
    }
}
