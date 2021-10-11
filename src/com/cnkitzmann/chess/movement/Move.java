package com.cnkitzmann.chess.movement;

import com.cnkitzmann.chess.Board;

import java.awt.*;

public class Move {
    private final Board b;
    private final Point curPos;
    private final Point newPos;
    private final boolean takes;
    private final char castles;

    public Move(Board b, Point p, Point dp) {
        this(b, p, dp, false, ' ');
    }

    public Move(Board b, Point p, Point dp, boolean t) {
        this(b, p, dp, t, ' ');
    }

    public Move(Board b, Point p, Point dp, boolean t, char c) {
        this.b = b;
        this.curPos = p;
        this.newPos = dp;
        this.takes = t;
        this.castles = c;
    }

    public void makeMove() {
        b.movePiece(curPos.x, curPos.y, newPos.x, newPos.y);

        switch (castles) {
            case 'K' -> b.movePiece(curPos.x + 3, curPos.y, curPos.x + 1, curPos.y);
            case 'Q' -> b.movePiece(curPos.x - 4, curPos.y, curPos.x - 1, curPos.y);
        }

        b.setTurn();
    }

    public Point getPoint() {
        return this.newPos;
    }
}
