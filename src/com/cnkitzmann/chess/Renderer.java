package com.cnkitzmann.chess;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Renderer extends JPanel {
    private Board b;

    public Renderer(Board board) {
        b = board;

        setPreferredSize(new Dimension(Settings.boardWidth, Settings.boardHeight));
        addMouseListener(new InputAdapter(b, this));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawPiece(g);
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
}