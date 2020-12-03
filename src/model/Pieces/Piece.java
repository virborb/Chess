package model.Pieces;

import model.Color;
import model.Position;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Piece {
    private Position position;
    private Color color;
    private BufferedImage image;

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }


    public abstract ArrayList<Position> getMoves();

    public abstract Piece copy();

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public BufferedImage getImage() {
        return image;
    }

    protected void setImage(BufferedImage image) {
        this.image = image;
    }
}
