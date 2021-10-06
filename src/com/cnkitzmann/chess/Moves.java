package com.cnkitzmann.chess;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Moves {
    private static ArrayList<Point> validMoves;
    private Board b;
    private Piece p;

    Point[] diagonals = {new Point(1,1), new Point(-1,1), new Point(-1,-1), new Point(1,-1)};
    Point[] lines = {new Point(1,0), new Point(0,1), new Point(-1,0), new Point(0,-1)};

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
            case 'Q' -> Queen();
            case 'B' -> Bishop();
            case 'N' -> Knight();
            case 'R' -> Rook();
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

    private boolean newMove(int x, int y) {
        x = p.getGridX() + x;
        y = p.getGridY() + y;

        if (inBounds(x, y)) {
            if (b.getPiece(x, y) != null) {
                if (canTake(x, y)) {
                    validMoves.add(new Point(x, y));
                }
                return true;
            } else {
                validMoves.add(new Point(x, y));
            }
        }
        return false;
    }

    //    methods for each piece
    private void Pawn() {
        int dir = 1;
        if (p.isWhite()) {
            dir = -1;
        }

        if (!newMove(0, dir) && !p.getMoved()) {
            newMove(0, 2 * dir);
        }
        if (b.getPiece(p.getGridX() - 1, p.getGridY() + dir) != null) newMove(-1, dir);
        if (b.getPiece(p.getGridX() + 1, p.getGridY() + dir) != null) newMove(1, dir);

//        TODO -- Add en passant rules
    }

    private void King() {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                newMove(i, j);
            }
        }
    }

    private void Queen() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if(newMove(lines[i].x * j, lines[i].y * j)) break;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if(newMove(diagonals[i].x * j, diagonals[i].y * j)) break;
            }
        }
    }

    private void Bishop() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if(newMove(diagonals[i].x * j, diagonals[i].y * j)) break;
            }
        }
    }

    private void Knight() {
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if ((abs(i) + abs(j)) == 3) {
                    newMove(i, j);
                }
            }
        }
    }

    private void Rook() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if(newMove(lines[i].x * j, lines[i].y * j)) break;
            }
        }
    }


}
