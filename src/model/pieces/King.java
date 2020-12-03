package model.pieces;

import controller.ChessController;
import model.Color;
import model.Position;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class King extends Piece {

    public King(Position position, Color color) {
        super(position, color);
        this.setImage();
    }

    @Override
    public ArrayList<Position> getMoves() {
        ArrayList<Position> positions = new ArrayList<>();
        Position start = this.getPosition();
        int startRow = start.getRow();
        int startCol = start.getCol();
        Position[] directions = new Position[8];
        directions[0] = new Position(startRow-1,startCol+1);
        directions[1] = new Position(startRow+1,startCol+1);
        directions[2] = new Position(startRow-1,startCol-1);
        directions[3] = new Position(startRow+1,startCol);
        directions[4] = new Position(startRow-1,startCol);
        directions[5] = new Position(startRow,startCol-1);
        directions[6] = new Position(startRow,startCol+1);
        directions[7] = new Position(startRow+1,startCol-1);
        for (Position direction:directions) {
            int row = direction.getRow();
            int col = direction.getCol();
            if (row >= 0 && row < ChessController.ROWS && col >= 0 && col < ChessController.COLUMNS) {
                positions.add(direction);
            }
        }
        return positions;
    }

    @Override
    public Piece copy() {
        return new King(this.getPosition(), this.getColor());
    }

    private void setImage() {
        URL url = null;
        if(this.getColor().equals(Color.BLACK)) {
            url = getClass().getResource("/images/blackKing.png");
        } else {
            url = getClass().getResource("/images/whiteKing.png");
        }
        try {
            this.setImage(ImageIO.read(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
