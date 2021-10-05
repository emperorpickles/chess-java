package com.cnkitzmann.chess;

import java.awt.*;
import java.util.ArrayList;

public class Moves {
    private static ArrayList<Point> validMoves;
    private Board b;
    private Piece p;

    public static ArrayList<Point> getValidMoves() {
        return validMoves;
    }

    //    return moves for given piece type
    public void setValidMoves(Board board, Piece piece) {
        validMoves = new ArrayList<>();
        b = board;
        p = piece;

        switch (p.getType()) {
            case 'P' -> Pawn();
            case 'K' -> King();
        }
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }

    private boolean canTake(int x, int y) {
        if (b.getPiece(x, y).isWhite() != p.isWhite()) {
            return p.getType() != 'P' || p.getGridX() != x;
        }
        return false;
    }

    private boolean isValid(int x, int y) {
        if (inBounds(x, y)) {
            if (b.getPiece(x, y) != null) {
                return canTake(x, y);
            }
            return true;
        }
        return false;
    }

    private void newMove(int x, int y) {
        x = p.getGridX() + x;
        y = p.getGridY() + y;

        if (isValid(x, y)) {
            validMoves.add(new Point(x, y));
        }
    }

    //    methods for each piece
    private void Pawn() {
        int dir = 1;
        if (p.isWhite()) {
            dir = -1;
        }

        newMove(0, dir);
        if (!p.getMoved()) {
            newMove(0, 2 * dir);
        }
        if (b.getPiece(p.getGridX() - 1, p.getGridY() + dir) != null) newMove(-1, dir);
        if (b.getPiece(p.getGridX() + 1, p.getGridY() + dir) != null) newMove(1, dir);

//        TODO -- Add en passant rules
    }

    private void King() {

    }


}
