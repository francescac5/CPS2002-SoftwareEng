package edu.cps2002.mazegame.map;

import edu.cps2002.mazegame.utils.MapUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Map {

     public Map(){
    }

    //Enum representing types of tiles
    public enum Tiles{
        GRASS,
        WATER,
        TREASURE, //1 tile
        GREY,
        GRASS_PLAYER
    }

    private MapUtils util = new MapUtils();

    //map size
    private static int size = -1;

    //number of map copies
    private static int mapCount;

    //array which stores tile types for the map
    private Tiles[][] mapTiles;

    //flag to determine that tile types are generated only once
    protected boolean tilesGenerated = false;

    //stores percentage of water tiles in map
    protected double waterPercentage = -1;

    //stores x and y pairs for all grass tiles
    protected ArrayList<Pair<Integer,Integer>> grassTiles = new ArrayList<>();

    //stores x and y pairs for all water tiles
    protected static ArrayList<Pair<Integer,Integer>> waterTiles = new ArrayList<>();

    //stores x and y pair for the treasure tiles
    protected static Pair<Integer, Integer> treasureTile;

    //stores a map representation for each player
    protected ArrayList<Tiles[][]> playerMaps = new ArrayList<>();

    //stores a map representation for each player in each team
    protected ArrayList<ArrayList<Tiles[][]>> teamMaps = new ArrayList<>();

    //stores x and y pairs for the players' initial tiles
    protected ArrayList<Pair<Integer,Integer>> initTiles = new ArrayList<>();

    //getters
    //returns size of map set by user
    public int getMapSize() {
        return size;
    }

    //returns map of a particular player in individual play
    protected Tiles[][] getPlayerMap(int playerNum){
        return playerMaps.get(playerNum-1);
    }

    //returns map of a particular player in collaborative play
    public Tiles[][] getTeamPlayerMap(int teamNum, int playerNum){
        return teamMaps.get(teamNum-1).get(playerNum);
    }

    //returns x-coordinate of a particular player's initial tile
    public Integer getPlayerInitPositionX(int playerNum){
        return initTiles.get(playerNum-1).getKey();
    }

    //returns y-coordinate of a particular player's initial tile
    public Integer getPlayerInitPositionY(int playerNum){
        return initTiles.get(playerNum-1).getValue();
    }

    //returns number of maps created
    protected int getMapCount() {
        return mapCount;
    }

    //returns 2D array mapTiles containing the type of each tile in the Map
    protected Tiles[][] getTiles() {
        return this.mapTiles;
    }

    //returns array list of coordinates for all grass tiles in the map
    protected ArrayList<Pair<Integer, Integer>> getGrassTiles() {
        return grassTiles;
    }

    //returns array list of coordinates for all water tiles in the map
    public ArrayList<Pair<Integer, Integer>> getWaterTiles() {
        return waterTiles;
    }

    //returns coordinates of the treasure tile in the map
    public static Pair<Integer, Integer> getTreasureTile() {
        return treasureTile;
    }

    //returns type of tile indicated by the given x and y coordinates
    public char getTileType(int x, int y) {
        if(x >= size || y >= size || x < 0 || y < 0){
            return 'E';
        }

        char type = 'E';

        switch(mapTiles[x][y]){
            case TREASURE:
                type = 'T';
                break;
            case GRASS:
                type = 'G';
                break;
            case WATER:
                type = 'W';
                break;
        }
        return type;
    }

    //setters
    //set number of maps to zero
    public void initMapCount(){
        mapCount = 0;
    }

    //set number of maps for testing
    protected void setMapCount(int newMapCount){
        mapCount = newMapCount;
    }

    //set map size to a number from 5 to 50
    public boolean setMapSize(int size) {
        //if inputted size is less than minimum of 5
        if(size < 5 || size > 50){
            return false;
        }
        else{
            Map.size = size;
            return true;
        }
    }

    //set percentage of water tiles which affects the percentage of grass tiles
    abstract public boolean setWaterPercentage(double waterPercentage);

    //generates player map and corresponding HTML file
    public void generate(int numOfTeamPlayers){

        //single set of tiles
        if(!tilesGenerated) {
            setUpMapTiles();
        }

        mapCount++;
        Tiles [][] playerMap = generateInitMap();

        //collaborative
        if(numOfTeamPlayers > 0) {
            teamMaps.add(new ArrayList<>());
            for (int i = 0; i < numOfTeamPlayers; i++) {
                teamMaps.get(mapCount-1).add(playerMap);
                util.generateMapHTML(mapCount, playerMap, i);
            }
        }
        //individual
        else{
            playerMaps.add(playerMap);
            util.generateMapHTML(mapCount, playerMap);
        }
    }

    //generates tiles for Map once in map's lifetime
    private void setUpMapTiles(){
        util.generateGameMapsFolder();

        // create instance of Random class
        Random rand = new Random();
        double randomValue;

        //set water percentage randomly
        boolean success = false;
        while(!success){
            randomValue = 100 * rand.nextDouble();
            success = this.setWaterPercentage(randomValue);
        }

        generateTileTypes();
        tilesGenerated = true;
    }

    //generates initial map for player with initial position on a random grass tile
    protected Tiles[][] generateInitMap() {
        Tiles[][] initMap = new Tiles[size][size];

        Collections.shuffle(grassTiles);
        Pair<Integer, Integer> initTile = grassTiles.get(0);
        this.initTiles.add(initTile);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if(initTile.getKey() == x && initTile.getValue() == y){
                    initMap[x][y] = Tiles.GRASS_PLAYER;
                }else {
                    initMap[x][y] = Tiles.GREY;
                }
            }
        }
        return initMap;
    }

    //generates grass, water and treasure tiles randomly
    //initializes mapTiles variable according to the generated tiles
    protected void generateTileTypes() {
        Pair<Integer, Integer> temp;
        int amountTiles = size * size;
        int tileCount = 1;

        //generate grass tiles
        ArrayList<Integer> grass = generateGrassTiles(amountTiles);

        //generate treasure tile
        int treasure = generateTreasureTile(amountTiles, grass);

        //generate water tiles
        ArrayList<Integer> water = generateWaterTiles(amountTiles, treasure, grass);

        mapTiles = new Tiles[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (tileCount == treasure) {
                    mapTiles[x][y] = Tiles.TREASURE;

                    treasureTile = new Pair<>(x, y);

                } else if (grass.contains(tileCount)) {
                    mapTiles[x][y] = Tiles.GRASS;

                    temp = new Pair<>(x, y);
                    grassTiles.add(temp);

                } else if (water.contains(tileCount)) {
                    mapTiles[x][y] = Tiles.WATER;

                    temp = new Pair<>(x, y);
                    waterTiles.add(temp);
                }
                tileCount++;
            }
        }
    }

    //calculates percentage of grass tiles from percentage of water tiles
    protected double calculateGrassPercentage(){
        double mapSize = (double)size;
        double tilePercentage = (1/(mapSize*mapSize))*100;

        return (100 - waterPercentage - tilePercentage);
    }

    //generates 85% of the map size as grass tiles
    private ArrayList<Integer> generateGrassTiles(int amountTiles) {
        ArrayList<Integer> grassTiles = new ArrayList<Integer>();

        Random r = new Random();
        int randNum;
        double grassPercentage = calculateGrassPercentage();
        int amountGrass = (int)Math.ceil((grassPercentage/100)*amountTiles);

        randNum = r.nextInt(amountGrass) + 1;
        grassTiles.add(randNum);

        for (int i = 1; i < amountGrass; i++) {
            do{
                randNum = r.nextInt(amountTiles) + 1;
            }while(grassTiles.contains(randNum));
            grassTiles.add(randNum);
        }
        return grassTiles;
    }

    //generates 1 treasure tile which is not a grass tile
    private int generateTreasureTile(int amountTiles, ArrayList<Integer> grassTiles) {
        //set treasure tile to a tile which is not a grass tile
        Random r = new Random();
        int treasure;
        do{
            treasure = r.nextInt(amountTiles) + 1;
        }while(grassTiles.contains(treasure));
        return treasure;
    }

    //tiles which are neither grass nor treasure are set to water tiles
    private ArrayList<Integer> generateWaterTiles(int amountTiles, int treasure, ArrayList<Integer> grassTiles) {
        ArrayList<Integer> waterTiles = new ArrayList<>();

        //if water tile percentage = 0%
        if(waterPercentage > 0) {
            for (int i = 1; i < amountTiles + 1; i++) {
                if (!grassTiles.contains(i) && i != treasure) {
                    waterTiles.add(i);
                }
            }
        }
        return waterTiles;
    }

    //gets player map for individual play and updates player's map contents
    public void updateMap(int xNew, int yNew, int playerNum){
        Tiles[][] playerMap = getPlayerMap(playerNum);
        updateMapContents(xNew, yNew, playerNum, playerMap);
        util.generateMapHTML(playerNum, playerMap);
    }

    //gets player map for collaborative play and updates player's map contents
    public void updateMap(int xNew, int yNew, int teamNum, int playerNum){
        Tiles[][] playerMap = getTeamPlayerMap(teamNum, playerNum);
        updateMapContents(xNew, yNew, teamNum, playerMap);
        util.generateMapHTML(playerNum, playerMap, playerNum);
    }

    //if the given x and y coordinates are within the map size limit, the map of the given player is
    //updated to reveal the tile indicated by those coordinates.
    //if the revealed tile is grass, then player moves on it,
    //else if it is water, the player is sent back to the initial tile,
    //else if it is treasure, the player moves on it.
    private void updateMapContents(int xNew, int yNew, int playerNum, Tiles[][] playerMap){

        if(xNew < size && yNew < size && xNew >= 0 && yNew >= 0) {

            Tiles revealedTile = this.mapTiles[xNew][yNew];

            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (xNew == x && yNew == y) {
                        //if in collaborative mode tile already revealed or if revealed tile is grass
                        if(playerMap[x][y] == Tiles.GRASS || revealedTile == Tiles.GRASS){
                            playerMap[x][y] = Tiles.GRASS_PLAYER;
                        }
                        else{
                            playerMap[x][y] = revealedTile;
                        }
                    }

                    //if grass tile is revealed then remove character from prev tile
                    if (playerMap[x][y] == Tiles.GRASS_PLAYER && (xNew != x || yNew != y)) {
                        playerMap[x][y] = Tiles.GRASS;
                    }

                    //if water tile is revealed then remove character from prev tile and place it in init tile
                    if (revealedTile == Tiles.WATER) {
                        int initX = getPlayerInitPositionX(playerNum);
                        int initY = getPlayerInitPositionY(playerNum);
                        playerMap[initX][initY] = Tiles.GRASS_PLAYER;
                    }
                }
            }
        }
    }

    //reveals tile for player map during collaborative play and updates player's html file accordingly
    //without moving detective on map except if treasure is found
    public void revealTile(int xNew, int yNew, int teamNum, int playerNum){
        Tiles[][] teamPlayerMap = getTeamPlayerMap(teamNum, playerNum);
        Tiles revealedTile = this.mapTiles[xNew][yNew];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (xNew == x && yNew == y) {
                    if(teamPlayerMap[x][y] != Tiles.GRASS_PLAYER) {
                        teamPlayerMap[x][y] = revealedTile;
                    }
                }

                //if treasure tile is revealed then remove character from prev tile and move it to treasure tile
                if (teamPlayerMap[x][y] == Tiles.GRASS_PLAYER && (xNew != x || yNew != y) && revealedTile == Tiles.TREASURE){
                    teamPlayerMap[x][y] = Tiles.GRASS;
                }
            }
        }
        util.generateMapHTML(playerNum, teamPlayerMap, playerNum);
    }

    //resets all variables associated with the Map
    public void resetMap() {
        initMapCount();

        grassTiles.clear();
        waterTiles.clear();
        treasureTile = null;

        playerMaps.clear();
        teamMaps.clear();
        initTiles.clear();
        tilesGenerated = false;

        waterPercentage = -1;
        size = -1;
    }

}