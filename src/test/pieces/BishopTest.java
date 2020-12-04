package test.pieces;

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
        Piece piece = board.getPiece(new Position(0, 2));
        board.movePiece(piece, new Position(4, 2));
        ArrayList<Position> positions = piece.getMoves(board);
        ArrayList<Position> expected = new ArrayList<>();

        expected.add(new Position(3, 1));
        expected.add(new Position(2, 0));

        expected.add(new Position(3, 3));
        expected.add(new Position(2, 4));

        expected.add(new Position(5, 1));
        expected.add(new Position(6, 0));

        expected.add(new Position(5, 3));
        expected.add(new Position(6, 4));

        assertTrue(positions.containsAll(expected) && positions.size() == expected.size());
    }
}