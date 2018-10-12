package cs361.battleships.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class shipTest {
    @Test
    public void testLengthKind() {
        Ship ship = new Ship("MINESWEEPER");
        assertTrue(ship.getKind() == "MINESWEEPER");
        assertTrue(ship.getLength() == 2);
        ship = new Ship("DESTROYER");
        assertTrue(ship.getKind() == "DESTROYER");
        assertTrue(ship.getLength() == 3);
        ship = new Ship("BATTLESHIP");
        assertTrue(ship.getKind() == "BATTLESHIP");
        assertTrue(ship.getLength() == 4);
    }

    @Test
    public void testOccupiedSquares() {
        Ship ship = new Ship("MINESWEEPER");
        ArrayList<Square> sqs = new ArrayList<>();
        sqs.add(new Square(1, 'A'));
        sqs.add(new Square(2, 'B'));
        ship.setOccupiedSquares(sqs);
        List<Square> check = ship.getOccupiedSquares();
        assertTrue(check.equals(sqs));
    }

    @Test
    public void testDefault() {
        Ship ship = new Ship();
        assertTrue(ship.getKind() == null);
    }
}
