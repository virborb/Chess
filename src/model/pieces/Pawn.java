package model.pieces;

import controller.ChessController;
import model.Board;
import model.Color;
import model.Position;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Position position, Color color) {
        super(position, color);
        this.setImage();
    }

    @Override
    public ArrayList<Position> getMoves(Board board) {
        ArrayList<Position> positions = new ArrayList<>();
        Position start = this.getPosition();
        ArrayList<Position> directions = this.getDirections(start.getRow(), start.getCol(), this.getColor());
        for (Position direction:directions) {
            int row = direction.getRow();
            int col = direction.getCol();
            if (row >= 0 && row < ChessController.ROWS && col >= 0 && col < ChessController.COLUMNS) {
                if (board.getPiece(direction) != null) {
                    if(board.getPiece(direction).getColor() != this.getColor() && start.getCol() != col) {
                        positions.add(direction);
                    }
                    continue;
                } else if (start.getCol() == col){
                    positions.add(direction);
                }
            }
        }
        return positions;
    }

    @Override
    public Piece copy() {
        return new Pawn(this.getPosition(), this.getColor());
    }

    private ArrayList<Position> getDirections(int startRow, int startCol, Color color) {
        ArrayList<Position> directions = new ArrayList<>();
        if (color == Color.BLACK) {
            directions.add(new Position(startRow+1,startCol+1));
            directions.add(new Position(startRow+1,startCol-1));
            directions.add(new Position(startRow+1,startCol));
            if(startRow == 1) {
                directions.add(new Position(startRow+2,startCol));
            }
        } else {
            directions.add(new Position(startRow-1,startCol+1));
            directions.add(new Position(startRow-1,startCol-1));
            directions.add(new Position(startRow-1,startCol));
            if(startRow == 6) {
                directions.add(new Position(startRow-2,startCol));
            }
        }
        return directions;
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
