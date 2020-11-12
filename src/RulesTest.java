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
        Disc disc = board.GetDisc(5, 5);
        assertFalse(rules.isLegalMove(board, disc, Color.WHITE));
    }

    @Test
    void isLegalMove() {
        Disc disc = board.GetDisc(3, 4);
        assertTrue(rules.isLegalMove(board, disc, Color.BLACK));
    }

    @Test
    void isNotLegalMove() {
        Disc disc = board.GetDisc(1, 1);
        assertFalse(rules.isLegalMove(board, disc, Color.WHITE));
    }

}