package model.pieces;

import controller.ChessController;
import model.Board;
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

    private Rook(Rook rook) {
        super(rook);
    }

    @Override
    public ArrayList<Position> getMoves(Board board) {
        ArrayList<Position> positions = new ArrayList<>();
        Position start = this.getPosition();
        Position[] directions = new Position[4];
        directions[0] = new Position(-1,0);
        directions[1] = new Position(1,0);
        directions[2] = new Position(0,-1);
        directions[3] = new Position(0,1);
        for (Position direction : directions) {
            int sumRow = start.getRow() + direction.getRow();
            int sumCol = start.getCol() + direction.getCol();
            while(sumRow >= 0 && sumRow < ChessController.ROWS && sumCol >= 0 && sumCol < ChessController.COLUMNS) {
                Position position = new Position(sumRow, sumCol);
                if (board.getPiece(position) != null) {
                    if(board.getPiece(position).getColor() != this.getColor()) {
                        positions.add(position);
                    }
                    break;
                }
                positions.add(position);
                sumRow = sumRow + direction.getRow();
                sumCol = sumCol + direction.getCol();
            }
        }
        return positions;
    }

    @Override
    public Piece copy() {
        return new Rook(this);
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
