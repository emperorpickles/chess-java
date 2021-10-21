package com.cnkitzmann.chess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Piece {
    private final Point renderPos = new Point();
    private final Point gridPos = new Point();
    private final boolean white;
    private final char type;
    private boolean moved;
    private BufferedImage sprite;

    public Piece(int x, int y, boolean isWhite, char t) {
        setPos(x, y);
        white = isWhite;
        type = t;
        moved = false;
        setSprite();
    }

    private void setSprite() {
        BufferedImage s;
        char color = white ? 'w' : 'b';

        try {
            String path = String.format("src/com/cnkitzmann/chess/resources/%s%s.png", color, type);
            s = ImageIO.read(new File(path));
        } catch (IOException e) {
            s = null;
            try {
                s = ImageIO.read(new File(String.format("src/com/cnkitzmann/chess/resources/%sx.png", color)));
            } catch (IOException err) {
                e.printStackTrace();
            }
        }

        sprite = s;
    }

    public BufferedImage getSprite() {
        return this.sprite;
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

    public void dragging(Point p) {
        renderPos.setLocation(p.x - (Settings.pieceSize / 2), p.y - (Settings.pieceSize / 2));
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
        return !this.moved;
    }

    public void setMoved() {
        this.moved = true;
    }
}
