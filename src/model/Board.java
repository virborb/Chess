package model;

import controller.ChessController;
import model.pieces.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Holds all the Discs that are on the board.
 */
public class Board {
    private final ConcurrentHashMap<Position, Piece> board;
    private BufferedImage boardImage;

    public Board() {
        this.board = new ConcurrentHashMap<>();
        try {
            this.setBoardImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Make a copy of the board.
     * @param b The board to copy
     */
    public Board(Board b) {
        this.board = new ConcurrentHashMap<>();
        b.getBoard().forEach((position, piece) -> {
            this.board.put(position, piece.copy());
        });
        this.boardImage = b.getBoardImage();
    }

    /**
     * Initiates a new board with the discs at starting positions
     * of the game.
     */
    public void initBoard() {
        for (int row = 0; row < ChessController.ROWS; row++) {
            if(row >= 2 && row <= 5) {
                continue;
            }
            for (int col = 0; col < ChessController.COLUMNS; col++) {
                Position position = new Position(row,col);
                Piece piece = null;
                if(row == 0) {
                    if(col == 0 || col == 7) {
                        piece = new Rook(position, Color.BLACK);
                    } else if (col == 1 || col == 6) {
                        piece = new Knight(position, Color.BLACK);
                    } else if (col == 2 || col == 5) {
                        piece = new Bishop(position, Color.BLACK);
                    } else if (col == 3) {
                        piece = new Queen(position, Color.BLACK);
                    } else {
                        piece = new King(position, Color.BLACK);
                    }
                } else if (row == 7) {
                    if (col == 0 || col == 7) {
                        piece = new Rook(position, Color.WHITE);
                    } else if (col == 1 || col == 6) {
                        piece = new Knight(position, Color.WHITE);
                    } else if (col == 2 || col == 5) {
                        piece = new Bishop(position, Color.WHITE);
                    } else if (col == 3) {
                        piece = new Queen(position, Color.WHITE);
                    } else {
                        piece = new King(position, Color.WHITE);
                    }
                } else if(row == 1) {
                    piece = new Pawn(position, Color.BLACK);
                } else {
                    piece = new Pawn(position, Color.WHITE);
                }
                board.put(position, piece);
            }
        }
    }


    public BufferedImage getBoardImage() {
        return boardImage;
    }

    /**
     * Gets a piece from specified position.
     * @param position The position of the piece.
     * @return The specified piece or null if empty position.
     */
    public Piece getPiece(Position position) {
        return board.get(position);
    }

    /**
     *
     * @param piece
     * @param moveTo
     * @return the previous value associated with key,
     *         or null if there was no mapping for key
     */
    public Piece movePiece(Piece piece, Position moveTo) {
        board.remove(piece.getPosition());
        piece.setPosition(moveTo);
        return board.put(moveTo, piece);
    }

    /**
     *
     * @return The board.
     */
    public ConcurrentHashMap<Position, Piece> getBoard() {
        return board;
    }

    private void setBoardImage() throws IOException {
        URL url = getClass().getResource("/images/boardTwo.png");
        boardImage = ImageIO.read(url);
    }
}
