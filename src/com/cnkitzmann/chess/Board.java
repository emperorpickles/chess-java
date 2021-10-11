package com.cnkitzmann.chess;

import com.cnkitzmann.chess.movement.Move;

public class Board {
    private final static Piece[][] board = new Piece[8][8];
    private boolean whiteTurn;
    private NotationHandler notationHandler;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        whiteTurn = true;
        newBoard(Settings.defaultBoard);
        notationHandler = new NotationHandler();
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
                    board[x][j] = new Piece(x, j, false, Character.toUpperCase(piece));
                    x++;
                } else {
                    board[x][j] = new Piece(x, j, true, piece);
                    x++;
                }
            }
        }
    }

    public Piece getPiece(int x, int y) {
        if (x >= 0 && y >= 0 && x < 8 && y < 8) return board[x][y];
        return null;
    }

    public void movePiece(int x, int y, int dx, int dy) {
        board[x][y].setMoved();
        board[x][y].setPos(dx, dy);
        board[dx][dy] = board[x][y];
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
}
