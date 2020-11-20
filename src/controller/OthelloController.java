package controller;

import model.Board;
import model.Color;
import model.Disc;
import model.Rules;
import view.OthelloView;
import view.Screens;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

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
        addGameScreenListeners();
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

    public void addGameScreenListeners() {
        Screens s = v.getScreens();
        JButton[][] tiles = s.getGameScreen().getTiles();
        flipTiles();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Disc disc = board.GetDisc(i, j);
                tiles[i][j].addActionListener(e -> {
                    if (rules.isLegalMove(board,disc, Color.BLACK)) {
                        rules.flipDiscs(board, disc.getPosition(), Color.BLACK);
                        ArrayList<Disc> discs =  rules.getValidMoves(board, Color.WHITE);
                        Random r = new Random();
                        rules.flipDiscs(board,discs.get(r.nextInt(discs.size())).getPosition(),
                                Color.WHITE);
                        flipTiles();
                    } else {
                        v.showMessage("Non valid move");
                    }
                });
            }
        }
    }

    public void flipTiles() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color color = board.GetDisc(i,j).getColor();
                if(Color.BLACK == color) {
                    v.getScreens().getGameScreen().changeTileColor(i, j, java.awt.Color.BLACK);
                } else if(Color.WHITE == color) {
                    v.getScreens().getGameScreen().changeTileColor(i, j, java.awt.Color.WHITE);
                }
            }
        }
    }
}
