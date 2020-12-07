package test.pieces;

import model.Board;
import model.Position;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initBoard();
    }

    @Test
    void getMoves() {
        Piece pawn = board.getPiece(new Position(1, 1));
        Piece opponentPawn = board.getPiece(new Position(6, 2));
        board.movePiece(opponentPawn, new Position(2, 2));

        Piece ownPawn = board.getPiece(new Position(1, 4));
        board.movePiece(ownPawn, new Position(3, 1));
        ArrayList<Position> positions = pawn.getMoves(board);
        ArrayList<Position> expected = new ArrayList<>();

        expected.add(new Position(2, 2));
        expected.add(new Position(2, 1));

        assertTrue(positions.containsAll(expected) && positions.size() == expected.size());
    }
}