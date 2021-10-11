package com.cnkitzmann.chess;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class ChessGame extends JFrame {

    public ChessGame() {
        initUI();
    }

    private void initUI() {
        Board board = new Board();
        add(new Renderer(board));

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
