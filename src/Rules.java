public class Rules {

    public Rules() {

    }

    public boolean isLegalMove(Board board, Disc disc, Color player) {
        Position p = disc.getPosition();
        Color color = disc.getColor();
        int row = p.getRow();
        int col = p.getCol();
        if(!disc.getColor().equals(Color.EMPTY)) {
            return false;
        }
        if(checkNorth(board, row, col, player)) {
            return true;
        }
        return false;
    }

    private boolean checkNorth(Board board, int row, int col, Color player) {
        int dRow = -1;
        int dCol = 0;
        return checkDirection(board, row, col, dRow, dCol, player);
    }

    private boolean checkNorthEast(Board board, int row, int col, Color player) {
        int dRow = -1;
        int dCol = 1;
        return checkDirection(board, row, col, dRow, dCol, player);
    }

    private boolean checkEast(Board board, int row, int col, Color player) {
        int dRow = 0;
        int dCol = 1;
        return checkDirection(board, row, col, dRow, dCol, player);
    }

    private boolean checkSouthEast(Board board, int row, int col, Color player) {
        int dRow = 1;
        int dCol = 1;
        return checkDirection(board, row, col, dRow, dCol, player);
    }

    private boolean checkSouth(Board board, int row, int col, Color player) {
        int dRow = 1;
        int dCol = 0;
        return checkDirection(board, row, col, dRow, dCol, player);
    }

    private boolean checkSouthWest(Board board, int row, int col, Color player) {
        int dRow = 1;
        int dCol = -1;
        return checkDirection(board, row, col, dRow, dCol, player);
    }

    private boolean checkWest(Board board, int row, int col, Color player) {
        int dRow = 0;
        int dCol = -1;
        return checkDirection(board, row, col, dRow, dCol, player);
    }

    private boolean checkNorthWest(Board board, int row, int col, Color player) {
        int dRow = -1;
        int dCol = -1;
        return checkDirection(board, row, col, dRow, dCol, player);
    }

    private boolean checkDirection(Board board, int row, int col, int dRow, int dCol, Color player) {
        int sumRow = row + dRow;
        int sumCol = col + dCol;
        while(sumRow >= 0 && sumRow < Othello.ROWS && sumCol >= 0 && sumCol < Othello.COLUMNS) {
            Disc deltaDisc = board.GetDisc(sumRow, sumCol);
            Color deltaColor = deltaDisc.getColor();
            if((player != deltaColor && deltaColor != Color.EMPTY)) {
                sumRow = sumRow + dRow;
                sumCol = sumCol + dCol;
            } else if(player == board.GetDisc(sumRow, sumCol).getColor()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
