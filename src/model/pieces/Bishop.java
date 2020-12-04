package model.pieces;

import controller.ChessController;
import model.Color;
import model.Position;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(Position position, Color color) {
        super(position, color);
        this.setImage();
    }

    @Override
    public ArrayList<Position> getMoves() {
        ArrayList<Position> positions = new ArrayList<>();
        Position start = this.getPosition();
        Position[] directions = new Position[4];
        directions[0] = new Position(-1,-1);
        directions[1] = new Position(1,1);
        directions[2] = new Position(1,-1);
        directions[3] = new Position(-1,1);
        for (Position direction : directions) {
            int sumRow = start.getRow() + direction.getRow();
            int sumCol = start.getCol() + direction.getCol();
            boolean opponentDisc = false;
            while(sumRow >= 0 && sumRow < ChessController.ROWS && sumCol >= 0 && sumCol < ChessController.COLUMNS) {
                positions.add(new Position(sumRow, sumCol));
                sumRow = sumRow + direction.getRow();
                sumCol = sumCol + direction.getCol();
            }
        }
        return positions;
    }

    @Override
    public Piece copy() {
        return new Bishop(this.getPosition(), this.getColor());
    }

    private void setImage() {
        URL url = null;
        if(this.getColor().equals(Color.BLACK)) {
            url = getClass().getResource("/images/blackBishop.png");
        } else {
            url = getClass().getResource("/images/whiteBishop.png");
        }
        try {
            this.setImage(ImageIO.read(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
