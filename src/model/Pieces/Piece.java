package model.Pieces;

import model.Color;
import model.Position;

import java.util.ArrayList;

public abstract class Piece {
    private Position position;
    private Color color;

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public abstract ArrayList<Position> getMoves();

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

}
