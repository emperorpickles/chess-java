package com.cnkitzmann.chess;

public class Board {
    private final static Piece[][] board = new Piece[8][8];

    public Board() {
        initBoard();
    }

    private void initBoard() {
        newGame(Settings.defaultBoard);
    }

    private void newGame(String FEN) {
        String[] ranks = FEN.split("/", 0);

        for (int j = 0; j < ranks.length; j++) {
            for (int i = 0; i < ranks[j].length(); i++) {
                char piece = ranks[j].charAt(i);

                if (Character.isDigit(piece)) {
                    board[i][j] = null;
                } else if (Character.isLowerCase(piece)) {
                    board[i][j] = new Piece(i, j, false, Character.toUpperCase(piece));
                } else {
                    board[i][j] = new Piece(i, j, true, piece);
                }
            }
        }
    }

    public Piece getPiece(int x, int y) {
        if (x >= 0 && y >= 0 && x < 8 && y < 8) return board[x][y];
        return null;
    }

    public void movePiece(int x, int y, int cx, int cy) {
        board[x][y].setMoved();
        board[x][y].setPos(cx, cy);
        board[cx][cy] = board[x][y];
        board[x][y] = null;
    }
}
