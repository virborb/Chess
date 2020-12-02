package model.Pieces;

import model.Color;
import model.Position;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(Position position, Color color) {
        super(position, color);
    }

    @Override
    public ArrayList<Position> getMoves() {
        return null;
    }
}
