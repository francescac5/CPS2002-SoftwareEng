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
        safeMap = MapFactory.getInstance("S");
        safeMap.initMapCount();
    }

    @After

    public void tearDown() {
        MapUtils utils = new MapUtils();
        utils.deleteHTMLFiles();
        safeMap.resetMap();

        MapFactory.TearDown();
    }

//******** safeMap.setMapSize() tests ********\\

    @Test
    public void testSetMapSize_GreaterThanMinSize() {
        //Exercise
        int size = 8;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, safeMap.getMapSize());
    }

    @Test
    public void testSetMapSize_MinSize() {
        //Exercise
        int size = 5;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, safeMap.getMapSize());
    }

    @Test
    public void testSetMapSize_LessThanMinSize() {
        //Exercise
        int size = 2;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, safeMap.getMapSize());
    }

    @Test
    public void testSetMapSize_MaxSize() {
        //Exercise
        int size = 50;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertTrue(result);
        assertEquals(size, safeMap.getMapSize());
    }

    @Test
    public void testSetMapSize_GreaterThanMaxSize() {
        //Exercise
        int size = 53;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, safeMap.getMapSize());
    }

    @Test
    public void testSetMapSize_NegativeSize() {
        //Exercise
        int size = -9;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, safeMap.getMapSize());
    }

    @Test
    public void testSetMapSize_ZeroSize() {
        //Exercise
        int size = 0;
        boolean result = safeMap.setMapSize(size);

        //Assert
        assertFalse(result);
        assertEquals(-1, safeMap.getMapSize());
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
        double size = (double)mapSize;
        double tilePercentage = (1/(size*size))*100;

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
        double size = (double)mapSize;
        double tilePercentage = (1/(size*size))*100;

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
        double size = (double)mapSize;
        double tilePercentage = (1/(size*size))*100;

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
        safeMap.setWaterPercentage(5);

        safeMap.generateTileTypes();

        Map.Tiles[][] tiles = safeMap.getTiles();

        //Assert
        assertEquals(5, tiles.length);
    }

    @Test
    public void testGenerateTileTypes_MaxSize() {
        //Exercise
        safeMap.setMapSize(50);
        safeMap.setWaterPercentage(5);

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
        safeMap.setWaterPercentage(5);
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
        safeMap.setWaterPercentage(5);
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
        safeMap.setWaterPercentage(5);
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
        safeMap.setWaterPercentage(5);
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
        safeMap.setWaterPercentage(5);
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
        safeMap.setWaterPercentage(5);
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
        safeMap.setWaterPercentage(5);
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
        safeMap.setWaterPercentage(5);
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
        safeMap.setWaterPercentage(5);
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
        safeMap.setWaterPercentage(5);
        safeMap.generateTileTypes();

        int x = size-3;
        int y = size+4;

        //Assert
        char type = safeMap.getTileType(x, y);
        assertEquals('E', type);
    }


