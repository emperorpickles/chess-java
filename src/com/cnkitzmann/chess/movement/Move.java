package com.cnkitzmann.chess.movement;

import com.cnkitzmann.chess.Board;
import com.cnkitzmann.chess.Piece;

import java.awt.*;

public class Move {
    private final Board b;
    private final Piece p;
    private final Point curPos;
    private final Point newPos;
    private final boolean takes;
    private final char castles;

    public Move(Board b, Piece p, Point cp, Point dp) {
        this(b, p, cp, dp, false, ' ');
    }

    public Move(Board b, Piece p, Point cp, Point dp, boolean t) {
        this(b, p, cp, dp, t, ' ');
    }

    public Move(Board b, Piece p, Point cp, Point dp, boolean t, char c) {
        this.b = b;
        this.p = p;
        this.curPos = cp;
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
        b.updatePGN(this);
    }

    public Point getNewPos() {
        return this.newPos;
    }

    public Point getCurPos() {
        return this.curPos;
    }

    public Piece getPiece() {
        return this.p;
    }

    public boolean getWhite() {
        return this.p.isWhite();
    }

    public char getCastles() {
        return this.castles;
    }

    public boolean getTakes() {
        return this.takes;
    }
}
