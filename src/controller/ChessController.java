package controller;

import model.*;
import model.Color;
import model.pieces.Piece;
import view.ChessView;
import view.ImagePanel;
import view.MenuBar;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Map;

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
            setTileImages();
        });

        menu.getQuit().addActionListener(e -> {
            System.exit(0);
        });
    }

    /**
     * Adds action listeners to all tiles on the board.
     */
    public void addGameScreenListeners() {
        ImagePanel[][] tiles = v.getGameScreen().getTiles();
        setTileImages();
        for (int row = 0; row < ROWS; row++) {
            if (row >= 2 && row <= 5) {
                continue;
            }
            for (int col = 0; col < COLUMNS; col++) {
                Position position = new Position(row, col);
                board.getPiece(position).setComp(tiles[row][col]);
                MouseAction mouseAction = new MouseAction(tiles[row][col]) {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        ImagePanel comp = this.getComp();
                        int newX = comp.getX() + e.getX();
                        int newY = comp.getY() + e.getY();
                        int row = newY/TILE_HEIGHT;
                        int col = newX/TILE_WIDTH;
                        Position oldPosition = new Position(this.getMyY()/TILE_HEIGHT, this.getMyX()/TILE_WIDTH);
                        Position moveTo = new Position(row, col);
                        if (!makeMove(oldPosition, moveTo)) {
                            comp.setLocation(this.getMyX(), this.getMyY());
                        }
                    }
                };

                tiles[row][col].addMouseMotionListener(mouseAction);
                tiles[row][col].addMouseListener(mouseAction);
            }
        }
    }

    /**
     * Make a move on board if it is a legal and check who
     * is next to make a move or if the game is over.
     * @param oldPosition
     * @param moveTo
     */
    public boolean makeMove(Position oldPosition, Position moveTo) {
        Piece piece = board.getPiece(oldPosition);
        if (rules.isLegalMove(board, piece, Color.WHITE, moveTo)) {
            Piece previous = board.movePiece(piece, moveTo);
            if( previous != null) {
                v.getGameScreen().removePanel(previous.getComp());
            }
            v.getGameScreen().moveTile(piece.getComp(), moveTo);
            long start = System.currentTimeMillis();
            Map.Entry<Piece, Position> entry = ai.nextMove(board, Color.BLACK);
            long end = System.currentTimeMillis();
            System.out.println("Ai time :" + (end-start) + "ms");
            previous = board.movePiece(entry.getKey(), entry.getValue());
            if( previous != null) {
                v.getGameScreen().removePanel(previous.getComp());
            }
            v.getGameScreen().moveTile(entry.getKey().getComp(), entry.getValue());
            if (rules.isGameOver(board, Color.WHITE)) {
                gameOver();
            }
            return true;
        }
        return false;
    }

    /**
     * Counts the discs on the board and
     * shows which player won the game.
     */
    private void gameOver() {
        int black = 0;
        int white = 0;
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
     * Sets all tile images on the gui board.
     */
    private void setTileImages() {
        Collection<Piece> pieces = board.getBoard().values();
        pieces.forEach(piece -> {
            int row = piece.getPosition().getRow();
            int col = piece.getPosition().getCol();
            v.getGameScreen().changeTileBackground(row, col, piece.getImage());
        });
    }
}
