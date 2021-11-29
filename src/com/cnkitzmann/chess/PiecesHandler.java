package com.cnkitzmann.chess;

import com.cnkitzmann.chess.pieces.*;

import java.util.ArrayList;

public class PiecesHandler {
    private final ArrayList<Piece> whitePieces, blackPieces, allPieces;

    public PiecesHandler() {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        allPieces = new ArrayList<>();
    }

    public Piece newPiece(int x, int y, boolean isWhite, char type, Board board) {
//        create new piece of the given type
        Piece piece = switch (type) {
            case 'K' -> new King(x, y, isWhite, this);
            case 'Q' -> new Queen(x, y, isWhite, this);
            case 'P' -> new Pawn(x, y, isWhite, this, board);
            case 'B' -> new Bishop(x, y, isWhite, this);
            case 'N' -> new Knight(x, y, isWhite, this);
            case 'R' -> new Rook(x, y, isWhite, this);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };

        addPiece(piece);
        return piece;
    }

    public void addPiece(Piece piece) {
        allPieces.add(piece);

        if (piece.isWhite()) {
            whitePieces.add(piece);
        }
        else {
            blackPieces.add(piece);
        }
    }

    public void removePiece(Piece piece) {
        if (piece == null) return;

        allPieces.remove(piece);

        if (piece.isWhite()) {
            whitePieces.remove(piece);
        }
        else {
            blackPieces.remove(piece);
        }
    }

    public void updatePiece(Piece piece) {
        if (piece.isWhite()) {
            whitePieces.remove(piece);
        }
        else {
            blackPieces.remove(piece);
        }
    }

    public void updateAllPieces() {
        updateWhitePieces();
        updateBlackPieces();
    }

    public void updateWhitePieces() {
        for (Piece piece : whitePieces) {
            piece.updatePiece();
        }
    }

    public void updateBlackPieces() {
        for (Piece piece : blackPieces) {
            piece.updatePiece();
        }
    }

    public ArrayList<Piece> getAllPieces() {
        return allPieces;
    }

    public ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public ArrayList<Piece> getBlackPieces() {
        return blackPieces;
    }

    public Piece getPieceAtCoord(int x, int y) {
        for (Piece piece : allPieces) {
            if (piece.getGridX() == x && piece.getGridY() == y) return piece;
        }
        return null;
    }

    public Piece getWhitePieceAtCoord(int x, int y) {
        for (Piece piece : whitePieces) {
            if (piece.getGridX() == x && piece.getGridY() == y) return piece;
        }
        return null;
    }

    public Piece getBlackPieceAtCoord(int x, int y) {
        for (Piece piece : blackPieces) {
            if (piece.getGridX() == x && piece.getGridY() == y) return piece;
        }
        return null;
    }
}
