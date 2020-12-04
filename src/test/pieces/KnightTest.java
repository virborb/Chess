package test.pieces;

import model.Board;
import model.Position;
import model.pieces.Piece;
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
        Piece knight = board.getPiece(new Position(0, 1));
        Piece pawn = board.getPiece(new Position(6, 1));
        board.movePiece(pawn, new Position(2, 0));
        ArrayList<Position> positions = knight.getMoves(board);
        ArrayList<Position> expected = new ArrayList<>();
        expected.add(new Position(2, 0));
        expected.add(new Position(2, 2));
        assertTrue(positions.containsAll(expected) && positions.size() == expected.size());
    }
}