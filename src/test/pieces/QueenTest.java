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
        Piece piece = board.getPiece(new Position(0, 3));
        board.movePiece(piece, new Position(2, 3));
        ArrayList<Position> positions = piece.getMoves(board);
        ArrayList<Position> expected = new ArrayList<>();
        
        expected.add(new Position(3, 2));
        expected.add(new Position(4, 1));
        expected.add(new Position(5, 0));

        expected.add(new Position(3, 4));
        expected.add(new Position(4, 5));
        expected.add(new Position(5, 6));
        expected.add(new Position(6, 7));
        for (int i = 0; i < ChessController.ROWS; i++) {
            if(i == 3) continue;
            expected.add(new Position(2, i));
        }
        for (int i = 3; i < ChessController.ROWS-1; i++) {
            expected.add(new Position(i,  3));
        }
        assertTrue(positions.containsAll(expected) && positions.size() == expected.size());
    }
}