//******** safeMap.updateMap tests ********\\

    @Test
    public void testUpdateMap_Individual_XGreaterThanSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(0);

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
    public void testUpdateMap_Individual_YGreaterThanSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(0);

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
    public void testUpdateMap_Individual_XEqualToSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(0);

        int playerNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        int y = size-2;

        safeMap.updateMap(size, y, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Individual_YEqualToSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(0);

        int playerNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        int x = size-3;

        safeMap.updateMap(x, size, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }


    @Test
    public void testUpdateMap_Individual_XLessThanZero() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(0);

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
    public void testUpdateMap_Individual_YLessThanZero() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(0);

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
    public void testUpdateMap_Individual_StayInSameTile(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate(0);

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
    public void testUpdateMap_Individual_Grass(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate(0);

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
    public void testUpdateMap_Individual_Water(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.setWaterPercentage(5);
        safeMap.generateTileTypes();
        safeMap.setMapCount(1);
        safeMap.playerMaps.add(safeMap.generateInitMap());

        int playerNum = safeMap.getMapCount();
        ArrayList<Pair<Integer, Integer>> waterTiles = safeMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = waterTiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        int initX = safeMap.getPlayerInitPositionX(playerNum);
        int initY = safeMap.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        //ensuring position to be revealed is a grey tile
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        safeMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert player returned to init position and water tile is revealed
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[initX][initY]);
        assertEquals(Map.Tiles.WATER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Individual_Treasure(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate(0);

        int playerNum = safeMap.getMapCount();
        Pair<Integer, Integer> treasureTile = Map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        int initX = safeMap.getPlayerInitPositionX(playerNum);
        int initY = safeMap.getPlayerInitPositionY(playerNum);

        Map.Tiles[][] prevTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        //ensuring position to be revealed is a grey tile
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        safeMap.updateMap(x, y, playerNum);
        Map.Tiles[][] postTiles = safeMap.getPlayerMap(playerNum);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.TREASURE, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Team_XGreaterThanSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(1);

        int teamNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        int x = size+3;
        int y = size-2;

        safeMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_YGreaterThanSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(1);

        int teamNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        int x = size-3;
        int y = size+2;

        safeMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_XEqualToSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(1);

        int teamNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        int y = size-2;

        safeMap.updateMap(size, y, teamNum, 0);
        Map.Tiles[][] postTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_YEqualToSize() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(1);

        int teamNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        int x = size-3;

        safeMap.updateMap(x, size, teamNum, 0);
        Map.Tiles[][] postTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }


    @Test
    public void testUpdateMap_Team_XLessThanZero() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(1);

        int teamNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        int x = -1;
        int y = size+2;

        safeMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_YLessThanZero() {
        //Exercise
        int size = 5;
        safeMap.setMapSize(size);
        safeMap.generate(1);

        int teamNum = safeMap.getMapCount();

        Map.Tiles[][] prevTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        int x = size+3;
        int y = -1;

        safeMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_StayInSameTile(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate(1);

        int teamNum = safeMap.getMapCount();

        int initX = safeMap.getPlayerInitPositionX(teamNum);
        int initY = safeMap.getPlayerInitPositionY(teamNum);

        Map.Tiles[][] prevTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        safeMap.updateMap(initX, initY, teamNum, 0);
        Map.Tiles[][] postTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertArrayEquals(prevTiles, postTiles);
    }

    @Test
    public void testUpdateMap_Team_Grass(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate(1);

        int teamNum = safeMap.getMapCount();

        int initX = safeMap.getPlayerInitPositionX(teamNum);
        int initY = safeMap.getPlayerInitPositionY(teamNum);

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

        Map.Tiles[][] prevTiles = safeMap.getTeamPlayerMap(teamNum, 0);
        Map.Tiles[][] postTiles;

        //Assert
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        safeMap.updateMap(x, y, teamNum, 0);
        postTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Team_Water(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.setWaterPercentage(5);
        safeMap.generateTileTypes();
        safeMap.setMapCount(1);
        safeMap.teamMaps.add(new ArrayList<>());
        safeMap.teamMaps.get(0).add(safeMap.generateInitMap());

        int teamNum = safeMap.getMapCount();
        ArrayList<Pair<Integer, Integer>> waterTiles = safeMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = waterTiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        int initX = safeMap.getPlayerInitPositionX(teamNum);
        int initY = safeMap.getPlayerInitPositionY(teamNum);

        Map.Tiles[][] prevTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        //ensuring position to be revealed is a grey tile
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        safeMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert player returned to init position and water tile is revealed
        assertEquals(Map.Tiles.GRASS_PLAYER, postTiles[initX][initY]);
        assertEquals(Map.Tiles.WATER, postTiles[x][y]);
    }

    @Test
    public void testUpdateMap_Team_Treasure(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate(1);

        int teamNum = safeMap.getMapCount();
        Pair<Integer, Integer> treasureTile = Map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        int initX = safeMap.getPlayerInitPositionX(teamNum);
        int initY = safeMap.getPlayerInitPositionY(teamNum);

        Map.Tiles[][] prevTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        //ensuring position to be revealed is a grey tile
        assertEquals(Map.Tiles.GREY, prevTiles[x][y]);

        //Exercise
        safeMap.updateMap(x, y, teamNum, 0);
        Map.Tiles[][] postTiles = safeMap.getTeamPlayerMap(teamNum, 0);

        //Assert
        assertEquals(Map.Tiles.GRASS, postTiles[initX][initY]);
        assertEquals(Map.Tiles.TREASURE, postTiles[x][y]);
    }

//******** safeMap.resetMap tests ********\\

    @Test
    public void testResetMap(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate(0);

        safeMap.resetMap();

        //Assert
        assertEquals(0, safeMap.getMapCount());

        assertEquals(0, safeMap.grassTiles.size());
        assertEquals(0, safeMap.waterTiles.size());
        assertNull(safeMap.treasureTile);

        assertEquals(0, safeMap.playerMaps.size());
        assertEquals(0, safeMap.teamMaps.size());
        assertEquals(0, safeMap.initTiles.size());

        assertFalse(safeMap.tilesGenerated);

        assertEquals(-1, safeMap.getMapSize());
    }

//******** safeMap.generate()tests ********\\

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
        safeMap.generate(0);

        //Assert
        assertTrue(safeMap.tilesGenerated);
    }

    @Test
    public void testGenerate_TilesGenerated_TrueAfterMultipleGenerate(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.generate(0);

        //Assert
        assertTrue(safeMap.tilesGenerated);

        //Exercise
        safeMap.generate(0);

        //Assert
        assertTrue(safeMap.tilesGenerated);
    }

    @Test
    public void testGenerate_PlayerMapsInitialised_Individual(){
        //Exercise
        safeMap.setMapSize(5);

        //Assert
        assertEquals(0, safeMap.playerMaps.size());

        //Exercise
        safeMap.generate(0);

        //Assert
        assertEquals(1, safeMap.playerMaps.size());

        //Exercise
        safeMap.generate(0);
        assertEquals(2, safeMap.playerMaps.size());

        assertEquals(0, safeMap.teamMaps.size());
    }

    @Test
    public void testGenerate_PlayerMapsInitialised_Team(){
        //Exercise
        safeMap.setMapSize(5);

        //Assert
        assertEquals(0, safeMap.teamMaps.size());

        //Exercise
        safeMap.generate(5);

        //Assert
        assertEquals(1, safeMap.teamMaps.size());
        assertEquals(5, safeMap.teamMaps.get(0).size());

        //Exercise
        safeMap.generate(6);

        //Assert
        assertEquals(2, safeMap.teamMaps.size());
        assertEquals(6, safeMap.teamMaps.get(1).size());

        assertEquals(0, safeMap.playerMaps.size());
    }

//******** safeMap.generateInitMapDeepCopy()tests ********\\

    @Test
    public void testGenerateInitMapDeepCopy_1Copy(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.setUpMapTiles();

        Map.Tiles[][] initMap = safeMap.generateInitMap();
        Map.Tiles[][] initMapCopy = safeMap.generateInitMapDeepCopy(initMap);

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
        safeMap.setMapSize(5);
        safeMap.setUpMapTiles();

        Map.Tiles[][] initMap = safeMap.generateInitMap();
        Map.Tiles[][] initMapCopy = safeMap.generateInitMapDeepCopy(initMap);
        Map.Tiles[][] initMapCopy2 = safeMap.generateInitMapDeepCopy(initMap);

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

//******** safeMap.revealTile()tests ********\\

    @Test
    public void testRevealTileTeam__PlayerOnTile(){
        //Exercise
        safeMap.setMapSize(8);
        safeMap.generate(1);

        int x = safeMap.getPlayerInitPositionX(1);
        int y = safeMap.getPlayerInitPositionY(1);

        Map.Tiles[][] playerMap = safeMap.getTeamPlayerMap(1, 0);

        //Assert
        assertEquals(Map.Tiles.GRASS_PLAYER, playerMap[x][y]);

        //Exercise
        safeMap.revealTile(x, y, 1, 0);

        //Assert
        playerMap = safeMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.GRASS_PLAYER, playerMap[x][y]);
    }

    @Test
    public void testRevealTileTeam_GRASS(){
        //Exercise
        safeMap.setMapSize(8);
        safeMap.generate(1);

        ArrayList<Pair<Integer, Integer>> tiles = safeMap.getGrassTiles();
        Pair<Integer, Integer> greenTile = tiles.get(0);
        int x = greenTile.getKey();
        int y = greenTile.getValue();
        int count = 1;

        while(x == safeMap.getPlayerInitPositionX(1) || y == safeMap.getPlayerInitPositionY(1)) {
            x = greenTile.getKey();
            y = greenTile.getValue();
            greenTile = tiles.get(count);
            count++;
        }

        Map.Tiles[][] playerMap = safeMap.getTeamPlayerMap(1, 0);

        //Assert
        assertEquals(Map.Tiles.GREY, playerMap[x][y]);

        //Exercise
        safeMap.revealTile(x, y, 1, 0);

        //Assert
        playerMap = safeMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.GRASS_PLAYER, playerMap[safeMap.getPlayerInitPositionX(1)][safeMap.getPlayerInitPositionY(1)]);
        assertEquals(Map.Tiles.GRASS, playerMap[x][y]);
    }

    @Test
    public void testRevealTileTeam_WATER(){
        //Exercise
        safeMap.setMapSize(20);
        safeMap.generate(1);

        ArrayList<Pair<Integer, Integer>> tiles = safeMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = tiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        Map.Tiles[][] playerMap = safeMap.getTeamPlayerMap(1, 0);

        //Assert
        assertEquals(Map.Tiles.GREY, playerMap[x][y]);

        //Exercise
        safeMap.revealTile(x, y, 1, 0);

        //Assert
        playerMap = safeMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.GRASS_PLAYER, playerMap[safeMap.getPlayerInitPositionX(1)][safeMap.getPlayerInitPositionY(1)]);
        assertEquals(Map.Tiles.WATER, playerMap[x][y]);
    }

    @Test
    public void testRevealTileTeam_TREASURE(){
        //Exercise
        safeMap.setMapSize(8);
        safeMap.generate(1);

        Pair<Integer, Integer> tile = safeMap.getTreasureTile();
        int x = tile.getKey();
        int y = tile.getValue();

        Map.Tiles[][] playerMap = safeMap.getTeamPlayerMap(1, 0);

        //Assert
        assertEquals(Map.Tiles.GREY, playerMap[x][y]);

        //Exercise
        safeMap.revealTile(x, y, 1, 0);

        //Assert
        playerMap = safeMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.GRASS, playerMap[safeMap.getPlayerInitPositionX(1)][safeMap.getPlayerInitPositionY(1)]);
        assertEquals(Map.Tiles.TREASURE, playerMap[x][y]);
    }

    @Test
    public void testRevealTileTeam_AlreadyRevealed_Grass(){
        //Exercise
        safeMap.setMapSize(8);
        safeMap.generate(1);

        ArrayList<Pair<Integer, Integer>> tiles = safeMap.getGrassTiles();
        Pair<Integer, Integer> greenTile = tiles.get(0);

        int x = greenTile.getKey();
        int y = greenTile.getValue();
        int count = 1;

        while(x == safeMap.getPlayerInitPositionX(1) || y == safeMap.getPlayerInitPositionY(1)) {
            x = greenTile.getKey();
            y = greenTile.getValue();
            greenTile = tiles.get(count);
            count++;
        }

        //Exercise
        safeMap.revealTile(x, y, 1, 0);
        safeMap.revealTile(x, y, 1, 0);

        //Assert
        Map.Tiles[][] playerMap = safeMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.GRASS, playerMap[x][y]);
    }

    @Test
    public void testRevealTileTeam_AlreadyRevealed_Water(){
        //Exercise
        safeMap.setMapSize(5);
        safeMap.setWaterPercentage(5);
        safeMap.generateTileTypes();
        safeMap.setMapCount(1);
        safeMap.teamMaps.add(new ArrayList<>());
        safeMap.teamMaps.get(0).add(safeMap.generateInitMap());

        ArrayList<Pair<Integer, Integer>> tiles = safeMap.getWaterTiles();
        Pair<Integer, Integer> waterTile = tiles.get(0);
        int x = waterTile.getKey();
        int y = waterTile.getValue();

        //Exercise
        safeMap.revealTile(x, y, 1, 0);
        safeMap.revealTile(x, y, 1, 0);

        //Assert
        Map.Tiles[][] playerMap = safeMap.getTeamPlayerMap(1, 0);
        assertEquals(Map.Tiles.WATER, playerMap[x][y]);
    }
}
