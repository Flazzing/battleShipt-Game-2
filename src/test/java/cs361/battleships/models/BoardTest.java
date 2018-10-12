package cs361.battleships.models;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class BoardTest {

    @Test
    public void testInvalidPlacement() {
        Board board = new Board();
        assertFalse(board.placeShip(new Ship("MINESWEEPER"), 11, 'C', true));
    }

    @Test
    public void testValidPlacement() {
        Board board = new Board();
        assertTrue(board.placeShip(new Ship("MINESWEEPER"), 5, 'C', true));
    }
    @Test
    public void testAttackInvalid(){
        Board board = new Board();
        board.attack(1, 'B'); //attack the same square twice
        assertTrue( board.attack(1, 'B').getResult() == AtackStatus.INVALID );
        board.attack(5, 'D'); //attack the same square twice
        assertTrue( board.attack(5, 'D').getResult() == AtackStatus.INVALID );
    }

    @Test
    public void testAttackHit(){
        Board board = new Board();
        board.placeShip(new Ship("MINESWEEPER"), 5, 'C', true); //place a ship and hit it twice in a row
        assertTrue( board.attack(5, 'C').getResult() == AtackStatus.HIT );
        assertTrue( board.attack(6, 'C').getResult() == AtackStatus.SUNK );

        board.placeShip(new Ship("DESTROYER"), 5, 'B', true);
        assertTrue( board.attack(5, 'B').getResult() == AtackStatus.HIT ); //Testing sink with misses in between
        assertTrue( board.attack(6, 'B').getResult() == AtackStatus.HIT );
        board.attack(5, 'A');
        board.attack(1, 'A');
        board.attack(3, 'B');
        assertTrue( board.attack(7, 'B').getResult() == AtackStatus.SUNK );
    }

    @Test
    public void testAttackMiss(){
        Board board = new Board();
        board.placeShip(new Ship("MINESWEEPER"), 5, 'C', true); //place 2 ships, miss 1 attack hit the next 2
        board.placeShip(new Ship("BATTLESHIP"),  4, 'E', true);
        assertTrue( board.attack(5, 'D').getResult() == AtackStatus.MISS); //miss
        assertFalse( board.attack(5, 'E').getResult() == AtackStatus.MISS); //hit battleship
        assertFalse( board.attack(6, 'C').getResult() == AtackStatus.MISS); //hit minesweeper
    }

    @Test
    public void testGameEnd(){
        Board board = new Board();
        board.placeShip(new Ship("MINESWEEPER"), 5, 'C', true); //place all 3 ships
        board.placeShip(new Ship("BATTLESHIP"),  4, 'E', true);
        board.placeShip(new Ship("DESTROYER"),  4, 'F', true);
        board.attack(5, 'C'); //sink minesweeper
        board.attack(6, 'C');

        board.attack(4, 'E'); //sink battleship
        board.attack(5, 'E');
        board.attack(6, 'E');
        board.attack(7, 'E');

        board.attack(4, 'F'); //sink destroyer
        board.attack(5, 'F');
        assertTrue( board.attack(6, 'F').getResult() == AtackStatus.SURRENDER ); //should end game on
                                                                                                // last destroyer hit
    }
}
