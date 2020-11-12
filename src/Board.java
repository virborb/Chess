public class Board {
    private Disc[][] board;

    public Board() {
        this.board = new Disc[8][8];
        this.board[4][4].setColor(Color.WHITE);
        this.board[4][5].setColor(Color.BLACK);
        this.board[5][4].setColor(Color.BLACK);
        this.board[5][5].setColor(Color.WHITE);
    }

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
