package test.pieces;

import model.Board;
import model.Position;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initBoard();
    }

    @Test
    void getMoves() {
        Piece piece = board.GetPiece(new Position(0, 1));
        ArrayList<Position> positions = piece.getMoves();
        ArrayList<Position> expected = new ArrayList<>();
        expected.add(new Position(2, 0));
        expected.add(new Position(2, 2));
        expected.add(new Position(1, 3));
        assertTrue(positions.containsAll(expected) && positions.size() == expected.size());
    }
}