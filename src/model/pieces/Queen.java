package model.pieces;

import model.Board;
import model.Color;
import model.Position;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Queen  extends Piece {
    private static final int VALUE = 9;

    public Queen(Position position, Color color) {
        super(position, color, VALUE);
        this.setImage();
    }

    private Queen(Queen queen) {
        super(queen);
    }

    @Override
    public ArrayList<Position> getMoves(Board board) {
        Rook rook = new Rook(this.getPosition(), this.getColor());
        Bishop bishop = new Bishop(this.getPosition(), this.getColor());
        ArrayList<Position> positions = rook.getMoves(board);
        positions.addAll(bishop.getMoves(board));
        return positions;
    }

    @Override
    public Piece copy() {
        return new Queen(this);
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
