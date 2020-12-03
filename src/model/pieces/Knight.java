package model.pieces;

import model.Color;
import model.Position;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(Position position, Color color) {
        super(position, color);
        this.setImage();
    }

    @Override
    public ArrayList<Position> getMoves() {
        return null;
    }

    @Override
    public Piece copy() {
        return new Knight(this.getPosition(), this.getColor());
    }

    private void setImage() {
        URL url = null;
        if(this.getColor().equals(Color.BLACK)) {
            url = getClass().getResource("/images/blackKnight.png");
        } else {
            url = getClass().getResource("/images/whiteKnight.png");
        }
        try {
            this.setImage(ImageIO.read(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
