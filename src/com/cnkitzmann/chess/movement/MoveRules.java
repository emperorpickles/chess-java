package com.cnkitzmann.chess.movement;

import com.cnkitzmann.chess.Board;
import com.cnkitzmann.chess.Piece;

import java.awt.*;

import static java.lang.Math.abs;

public class MoveRules {
    private static Point dir;
    private final Board b;
    private final Piece p;
    private final MoveHandler m;
    Point[] diagonals = {new Point(1, 1), new Point(-1, 1), new Point(-1, -1), new Point(1, -1)};
    Point[] lines = {new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)};

    public MoveRules(Board board, Piece piece, MoveHandler moveHandler) {
        b = board;
        p = piece;
        m = moveHandler;

        if (p.isWhite()) {
            dir = new Point(1, -1);
        } else {
            dir = new Point(-1, 1);
        }

        switch (piece.getType()) {
            case 'P' -> Pawn();
            case 'K' -> King();
            case 'Q' -> Queen();
            case 'B' -> Bishop();
            case 'N' -> Knight();
            case 'R' -> Rook();
        }
    }

    //    methods for each piece
    private void Pawn() {
        if (!m.newMove(0, dir.y) && p.getMoved()) {
            m.newMove(0, 2 * dir.y);
        }
        if (b.getPiece(p.getGridX() - 1, p.getGridY() + dir.y) != null) m.newMove(-1, dir.y);
        if (b.getPiece(p.getGridX() + 1, p.getGridY() + dir.y) != null) m.newMove(1, dir.y);

//        TODO -- Add en passant rules
    }

    private void King() {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                m.newMove(i, j);
            }
        }
//        TODO -- Add castling
//        if king hasn't moved + rook hasn't moved + no pieces in between
//
//        short castles
        if (
                p.getMoved() &&
                        b.getPiece(p.getGridX() + 3, p.getGridY()).getMoved() &&
                        b.getPiece(p.getGridX() + 2, p.getGridY()) == null &&
                        b.getPiece(p.getGridX() + 1, p.getGridY()) == null
        ) {
            m.newMove(2, 0, 'K');
        }

        if (
                p.getMoved() &&
                        b.getPiece(p.getGridX() - 4, p.getGridY()).getMoved() &&
                        b.getPiece(p.getGridX() - 3, p.getGridY()) == null &&
                        b.getPiece(p.getGridX() - 2, p.getGridY()) == null &&
                        b.getPiece(p.getGridX() - 1, p.getGridY()) == null
        ) {
            m.newMove(-2, 0, 'Q');
        }

//        TODO -- Add logic for checks / checkmate
    }

    private void Queen() {
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

    private void Bishop() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if (m.newMove(diagonals[i].x * j, diagonals[i].y * j)) break;
            }
        }
    }

    private void Knight() {
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if ((abs(i) + abs(j)) == 3) {
                    m.newMove(i, j);
                }
            }
        }
    }

    private void Rook() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 8; j++) {
                if (m.newMove(lines[i].x * j, lines[i].y * j)) break;
            }
        }
    }
}
