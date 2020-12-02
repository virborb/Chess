package model.Pieces;

import model.Color;
import model.Position;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Rook  extends Piece{

    public Rook(Position position, Color color) {
        super(position, color);
        this.setImage();
    }

    @Override
    public ArrayList<Position> getMoves() {
        return null;
    }

    private void setImage() {
        URL url = null;
        if(this.getColor().equals(Color.BLACK)) {
            url = getClass().getResource("/images/blackRook.png");
        } else {
            url = getClass().getResource("/images/whiteRook.png");
        }
        try {
            this.setImage(ImageIO.read(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
