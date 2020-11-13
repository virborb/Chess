import org.jetbrains.annotations.NotNull;
import java.util.Arrays;

public class Board {
    private Disc[][] board;

    public Board() {
        this.board = new Disc[8][8];
    }

    /*
    Initiates a new board with the discs at starting positions
    of the game.
     */
    public void initBoard() {
        for (int row = 0; row < Othello.ROWS; row++) {
            for (int col = 0; col < Othello.COLUMNS; col++) {
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

    /*
    Gets a disc from specified position.
     */
    public Disc GetDisc(int row, int col) {
        return board[row][col];
    }

    public Disc[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        String string = "";
        for (int row = 0; row < Othello.ROWS; row++) {
            for (int col = 0; col < Othello.COLUMNS; col++) {
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
                string = new StringBuilder().append(string).append(tmp).toString();
            }
            string = new StringBuilder().append(string).append("\n").toString();
        }
        return string;
    }
}
