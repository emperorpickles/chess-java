package com.cnkitzmann.chess.pieces;

import com.cnkitzmann.chess.PiecesHandler;
import com.cnkitzmann.chess.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.cnkitzmann.chess.movement.Move;

/*
parent piece class

contains general piece data: position, color, type, if moved, sprite image
*/

public class Piece {
    private final Point renderPos = new Point();
    protected final Point gridPos = new Point();
    private final boolean white;
    private final char type;
    protected final PiecesHandler ph;
    protected boolean moved;
    private BufferedImage sprite;

    ArrayList<Move> moves;
    Point[] diagonals = {new Point(1, 1), new Point(-1, 1), new Point(-1, -1), new Point(1, -1)};
    Point[] lines = {new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)};

    public Piece(int x, int y, boolean isWhite, char t, PiecesHandler piecesHandler) {
        setPos(x, y);
        white = isWhite;
        moved = false;
        type = t;
        ph = piecesHandler;
        moves = new ArrayList<>();
        setSprite();
    }


    /*
     * * * * * * * * * * * * * * * * * *
     * Move Generation
     * * * * * * * * * * * * * * * * * *
     */

//    create a new move object and append on moves array. returns true if the move intersects with another piece
    boolean newMove(int dx, int dy) {
        return newMove(dx, dy, ' ');
    }
    boolean newMove(int dx, int dy, char special) {
        Point newPos = (Point) gridPos.clone();
        newPos.translate(dx, dy);

        if (newPos.x >= 0 && newPos.y >= 0 && newPos.x < 8 && newPos.y < 8) {
            if (ph.getPieceAtCoord(newPos.x, newPos.y) != null) {
                if (canTake(newPos)) moves.add(new Move(this, gridPos, newPos, true, special));
                return true;
            }
            else {
               moves.add(new Move(this, gridPos, newPos, false, special));
            }
        }
        return false;
    }

    private boolean canTake(Point newPos) {
        if (ph.getPieceAtCoord(newPos.x, newPos.y).isWhite() != white) {
            return type != 'p' || gridPos.x != newPos.x;
        }
        return false;
    }

    public void updatePiece() {
        moves.clear();
        generateMoves();
    }

    protected void generateMoves() {}


    /*
    * * * * * * * * * * * * * * * * * *
    * Getters & Setters
    * * * * * * * * * * * * * * * * * *
    */

    public ArrayList<Move> getMoves() {
        return moves;
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

    public boolean isWhite() {
        return this.white;
    }

    public char getType() {
        return this.type;
    }

    public boolean getMoved() {
        return this.moved;
    }

    public void setMoved() {
        this.moved = true;
    }
}
