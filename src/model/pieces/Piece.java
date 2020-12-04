package model.pieces;

import model.Board;
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


    public abstract ArrayList<Position> getMoves(Board board);

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

    public void setPosition(Position position) {
        this.position = position;
    }

    protected void setImage(BufferedImage image) {
        this.image = image;
    }
}
