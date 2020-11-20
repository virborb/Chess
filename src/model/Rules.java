package model;

import controller.OthelloController;

import java.util.ArrayList;

public class Rules {
    private final Position[] directions;

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

    public ArrayList<Disc> getValidMoves(Board board, Color player) {
        ArrayList<Disc> discs = new ArrayList<>();
        for (int i = 0; i < OthelloController.ROWS; i++) {
            for (int j = 0; j < OthelloController.COLUMNS; j++) {
                Disc disc = board.GetDisc(i, j);
                if(isLegalMove(board, disc, player)) {
                    discs.add(disc);
                }
            }
        }
        return discs;
    }

    public boolean hasLegalMoves(Board b, Color player) {
        return !getValidMoves(b, player).isEmpty();
    }
    public boolean isGameOver(Board b) {
        return !hasLegalMoves(b, Color.WHITE) && !hasLegalMoves(b, Color.BLACK);
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

    public void flipDiscs(Board board, Position position, Color player) {
        board.GetDisc(position.getRow(), position.getCol()).setColor(player);
        for (Position dir : directions) {
            flipDiscDirection(board, position, dir, player);
        }
    }

    private void flipDiscDirection(Board board, Position start, Position direction, Color player) {
        int sumRow = start.getRow() + direction.getRow();
        int sumCol = start.getCol() + direction.getCol();
        boolean opponentDisc = false;
        ArrayList<Disc> discs = new ArrayList<>();
        while(sumRow >= 0 && sumRow < OthelloController.ROWS && sumCol >= 0 && sumCol < OthelloController.COLUMNS) {
            Disc deltaDisc = board.GetDisc(sumRow, sumCol);
            Color deltaColor = deltaDisc.getColor();
            if((player != deltaColor && deltaColor != Color.EMPTY)) {
                opponentDisc = true;
                discs.add(deltaDisc);
                sumRow = sumRow + direction.getRow();
                sumCol = sumCol + direction.getCol();
            } else if(player == deltaColor && opponentDisc) {
                discs.forEach(Disc::flipDisc);
                return;
            } else {
                return;
            }
        }
    }

    private boolean checkDirection(Board board, Position start, Position direction, Color player) {
        int sumRow = start.getRow() + direction.getRow();
        int sumCol = start.getCol() + direction.getCol();
        boolean opponentDisc = false;
        while(sumRow >= 0 && sumRow < OthelloController.ROWS && sumCol >= 0 && sumCol < OthelloController.COLUMNS) {
            Disc deltaDisc = board.GetDisc(sumRow, sumCol);
            Color deltaColor = deltaDisc.getColor();
            if((player != deltaColor && deltaColor != Color.EMPTY)) {
                opponentDisc = true;
                sumRow = sumRow + direction.getRow();
                sumCol = sumCol + direction.getCol();
            } else return player == deltaColor && opponentDisc;
        }
        return false;
    }
}
