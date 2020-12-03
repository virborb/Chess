package test.pieces;

import model.Board;
import model.Position;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initBoard();
    }

    @Test
    void testGetMoves() {
        Piece piece = board.GetPiece(new Position(0, 4));
        ArrayList<Position> positions = piece.getMoves();
        ArrayList<Position> expected = new ArrayList<>();
        expected.add(new Position(0, 3));
        expected.add(new Position(0, 5));
        expected.add(new Position(1, 3));
        expected.add(new Position(1, 4));
        expected.add(new Position(1, 5));
        assertTrue(positions.containsAll(expected) && positions.size() == 5);
    }
}