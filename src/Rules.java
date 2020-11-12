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
        int delta = row-1;
        while(delta >= 0) {
            if(checkDirection(board, row, col, delta, col, player)) {
                delta--;
            } else if(player == board.GetDisc(row, col).getColor()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean checkDirection(Board board, int row, int col, int dRow, int dCol, Color player) {
        Disc deltaDisc = board.GetDisc(row + dRow, col + dCol);
        Color deltaColor = deltaDisc.getColor();
        return (player != deltaColor && deltaColor != Color.EMPTY);
    }
}
