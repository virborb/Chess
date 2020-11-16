import java.util.ArrayList;

public class Rules {
    private Position[] directions;

    public Rules() {
        this.directions = new Position[8];
        directions[0] = new Position(-1,1);
        directions[1] = new Position(1,1);
        directions[2] = new Position(-1,-1);
        directions[3] = new Position(1,0);
        directions[4] = new Position(-1,0);
        directions[5] = new Position(0,-1);
        directions[6] = new Position(0,1);
        directions[7] = new Position(1,-1);
    }

    public boolean isLegalMove(Board board, Disc disc, Color player) {
        Position start = disc.getPosition();
        if(!disc.getColor().equals(Color.EMPTY)) {
            return false;
        }
        for (Position dir : directions) {
            if (checkDirection(board, start, dir, player)) {
                return true;
            }
        }
        return false;
    }

    public Board flipDiscs(Board board, Disc disc, Color player) {
        Position start = disc.getPosition();
        disc.setColor(player);
        for (Position dir : directions) {
            flipDiscDirection(board, start, dir, player);
        }
        return board;
    }

    private boolean flipDiscDirection(Board board, Position start, Position direction, Color player) {
        int sumRow = start.getRow() + direction.getRow();
        int sumCol = start.getCol() + direction.getCol();
        boolean opponentDisc = false;
        ArrayList<Disc> discs = new ArrayList<Disc>();
        while(sumRow >= 0 && sumRow < Othello.ROWS && sumCol >= 0 && sumCol < Othello.COLUMNS) {
            Disc deltaDisc = board.GetDisc(sumRow, sumCol);
            Color deltaColor = deltaDisc.getColor();
            if((player != deltaColor && deltaColor != Color.EMPTY)) {
                opponentDisc = true;
                discs.add(deltaDisc);
                sumRow = sumRow + direction.getRow();
                sumCol = sumCol + direction.getCol();
            } else if(player == deltaColor && opponentDisc) {
                discs.forEach(disc -> disc.flipDisc());
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean checkDirection(Board board, Position start, Position direction, Color player) {
        int sumRow = start.getRow() + direction.getRow();
        int sumCol = start.getCol() + direction.getCol();
        boolean opponentDisc = false;
        while(sumRow >= 0 && sumRow < Othello.ROWS && sumCol >= 0 && sumCol < Othello.COLUMNS) {
            Disc deltaDisc = board.GetDisc(sumRow, sumCol);
            Color deltaColor = deltaDisc.getColor();
            if((player != deltaColor && deltaColor != Color.EMPTY)) {
                opponentDisc = true;
                sumRow = sumRow + direction.getRow();
                sumCol = sumCol + direction.getCol();
            } else if(player == deltaColor && opponentDisc) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
