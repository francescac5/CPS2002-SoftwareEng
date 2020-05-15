package edu.cps2002.mazegame.map;

import edu.cps2002.mazegame.utils.MapUtils;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class TestHazardousMap {

    public Map hazardousMap;

    @Before
    public void setup() {
        hazardousMap = new HazardousMap();
        hazardousMap.initMapCount();
    }

    @After
    public void tearDown() {
        MapUtils utils = new MapUtils();
        utils.deleteHTMLFiles();
        hazardousMap.resetMap();

        hazardousMap = null;
    }

//******** hazardousMap.setMapSize() tests ********\\

    @Test
    public void testSetMapSize_GreaterThanMinSize() {
        //Exercise
        int size = 8;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_MinSize() {
        //Exercise
        int size = 5;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_LessThanMinSize() {
        //Exercise
        int size = 2;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_MaxSize() {
        //Exercise
        int size = 50;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_GreaterThanMaxSize() {
        //Exercise
        int size = 53;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_NegativeSize() {
        //Exercise
        int size = -9;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, Map.getMapSize());
    }

    @Test
    public void testSetMapSize_ZeroSize() {
        //Exercise
        int size = 0;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, Map.getMapSize());
    }

//******** hazardousMap.setWaterPercentage() tests ********\\

    @Test
    public void testSetWaterPercentage_ValidMax(){
        //Exercise
        double expectedPercentage = 35;

        boolean result = hazardousMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(expectedPercentage, hazardousMap.waterPercentage, 0.0);
        assertTrue(result);
    }

    @Test
    public void testSetWaterPercentage_ValidLessThanMax(){
        //Exercise
        double expectedPercentage = 30;

        boolean result = hazardousMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(expectedPercentage, hazardousMap.waterPercentage, 0.0);
        assertTrue(result);
    }

    @Test
    public void testSetWaterPercentage_ValidMinimum(){
        //Exercise
        double expectedPercentage = 25;

        boolean result = hazardousMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(expectedPercentage, hazardousMap.waterPercentage, 0.0);
        assertTrue(result);
    }

    @Test
    public void testSetWaterPercentage_InvalidZero(){
        //Exercise
        double expectedPercentage = 0;

        boolean result = hazardousMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(-1, hazardousMap.waterPercentage, 0.0);
        assertFalse(result);
    }

    @Test
    public void testSetWaterPercentage_InvalidNegative(){
        //Exercise
        double expectedPercentage = -10;

        boolean result = hazardousMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(-1, hazardousMap.waterPercentage, 0.0);
        assertFalse(result);
    }

    @Test
    public void testSetWaterPercentage_InvalidGreaterThenMax(){
        //Exercise
        double expectedPercentage = 40;

        boolean result = hazardousMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(-1, hazardousMap.waterPercentage, 0.0);
        assertFalse(result);
    }

    @Test
    public void testSetWaterPercentage_InvalidLessThanMin(){
        //Exercise
        double expectedPercentage = 15;

        boolean result = hazardousMap.setWaterPercentage(expectedPercentage);

        //Assert
        assertEquals(-1, hazardousMap.waterPercentage, 0.0);
        assertFalse(result);
    }

//******** hazardousMap.calculateGrassPercentage() tests ********\\

    @Test
    public void testCalculateGrassPercentage_MaxWater(){
        //Exercise
        int mapSize = 5;
        hazardousMap.setMapSize(mapSize);

        double waterPercentage = 35;
        int tilePercentage = (1/mapSize)*100;

        double expectedGrassPercentage = 100 - waterPercentage - tilePercentage;

        hazardousMap.setWaterPercentage(waterPercentage);
        double actualGrassPercentage = hazardousMap.calculateGrassPercentage();

        //Assert
        assertEquals(expectedGrassPercentage, actualGrassPercentage, 0.0);
    }

    @Test
    public void testCalculateGrassPercentage_LessThanMaxWater(){
        //Exercise
        int mapSize = 5;
        hazardousMap.setMapSize(mapSize);

        double waterPercentage = 30;
        int tilePercentage = (1/mapSize)*100;

        double expectedGrassPercentage = 100 - waterPercentage - tilePercentage;

        hazardousMap.setWaterPercentage(waterPercentage);
        double actualGrassPercentage = hazardousMap.calculateGrassPercentage();

        //Assert
        assertEquals(expectedGrassPercentage, actualGrassPercentage, 0.0);
    }

    @Test
    public void testCalculateGrassPercentage_ValidMinimum(){
        //Exercise
        int mapSize = 5;
        hazardousMap.setMapSize(mapSize);

        double waterPercentage = 25;
        int tilePercentage = (1/mapSize)*100;

        double expectedGrassPercentage = 100 - waterPercentage - tilePercentage;

        hazardousMap.setWaterPercentage(waterPercentage);
        double actualGrassPercentage = hazardousMap.calculateGrassPercentage();

        //Assert
        assertEquals(expectedGrassPercentage, actualGrassPercentage, 0.0);
    }

//******** hazardousMap.generateTileTypes() tests ********\\

    @Test
    public void testGenerateTileTypes_MinSize() {
        //Exercise
        hazardousMap.setMapSize(5);

        hazardousMap.generateTileTypes();

        Map.Tiles[][] tiles = hazardousMap.getTiles();

        //Assert
        assertEquals(5, tiles.length);
    }

    @Test
    public void testGenerateTileTypes_MaxSize() {
        //Exercise
        hazardousMap.setMapSize(50);

        //generates map_player_0.html file
        hazardousMap.generateTileTypes();

        Map.Tiles[][] tiles = hazardousMap.getTiles();

        //Assert
        assertEquals(50, tiles.length);
    }

//******** hazardousMap.getTileType() tests ********\\

    @Test
    public void testGetTileType_Grass(){
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generateTileTypes();

        ArrayList<Pair<Integer, Integer>> tiles = hazardousMap.getGrassTiles();
        Pair<Integer, Integer> greenTile = tiles.get(0);
        int x = greenTile.getKey();
        int y = greenTile.getValue();

        //Assert
        char type = hazardousMap.getTileType(x, y);
        assertEquals('G', type);
    }

    @Test
    public void testGetTileType_Water(){
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generateTileTypes();

        ArrayList<Pair<Integer, Integer>> tiles = hazardousMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = tiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        //Assert
        char type = hazardousMap.getTileType(x, y);
        assertEquals('W', type);
    }

    @Test
    public void testGetTileType_Treasure(){
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generateTileTypes();

        Pair<Integer, Integer> treasureTile = Map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        //Assert
        char type = hazardousMap.getTileType(x, y);
        assertEquals('T', type);
    }

    @Test
    public void testGetTileType_Invalid(){
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generateTileTypes();

        int x = size+3;
        int y = size+4;

        //Assert
        char type = hazardousMap.getTileType(x, y);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_XLessThanZero(){
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generateTileTypes();

        int x = -1;
        int y = size-4;

        //Assert
        char type = hazardousMap.getTileType(x, y);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_YLessThanZero(){
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generateTileTypes();

        int x = size-3;
        int y = -1;

        //Assert
        char type = hazardousMap.getTileType(x, y);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_XEqualSize(){
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generateTileTypes();

        int y = size+4;

        //Assert
        char type = hazardousMap.getTileType(size, y);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_YEqualSize(){
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generateTileTypes();

        int x = size+3;

        //Assert
        char type = hazardousMap.getTileType(x, size);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_XGreaterThanSize(){
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generateTileTypes();

        int x = size+3;
        int y = size-4;

        //Assert
        char type = hazardousMap.getTileType(x, y);
        assertEquals('E', type);
    }

    @Test
    public void testGetTileType_Invalid_YGreaterThanSize(){
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generateTileTypes();

        int x = size-3;
        int y = size+4;

        //Assert
        char type = hazardousMap.getTileType(x, y);
        assertEquals('E', type);
    }


//******** hazardousMap.updateMap tests ********\\

    @Test
    public void testUpdateMap_XGreaterThanSize() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate();

        int playerNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        int x = size+3;
        int y = size-2;

        hazardousMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_YGreaterThanSize() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate();

        int playerNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        int x = size-3;
        int y = size+2;

        hazardousMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_XEqualToSize() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate();

        int playerNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        int y = size-2;

        hazardousMap.updateMap(size, y, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_YEqualToSize() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate();

        int playerNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        int x = size-3;

        hazardousMap.updateMap(x, size, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }


    @Test
    public void testUpdateMap_XLessThanZero() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate();

        int playerNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        int x = -1;
        int y = size+2;

        hazardousMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_YLessThanZero() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate();

        int playerNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        int x = size+3;
        int y = -1;

        hazardousMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_StayInSameTile(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate();

        int playerNum = hazardousMap.getMapCount();

        int initX = hazardousMap.getPlayerInitPositionX(playerNum);
        int initY = hazardousMap.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        hazardousMap.updateMap(initX, initY, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Grass(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate();

        int playerNum = hazardousMap.getMapCount();

        int initX = hazardousMap.getPlayerInitPositionX(playerNum);
        int initY = hazardousMap.getPlayerInitPositionY(playerNum);

        ArrayList<Pair<Integer, Integer>> grassTiles = hazardousMap.getGrassTiles();
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

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);
        Map.Tiles[][] postTiles;

        //Assert
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        hazardousMap.updateMap(x, y, playerNum);
        postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Water(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate();

        int playerNum = hazardousMap.getMapCount();
        ArrayList<Pair<Integer, Integer>> waterTiles = hazardousMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = waterTiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        int initX = hazardousMap.getPlayerInitPositionX(playerNum);
        int initY = hazardousMap.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        //ensuring position to be revealed is an init position or grey
        if(x == initX && y == initY){
            assertEquals(Map.Tiles.GRASS_PLAYER, prevTiles[x][y]);
        }else {
            assertEquals(Map.Tiles.GREY, prevTiles[x][y]);
        }

        //Exercise
        hazardousMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert player returned to init position and water tile is revealed
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[initX][initY]);
        assertEquals(Map.Tiles.WATER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Treasure(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate();

        int playerNum = hazardousMap.getMapCount();
        Pair<Integer, Integer> treasureTile = Map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        int initX = hazardousMap.getPlayerInitPositionX(playerNum);
        int initY = hazardousMap.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        //ensuring position to be revealed is an init position or grey
        if(x == hazardousMap.getPlayerInitPositionX(playerNum) && y == hazardousMap.getPlayerInitPositionY(playerNum)){
            assertEquals(Map.Tiles.GRASS_PLAYER, prevTiles[x][y]);
        }else {
            assertEquals(Map.Tiles.GREY, prevTiles[x][y]);
        }

        //Exercise
        hazardousMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.TREASURE, postTiles[x][y]);
    }

//******** hazardousMap.resetMap tests ********\\

    @Test
    public void testResetMap(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate();

        hazardousMap.resetMap();

        //Assert
        assertEquals(0, hazardousMap.getMapCount());

        assertEquals(0, hazardousMap.grassTiles.size());
        assertEquals(0, hazardousMap.waterTiles.size());
        assertNull(hazardousMap.treasureTile);

        assertEquals(0, hazardousMap.playerMaps.size());
        assertEquals(0, hazardousMap.initTiles.size());

        assertFalse(hazardousMap.tilesGenerated);

        assertEquals(-1, Map.getMapSize());
    }

//******** hazardousMap.generate tests ********\\

    @Test
    public void testGenerate_TilesGenerated_FalseBeforeGenerate(){
        //Exercise
        hazardousMap.setMapSize(5);

        //Assert
        assertFalse(hazardousMap.tilesGenerated);
    }

    @Test
    public void testGenerate_TilesGenerated_TrueAfterGenerate(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate();

        //Assert
        assertTrue(hazardousMap.tilesGenerated);
    }

    @Test
    public void testGenerate_TilesGenerated_TrueAfterMultipleGenerate(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate();

        //Assert
        assertTrue(hazardousMap.tilesGenerated);

        //Exercise
        hazardousMap.generate();

        //Assert
        assertTrue(hazardousMap.tilesGenerated);
    }
}
