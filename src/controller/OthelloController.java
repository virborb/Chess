package controller;

import model.*;
import view.MenuBar;
import view.OthelloView;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class OthelloController {
    public final static int ROWS = 8;
    public final static int COLUMNS = 8;
    public final static int TILE_WIDTH = 75;
    public final static int TILE_HEIGHT = 75;
    private OthelloView v;
    private final Rules rules;
    private Board board;
    private final AIPlayer ai;

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
        addGameScreenListeners();
        addMenuBarListeners();
        BufferedImage bg = board.getBoardImage();
        v.getGameScreen().setBackgroundImage(bg);
        v.show();
    }

    public void addMenuBarListeners() {
        MenuBar menu = v.getMenubar();

        menu.getNewGame().addActionListener(e -> {
            board = new Board();
            board.initBoard();
            flipTiles();
        });

        menu.getQuit().addActionListener(e -> {
            System.exit(0);
        });
    }

    public void addGameScreenListeners() {
        JButton[][] tiles = v.getGameScreen().getTiles();
        flipTiles();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int finalI = i;
                int finalJ = j;
                tiles[i][j].addActionListener(e -> makeMove(board.GetDisc(finalI, finalJ)));
            }
        }
    }

    private void makeMove(Disc disc) {
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
                gameOver();
            }
        } else {
            v.showMessage("Non valid move");
        }
    }

    private void gameOver() {
        int black = board.countBlackDiscs();
        int white = board.countWhiteDiscs();
        if(black > white) {
            v.showMessage("Black Won with " + black +
                    " against " + white);
        } else if(white > black) {
            v.showMessage("White Won with " + white +
                    " against " + black);
        } else {
            v.showMessage("Tie");
        }
    }

    private void flipTiles() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color color = board.GetDisc(i,j).getColor();
                if(Color.BLACK == color) {
                    v.getGameScreen().changeTileBackground(i, j, board.getBlackDisc());
                } else if(Color.WHITE == color) {
                    v.getGameScreen().changeTileBackground(i, j, board.getWhiteDisc());
                } else {
                    v.getGameScreen().changeTileBackground(i, j, board.getEmptyDisc());
                }
            }
        }
    }
}
