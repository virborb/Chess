package model;

import controller.OthelloController;

import java.util.ArrayList;

public class AIPlayer {
    private static final int CORNER = 20;
    private static final int EDGE = 10;
    private static final int NEXT_TO_CORNER = 1;
    private static final int NEXT_TO_EDGE = 2;
    private static final int OTHER = 5;

    private static final int CORNERS[][] = {{0,0},{7,7},{0,7},{7,0}};
    private static final int EDGES[][] = {{0,2},{0,3},{0,4},{0,5},
            {7,2},{7,3}, {7,4},{7,5},
            {2,0},{3,0},{4,0},{5,0},
            {2,0},{3,0},{4,0},{5,0},
            {2,7},{3,7},{4,7},{5,7}};

    private static final int MAX_DEPTH = 4;
    private static final int SIGN[] = {1, -1}; //0 is black, 1 is white

    private Rules r;

    public AIPlayer() {
        r = new Rules();
    }

    public Position nextMove(Board b, Color player) {
        ArrayList<Disc> discs = r.getValidMoves(b, player);
        int value = 0;
        Position position = null;
        for (Disc dice : discs) {
            Board c = new Board(b);
            position = dice.getPosition();
            r.flipDiscs(c, position, player);
            int color = (player == Color.BLACK) ? 1 : 0;
            int tmp = negaMax(c, 0, color, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (player == Color.BLACK && tmp > value) {
                position = dice.getPosition();
            } else if (player == Color.WHITE && tmp < value) {
                position = dice.getPosition();
            }
        }
        return position;
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
        Disc[][] discs = board.getBoard();
        int value = 0;
        for (int i = 0; i < OthelloController.ROWS; i++) {
            for (int j = 0; j < OthelloController.COLUMNS; j++) {
                Color color = discs[i][j].getColor();
                if(color == Color.BLACK) {
                    value++;
                } else if(color == Color.WHITE) {
                    value--;
                }
            }
        }
        return 0;
    }

}
