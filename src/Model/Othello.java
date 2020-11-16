package Model;

public class Othello {
    public final static int ROWS = 8;
    public final static int COLUMNS = 8;

    public static void main(String[] args) {
        Board board = new Board();
        Rules rules = new Rules();
        board.initBoard();
        System.out.println(board.toString());
        System.out.println(rules.isLegalMove(board, board.GetDisc(5, 3), Color.WHITE));
    }
}
