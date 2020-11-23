package controller;

import model.*;
import view.EndScreen;
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
    private AIPlayer ai;
    public OthelloController() {
        board = new Board();
        rules = new Rules();
        ai = new AIPlayer();
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
        addEndScreenListeners();
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

    public void addEndScreenListeners() {
        Screens s = v.getScreens();

        s.getEndScreen().getNewGame().addActionListener(e -> {
            board = new Board();
            board.initBoard();
            flipTiles();
            s.showGameScreen();
        });

        s.getEndScreen().getQuit().addActionListener(e -> {
            System.exit(0);
        });
    }

    public void addGameScreenListeners() {
        Screens s = v.getScreens();
        JButton[][] tiles = s.getGameScreen().getTiles();
        flipTiles();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int finalI = i;
                int finalJ = j;
                tiles[i][j].addActionListener(e -> {
                    Disc disc = board.GetDisc(finalI, finalJ);
                    if (rules.isLegalMove(board, disc, Color.BLACK)) {
                        rules.flipDiscs(board, disc.getPosition(), Color.BLACK);
                        do {
                            if (rules.hasLegalMoves(board, Color.WHITE)) {
                                Position position = ai.nextMove(board, Color.WHITE);
                                rules.flipDiscs(board, position, Color.WHITE);
                            } else {
                                break;
                            }
                        } while (!rules.hasLegalMoves(board, Color.BLACK));
                        flipTiles();
                        if (rules.isGameOver(board)) {
                            s.showEndScreen();
                            EndScreen endScreen = s.getEndScreen();
                            int black = rules.countBlackDiscs(board);
                            int white = rules.countWhiteDiscs(board);
                            endScreen.setBlackScore(black);
                            endScreen.setWhiteScore(white);
                            if(black > white) {
                                endScreen.setWinner("Black Won");
                            } else if(white > black) {
                                endScreen.setWinner("White Won");
                            } else {
                                endScreen.setWinner("Tie");
                            }
                        }
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
                } else {
                    v.getScreens().getGameScreen().changeTileColor(i, j, java.awt.Color.GREEN);
                }
            }
        }
    }
}
