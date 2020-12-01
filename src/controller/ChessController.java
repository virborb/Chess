package controller;

import model.*;
import view.ChessView;
import view.MenuBar;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * The controller class which handles the communication between the model and view.
 * Starts the gui and add action listeners to the buttons.
 */
public class ChessController {
    public final static int ROWS = 8;
    public final static int COLUMNS = 8;
    public final static int TILE_WIDTH = 75;
    public final static int TILE_HEIGHT = 75;
    private ChessView v;
    private final Rules rules;
    private Board board;
    private final AIPlayer ai;

    /**
     * Creates a new Board, Rules and AIPlayer.
     * starts a Swing utility which creates
     * the gui.
     */
    public ChessController() {
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

    /**
     * Creates the gui with all the action listeners and
     * sets the background images for the board and
     * then shows the gui.
     */
    public void createAndShowGUI() {
        v = new ChessView();
        addGameScreenListeners();
        addMenuBarListeners();
        BufferedImage bg = board.getBoardImage();
        v.getGameScreen().setBackgroundImage(bg);
        v.show();
    }

    /**
     * Adds action listener to start a new game and
     * to exit the game.
     */
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

    /**
     * Adds action listeners to all tiles on the board.
     */
    public void addGameScreenListeners() {
        JButton[][] tiles = v.getGameScreen().getTiles();
        flipTiles();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                int finalI = i;
                int finalJ = j;
                tiles[i][j].addActionListener(e -> makeMove(board.GetDisc(finalI, finalJ)));
            }
        }
    }

    /**
     * Make a move on board if it is a legal and check who
     * is next to make a move or if the game is over.
     * @param disc The disc where the move is made.
     */
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
        }
    }

    /**
     * Counts the discs on the board and
     * shows which player won the game.
     */
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

    /**
     * Flips all tiles on the gui board.
     */
    private void flipTiles() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
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
