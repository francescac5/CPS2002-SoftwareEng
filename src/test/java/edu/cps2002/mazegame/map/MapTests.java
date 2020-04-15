package edu.cps2002.mazegame.map;

import edu.cps2002.utils.MapUtils;
import org.junit.*;
import static org.junit.Assert.*;

public class MapTests {

    public Map map;

    @Before
    public void setup() {
        map = new Map();
        map.initMapCount();
    }

    @After
    public void tearDown() {
        MapUtils utils = new MapUtils();
        utils.deleteHTMLFiles();
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
        assertEquals(8, map.getMapSize());
    }

    @Test
    public void testSetMapSize_MinSize() {
        //Exercise
        int size = 5;
        boolean result = map.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(5, map.getMapSize());
    }

    @Test
    public void testSetMapSize_LessThanMinSize() {
        //Exercise
        int size = 2;
        boolean result = map.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, map.getMapSize());
    }

    @Test
    public void testSetMapSize_NegativeSize() {
        //Exercise
        int size = -9;
        boolean result = map.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, map.getMapSize());
    }

    @Test
    public void testSetMapSize_ZeroSize() {
        //Exercise
        int size = 0;
        boolean result = map.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, map.getMapSize());
    }

//******** map.generateTileTypes() tests ********\\

    @Test
    public void testGenerateTileTypes_MinSize() {
        //Exercise
        map.setMapSize(5);

        //generates map_player_0.html file
        map.generate();

        Map.Tiles[][] tiles = map.getTiles();

        //Assert
        assertEquals(5, tiles.length);
        assertEquals(1, map.getMapCount());
    }

    @Test
    public void testGenerateTileTypes_MaxSize() {
        //Exercise
        map.setMapSize(50);

        //generates map_player_0.html file
        map.generate();

        Map.Tiles[][] tiles = map.getTiles();

        //Assert
        assertEquals(50, tiles.length);
        assertEquals(1, map.getMapCount());
    }
}
