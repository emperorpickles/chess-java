package com.cnkitzmann.chess.pieces;

/*
child class for the Queen piece

contains list of all possible moves based on pieces current board position
each turn if the piece has moved this list is updated to reflect its new position

move list should take other pieces into consideration: yes/no?
*/

import com.cnkitzmann.chess.PiecesHandler;

public class Queen extends Piece {

    public Queen(int x, int y, boolean isWhite, PiecesHandler ph) {
        super(x, y, isWhite, 'q', ph);
    }

    protected void generateMoves() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if (newMove(lines[i].x * j, lines[i].y * j)) break;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if (newMove(diagonals[i].x * j, diagonals[i].y * j)) break;
            }
        }
    }
}
