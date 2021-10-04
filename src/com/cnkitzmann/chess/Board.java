package com.cnkitzmann.chess;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class Board extends JPanel {
    private final int tileSize = Settings.tileSize;
    private final Piece[][] board = new Piece[8][8];

    public Board() {
        initBoard();
    }

    private void initBoard() {
        setPreferredSize(new Dimension(Settings.boardWidth, Settings.boardHeight));

        addMouseListener(new PieceAdapter());
        newGame(board, Settings.defaultBoard);
    }

    private void newGame(Piece[][] board, String FEN) {
        String[] ranks = FEN.split("/", 0);

        for (int j = 0; j < ranks.length; j++) {
            for (int i = 0; i < ranks[j].length(); i++) {
                char piece = ranks[j].charAt(i);

                if (Character.isDigit(piece)) {
                    board[i][j] = null;
                }
                else if (Character.isLowerCase(piece)) {
                    board[i][j] = new Piece(i, j, false, Character.toUpperCase(piece));
                }
                else {
                    board[i][j] = new Piece(i, j, true, piece);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawPiece(g);
    }

    private void drawGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (int x = 0; x < Settings.boardWidth; x += tileSize) {
            for (int y = 0; y < Settings.boardHeight; y += tileSize) {
                g2d.setPaint(new Color(20));
                g2d.drawRect(x, y, tileSize, tileSize);
            }
        }
    }

    private void drawPiece(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

//        g2d.fill(new Ellipse2D.Double(100, 100, 100, 100));
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y] != null) {
                    Piece piece = board[x][y];
                    g2d.setPaint(new Color(piece.color()));
                    g2d.fill(new Ellipse2D.Double(piece.getX(), piece.getY(), Settings.pieceSize, Settings.pieceSize));
                }
            }
        }
    }

    private class PieceAdapter extends MouseAdapter {
        private boolean moving = false;
        private Piece toMove;

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            int gridX = x / tileSize;
            int gridY = y / tileSize;

            Piece piece = board[gridX][gridY];
            if (piece != null | moving) {
                movePiece(piece, gridX, gridY);
            }
        }

        private void movePiece(Piece piece, int x, int y) {
            if (moving) {
                board[x][y] = toMove;
                board[toMove.getGridX()][toMove.getGridY()] = null;

                toMove.setPos(x, y);
                repaint();
            } else {
                toMove = piece;
            }
            moving = !moving;
        }
    }
}
