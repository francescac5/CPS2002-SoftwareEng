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
        hazardousMap = MapFactory.getInstance("H");
        hazardousMap.initMapCount();
    }

    @After
    public void tearDown() {
        MapUtils utils = new MapUtils();
        utils.deleteHTMLFiles();
        hazardousMap.resetMap();

        MapFactory.TearDown();
    }

//******** hazardousMap.setMapSize() tests ********\\

    @Test
    public void testSetMapSize_GreaterThanMinSize() {
        //Exercise
        int size = 8;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, hazardousMap.getMapSize());
    }

    @Test
    public void testSetMapSize_MinSize() {
        //Exercise
        int size = 5;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, hazardousMap.getMapSize());
    }

    @Test
    public void testSetMapSize_LessThanMinSize() {
        //Exercise
        int size = 2;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, hazardousMap.getMapSize());
    }

    @Test
    public void testSetMapSize_MaxSize() {
        //Exercise
        int size = 50;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, hazardousMap.getMapSize());
    }

    @Test
    public void testSetMapSize_GreaterThanMaxSize() {
        //Exercise
        int size = 53;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, hazardousMap.getMapSize());
    }

    @Test
    public void testSetMapSize_NegativeSize() {
        //Exercise
        int size = -9;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, hazardousMap.getMapSize());
    }

    @Test
    public void testSetMapSize_ZeroSize() {
        //Exercise
        int size = 0;
        boolean result = hazardousMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, hazardousMap.getMapSize());
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
        double size = (double)mapSize;
        double tilePercentage = (1/(size*size))*100;

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
        double size = (double)mapSize;
        double tilePercentage = (1/(size*size))*100;

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
        double size = (double)mapSize;
        double tilePercentage = (1/(size*size))*100;

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
        hazardousMap.setWaterPercentage(30);

        hazardousMap.generateTileTypes();

        Map.Tiles[][] tiles = hazardousMap.getTiles();

        //Assert
        assertEquals(5, tiles.length);
    }

    @Test
    public void testGenerateTileTypes_MaxSize() {
        //Exercise
        hazardousMap.setMapSize(50);
        hazardousMap.setWaterPercentage(30);

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
        hazardousMap.setWaterPercentage(30);
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
        hazardousMap.setWaterPercentage(30);
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
        hazardousMap.setWaterPercentage(30);
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
        hazardousMap.setWaterPercentage(30);
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
        hazardousMap.setWaterPercentage(30);
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
        hazardousMap.setWaterPercentage(30);
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
        hazardousMap.setWaterPercentage(30);
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
        hazardousMap.setWaterPercentage(30);
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
        hazardousMap.setWaterPercentage(30);
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
        hazardousMap.setWaterPercentage(30);
        hazardousMap.generateTileTypes();

        int x = size-3;
        int y = size+4;

        //Assert
        char type = hazardousMap.getTileType(x, y);
        assertEquals('E', type);
    }


