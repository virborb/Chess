package model;

import controller.OthelloController;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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

    public AIPlayer() {
        r = new Rules();
    }
    public Position nextMove(Board b, Color player) {
        ArrayList<Disc> discs = r.getValidMoves(b, player);
        AtomicInteger value = new AtomicInteger(Integer.MAX_VALUE);
        AtomicReference<Position> position = new AtomicReference<>();
        discs.parallelStream().forEach( disc -> {
            Board c = new Board(b);
            r.flipDiscs(c, disc.getPosition(), player);
            int color = (player == Color.WHITE) ? 0 : 1;
            int tmp = negaMax(c, 0, color, Integer.MIN_VALUE, Integer.MAX_VALUE);
            value.updateAndGet( v -> {
                if (tmp < v) {
                    position.set(disc.getPosition());
                    System.out.println(tmp);
                    return tmp;
                }
                return v;
            });
        });
        return position.get();
    }

    /**
     * A negaMax algorithm with alpha beta pruning to find the best move. the initial should be called
     * with {@code depth=0, alpha=-infinity and beta=infinity}.
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
        ArrayList<Disc> legalMoves = r.getValidMoves(b, player);
        for (Disc move: legalMoves) {
            Board c = new Board(b);
            r.flipDiscs(c, move.getPosition(), player);
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
        ArrayList<Disc> discs = board.getBoard();
        int value = discs.parallelStream().filter(e -> e.getColor() != Color.EMPTY).mapToInt(this::sumScore).sum();
        value = value + mobility(board);
        System.out.println(value);
        return value;
    }

    private int sumScore(Disc disc) {
        int color = (disc.getColor() == Color.BLACK) ? 0 : 1;
        Position p = disc.getPosition();
        int sum = POINTS[p.getRow()*OthelloController.ROWS+p.getCol()];
        return SIGN[color]*sum;
    }

    private int mobility(Board board) {
        return (r.getValidMoves(board, Color.BLACK).size()-r.getValidMoves(board, Color.WHITE).size())*5;
    }

}
