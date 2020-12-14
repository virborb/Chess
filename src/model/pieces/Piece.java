package model.pieces;

import model.Board;
import model.Color;
import model.Position;
import view.ImagePanel;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Piece {
    private Position position;
    private Color color;
    private BufferedImage image;
    private ImagePanel comp;

    public Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Piece(Piece piece) {
        this.position = piece.position;
        this.color = piece.color;
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
