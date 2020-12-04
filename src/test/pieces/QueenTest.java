package test.pieces;

import controller.ChessController;
import model.Board;
import model.Position;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initBoard();
    }

    @Test
    void getMoves() {
        Piece piece = board.GetPiece(new Position(0, 3));
        ArrayList<Position> positions = piece.getMoves();
        ArrayList<Position> expected = new ArrayList<>();
        for (int i = 4; i < ChessController.ROWS; i++) {
            expected.add(new Position(i-3, i));
        }
        expected.add(new Position(1, 2));
        expected.add(new Position(2, 1));
        expected.add(new Position(3, 0));
        for (int i = 0; i < ChessController.ROWS; i++) {
            if(i == 3) continue;
            expected.add(new Position(0, i));
        }
        for (int i = 1; i < ChessController.ROWS; i++) {
            expected.add(new Position(i,  3));
        }
        assertTrue(positions.containsAll(expected) && positions.size() == expected.size());
    }
}