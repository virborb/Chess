package model.pieces;

import model.Color;
import model.Position;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Queen  extends Piece {

    public Queen(Position position, Color color) {
        super(position, color);
        this.setImage();
    }

    @Override
    public ArrayList<Position> getMoves() {
        Rook rook = new Rook(this.getPosition(), this.getColor());
        Bishop bishop = new Bishop(this.getPosition(), this.getColor());
        ArrayList<Position> positions = rook.getMoves();
        positions.addAll(bishop.getMoves());
        return positions;
    }

    @Override
    public Piece copy() {
        return new Queen(this.getPosition(), this.getColor());
    }

    private void setImage() {
        URL url = null;
        if(this.getColor().equals(Color.BLACK)) {
            url = getClass().getResource("/images/blackQueen.png");
        } else {
            url = getClass().getResource("/images/whiteQueen.png");
        }
        try {
            this.setImage(ImageIO.read(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
