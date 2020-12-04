package test.pieces;

import model.Board;
import model.Position;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        Piece king = board.getPiece(new Position(0, 4));
        Piece pawn = board.getPiece(new Position(6, 4));
        board.movePiece(king, new Position(2, 4));
        board.movePiece(pawn, new Position(3, 4));
        ArrayList<Position> positions = king.getMoves(board);
        ArrayList<Position> expected = new ArrayList<>();
        expected.add(new Position(2, 3));
        expected.add(new Position(2, 5));
        expected.add(new Position(3, 3));
        expected.add(new Position(3, 4));
        expected.add(new Position(3, 5));
        assertTrue(positions.containsAll(expected) && positions.size() == expected.size());
    }
}