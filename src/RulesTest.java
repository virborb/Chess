import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RulesTest {
    private Board board;
    private Rules rules;

    @BeforeEach
    void setUp() {
        this.board = new Board();
        this.board.initBoard();
        this.rules = new Rules();
    }

    @Test
    void testNotEmpty() {
        Disc disc = board.GetDisc(3, 3);
        assertFalse(rules.isLegalMove(board, disc, Color.WHITE));
    }

    @Test
    void isLegalMoveNorth() {
        Disc disc = board.GetDisc(5, 4);
        assertTrue(rules.isLegalMove(board, disc, Color.BLACK));
    }

    @Test
    void isLegalMoveNorthEast() {
        Disc disc = board.GetDisc(4, 2);
        board.GetDisc(2, 4).setColor(Color.BLACK);
        System.out.println(board.toString());
        assertTrue(rules.isLegalMove(board, disc, Color.BLACK));
    }

    @Test
    void isNonLegalMoveNorthEast() {
        Disc disc = board.GetDisc(4, 2);
        assertFalse(rules.isLegalMove(board, disc, Color.BLACK));
    }

    @Test
    void isNotLegalMove() {
        Disc disc = board.GetDisc(1, 1);
        assertFalse(rules.isLegalMove(board, disc, Color.WHITE));
    }

}