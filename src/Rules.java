public class Rules {

    public Rules() {

    }

    public boolean isLegalMove(Board board, Disc disc, Color player) {
        Position start = disc.getPosition();
        Color color = disc.getColor();
        if(!disc.getColor().equals(Color.EMPTY)) {
            return false;
        }
        Position[] directions = new Position[8];
        directions[0] = new Position(-1,1);
        directions[1] = new Position(1,1);
        directions[2] = new Position(-1,-1);
        directions[3] = new Position(1,0);
        directions[4] = new Position(-1,0);
        directions[5] = new Position(0,-1);
        directions[6] = new Position(0,1);
        directions[7] = new Position(1,-1);
        for (Position dir : directions) {
            if (checkDirection(board, start, dir, player)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDirection(Board board, Position start, Position direction, Color player) {
        int sumRow = start.getRow() + direction.getRow();
        int sumCol = start.getCol() + direction.getCol();
        while(sumRow >= 0 && sumRow < Othello.ROWS && sumCol >= 0 && sumCol < Othello.COLUMNS) {
            Disc deltaDisc = board.GetDisc(sumRow, sumCol);
            Color deltaColor = deltaDisc.getColor();
            if((player != deltaColor && deltaColor != Color.EMPTY)) {
                sumRow = sumRow + direction.getRow();
                sumCol = sumCol + direction.getCol();
            } else if(player == board.GetDisc(sumRow, sumCol).getColor()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
