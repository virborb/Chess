public class Board {
    private final static int ROWS = 8;
    private final static int COLUMNS = 8;
    private Disc[][] board;

    public Board() {
        this.board = new Disc[8][8];
    }

    /*
    Initiates a new board with the discs at starting positions
    of the game.
     */
    public void initBoard() {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLUMNS; y++) {
                Position position = new Position(x,y);
                board[x][y] = new Disc(position);
                if ((x == 4 && y == 4) || (x == 5 && y == 5)) {
                    board[x][y].setColor(Color.WHITE);
                } else if ((x == 4 && y == 5) || (x == 5 && y == 4)) {
                    board[x][y].setColor(Color.BLACK);
                }
            }
        }
    }

    /*
    Gets a disc from specified position.
     */
    public Disc GetDisc(int x, int y) {
        return board[x][y];
    }

    public Disc[][] getBoard() {
        return board;
    }

    public void setBoard(Disc[][] board) {
        this.board = board;
    }
}
