package edu.cps2002.mazegame.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestPlayerTile {

    public PlayerTile tile;

    @Before
    public void setup() {
        tile = new PlayerTile();
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
        assertTrue(result.contains("<img src=\"/Assignment/src/main/resources/detective.png\" height=\"50\" width=\"50\">"));
    }

    @Test
    public void testGetTileHTML_Valid_Yellow() {
        //Exercise
        String result = tile.getTileHTML("yellow");

        //Assert
        assertTrue(result.contains("yellow"));
        assertTrue(result.contains("<img src=\"/Assignment/src/main/resources/detective.png\" height=\"50\" width=\"50\">"));
    }

    @Test
    public void testGetTileHTML_Valid_Blue() {
        //Exercise
        String result = tile.getTileHTML("blue");

        //Assert
        assertTrue(result.contains("blue"));
        assertTrue(result.contains("<img src=\"/Assignment/src/main/resources/detective.png\" height=\"50\" width=\"50\">"));
    }
}
