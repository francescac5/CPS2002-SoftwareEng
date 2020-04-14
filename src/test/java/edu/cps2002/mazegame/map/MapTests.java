package edu.cps2002.mazegame.map;

import org.junit.*;
import static org.junit.Assert.*;

public class MapTests {

    public Map map;

    @Before
    public void setup() {
        map = new Map();
    }

    @After
    public void tearDown() {
        map = null;
    }

/********** map.setMapSize() tests **********/

    @Test
    public void testSetMapSize_GreaterThanMinSize() {
        //Exercise
        int size = 8;
        boolean result = map.setMapSize(size);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testSetMapSize_MinSize() {
        //Exercise
        int size = 5;
        boolean result = map.setMapSize(size);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testSetMapSize_LessThanMinSize() {
        //Exercise
        int size = 2;
        boolean result = map.setMapSize(size);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testSetMapSize_NegativeSize() {
        //Exercise
        int size = -9;
        boolean result = map.setMapSize(size);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testSetMapSize_ZeroSize() {
        //Exercise
        int size = 0;
        boolean result = map.setMapSize(size);

        //Assert
        assertFalse(result);
    }

/******* map.generateHTMLFile() tests ********/

    @Test
    public void testGenerateHTMLFile() {
        //Exercise
        boolean result = map.generateHTMLFile();

        //Assert
        assertTrue(result);
    }
}
