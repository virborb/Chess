package model;

import controller.OthelloController;

/**
 * Holds all Model.Disc that are on the board.
 */
public class Board {
    private final Disc[][] board;

    public Board() {
        this.board = new Disc[8][8];
    }

    /**
     * Initiates a new board with the discs at starting positions
     * of the game.
     */
    public void initBoard() {
        for (int row = 0; row < OthelloController.ROWS; row++) {
            for (int col = 0; col < OthelloController.COLUMNS; col++) {
                Position position = new Position(row,col);
                board[row][col] = new Disc(position);
                if ((row == 3 && col == 3) || (row == 4 && col == 4)) {
                    board[row][col].setColor(Color.WHITE);
                } else if ((row == 3 && col == 4) || (row == 4 && col == 3)) {
                    board[row][col].setColor(Color.BLACK);
                }
            }
        }
    }

    /**
     * Gets a disc from specified position.
     * @param row The row where the disc is.
     * @param col The column where the disc is.
     * @return The specified disc.
     */
    public Disc GetDisc(int row, int col) {
        return board[row][col];
    }

    /**
     *
     * @return The board.
     */
    public Disc[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int row = 0; row < OthelloController.ROWS; row++) {
            for (int col = 0; col < OthelloController.COLUMNS; col++) {
                String tmp = "b";
                switch (board[row][col].getColor()) {
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
}
