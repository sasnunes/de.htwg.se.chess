package de.htwg.chess;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import junit.framework.TestCase;

@Ignore("Ignore this Test-Class if you run tests with an active DAO.\n" +
        "To run this tests, make sure to disable the DAO instantiation " +
        "within <ChessController.java>'s constructor.")
public class ChessTest extends TestCase {
    String[] some_moves = {"a2-a3", "a7-a5", "b2-b4", "print",
            "printall", "ahsfl", "h"};
    String[] check = {"c2-c3", "d7-d6", "d1-a4", "c7-c6"};
    String[] fastmate = {"e2-e3", "f7-f6", "f2-f4", "g7-g5", "d1-h5"};
    String[] transformToQueen = {"d2-a7", "d2-d4", "e7-e5", "d4-e5", "e8-e7", "e5-e6", "e7-d6", "e7-f6", "e6-e7",
            "d8-d7", "d7-d5", "e7-e8", "queen"};

    @Before
    public void setUp() {
        Chess.getInstance().getGui().disableGUI();
    }

    @Test
    public void testMain() {
        Chess.main(some_moves);
        assertEquals("\u2659",
                Chess.getInstance().getController().getSymboleByField('A', 3));
        assertEquals("\u2659",
                Chess.getInstance().getController().getSymboleByField('B', 4));
        assertEquals("\u265F",
                Chess.getInstance().getController().getSymboleByField('A', 5));
    }

    @Test
    public void testGetInstance() {
        Chess game = Chess.getInstance();
        assertNotNull(game);
        assertNotNull(game.getGui());
    }

    private void restart() {
        Chess.main(new String[]{"r"});
    }
}