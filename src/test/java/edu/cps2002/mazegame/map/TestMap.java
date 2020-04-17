package edu.cps2002.mazegame.map;

import edu.cps2002.utils.MapUtils;
import javafx.util.Pair;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestMap {

    public Map map;

    @Before
    public void setup() {
        map = new Map();
        map.initMapCount();
    }

    @After
    public void tearDown() {
      // MapUtils utils = new MapUtils();
      //  utils.deleteHTMLFiles();
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
    public void testSetMapSize_GreaterThanMaxSize() {
        //Exercise
        int size = 52;
        boolean result = map.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, map.getMapSize());
    }

    @Test
    public void testSetMapSize_MaxSize() {
        //Exercise
        int size = 50;
        boolean result = map.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(50, map.getMapSize());
    }

    @Test
    public void testSetMapSize_LessThanMaxSize() {
        //Exercise
        int size = 49;
        boolean result = map.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(49, map.getMapSize());
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

//    @Test
//    public void testGenerateTileTypes_MinSize() {
//        //Exercise
//        map.setMapSize(5);
//
//        map.generateTileTypes();
//
//        Map.Tiles[][] tiles = map.getTiles();
//
//        //Assert
//        assertEquals(5, tiles.length);
//    }
//
//    @Test
//    public void testGenerateTileTypes_MaxSize() {
//        //Exercise
//        map.setMapSize(50);
//
//        //generates map_player_0.html file
//        map.generateTileTypes();
//
//        Map.Tiles[][] tiles = map.getTiles();
//
//        //Assert
//        assertEquals(50, tiles.length);
//    }

//******** map.getTileType() tests ********\\

    @Test
    public  void testGetTileType_Grass(){
        //Exercise
        int size = 5;
        map.setMapSize(size);
        map.generateTileTypes();

        ArrayList<Pair<Integer, Integer>> tiles = map.getGrassTiles();
        Pair<Integer, Integer> greenTile = tiles.get(0);
        int x = greenTile.getKey();
        int y = greenTile.getValue();

        char type = map.getTileType(x, y, 1);
        assertEquals('G', type);
    }

    @Test
    public  void testGetTileType_Water(){
        //Exercise
        int size = 5;
        map.setMapSize(size);
        map.generateTileTypes();

        ArrayList<Pair<Integer, Integer>> tiles = map.getWaterTiles();
        Pair<Integer, Integer> waterTile = tiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        char type = map.getTileType(x, y, 1);
        assertEquals('W', type);
    }

    @Test
    public  void testGetTileType_Treasure(){
        //Exercise
        int size = 5;
        map.setMapSize(size);
        map.generateTileTypes();

        Pair<Integer, Integer> treasureTile = map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        char type = map.getTileType(x, y, 1);
        assertEquals('T', type);
    }

    @Test
    public  void testGetTileType_Invalid(){
        //Exercise
        int size = 5;
        map.setMapSize(size);
        map.generateTileTypes();

        int x = size+3;
        int y = size+4;

        char type = map.getTileType(x, y, 1);
        assertEquals('E', type);
    }
}
