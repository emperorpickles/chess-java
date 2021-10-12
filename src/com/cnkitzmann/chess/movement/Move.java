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
    private char special;

    public Move(Board b, Piece p, Point cp, Point dp) {
        this(b, p, cp, dp, false, ' ');
    }

    public Move(Board b, Piece p, Point cp, Point dp, boolean t) {
        this(b, p, cp, dp, t, ' ');
    }

    public Move(Board b, Piece p, Point cp, Point dp, boolean t, char s) {
        this.b = b;
        this.p = p;
        this.curPos = cp;
        this.newPos = dp;
        this.takes = t;
        this.special = s;
    }

    public void makeMove() {
        b.movePiece(curPos.x, curPos.y, newPos.x, newPos.y);

        switch (special) {
            case 'K' -> b.movePiece(curPos.x + 3, curPos.y, curPos.x + 1, curPos.y);
            case 'Q' -> b.movePiece(curPos.x - 4, curPos.y, curPos.x - 1, curPos.y);
            case 'D' -> {
                if (!p.isWhite()) this.special = 'd';
            }
            case 'E' -> b.removePiece(newPos.x, curPos.y);
        }

        b.setTurn();
        b.updatePGN(this);
        b.updateFEN(b, this);
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

    public char getSpecial() {
        return this.special;
    }

    public boolean getTakes() {
        return this.takes;
    }
}
