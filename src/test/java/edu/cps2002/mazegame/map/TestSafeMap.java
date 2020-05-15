package edu.cps2002.mazegame.map;

import edu.cps2002.mazegame.utils.MapUtils;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class TestSafeMap {

    public Map safeMap;

    @Before
    public void setup() {
        safeMap = new SafeMap();
        safeMap.initMapCount();
    }

    @After

    public void tearDown() {
        MapUtils utils = new MapUtils();
        utils.deleteHTMLFiles();
        safeMap.resetMap();

        safeMap = null;
    }

//******** safeMap.setMapSize() tests ********\\

    @Test
    public void testSetMapSize_GreaterThanMinSize() {
        //Exercise
        int size = 8;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_MinSize() {
        //Exercise
        int size = 5;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_LessThanMinSize() {
        //Exercise
        int size = 2;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_MaxSize() {
        //Exercise
        int size = 50;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_GreaterThanMaxSize() {
        //Exercise
        int size = 53;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_NegativeSize() {
        //Exercise
        int size = -9;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_ZeroSize() {
        //Exercise
        int size = 0;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, Map.getMapSize());
    }

//******** safeMap.setWaterPercentage() tests ********\\

    @Test
    public void testSetWaterPercentage_ValidMax(){
        //Exercise
        double expectedPercentage = 10;

        boolean result = safeMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(expectedPercentage, safeMap.waterPercentage, 0.0);
        assertTrue(result);
    }

    @Test
    public void testSetWaterPercentage_ValidLessThanMax(){
        //Exercise
        double expectedPercentage = 5;

        boolean result = safeMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(expectedPercentage, safeMap.waterPercentage, 0.0);
        assertTrue(result);
    }

    @Test
    public void testSetWaterPercentage_ValidZero(){
        //Exercise
        double expectedPercentage = 0;

        boolean result = safeMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(expectedPercentage, safeMap.waterPercentage, 0.0);
        assertTrue(result);
    }

    @Test
    public void testSetWaterPercentage_InvalidNegative(){
        //Exercise
        double expectedPercentage = -10;

        boolean result = safeMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(-1, safeMap.waterPercentage, 0.0);
        assertFalse(result);
    }

    @Test
    public void testSetWaterPercentage_InvalidGreaterThenMax(){
        //Exercise
        double expectedPercentage = 15;

        boolean result = safeMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(-1, safeMap.waterPercentage, 0.0);
        assertFalse(result);
    }

//******** safeMap.calculateGrassPercentage() tests ********\\

    @Test
    public void testCalculateGrassPercentage_MaxWater(){
        //Exercise
        int mapSize = 5;
        safeMap.setMapSize(mapSize);

        double waterPercentage = 10;
        int tilePercentage = (1/mapSize)*100;

        double expectedGrassPercentage = 100 - waterPercentage - tilePercentage;

        safeMap.setWaterPercentage(waterPercentage);
        double actualGrassPercentage = safeMap.calculateGrassPercentage();

        //Assert
        assertEquals(expectedGrassPercentage, actualGrassPercentage, 0.0);
    }

    @Test
    public void testCalculateGrassPercentage_LessThanMaxWater(){
        //Exercise
        int mapSize = 5;
        safeMap.setMapSize(mapSize);

        double waterPercentage = 5;
        int tilePercentage = (1/mapSize)*100;

        double expectedGrassPercentage = 100 - waterPercentage - tilePercentage;

        safeMap.setWaterPercentage(waterPercentage);
        double actualGrassPercentage = safeMap.calculateGrassPercentage();

        //Assert
        assertEquals(expectedGrassPercentage, actualGrassPercentage, 0.0);
    }

    @Test
    public void testCalculateGrassPercentage_ZeroWater(){
        //Exercise
        int mapSize = 5;
        safeMap.setMapSize(mapSize);

        double waterPercentage = 0;
        int tilePercentage = (1/mapSize)*100;

        double expectedGrassPercentage = 100 - waterPercentage - tilePercentage;

        safeMap.setWaterPercentage(waterPercentage);
        double actualGrassPercentage = safeMap.calculateGrassPercentage();

        //Assert
        assertEquals(expectedGrassPercentage, actualGrassPercentage, 0.0);
    }

//******** safeMap.generateTileTypes() tests ********\\

    @Test
    public void testGenerateTileTypes_MinSize() {
        //Exercise
        safeMap.setMapSize(5);

        safeMap.generateTileTypes();

        Map.Tiles[][] tiles = safeMap.getTiles();

        //Assert
        assertEquals(5, tiles.length);
    }

    @Test
    public void testGenerateTileTypes_MaxSize() {
        //Exercise
        safeMap.setMapSize(50);

        //generates map_player_0.html file
        safeMap.generateTileTypes();

        Map.Tiles[][] tiles = safeMap.getTiles();

        //Assert
        assertEquals(50, tiles.length);
    }

//******** safeMap.getTileType() tests ********\\

    @Test
    public void testGetTileType_Grass(){
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generateTileTypes();

        ArrayList<Pair<Integer, Integer>> tiles = safeMap.getGrassTiles();
        Pair<Integer, Integer> greenTile = tiles.get(0);
        int x = greenTile.getKey();
        int y = greenTile.getValue();

        //Assert
        char type = safeMap.getTileType(x, y);
        assertEquals('G', type);
    }

    @Test
    public void testGetTileType_Water(){
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generateTileTypes();

        ArrayList<Pair<Integer, Integer>> tiles = safeMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = tiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        //Assert
        char type = safeMap.getTileType(x, y);
        assertEquals('W', type);
    }

    @Test
    public void testGetTileType_Treasure(){
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generateTileTypes();

        Pair<Integer, Integer> treasureTile = Map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        //Assert
        char type = safeMap.getTileType(x, y);
        assertEquals('T', type);
    }

    @Test
    public void testGetTileType_Invalid(){
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generateTileTypes();

        int x = size+3;
        int y = size+4;

        //Assert
        char type = safeMap.getTileType(x, y);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_XLessThanZero(){
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generateTileTypes();

        int x = -1;
        int y = size-4;

        //Assert
        char type = safeMap.getTileType(x, y);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_YLessThanZero(){
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generateTileTypes();

        int x = size-3;
        int y = -1;

        //Assert
        char type = safeMap.getTileType(x, y);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_XEqualSize(){
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generateTileTypes();

        int y = size+4;

        //Assert
        char type = safeMap.getTileType(size, y);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_YEqualSize(){
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generateTileTypes();

        int x = size+3;

        //Assert
        char type = safeMap.getTileType(x, size);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_XGreaterThanSize(){
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generateTileTypes();

        int x = size+3;
        int y = size-4;

        //Assert
        char type = safeMap.getTileType(x, y);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_YGreaterThanSize(){
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generateTileTypes();

        int x = size-3;
        int y = size+4;

        //Assert
        char type = safeMap.getTileType(x, y);
        assertEquals('E', type);
    }


//******** safeMap.updateMap tests ********\\

    @Test
    public void testUpdateMap_XGreaterThanSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate();

        int playerNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        int x = size+3;
        int y = size-2;

        safeMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_YGreaterThanSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate();

        int playerNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        int x = size-3;
        int y = size+2;

        safeMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_XEqualToSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate();

        int playerNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        int y = size-2;

        safeMap.updateMap(size, y, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_YEqualToSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate();

        int playerNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        int x = size-3;

        safeMap.updateMap(x, size, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }


    @Test
    public void testUpdateMap_XLessThanZero() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate();

        int playerNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        int x = -1;
        int y = size+2;

        safeMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_YLessThanZero() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate();

        int playerNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        int x = size+3;
        int y = -1;

        safeMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_StayInSameTile(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate();

        int playerNum = safeMap.getMapCount();

        int initX = safeMap.getPlayerInitPositionX(playerNum);
        int initY = safeMap.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        safeMap.updateMap(initX, initY, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Grass(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate();

        int playerNum = safeMap.getMapCount();

        int initX = safeMap.getPlayerInitPositionX(playerNum);
        int initY = safeMap.getPlayerInitPositionY(playerNum);

        ArrayList<Pair<Integer, Integer>> grassTiles = safeMap.getGrassTiles();
        Collections.shuffle(grassTiles);
        Pair<Integer, Integer> grassTile = grassTiles.get(0);

        int x = grassTile.getKey();
        int y = grassTile.getValue();

        //ensure new grass tile is not the initial tile
        if(x == initX && y == initY){
            grassTile = grassTiles.get(1);

            x = grassTile.getKey();
            y = grassTile.getValue();
        }

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);
        Map.Tiles[][] postTiles;

        //Assert
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        safeMap.updateMap(x, y, playerNum);
        postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Water(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate();

        int playerNum = safeMap.getMapCount();
        ArrayList<Pair<Integer, Integer>> waterTiles = safeMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = waterTiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        int initX = safeMap.getPlayerInitPositionX(playerNum);
        int initY = safeMap.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        //ensuring position to be revealed is an init position or grey
        if(x == initX && y == initY){
            assertEquals(Map.Tiles.GRASS_PLAYER, prevTiles[x][y]);
        }else {
            assertEquals(Map.Tiles.GREY, prevTiles[x][y]);
        }

        //Exercise
        safeMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert player returned to init position and water tile is revealed
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[initX][initY]);
        assertEquals(Map.Tiles.WATER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Treasure(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate();

        int playerNum = safeMap.getMapCount();
        Pair<Integer, Integer> treasureTile = Map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        int initX = safeMap.getPlayerInitPositionX(playerNum);
        int initY = safeMap.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        //ensuring position to be revealed is an init position or grey
        if(x == safeMap.getPlayerInitPositionX(playerNum) && y == safeMap.getPlayerInitPositionY(playerNum)){
            assertEquals(Map.Tiles.GRASS_PLAYER, prevTiles[x][y]);
        }else {
            assertEquals(Map.Tiles.GREY, prevTiles[x][y]);
        }

        //Exercise
        safeMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.TREASURE, postTiles[x][y]);
    }

//******** safeMap.resetMap tests ********\\

    @Test
    public void testResetMap(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate();

        safeMap.resetMap();

        //Assert
        assertEquals(0, safeMap.getMapCount());

        assertEquals(0, safeMap.grassTiles.size());
        assertEquals(0, safeMap.waterTiles.size());
        assertNull(safeMap.treasureTile);

        assertEquals(0, safeMap.playerMaps.size());
        assertEquals(0, safeMap.initTiles.size());

        assertFalse(safeMap.tilesGenerated);

        assertEquals(-1, Map.getMapSize());
    }

//******** safeMap.generate tests ********\\

    @Test
    public void testGenerate_TilesGenerated_FalseBeforeGenerate(){
        //Exercise
        safeMap.setMapSize(5);

        //Assert
        assertFalse(safeMap.tilesGenerated);
    }

    @Test
    public void testGenerate_TilesGenerated_TrueAfterGenerate(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate();

        //Assert
        assertTrue(safeMap.tilesGenerated);
    }

    @Test
    public void testGenerate_TilesGenerated_TrueAfterMultipleGenerate(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate();

        //Assert
        assertTrue(safeMap.tilesGenerated);

        //Exercise
        safeMap.generate();

        //Assert
        assertTrue(safeMap.tilesGenerated);
    }
}
