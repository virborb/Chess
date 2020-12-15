package model.pieces;

import controller.ChessController;
import model.Board;
import model.Color;
import model.Position;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class King extends Piece {
    private static final int VALUE = 10;

    public King(Position position, Color color) {
        super(position, color, VALUE);
        this.setImage();
    }

    private King(King king) {
        super(king);
    }

    @Override
    public ArrayList<Position> getMoves(Board board) {
        ArrayList<Position> positions = new ArrayList<>();
        Position start = this.getPosition();
        Position[] directions = this.getDirections(start.getRow(), start.getCol());
        for (Position direction:directions) {
            int row = direction.getRow();
            int col = direction.getCol();
            if (row >= 0 && row < ChessController.ROWS && col >= 0 && col < ChessController.COLUMNS) {
                if (board.getPiece(direction) != null) {
                    if(board.getPiece(direction).getColor() != this.getColor()) {
                        positions.add(direction);
                    }
                    continue;
                }
                positions.add(direction);
            }
        }
        return positions;
    }

    @Override
    public Piece copy() {
        return new King(this);
    }

    private Position[] getDirections(int startRow, int startCol) {
        Position[] directions = new Position[8];
        directions[0] = new Position(startRow-1,startCol+1);
        directions[1] = new Position(startRow+1,startCol+1);
        directions[2] = new Position(startRow-1,startCol-1);
        directions[3] = new Position(startRow+1,startCol);
        directions[4] = new Position(startRow-1,startCol);
        directions[5] = new Position(startRow,startCol-1);
        directions[6] = new Position(startRow,startCol+1);
        directions[7] = new Position(startRow+1,startCol-1);
        return directions;
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
