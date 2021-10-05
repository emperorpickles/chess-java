package com.cnkitzmann.chess;

import java.awt.*;

public class Piece {
    private Point renderPos = new Point();
    private Point gridPos = new Point();
    private boolean white;
    private char type;
    private boolean moved;

    public Piece(int x, int y, boolean isWhite, char t) {
        setPos(x, y);
        white = isWhite;
        type = t;
        moved = false;
    }

    public int color() {
        int color;
        if (this.white) {
            color = 220;
        } else {
            color = 80;
        }
        return color;
    }

    public void setPos(int x, int y) {
        renderPos.setLocation(
                (x * Settings.tileSize) + (Settings.tileSize / 2) - (Settings.pieceSize / 2),
                (y * Settings.tileSize) + (Settings.tileSize / 2) - (Settings.pieceSize / 2));
        gridPos.setLocation(x, y);
    }

    public int getX() {
        return this.renderPos.x;
    }

    public int getY() {
        return this.renderPos.y;
    }

    public int getGridX() {
        return this.gridPos.x;
    }

    public int getGridY() {
        return this.gridPos.y;
    }

    public char getType() {
        return this.type;
    }

    public boolean isWhite() {
        return this.white;
    }

    public boolean getMoved() {
        return this.moved;
    }

    public void setMoved() {
        this.moved = true;
    }
}
