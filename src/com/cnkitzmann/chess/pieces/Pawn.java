package com.cnkitzmann.chess.pieces;

import com.cnkitzmann.chess.Board;
import com.cnkitzmann.chess.PiecesHandler;

public class Pawn extends Piece {
    private final int dir;
    private final Board b;

    public Pawn(int x, int y, boolean isWhite, PiecesHandler ph, Board board) {
        super(x, y, isWhite, 'p', ph);
        b = board;

        if (isWhite) dir = -1;
        else dir = 1;
    }

    protected void generateMoves() {
        if (!newMove(0, dir) && !moved) {
            newMove(0, 2 * dir, 'D');
        }
        if (ph.getPieceAtCoord(gridPos.x - 1, gridPos.y + dir) != null) newMove(-1, dir);
        if (ph.getPieceAtCoord(gridPos.x + 1, gridPos.y + dir) != null) newMove(1, dir);

//        en passant
        String[] fenSections = b.getFEN().split("\\s");
        if (fenSections.length > 1) {
            String enPassant = fenSections[3];
            if (!enPassant.contains("-") && (gridPos.y == 3 || gridPos.y == 4)) {
                System.out.println("en passant possible");
                int x = (enPassant.charAt(0) - 'a') - gridPos.x;
                if (x >= -1 && x <= 1) {
                    newMove(x, dir, 'E');
                }
            }
        }
    }
}
