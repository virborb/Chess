package test.pieces;

import controller.ChessController;
import model.Board;
import model.Position;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initBoard();
    }

    @Test
    void getMoves() {
        Piece piece = board.GetPiece(new Position(0, 2));
        ArrayList<Position> positions = piece.getMoves();
        ArrayList<Position> expected = new ArrayList<>();
        for (int i = 3; i < ChessController.ROWS; i++) {
            expected.add(new Position(i-2, i));
        }
        expected.add(new Position(1, 1));
        expected.add(new Position(2, 0));
        assertTrue(positions.containsAll(expected) && positions.size() == expected.size());
    }
}