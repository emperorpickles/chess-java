package com.cnkitzmann.chess;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class InputAdapter extends MouseAdapter {
    private final Board b;
    private final Renderer r;
    private final Moves moves = new Moves();

    private boolean moving = false;
    private Piece toMove;
    private ArrayList<Point> validMoves;

    public InputAdapter(Board board, Renderer renderer) {
        b = board;
        r = renderer;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int gridX = Math.floorDiv(x, Settings.tileSize);
        int gridY = Math.floorDiv(y, Settings.tileSize);

        Piece piece = b.getPiece(gridX, gridY);
        if (piece != null | moving) {
            movePiece(piece, gridX, gridY);
        }
    }

    private void movePiece(Piece piece, int x, int y) {
        if (!moving) {
            System.out.println("starting move");
            toMove = piece;
            moves.setValidMoves(b, toMove);
            validMoves = Moves.getValidMoves();
        } else {
            System.out.println("finishing move");

            Point move = new Point(x, y);
            if (validMoves.contains(move)) {
                b.movePiece(toMove.getGridX(), toMove.getGridY(), x, y);
                r.redraw();
            }
            validMoves.clear();
        }
        moving = !moving;
        r.redraw();
    }
}
