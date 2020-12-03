package model.Pieces;

import model.Color;
import model.Position;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean isFirstMove;

    public Pawn(Position position, Color color) {
        super(position, color);
        isFirstMove = true;
        this.setImage();
    }

    @Override
    public ArrayList<Position> getMoves() {
        if(isFirstMove) {
            return null;
        }
        return null;
    }

    @Override
    public Piece copy() {
        return new Pawn(this.getPosition(), this.getColor());
    }

    private void setImage() {
        URL url = null;
        if(this.getColor().equals(Color.BLACK)) {
            url = getClass().getResource("/images/blackPawn.png");
        } else {
            url = getClass().getResource("/images/whitePawn.png");
        }
        try {
            this.setImage(ImageIO.read(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
