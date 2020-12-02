package model.Pieces;

import model.Color;
import model.Position;

import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean isFirstMove;

    public Pawn(Position position, Color color) {
        super(position, color);
        isFirstMove = true;
    }

    @Override
    public ArrayList<Position> getMoves() {
        if(isFirstMove) {
            return null;
        }
        return null;
    }

}
