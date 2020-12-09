package model;

import model.pieces.King;
import model.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

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
    public HashMap<Piece, ArrayList<Position>> getValidMoves(Board board, Color player) {
        Collection<Piece>  pieces = board.getBoard().values();
        HashMap<Piece, ArrayList<Position>> moves = new HashMap<>();
        for (Piece p: pieces) {
            if(p.getColor() == player) {
                moves.put(p, p.getMoves(board));
            }
        }
        return moves;
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
    public boolean isGameOver(Board b, Color player) {
        Collection<Piece> pieces = b.getBoard().values();
        AtomicBoolean checkmate = new AtomicBoolean(true);
        for (Piece p: pieces) {
            if(p.getColor() == player && p.getClass().getSimpleName().equals(King.class.getSimpleName())) {
                if(isCheck(b, p)) {
                    HashMap<Piece, ArrayList<Position>> moves = this.getValidMoves(b, player);
                    moves.entrySet().parallelStream().forEach(entry -> {
                        Piece piece = entry.getKey().copy();
                        ArrayList<Position> positions = entry.getValue();
                        positions.parallelStream().forEach(position -> {
                            Board copyBoard = new Board(b);
                            copyBoard.movePiece(piece, position);
                            if (!isCheck(copyBoard, p)) {
                                checkmate.set(false);
                            }
                        });
                    });
                    return checkmate.get();
                }
            }
        }
        return false;
    }

    /**
     * Tests if a move is legal.
     * @param board The board to test on.
     * @param piece The piece to test
     * @param player The player to make the move.
     * @param moveTo
     * @return True if move is legal otherwise false.
     */
    public boolean isLegalMove(Board board, Piece piece, Color player, Position moveTo) {
        Position start = piece.getPosition();
        ArrayList<Position> positions = piece.getMoves(board);
        return piece.getColor() == player && positions.contains(moveTo);
    }

    private boolean isCheck(Board b, Piece p) {
        Color player = (p.getColor() == Color.BLACK) ? Color.WHITE : Color.BLACK;
        HashMap<Piece, ArrayList<Position>> pieces = this.getValidMoves(b, player);
        for (ArrayList<Position> positions : pieces.values()) {
            if (positions.contains(p.getPosition())) {
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
    /*public void flipDiscs(Board board, Position position, Color player) {
        board.GetPiece(position).setColor(player);
        for (Position dir : directions) {
            flipDiscDirection(board, position, dir, player);
        }
    }*/

    /**
     * Flips all disc in one direction on the board if it's possible.
     * @param board The board which has the discs.
     * @param start The start position
     * @param direction The direction to flip the discs.
     * @param player The player to check with.
     */
    /*private void flipDiscDirection(Board board, Position start, Position direction, Color player) {
        int sumRow = start.getRow() + direction.getRow();
        int sumCol = start.getCol() + direction.getCol();
        boolean opponentDisc = false;
        ArrayList<Disc> discs = new ArrayList<>();
        while(sumRow >= 0 && sumRow < ChessController.ROWS && sumCol >= 0 && sumCol < ChessController.COLUMNS) {
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
    }*/

    /**
     * Check in one direction if it is a legal move.
     * @param board The board to check on.
     * @param start The start position
     * @param direction The direction to check in.
     * @param player The player to check with.
     * @return true if move is legal otherwise false.
     */
    /*private boolean checkDirection(Board board, Position start, Position direction, Color player) {
        int sumRow = start.getRow() + direction.getRow();
        int sumCol = start.getCol() + direction.getCol();
        boolean opponentDisc = false;
        while(sumRow >= 0 && sumRow < ChessController.ROWS && sumCol >= 0 && sumCol < ChessController.COLUMNS) {
            Disc deltaDisc = board.GetDisc(sumRow, sumCol);
            Color deltaColor = deltaDisc.getColor();
            if((player != deltaColor && deltaColor != Color.EMPTY)) {
                opponentDisc = true;
                sumRow = sumRow + direction.getRow();
                sumCol = sumCol + direction.getCol();
            } else return player == deltaColor && opponentDisc;
        }
        return false;
    }*/
}
