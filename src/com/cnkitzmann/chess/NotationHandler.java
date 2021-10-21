package com.cnkitzmann.chess;

import com.cnkitzmann.chess.movement.Move;

public class NotationHandler {
    private String pgn;
    private String fen;
    private int turnNum;

    public NotationHandler() {
        pgn = "";
        fen = "";
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

        if (move.getSpecial() == 'K') pos = "O-O";
        else if (move.getSpecial() == 'Q') pos = "O-O-O";

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

    public void fenWriter(Board b, Move m) {
        fen = "";
        StringBuilder strBuilder = new StringBuilder(fen);

//        board state
        int gap = 0;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                Piece piece = b.getPiece(i, j);
                if (piece == null) {
                    gap++;
                } else {
                    if (gap > 0) strBuilder.append(gap);
                    gap = 0;

                    if (piece.isWhite()) strBuilder.append(piece.getType());
                    else strBuilder.append(Character.toLowerCase(piece.getType()));
                }
            }
            if (gap > 0) strBuilder.append(gap);
            gap = 0;
            strBuilder.append("/");
        }

//        current turn
        if (b.getTurn()) strBuilder.append(" w");
        else strBuilder.append(" b");

//        castling rights
        strBuilder.append(" -");

//        en passant target square
        if (m.getSpecial() == 'D') {
            String pos = String.valueOf((char) (m.getNewPos().x + 'a')) + '3';
            strBuilder.append(' ').append(pos);
        } else if (m.getSpecial() == 'd') {
            String pos = String.valueOf((char) (m.getNewPos().x + 'a')) + '6';
            strBuilder.append(' ').append(pos);
        } else strBuilder.append(" -");

//        half move clock
        strBuilder.append(" -");

//        full move counter
        strBuilder.append(" -");

        fen = strBuilder.toString();
        System.out.println(fen);
    }

    public void fenEnPassant() {

    }

    public String getFen() {
        return fen;
    }
}
