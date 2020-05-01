package edu.cps2002.mazegame.map;

import org.junit.*;
import static org.junit.Assert.*;

public class TestTile {

    public Tile tile;

    @Before
    public void setup() {
        tile = new Tile();
    }

    @After
    public void tearDown() {
        tile = null;
    }

//********** tile.getTileHTML() tests **********//

    @Test
    public void testGetTileHTML_Valid_Green() {
        //Exercise
        String result = tile.getTileHTML("green");

        //Assert
        assertTrue(result.contains("green"));
    }

    @Test
    public void testGetTileHTML_Valid_Yellow() {
        //Exercise
        String result = tile.getTileHTML("yellow");

        //Assert
        assertTrue(result.contains("yellow"));
    }

    @Test
    public void testGetTileHTML_Valid_Blue() {
        //Exercise
        String result = tile.getTileHTML("blue");

        //Assert
        assertTrue(result.contains("blue"));
    }
}
