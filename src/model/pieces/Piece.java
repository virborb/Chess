package model.pieces;

import model.Board;
import model.Color;
import model.Position;
import view.ImagePanel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Piece {
    private Position position;
    private final Color color;
    private final int value;
    private BufferedImage image;
    private ImagePanel comp;

    public Piece(Position position, Color color, int value) {
        this.position = position;
        this.color = color;
        this.value = value;
    }

    public Piece(Piece piece) {
        this.position = piece.position;
        this.color = piece.color;
        this.value = piece.value;
        this.image = piece.image;
        this.comp = piece.comp;
    }

    public abstract ArrayList<Position> getMoves(Board board);

    public abstract Piece copy();

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public BufferedImage getImage() {
        return image;
    }

    public ImagePanel getComp() {
        return comp;
    }

    public void setComp(ImagePanel comp) {
        this.comp = comp;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    protected void setImage(BufferedImage image) {
        this.image = image;
    }
}
