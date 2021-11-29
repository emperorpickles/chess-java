package com.cnkitzmann.chess;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class ChessGame extends JFrame {

    public ChessGame() {
        initUI();
    }

    private void initUI() {
        PiecesHandler piecesHandler = new PiecesHandler();
        Board board = new Board(piecesHandler);
        add(new Renderer(board, piecesHandler));

        setSize(Settings.gameWidth + 17, Settings.gameHeight + 40);
        setTitle("ChessGame");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ChessGame ex = new ChessGame();
            ex.setVisible(true);
        });
    }
}