//******** hazardousMap.updateMap tests ********\\

    @Test
    public void testUpdateMap_Individual_XGreaterThanSize() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate(0);

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
        hazardousMap.generate(0);

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
    public void testUpdateMap_Individual_XEqualToSize() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate(0);

        int playerNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        int y = size-2;

        hazardousMap.updateMap(size, y, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Individual_YEqualToSize() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate(0);

        int playerNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        int x = size-3;

        hazardousMap.updateMap(x, size, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }


    @Test
    public void testUpdateMap_Individual_XLessThanZero() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate(0);

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
    public void testUpdateMap_Individual_YLessThanZero() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate(0);

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
    public void testUpdateMap_Individual_StayInSameTile(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate(0);

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
    public void testUpdateMap_Individual_Grass(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate(0);

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
    public void testUpdateMap_Individual_Water(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate(0);

        int playerNum = hazardousMap.getMapCount();
        ArrayList<Pair<Integer, Integer>> waterTiles = hazardousMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = waterTiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        int initX = hazardousMap.getPlayerInitPositionX(playerNum);
        int initY = hazardousMap.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        //ensuring position to be revealed is a grey tile
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        hazardousMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert player returned to init position and water tile is revealed
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[initX][initY]);
        assertEquals(Map.Tiles.WATER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Individual_Treasure(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate(0);

        int playerNum = hazardousMap.getMapCount();
        Pair<Integer, Integer> treasureTile = Map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        int initX = hazardousMap.getPlayerInitPositionX(playerNum);
        int initY = hazardousMap.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        //ensuring position to be revealed is a grey tile
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        hazardousMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = hazardousMap.getPlayerMap(playerNum);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.TREASURE, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Team_XGreaterThanSize() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate(1);

        int teamNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        int x = size+3;
        int y = size-2;

        hazardousMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_YGreaterThanSize() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate(1);

        int teamNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        int x = size-3;
        int y = size+2;

        hazardousMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_XEqualToSize() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate(1);

        int teamNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        int y = size-2;

        hazardousMap.updateMap(size, y, teamNum, 0);
        Map.Tiles[][] postTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_YEqualToSize() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate(1);

        int teamNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        int x = size-3;

        hazardousMap.updateMap(x, size, teamNum, 0);
        Map.Tiles[][] postTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }


    @Test
    public void testUpdateMap_Team_XLessThanZero() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate(1);

        int teamNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        int x = -1;
        int y = size+2;

        hazardousMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_YLessThanZero() {
        //Exercise
        int size = 5;
        hazardousMap.setMapSize(size);
        hazardousMap.generate(1);

        int teamNum = hazardousMap.getMapCount();

        Map.Tiles[][] prevTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        int x = size+3;
        int y = -1;

        hazardousMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_StayInSameTile(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate(1);

        int teamNum = hazardousMap.getMapCount();

        int initX = hazardousMap.getPlayerInitPositionX(teamNum);
        int initY = hazardousMap.getPlayerInitPositionY(teamNum);

        Map.Tiles[][] prevTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        hazardousMap.updateMap(initX, initY, teamNum, 0);
        Map.Tiles[][] postTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_Grass(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate(1);

        int teamNum = hazardousMap.getMapCount();

        int initX = hazardousMap.getPlayerInitPositionX(teamNum);
        int initY = hazardousMap.getPlayerInitPositionY(teamNum);

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

        Map.Tiles[][] prevTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);
        Map.Tiles[][] postTiles;

        //Assert
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        hazardousMap.updateMap(x, y, teamNum, 0);
        postTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Team_Water(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.setWaterPercentage(30);
        hazardousMap.generateTileTypes();
        hazardousMap.setMapCount(1);
        hazardousMap.teamMaps.add(new ArrayList<>());
        hazardousMap.teamMaps.get(0).add(hazardousMap.generateInitMap());

        int teamNum = hazardousMap.getMapCount();
        ArrayList<Pair<Integer, Integer>> waterTiles = hazardousMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = waterTiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        int initX = hazardousMap.getPlayerInitPositionX(teamNum);
        int initY = hazardousMap.getPlayerInitPositionY(teamNum);

        Map.Tiles[][] prevTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        //ensuring position to be revealed is a grey tile
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        hazardousMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert player returned to init position and water tile is revealed
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[initX][initY]);
        assertEquals(Map.Tiles.WATER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Team_Treasure(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate(1);

        int teamNum = hazardousMap.getMapCount();
        Pair<Integer, Integer> treasureTile = Map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        int initX = hazardousMap.getPlayerInitPositionX(teamNum);
        int initY = hazardousMap.getPlayerInitPositionY(teamNum);

        Map.Tiles[][] prevTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        //ensuring position to be revealed is a grey tile
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        hazardousMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = hazardousMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.TREASURE, postTiles[x][y]);
    }

//******** hazardousMap.resetMap tests ********\\

    @Test
    public void testResetMap(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate(0);

        hazardousMap.resetMap();

        //Assert
        assertEquals(0, hazardousMap.getMapCount());

        assertEquals(0, hazardousMap.grassTiles.size());
        assertEquals(0, hazardousMap.waterTiles.size());
        assertNull(hazardousMap.treasureTile);

        assertEquals(0, hazardousMap.playerMaps.size());
        assertEquals(0, hazardousMap.teamMaps.size());
        assertEquals(0, hazardousMap.initTiles.size());

        assertFalse(hazardousMap.tilesGenerated);

        assertEquals(-1, hazardousMap.getMapSize());
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
        hazardousMap.generate(0);

        //Assert
        assertTrue(hazardousMap.tilesGenerated);
    }

    @Test
    public void testGenerate_TilesGenerated_TrueAfterMultipleGenerate(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.generate(0);

        //Assert
        assertTrue(hazardousMap.tilesGenerated);

        //Exercise
        hazardousMap.generate(0);

        //Assert
        assertTrue(hazardousMap.tilesGenerated);
    }

    @Test
    public void testGenerate_PlayerMapsInitialised_Individual(){
        //Exercise
        hazardousMap.setMapSize(5);

        //Assert
        assertEquals(0, hazardousMap.playerMaps.size());

        //Exercise
        hazardousMap.generate(0);

        //Assert
        assertEquals(1, hazardousMap.playerMaps.size());

        //Exercise
        hazardousMap.generate(0);
        assertEquals(2, hazardousMap.playerMaps.size());

        assertEquals(0, hazardousMap.teamMaps.size());
    }

    @Test
    public void testGenerate_PlayerMapsInitialised_Team(){
        //Exercise
        hazardousMap.setMapSize(5);

        //Assert
        assertEquals(0, hazardousMap.teamMaps.size());

        //Exercise
        hazardousMap.generate(5);

        //Assert
        assertEquals(1, hazardousMap.teamMaps.size());
        assertEquals(5, hazardousMap.teamMaps.get(0).size());

        //Exercise
        hazardousMap.generate(6);
        assertEquals(2, hazardousMap.teamMaps.size());
        assertEquals(6, hazardousMap.teamMaps.get(1).size());

        assertEquals(0, hazardousMap.playerMaps.size());
    }

//******** hazardousMap.generateInitMapDeepCopy()tests ********\\

    @Test
    public void testGenerateInitMapDeepCopy_1Copy(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.setUpMapTiles();

        Map.Tiles[][] initMap = hazardousMap.generateInitMap();
        Map.Tiles[][] initMapCopy = hazardousMap.generateInitMapDeepCopy(initMap);

        //Assert
        assertNotEquals(initMapCopy, initMap);

        initMap[0][1] = Map.Tiles.WATER;
        initMapCopy[0][1] = Map.Tiles.GRASS_PLAYER;

        assertEquals(Map.Tiles.WATER, initMap[0][1]);
        assertEquals(Map.Tiles.GRASS_PLAYER, initMapCopy[0][1]);
    }

    @Test
    public void testGenerateInitMapDeepCopy_2Copies(){
        //Exercise
        hazardousMap.setMapSize(5);
        hazardousMap.setUpMapTiles();

        Map.Tiles[][] initMap = hazardousMap.generateInitMap();
        Map.Tiles[][] initMapCopy = hazardousMap.generateInitMapDeepCopy(initMap);
        Map.Tiles[][] initMapCopy2 = hazardousMap.generateInitMapDeepCopy(initMap);

        //Assert
        assertNotEquals(initMapCopy, initMap);
        assertNotEquals(initMapCopy2, initMap);
        assertNotEquals(initMapCopy, initMapCopy2);

        initMap[0][1] = Map.Tiles.WATER;
        initMapCopy[0][1] = Map.Tiles.GRASS_PLAYER;
        initMapCopy2[0][1] = Map.Tiles.TREASURE;

        assertEquals(Map.Tiles.WATER, initMap[0][1]);
        assertEquals(Map.Tiles.GRASS_PLAYER, initMapCopy[0][1]);
        assertEquals(Map.Tiles.TREASURE, initMapCopy2[0][1]);
    }

    
//******** hazardousMap.revealTile()tests ********\\

    @Test
    public void testRevealTileTeam__PlayerOnTile(){
        //Exercise
        hazardousMap.setMapSize(8);
        hazardousMap.generate(1);

        //playerNum equivalent to teamNum in parameter
        int x = hazardousMap.getPlayerInitPositionX(1);
        int y = hazardousMap.getPlayerInitPositionY(1);

        Map.Tiles[][] playerMap = hazardousMap.getTeamPlayerMap(1, 0);

        //Assert
        assertEquals(Map.Tiles.GRASS_PLAYER, playerMap[x][y]);

        //Exercise
        hazardousMap.revealTile(x, y, 1, 0);

        //Assert
        playerMap = hazardousMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.GRASS_PLAYER, playerMap[x][y]);
    }

    @Test
    public void testRevealTileTeam_GRASS(){
        //Exercise
        hazardousMap.setMapSize(8);
        hazardousMap.generate(1);

        ArrayList<Pair<Integer, Integer>> tiles = hazardousMap.getGrassTiles();
        Pair<Integer, Integer> greenTile = tiles.get(0);
        int x = greenTile.getKey();
        int y = greenTile.getValue();

        int count = 1;
        while(x == hazardousMap.getPlayerInitPositionX(1) || y == hazardousMap.getPlayerInitPositionY(1)) {
            x = greenTile.getKey();
            y = greenTile.getValue();
            greenTile = tiles.get(count);
            count++;
        }

        Map.Tiles[][] playerMap = hazardousMap.getTeamPlayerMap(1, 0);

        //Assert
        assertEquals(Map.Tiles.GREY, playerMap[x][y]);

        //Exercise
        hazardousMap.revealTile(x, y, 1, 0);

        //Assert
        playerMap = hazardousMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.GRASS_PLAYER, playerMap[hazardousMap.getPlayerInitPositionX(1)][hazardousMap.getPlayerInitPositionY(1)]);
        assertEquals(Map.Tiles.GRASS, playerMap[x][y]);
    }

    @Test
    public void testRevealTileTeam_WATER(){
        //Exercise
        hazardousMap.setMapSize(8);
        hazardousMap.generate(1);

        ArrayList<Pair<Integer, Integer>> tiles = hazardousMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = tiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        Map.Tiles[][] playerMap = hazardousMap.getTeamPlayerMap(1, 0);

        //Assert
        assertEquals(Map.Tiles.GREY, playerMap[x][y]);

        //Exercise
        hazardousMap.revealTile(x, y, 1, 0);

        //Assert
        playerMap = hazardousMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.GRASS_PLAYER, playerMap[hazardousMap.getPlayerInitPositionX(1)][hazardousMap.getPlayerInitPositionY(1)]);
        assertEquals(Map.Tiles.WATER, playerMap[x][y]);
    }

    @Test
    public void testRevealTileTeam_TREASURE(){
        //Exercise
        hazardousMap.setMapSize(8);
        hazardousMap.generate(1);

        Pair<Integer, Integer> tile = hazardousMap.getTreasureTile();
        int x = tile.getKey();
        int y = tile.getValue();

        Map.Tiles[][] playerMap = hazardousMap.getTeamPlayerMap(1, 0);

        //Assert
        assertEquals(Map.Tiles.GREY, playerMap[x][y]);

        //Exercise
        hazardousMap.revealTile(x, y, 1, 0);

        //Assert
        playerMap = hazardousMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.GRASS, playerMap[hazardousMap.getPlayerInitPositionX(1)][hazardousMap.getPlayerInitPositionY(1)]);
        assertEquals(Map.Tiles.TREASURE, playerMap[x][y]);
    }

    @Test
    public void testRevealTileTeam_AlreadyRevealed_Grass(){
        //Exercise
        hazardousMap.setMapSize(8);
        hazardousMap.generate(1);

        ArrayList<Pair<Integer, Integer>> tiles = hazardousMap.getGrassTiles();
        Pair<Integer, Integer> greenTile = tiles.get(0);
        int x = greenTile.getKey();
        int y = greenTile.getValue();

        int count = 1;
        while(x == hazardousMap.getPlayerInitPositionX(1) || y == hazardousMap.getPlayerInitPositionY(1)) {
            greenTile = tiles.get(count);
            x = greenTile.getKey();
            y = greenTile.getValue();
            count++;
        }

        //Exercise
        hazardousMap.revealTile(x, y, 1, 0);
        hazardousMap.revealTile(x, y, 1, 0);

        //Assert
        Map.Tiles[][] playerMap = hazardousMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.GRASS, playerMap[x][y]);
    }

    @Test
    public void testRevealTileTeam_AlreadyRevealed_Water(){
        //Exercise
        hazardousMap.setMapSize(8);
        hazardousMap.generate(1);

        ArrayList<Pair<Integer, Integer>> tiles = hazardousMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = tiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        //Exercise
        hazardousMap.revealTile(x, y, 1, 0);
        hazardousMap.revealTile(x, y, 1, 0);

        //Assert
        Map.Tiles[][] playerMap = hazardousMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.WATER, playerMap[x][y]);
    }
}
