package com.cnkitzmann.chess.pieces;

/*
child class for the Queen piece

contains list of all possible moves based on pieces current board position
each turn if the piece has moved this list is updated to reflect its new position

move list should take other pieces into consideration: yes/no?
*/

import java.util.ArrayList;

public class Queen extends Piece {
    private ArrayList<Move> moves;

    public Queen(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
    }

    private void findMoves() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if (m.newMove(lines[i].x * j, lines[i].y * j)) break;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if (m.newMove(diagonals[i].x * j, diagonals[i].y * j)) break;
            }
        }
    }

    private ArrayList<Move> getMoves() {
        return moves;
    }
}
