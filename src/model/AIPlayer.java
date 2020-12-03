package model;

import controller.ChessController;
import model.pieces.Piece;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The ai player tries to predict the best move to make on
 * the board using an negaMax algorithm with alpha beta pruning.
 */
public class AIPlayer {
    private static final int[] POINTS = { 5,-1, 4, 4, 4, 4,-1, 5,
                                         -1,-2, 2, 2, 2, 2,-2,-1,
                                          4, 2, 3, 3, 3, 3, 2, 4,
                                          4, 2, 3, 4, 4, 3, 2, 4,
                                          4, 2, 3, 4, 4, 3, 2, 4,
                                          4, 2, 3, 3, 3, 3, 2, 4,
                                         -1,-2, 2, 2, 2, 2,-2,-1,
                                          5,-1, 4, 4, 4, 4,-1, 5};

    private static final int MAX_DEPTH = 4;
    private static final int[] SIGN = {1, -1}; //0 is black, 1 is white

    private Rules r;

    /**
     * Constructs a new AIPlayer.
     */
    public AIPlayer() {
        r = new Rules();
    }

    /**
     * Find the best move for the player.
     * @param b the board to find the move on.
     * @param player the player which is making the move.
     * @return the next move to make.
     */
    public Position nextMove(Board b, Color player) {
        Collection<Piece> pieces = r.getValidMoves(b, player);
        AtomicInteger value = new AtomicInteger(Integer.MAX_VALUE);
        AtomicReference<Position> position = new AtomicReference<>();
        pieces.parallelStream().forEach( piece -> {
            Board c = new Board(b);
            //r.flipDiscs(c, piece.getPosition(), player);
            int color = (player == Color.WHITE) ? 0 : 1;
            int tmp = negaMax(c, 0, color, Integer.MIN_VALUE, Integer.MAX_VALUE);
            value.updateAndGet( v -> {
                if (tmp < v) {
                    position.set(piece.getPosition());
                    return tmp;
                }
                return v;
            });
        });
        return position.get();
    }

    /**
     * A negaMax algorithm with alpha beta pruning to find the best move. the initial should be called
     * with {@code depth=0, alpha=-infinity and beta=infinity}. Based on pseudocode
     * found here <A "href=http://www.hamedahmadi.com/gametree/">http://www.hamedahmadi.com/gametree/</A>
     * @param b The board
     * @param depth The depth of the search
     * @param color The color of the player
     * @param alpha
     * @param beta
     * @return
     */
    private int negaMax(Board b, int depth, int color, int alpha, int beta) {
        Color player = (color == 0) ? Color.BLACK : Color.WHITE;
        if(!r.hasLegalMoves(b, player) || depth > MAX_DEPTH) {
            return SIGN[color]*analysis(b);
        }
        int max = Integer.MIN_VALUE;
        Collection<Piece>  legalMoves = r.getValidMoves(b, player);
        for (Piece move: legalMoves) {
            Board c = new Board(b);
            //r.flipDiscs(c, move.getPosition(), player);
            int x = - negaMax(c, depth+1, 1-color, -beta, -alpha);
            if (x > max) max = x;
            if (x > alpha) alpha = x;
            if (alpha >= beta) return alpha;
        }
        return max;
    }

    /**
     * Analyses the current state of the board
     * @param board The board to analyse.
     * @return the value of the board.
     */
    private int analysis(Board board) {
        Collection<Piece> pieces = board.getBoard().values();
        int value = pieces.parallelStream().filter(e -> e.getColor() != Color.EMPTY).mapToInt(this::checkScore).sum();
        value = value + mobility(board);
        return value;
    }

    /**
     * Gets the score of the piece's position
     * @param piece the position to check
     * @return the score
     */
    private int checkScore(Piece piece) {
        int color = (piece.getColor() == Color.BLACK) ? 0 : 1;
        Position p = piece.getPosition();
        int sum = POINTS[p.getRow()* ChessController.ROWS+p.getCol()];
        return SIGN[color]*sum;
    }

    /**
     * Check the mobility of both player.
     * high value good for black and a
     * lower value is good for white
     * @param board the board to check
     * @return a value based on both players mobility.
     */
    private int mobility(Board board) {
        return (r.getValidMoves(board, Color.BLACK).size()-r.getValidMoves(board, Color.WHITE).size())*5;
    }

}
