package edu.cps2002.mazegame.map;

import edu.cps2002.utils.MapUtils;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;

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
        MapUtils utils = new MapUtils();
        utils.deleteHTMLFiles();

        map = null;
    }

//******** map.setMapSize() tests ********\\

    @Test
    public void testSetMapSize_GreaterThanMinSize() {
        //Exercise
        int size = 8;
        boolean result = map.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, map.getMapSize());
    }

    @Test
    public void testSetMapSize_MinSize() {
        //Exercise
        int size = 5;
        boolean result = map.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, map.getMapSize());
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

        map.generateTileTypes();

        Map.Tiles[][] tiles = map.getTiles();

        //Assert
        assertEquals(5, tiles.length);
    }

    @Test
    public void testGenerateTileTypes_MaxSize() {
        //Exercise
        map.setMapSize(50);

        //generates map_player_0.html file
        map.generateTileTypes();

        Map.Tiles[][] tiles = map.getTiles();

        //Assert
        assertEquals(50, tiles.length);
    }

//******** map.getTileType() tests ********\\

    @Test
    public void testGetTileType_Grass(){
        //Exercise
        int size = 5;
        map.setMapSize(size);
        map.generateTileTypes();

        ArrayList<Pair<Integer, Integer>> tiles = map.getGrassTiles();
        Pair<Integer, Integer> greenTile = tiles.get(0);
        int x = greenTile.getKey();
        int y = greenTile.getValue();

        //Assert
        char type = map.getTileType(x, y);
        assertEquals('G', type);
    }

    @Test
    public void testGetTileType_Water(){
        //Exercise
        int size = 5;
        map.setMapSize(size);
        map.generateTileTypes();

        ArrayList<Pair<Integer, Integer>> tiles = map.getWaterTiles();
        Pair<Integer, Integer> waterTile = tiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        //Assert
        char type = map.getTileType(x, y);
        assertEquals('W', type);
    }

    @Test
    public void testGetTileType_Treasure(){
        //Exercise
        int size = 5;
        map.setMapSize(size);
        map.generateTileTypes();

        Pair<Integer, Integer> treasureTile = map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        //Assert
        char type = map.getTileType(x, y);
        assertEquals('T', type);
    }

    @Test
    public void testGetTileType_Invalid(){
        //Exercise
        int size = 5;
        map.setMapSize(size);
        map.generateTileTypes();

        int x = size+3;
        int y = size+4;

        //Assert
        char type = map.getTileType(x, y);
        assertEquals('E', type);
    }

//******** map.updateMap tests ********\\

    @Test
    public void testUpdateMap_Grass(){
        //Exercise
        map.setMapSize(5);
        map.generate();

        int playerNum = map.getMapCount();

        ArrayList<Pair<Integer, Integer>> grassTiles = map.getGrassTiles();
        Collections.shuffle(grassTiles);
        Pair<Integer, Integer> grassTile = grassTiles.get(0);
        int x = grassTile.getKey();
        int y = grassTile.getValue();

        int initX = map.getPlayerInitPositionX(playerNum);
        int initY = map.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = map.getPlayerMap(playerNum);

        //Assert
        //ensuring position to be revealed is an init position or grey
        if(x == initX && y == initY){
            assertEquals(Map.Tiles.GRASS_PLAYER, prevTiles[x][y]);
        }else {
            assertEquals(Map.Tiles.GREY, prevTiles[x][y]);
        }

        //Exercise
        map.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = map.getPlayerMap(playerNum);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Water(){
        //Exercise
        map.setMapSize(5);
        map.generate();

        int playerNum = map.getMapCount();
        ArrayList<Pair<Integer, Integer>> waterTiles = map.getWaterTiles();
        Pair<Integer, Integer> waterTile = waterTiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        int initX = map.getPlayerInitPositionX(playerNum);
        int initY = map.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = map.getPlayerMap(playerNum);

        //Assert
        //ensuring position to be revealed is an init position or grey
        if(x == initX && y == initY){
            assertEquals(Map.Tiles.GRASS_PLAYER, prevTiles[x][y]);
        }else {
            assertEquals(Map.Tiles.GREY, prevTiles[x][y]);
        }

        //Exercise
        map.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = map.getPlayerMap(playerNum);

        //Assert player returned to init position and water tile is revealed
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[initX][initY]);
        assertEquals(Map.Tiles.WATER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Treasure(){
        //Exercise
        map.setMapSize(5);
        map.generate();

        int playerNum = map.getMapCount();
        Pair<Integer, Integer> treasureTile = map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        int initX = map.getPlayerInitPositionX(playerNum);
        int initY = map.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = map.getPlayerMap(playerNum);

        //Assert
        //ensuring position to be revealed is an init position or grey
        if(x == map.getPlayerInitPositionX(playerNum) && y == map.getPlayerInitPositionY(playerNum)){
            assertEquals(Map.Tiles.GRASS_PLAYER, prevTiles[x][y]);
        }else {
            assertEquals(Map.Tiles.GREY, prevTiles[x][y]);
        }

        //Exercise
        map.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = map.getPlayerMap(playerNum);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.TREASURE, postTiles[x][y]);
    }

    //******** map.resetMap tests ********\\

    @Test
    public void testResetMap(){
        //Exercise
        map.setMapSize(5);
        map.generate();

        map.resetMap();

        //Assert
        assertEquals(0, map.getMapCount());

        assertEquals(0, map.grassTiles.size());
        assertEquals(0, map.waterTiles.size());
        assertNull(map.treasureTile);

        assertEquals(0, map.playerMaps.size());
        assertEquals(0, map.initTiles.size());

        assertFalse(map.tilesGenerated);

        assertEquals(-1, map.getMapSize());
    }
}
