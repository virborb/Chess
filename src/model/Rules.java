package model;

import model.pieces.King;
import model.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Checks if a player has legal moves or if game is over.
 */
public class Rules {

    /**
     * Constructs Rules.
     */
    public Rules() {

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
}
