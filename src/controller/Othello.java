package controller;

import model.Board;
import model.Rules;
import view.EndScreen;
import view.OthelloView;

import javax.swing.*;

public class Othello {
    public final static int ROWS = 8;
    public final static int COLUMNS = 8;
    public final static int TILE_WIDTH = 50;
    public final static int TILE_HEIGHT = 50;


    public static void main(String[] args) {
        OthelloView view = new OthelloView();
        SwingUtilities.invokeLater(() -> {
            try {
                view.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Board board = new Board();
        Rules rules = new Rules();
        board.initBoard();


    }

    public static void createAndShowGUI() throws Exception {

    }
}
