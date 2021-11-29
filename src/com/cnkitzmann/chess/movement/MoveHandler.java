package com.cnkitzmann.chess.movement;

import com.cnkitzmann.chess.Board;
import com.cnkitzmann.chess.pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public class MoveHandler {
    private final ArrayList<Move> moves;
    private final ArrayList<Point> movePoints;
    private final Board b;

    public MoveHandler(Board board) {
        moves = new ArrayList<>();
        movePoints = new ArrayList<>();
        b = board;
    }

//    attempt to execute move for given position
    public boolean makeMove(Point pos) {
        Move move = getMoveFromPoint(pos);
        moves.clear();
        if (move != null) {
            move.makeMove(b);
            return true;
        } else {
            return false;
        }
    }

    //    return moves for given piece type
    public void getPieceMoves(Piece piece) {
        moves.addAll(piece.getMoves());

        movePoints.clear();
        for (Move move : moves) {
            movePoints.add(move.getNewPos());
        }
    }

//    convert list of moves to list of points
    public ArrayList<Point> getMovePoints() {
        if (movePoints.isEmpty()) return null;
        else return movePoints;
    }

    public void clearMovePoints() {
        movePoints.clear();
    }

//    get move that corresponds to a given position
    public Move getMoveFromPoint(Point point) {
        for (Move move : moves) {
            if (point.equals(move.getNewPos())) {
                return move;
            }
        }
        return null;
    }
}
