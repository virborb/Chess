package model;

import controller.OthelloController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Holds all the Discs that are on the board.
 */
public class Board {
    private final ArrayList<Disc> board;
    private BufferedImage blackDisc;
    private BufferedImage whiteDisc;
    private BufferedImage boardImage;
    private BufferedImage emptyDisc;

    public Board() {
        this.board = new ArrayList<>(OthelloController.ROWS*OthelloController.COLUMNS);
        try {
            this.getDiscImages();
            this.setBoardImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Make a copy of the board.
     * @param b The board to copy
     */
    public Board(Board b) {
        this.board = new ArrayList<>(OthelloController.ROWS*OthelloController.COLUMNS);
        for (int row = 0; row < OthelloController.ROWS; row++) {
            for (int col = 0; col < OthelloController.COLUMNS; col++) {
                Position position = new Position(row,col);
                Color color = b.GetDisc(row, col).getColor();
                this.board.add(new Disc(position, color));
            }
        }
        this.blackDisc = b.getBlackDisc();
        this.whiteDisc = b.getWhiteDisc();
        this.boardImage = b.getBoardImage();
    }

    /**
     * Initiates a new board with the discs at starting positions
     * of the game.
     */
    public void initBoard() {
        for (int row = 0; row < OthelloController.ROWS; row++) {
            for (int col = 0; col < OthelloController.COLUMNS; col++) {
                Position position = new Position(row,col);
                board.add(new Disc(position));
                if ((row == 3 && col == 3) || (row == 4 && col == 4)) {
                    board.get(row*OthelloController.ROWS+col).setColor(Color.WHITE);
                } else if ((row == 3 && col == 4) || (row == 4 && col == 3)) {
                    board.get(row*OthelloController.ROWS+col).setColor(Color.BLACK);
                }
            }
        }
    }

    /**
     * @return The black disc image
     */
    public BufferedImage getBlackDisc() {
        return blackDisc;
    }

    /**
     * @return The white disc image.
     */
    public BufferedImage getWhiteDisc() {
        return whiteDisc;
    }

    /**
     * @return The empty disc image.
     */
    public BufferedImage getEmptyDisc() {
        return emptyDisc;
    }

    public BufferedImage getBoardImage() {
        return boardImage;
    }

    /**
     * Counts all the white discs on the board.
     * @return The number of white discs.
     */
    public int countWhiteDiscs() {
        int sum = 0;
        for (Disc d: board) {
            if(d.getColor() == Color.WHITE) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Counts all the black discs on the board.
     * @return The number of black discs.
     */
    public int countBlackDiscs() {
        ArrayList<Disc> discs = getBoard();
        int sum = 0;
        for (Disc d: discs) {
            if(d.getColor() == Color.BLACK) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Gets a disc from specified position.
     * @param row The row where the disc is.
     * @param col The column where the disc is.
     * @return The specified disc.
     */
    public Disc GetDisc(int row, int col) {
        return board.get(row*OthelloController.ROWS+col);
    }

    /**
     *
     * @return The board.
     */
    public ArrayList<Disc> getBoard() {
        return board;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int row = 0; row < OthelloController.ROWS; row++) {
            for (int col = 0; col < OthelloController.COLUMNS; col++) {
                String tmp = "b";
                switch (board.get(row*OthelloController.ROWS+col).getColor()) {
                    case BLACK:
                        tmp = "B";
                        break;
                    case WHITE:
                        tmp = "W";
                        break;
                    case EMPTY:
                        tmp = "E";
                        break;
                }
                string.append(tmp);
            }
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * Gets the images for the disc
     * @throws IOException if it can't get an image.
     */
    private void getDiscImages() throws IOException {
        URL url = getClass().getResource("/images/black_disc.png");
        blackDisc = ImageIO.read(url);
        url = getClass().getResource("/images/white_disc.png");
        whiteDisc = ImageIO.read(url);
        emptyDisc = new BufferedImage(OthelloController.TILE_WIDTH, OthelloController.TILE_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
    }

    private void setBoardImage() throws IOException {
        URL url = getClass().getResource("/images/board.png");
        boardImage = ImageIO.read(url);
    }
}
