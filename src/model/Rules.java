package model;

import controller.OthelloController;

import java.util.ArrayList;

/**
 * Checks if a player has legal moves and also makes them.
 */
public class Rules {
    private final Position[] directions;

    /**
     * Constructs Rules and fills the array directions with
     * the possible direction to check on the board.
     */
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

    /**
     * Gets all possible moves to make for a player.
     * @param board The board to check on.
     * @param player The player to check for move.
     * @return A list with all possible moves.
     */
    public ArrayList<Disc> getValidMoves(Board board, Color player) {
        ArrayList<Disc> discs = (ArrayList<Disc>) board.getBoard().clone();
        discs.removeIf(disc -> !isLegalMove(board, disc, player));
        return discs;
    }

    /**
     * Checks if a player has any legal moves.
     * @param b The board to check on.
     * @param player The player to test for.
     * @return True if player has moves otherwise returns false.
     */
    public boolean hasLegalMoves(Board b, Color player) {
        return !getValidMoves(b, player).isEmpty();
    }

    /**
     * Checks if there is no moves left for ether player.
     * @param b The board to check.
     * @return True if game is over otherwise false.
     */
    public boolean isGameOver(Board b) {
        return !hasLegalMoves(b, Color.WHITE) && !hasLegalMoves(b, Color.BLACK);
    }

    /**
     * Tests if a move is legal.
     * @param board The board to test on.
     * @param disc The disc to test
     * @param player  The player to make the move.
     * @return True if move is legal otherwise false.
     */
    public boolean isLegalMove(Board board, Disc disc, Color player) {
        if(!disc.getColor().equals(Color.EMPTY)) {
            return false;
        }
        Position start = disc.getPosition();
        for (Position dir : directions) {
            if (checkDirection(board, start, dir, player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Flips the discs in all directions that are possible.
     * @param board
     * @param position
     * @param player
     */
    public void flipDiscs(Board board, Position position, Color player) {
        board.GetDisc(position.getRow(), position.getCol()).setColor(player);
        for (Position dir : directions) {
            flipDiscDirection(board, position, dir, player);
        }
    }

    /**
     * Counts all the black discs on the board.
     * @param b The board to count on.
     * @return The number of black discs.
     */
    public int countBlackDiscs(Board b) {
        ArrayList<Disc> discs = b.getBoard();
        int sum = 0;
        for (Disc d: discs) {
            if(d.getColor() == Color.BLACK) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Counts all the white discs on the board.
     * @param b The board to count on.
     * @return The number of white discs.
     */
    public int countWhiteDiscs(Board b) {
        ArrayList<Disc> discs = b.getBoard();
        int sum = 0;
        for (Disc d: discs) {
            if(d.getColor() == Color.WHITE) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Flips all disc in one direction on the board if it's possible.
     * @param board The board which has the discs.
     * @param start The start position
     * @param direction The direction to flip the discs.
     * @param player The player to check with.
     */
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

    /**
     * Check in one direction if it is a legal move.
     * @param board The board to check on.
     * @param start The start position
     * @param direction The direction to check in.
     * @param player The player to check with.
     * @return true if move is legal otherwise false.
     */
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
