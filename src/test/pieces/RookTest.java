package test.pieces;

import controller.ChessController;
import model.Board;
import model.Position;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initBoard();
    }

    @Test
    void TestGetMoves() {
        Piece piece = board.getPiece(new Position(0, 0));
        board.movePiece(piece, new Position(2, 0));
        ArrayList<Position> positions = piece.getMoves(board);
        ArrayList<Position> expected = new ArrayList<>();
        for (int i = 1; i < ChessController.ROWS; i++) {
            expected.add(new Position(2,  i));
        }
        for (int i = 3; i < ChessController.ROWS-1; i++) {
            expected.add(new Position(i, 0));
        }
        assertTrue(positions.containsAll(expected) && positions.size() == expected.size());
    }
}