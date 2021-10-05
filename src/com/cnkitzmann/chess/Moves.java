package com.cnkitzmann.chess;

import java.awt.*;
import java.util.ArrayList;

public class Moves {
    private ArrayList<Point> validMoves;
    private Board b;
    private Piece p;

//    return moves for given piece type
    public ArrayList<Point> getValidMoves(Board board, Piece piece) {
        validMoves = new ArrayList<>();
        b = board;
        p = piece;

        switch (p.getType()) {
            case 'P' -> Pawn();
            case 'K' -> King();
        }

        return validMoves;
    }

//    methods for each piece
    private void Pawn() {
        int dir = 1;
        if (p.isWhite()) {
            dir = -1;
        }

        validMoves.add(newMove(0, dir));
        if (!p.getMoved()) {
            validMoves.add(newMove(0,2 * dir));
            p.setMoved();
        }
    }
    private void King() {

    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }
    private Point newMove(int x, int y) {
        return new Point(p.getGridX() + x, p.getGridY() + y);
    }
}
