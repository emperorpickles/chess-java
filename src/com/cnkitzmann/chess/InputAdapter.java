package com.cnkitzmann.chess;

import com.cnkitzmann.chess.movement.MoveHandler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputAdapter extends MouseAdapter {
    private final Board b;
    private final Renderer r;
    private final MoveHandler moves;

    private boolean moving = false;

    public InputAdapter(Board board, Renderer renderer) {
        b = board;
        r = renderer;

        moves = new MoveHandler(b);
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
            moves.findValidMoves(piece);
        } else {
            System.out.println("finishing move");

            Point move = new Point(x, y);
            moves.makeMove(move);
        }
        moving = !moving;
        r.redraw();
    }
}
