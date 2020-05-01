package edu.cps2002.mazegame.map;

import edu.cps2002.mazegame.utils.MapUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Map {

    public enum Tiles{
        GRASS,
        WATER,
        TREASURE, //1 tile
        GREY,
        GRASS_PLAYER
    }

    private MapUtils util = new MapUtils();

    private static int size = -1;
    private static int mapCount;
    private Tiles[][] mapTiles;
    protected boolean tilesGenerated = false;

    protected ArrayList<Pair<Integer,Integer>> grassTiles = new ArrayList<>();
    protected static ArrayList<Pair<Integer,Integer>> waterTiles = new ArrayList<>();
    protected static Pair<Integer, Integer> treasureTile;

    protected ArrayList<Tiles[][]> playerMaps = new ArrayList<>();
    protected ArrayList<Pair<Integer,Integer>> initTiles = new ArrayList<>();

    //getters
    //returns size of map set by user
    public static int getMapSize() {
        return size;
    }

    //returns map of a particular player
    protected Tiles[][] getPlayerMap(int playerNum){
        return playerMaps.get(playerNum-1);
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
    public static ArrayList<Pair<Integer, Integer>> getWaterTiles() {
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
            case GRASS_PLAYER:
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

    //generates tiles for Map once in map's lifetime
    //generates player map and corresponding HTML file
    public void generate(){

        //single set of tiles
        if(!tilesGenerated){
            util.generateGameMapsFolder();

            generateTileTypes();
            tilesGenerated = true;
        }

        mapCount++;
        Tiles [][] playerMap = generateInitMap();
        playerMaps.add(playerMap);

        util.generateMapHTML(mapCount, playerMap);
    }

    //generates initial map for player with initial position on a random grass tile
    private Tiles[][] generateInitMap() {
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
                    mapTiles[x][y] = Tiles.GRASS_PLAYER;

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

    //generates 85% of the map size as grass tiles
    private ArrayList<Integer> generateGrassTiles(int amountTiles) {
        ArrayList<Integer> grassTiles = new ArrayList<Integer>();

        Random r = new Random();
        int randNum;
        int amountGrass = (int)Math.ceil(0.85*amountTiles);

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

        for (int i = 1; i < amountTiles+1; i++) {
            if(!grassTiles.contains(i) && i != treasure){
                waterTiles.add(i);
            }
        }
        return waterTiles;
    }

    //if the given x and y coordinates are within the map size limit, the map of the given player is
    //updated to reveal the tile indicated by those coordinates.
    //if the revealed tile is grass, then player moves on it,
    //else if it is water, the player is sent back to the initial tile,
    //else if it is treasure, the player moves on it.
    public void updateMap(int xNew, int yNew, int playerNum){

        if(xNew < size && yNew < size && xNew >= 0 && yNew >= 0) {

            Tiles[][] playerMap = getPlayerMap(playerNum);
            Tiles revealedTile = this.mapTiles[xNew][yNew];

            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (xNew == x && yNew == y) {
                        playerMap[x][y] = revealedTile;
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
            util.generateMapHTML(playerNum, playerMap);
        }
    }

    //resets all variables associated with the Map
    public void resetMap() {
        initMapCount();

        grassTiles.clear();
        waterTiles.clear();
        treasureTile = null;

        playerMaps.clear();
        initTiles.clear();
        tilesGenerated = false;

        size = -1;
    }

}