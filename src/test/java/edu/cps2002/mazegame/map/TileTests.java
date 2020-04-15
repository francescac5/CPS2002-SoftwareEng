package edu.cps2002.mazegame.map;

import org.junit.*;
import static org.junit.Assert.*;

public class TileTests {

    public Tile tile;

    @Before
    public void setup() {
        tile = new Tile();
    }

    @After
    public void tearDown() {
        tile = null;
    }

    /********** tile.getTileHTML() tests **********/

    @Test
    public void testGetTileHTML_Valid_Green() {
        //Exercise
        String colour = "green";
        tile.setColour(colour);
        String result = tile.getTileHTML();

        //Assert
        assertTrue(result.contains(colour));
    }

    @Test
    public void testGetTileHTML_Valid_Yellow() {
        //Exercise
        String colour = "yellow";
        tile.setColour(colour);
        String result = tile.getTileHTML();

        //Assert
        assertTrue(result.contains(colour));
    }

    @Test
    public void testGetTileHTML_Valid_Blue() {
        //Exercise
        String colour = "blue";
        tile.setColour(colour);
        String result = tile.getTileHTML();

        //Assert
        assertTrue(result.contains(colour));
    }

    @Test
    public void testGetTileHTML_Invalid() {
        //Exercise
        String colour = "purple";
        tile.setColour(colour);
        String result = tile.getTileHTML();

        //Assert
        assertTrue(result.contains(colour));
    }
}
