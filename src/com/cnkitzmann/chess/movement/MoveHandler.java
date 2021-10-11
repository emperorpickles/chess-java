package com.cnkitzmann.chess.movement;

import com.cnkitzmann.chess.Board;
import com.cnkitzmann.chess.Piece;

import java.awt.*;
import java.util.ArrayList;

public class MoveHandler {
    private static ArrayList<Move> moves;
    private final Board b;
    private Piece p;

    public MoveHandler(Board board) {
        moves = new ArrayList<>();
        b = board;
    }

    public void makeMove(Point pos) {
        Move move = getMove(pos);
        if (move != null) {
            move.makeMove();
        }
        moves.clear();
    }


    //    return moves for given piece type
    public void findValidMoves(Piece piece) {
        this.p = piece;
        new MoveRules(b, piece, this);
    }

    public static ArrayList<Point> getMovePoints() {
        if (moves.isEmpty()) return null;

        ArrayList<Point> movePoints = new ArrayList<>();
        for (Move move : moves) {
            movePoints.add(move.getPoint());
        }
        return movePoints;
    }

    public Move getMove(Point point) {
        for (Move move : moves) {
            if (point.equals(move.getPoint())) {
                return move;
            }
        }
        return null;
    }


    public boolean newMove(int dx, int dy) {
        return newMove(dx, dy, ' ');
    }

    public boolean newMove(int dx, int dy, char castles) {
        Point curPos = new Point(p.getGridX(), p.getGridY());

        Point newPos = (Point) curPos.clone();
        newPos.translate(dx, dy);

        if (inBounds(newPos)) {
            if (b.getPiece(newPos.x, newPos.y) != null) {
                if (canTake(newPos)) {
                    moves.add(new Move(b, curPos, newPos, true, castles));
                }
                return true;
            } else {
                moves.add(new Move(b, curPos, newPos, true, castles));
            }
        }
        return false;
    }

    private boolean inBounds(Point newPos) {
        return newPos.x >= 0 && newPos.y >= 0 && newPos.x < 8 && newPos.y < 8;
    }

    private boolean canTake(Point newPos) {
        if (b.getPiece(newPos.x, newPos.y).isWhite() != p.isWhite()) {
            return p.getType() != 'P' || p.getGridX() != newPos.x;
        }
        return false;
    }
}
