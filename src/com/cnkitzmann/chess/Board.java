package com.cnkitzmann.chess;

import com.cnkitzmann.chess.movement.Move;
import com.cnkitzmann.chess.pieces.Piece;

public class Board {
    private final static Piece[][] board = new Piece[8][8];
    private boolean whiteTurn;
    private NotationHandler notationHandler;
    private PiecesHandler piecesHandler;

    public Board(PiecesHandler ph) {
        initBoard(ph);
    }

    private void initBoard(PiecesHandler ph) {
        whiteTurn = true;
        piecesHandler = ph;
        newBoard(Settings.defaultBoard);
        notationHandler = new NotationHandler();

        piecesHandler.updateAllPieces();
    }

    private void newBoard(String FEN) {
        String[] ranks = FEN.split("/", 0);

        for (int j = 0; j < ranks.length; j++) {
            int x = 0;
            for (int i = 0; i < ranks[j].length(); i++) {
                char piece = ranks[j].charAt(i);

                if (Character.isDigit(piece)) {
                    x += Character.getNumericValue(piece);
                } else if (Character.isLowerCase(piece)) {
//                    lowercase = piece is black
                    board[x][j] = piecesHandler.newPiece(x, j, false, Character.toUpperCase(piece), this);
                    x++;
                } else {
//                    piece is white
                    board[x][j] = piecesHandler.newPiece(x, j, true, piece, this);
                    x++;
                }
            }
        }
    }

    public Piece getPiece(int x, int y) {
        if (x >= 0 && y >= 0 && x < 8 && y < 8) return board[x][y];
        return null;
    }

    public void makeMove(Move move) {
        piecesHandler.removePiece(piecesHandler.getPieceAtCoord(move.getNewPos().x, move.getNewPos().y));
        movePiece(move.getCurPos().x, move.getCurPos().y, move.getNewPos().x, move.getNewPos().y);
        updatePGN(move);
        updateFEN(this, move);
        piecesHandler.updateAllPieces();
        setTurn();
    }

    public void movePiece(int x, int y, int dx, int dy) {
        board[x][y].setMoved();
        board[x][y].setPos(dx, dy);
        board[dx][dy] = board[x][y];
        board[x][y] = null;
    }

    public void removePiece(int x, int y) {
        piecesHandler.removePiece(board[x][y]);
        board[x][y] = null;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setTurn() {
        whiteTurn = !whiteTurn;
    }

    public void updatePGN(Move move) {
        notationHandler.pgnWriter(move);
    }

    public String getPGN() {
        return notationHandler.getPGN();
    }

    public void updateFEN(Board b, Move move) {
        notationHandler.fenWriter(b, move);
    }

    public String getFEN() {
        return notationHandler.getFen();
    }
}
