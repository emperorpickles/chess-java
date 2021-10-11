package com.cnkitzmann.chess;

import com.cnkitzmann.chess.movement.Move;

public class NotationHandler {
    private String pgn;
    private int turnNum;

    public NotationHandler() {
        pgn = "";
        turnNum = 0;
    }

    public void pgnWriter(Move move) {
        String file = String.valueOf((char) (move.getNewPos().x + 'a'));
        String pos = file + (8 - move.getNewPos().y);

        if (move.getTakes()) {
            pos = String.valueOf((char) (move.getCurPos().x + 'a')) + 'x' + pos;
        }

        if (move.getPiece().getType() != 'P') {
            pos = move.getPiece().getType() + pos;
        }

        if (move.getCastles() == 'K') pos = "O-O";
        else if (move.getCastles() == 'Q') pos = "O-O-O";

        if (move.getWhite()) {
            turnNum++;
            pgn = pgn + turnNum + ". " + pos + " ";
        } else {
            pgn = pgn + pos + " ";
        }
    }

    public String getPGN() {
        return pgn;
    }
}
