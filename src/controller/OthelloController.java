package controller;

import model.Board;
import model.Rules;
import view.OthelloView;
import view.Screens;

import javax.swing.*;

public class OthelloController {
    public final static int ROWS = 8;
    public final static int COLUMNS = 8;
    public final static int TILE_WIDTH = 50;
    public final static int TILE_HEIGHT = 50;
    private OthelloView v;
    private Rules rules;
    private Board board;

    public OthelloController() {
        board = new Board();
        rules = new Rules();
        board.initBoard();
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void createAndShowGUI() {
        v = new OthelloView();
        addStartScreenListeners();
        v.show();
    }

    public void addStartScreenListeners() {
        Screens s = v.getScreens();

        s.getStartScreen().getStartButton().addActionListener(e -> {
            s.showGameScreen();
        });

        s.getStartScreen().getQuitButton().addActionListener(e -> {
            System.exit(0);
        });
    }
}
