package model;

import java.util.ArrayList;

public class AIPlayer {
    private static final int CORNER = 10;
    private static final int EDGE = 5;
    private static final int NEXT_TO_CORNER = 1;
    private static final int NEXT_TO_EDGE = 2;
    private static final int MAX_DEPTH = 4;
    private static final int SIGN[] = {1, -1}; //0 is black, 1 is white

    private Rules r;

    public AIPlayer() {
        r = new Rules();
    }

    private int negaMax(Board b, int depth, int color, int alpha, int beta) {
        if(r.isGameOver(b) || depth > MAX_DEPTH) {
            return SIGN[color]*analysis(b);
        }
        int max = Integer.
    }

    private int analysis(Board board) {
        return 0;
    }

}
