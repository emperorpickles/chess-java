package com.cnkitzmann.chess;

import com.cnkitzmann.chess.movement.MoveHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Renderer extends JPanel {
    private final Board b;
    private JTextArea ta;

    public Renderer(Board board) {
        b = board;

        setPreferredSize(new Dimension(Settings.gameWidth, Settings.gameHeight));
        addMouseListener(new InputAdapter(b, this));
        ta = new JTextArea();
        ta.setEditable(false);
        ta.setLineWrap(true);
        this.add(ta);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawPiece(g);
        drawPossibleMoves(g);
        drawPGN(g);
    }

    public void redraw() {
        repaint();
    }

    private void drawGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int tileSize = Settings.tileSize;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                g2d.setPaint(new Color(20));
                g2d.drawRect(x*tileSize, y*tileSize, tileSize, tileSize);
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

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (b.getPiece(x, y) != null) {
                    Piece piece = b.getPiece(x, y);
                    g2d.setPaint(new Color(piece.color()));
                    g2d.fill(new Ellipse2D.Double(piece.getX(), piece.getY(), Settings.pieceSize, Settings.pieceSize));
                    g2d.drawString(String.valueOf(piece.getType()), piece.getX(), piece.getY());
                }
            }
        }
    }

    private void drawPossibleMoves(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        ArrayList<Point> validMoves = MoveHandler.getMovePoints();
        double pointSize = Settings.pieceSize * 0.6;

        if (validMoves != null) {
            for (Point move : validMoves) {
                g2d.setPaint(new Color(20, 200, 20, 60));
                g2d.fill(new Ellipse2D.Double(
                        (move.x * Settings.tileSize) + (double)(Settings.tileSize / 2) - (pointSize / 2),
                        (move.y * Settings.tileSize) + (double)(Settings.tileSize / 2) - (pointSize / 2),
                        pointSize, pointSize
                ));
            }
        }
    }

    private void drawPGN(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        String pgn = b.getPGN();
        ta.setBounds(Settings.gameWidth - 280, Settings.gameHeight / 2 - 150, 260, 300);
        ta.replaceRange(pgn, 0, ta.getText().length());
    }
}
