package com.cnkitzmann.chess;

import com.cnkitzmann.chess.movement.MoveHandler;
import com.cnkitzmann.chess.pieces.Piece;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputAdapter implements MouseListener, MouseMotionListener {
    private final Board b;
    private final Renderer r;
    private final MoveHandler m;

    private Piece p;

    private boolean moving = false;
    private boolean dragging = false;

    public InputAdapter(Board board, Renderer renderer, MoveHandler moves) {
        b = board;
        r = renderer;
        m = moves;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClick(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        dragging = true;

        Point gridPos = mouseGridPos(e.getPoint());
        p = b.getPiece(gridPos.x, gridPos.y);

        if (p != null && p.isWhite() == b.getTurn()) {
            m.getPieceMoves(p);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        if (p != null) {
            boolean moved = m.makeMove(mouseGridPos(e.getPoint()));
            if (!moved) {
                p.setPos(p.getGridX(), p.getGridY());
            }
            p = null;
        }
        r.redraw();
    }

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging && p != null) {
            p.dragging(e.getPoint());
            r.redraw();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    private void mouseClick(int x, int y) {
        int gridX = Math.floorDiv(x, Settings.tileSize);
        int gridY = Math.floorDiv(y, Settings.tileSize);

        Piece piece = b.getPiece(gridX, gridY);
        if ((piece != null && piece.isWhite() == b.getTurn()) | moving) {
            movePiece(piece, gridX, gridY);
        }
    }

    private Point mouseGridPos(Point pos) {
        int gridX = Math.floorDiv(pos.x, Settings.tileSize);
        int gridY = Math.floorDiv(pos.y, Settings.tileSize);

        return new Point(gridX, gridY);
    }

    private void movePiece(Piece piece, int x, int y) {
        if (!moving) {
            System.out.println("starting move");
            m.getPieceMoves(piece);
        } else {
            System.out.println("finishing move");
            m.makeMove(new Point(x, y));
        }
        moving = !moving;
        r.redraw();
    }
}
