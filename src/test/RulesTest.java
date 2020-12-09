package test;

import model.Board;
import model.Color;
import model.Position;
import model.Rules;
import model.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RulesTest {
    private Board board;
    private Rules rules;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initBoard();
        rules = new Rules();
    }

    @Test
    void testIsNotGameOver() {
        assertFalse(rules.isGameOver(board, Color.BLACK) && rules.isGameOver(board, Color.WHITE));
    }

    @Test
    void testIsGameOver() {
        Piece blackKing = board.getPiece(new Position(0, 4));
        Piece whiteRook = board.getPiece(new Position(7, 0));
        Piece whiteBishop = board.getPiece(new Position(7, 5));
        board.movePiece(blackKing, new Position(5, 0));
        board.movePiece(whiteRook, new Position(4, 0));
        board.movePiece(whiteBishop, new Position(5, 1));
        assertTrue(rules.isGameOver(board, Color.BLACK));
    }
}