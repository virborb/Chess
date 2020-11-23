package model;

import controller.OthelloController;

import java.util.ArrayList;
import java.util.Arrays;

public class AIPlayer {
    private static final int CORNER = 20;
    private static final int EDGE = 10;
    private static final int NEXT_TO_CORNER = 1;
    private static final int NEXT_TO_EDGE = 2;
    private static final int OTHER = 5;


    private static final int[][] CORNERS = {{0,0},{7,7},{0,7},{7,0}};
    private static final int[][] EDGES = {{0,2},{0,3},{0,4},{0,5},
            {7,2},{7,3}, {7,4},{7,5},
            {2,0},{3,0},{4,0},{5,0},
            {2,7},{3,7},{4,7},{5,7}};
    private static final int[][] NEXT_TO_CORNERS = {{0,1},{1,1},{1,0},
            {0,6},{1,6},{1,7},
            {6,0},{6,1},{7,1},
            {6,6},{6,7},{7,6}};
    private static final int[][] NEXT_TO_EDGES = {{1,2},{1,3},{1,4},{1,5},
            {6,2},{6,3}, {6,4},{6,5},
            {2,1},{3,1},{4,1},{5,1},
            {2,6},{3,6},{4,6},{5,6}};

    private static final int MAX_DEPTH = 4;
    private static final int[] SIGN = {1, -1}; //0 is black, 1 is white

    private Rules r;
    private ArrayList<Position> corners;
    private ArrayList<Position> edges;
    private ArrayList<Position> nextToCorners;
    private ArrayList<Position> nextToEdges;

    public AIPlayer() {
        r = new Rules();
        corners = new ArrayList<>();
        edges = new ArrayList<>();
        nextToCorners = new ArrayList<>();
        nextToEdges = new ArrayList<>();
        Arrays.stream(CORNERS).parallel().forEach( e -> corners.add(new Position(e)));
        Arrays.stream(EDGES).parallel().forEach( e -> edges.add(new Position(e)));
        Arrays.stream(NEXT_TO_CORNERS).parallel().forEach( e -> nextToCorners.add(new Position(e)));
        Arrays.stream(NEXT_TO_EDGES).parallel().forEach( e -> nextToEdges.add(new Position(e)));
    }

    public Position nextMove(Board b, Color player) {
        ArrayList<Disc> discs = r.getValidMoves(b, player);
        int value = 0;
        Position position = null;
        System.out.println("Moves: " + discs.size());
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
                if(discs[i][j].getColor() != Color.EMPTY) {
                    value = value + sumScore(discs[i][j]);
                }
            }
        }
        return value;
    }

    private int sumScore(Disc disc) {
        int color = (disc.getColor() == Color.BLACK) ? 0 : 1;
        int sum = 0;
        Position p = disc.getPosition();
        if (corners.parallelStream().anyMatch(position -> position.equals(p))) {
            sum = sum + CORNER;
        } else if (edges.parallelStream().anyMatch(position -> position.equals(p))) {
            sum = sum + EDGE;
        } else if (nextToCorners.parallelStream().anyMatch(position -> position.equals(p))) {
            sum = sum + NEXT_TO_CORNER;
        } else if (nextToEdges.parallelStream().anyMatch(position -> position.equals(p))) {
            sum = sum + NEXT_TO_EDGE;
        } else {
            sum = sum + OTHER;
        }
        return SIGN[color]*sum;
    }

}
