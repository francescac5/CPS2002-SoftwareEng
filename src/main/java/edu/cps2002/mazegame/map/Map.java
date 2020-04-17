package edu.cps2002.mazegame.map;

import edu.cps2002.utils.MapUtils;
import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Map {

    public enum Tiles{
        GRASS,
        WATER,
        TREASURE, //1 tile
        GREY,
        GRASS_PLAYER,
        GRASS_INIT
    }

    private MapUtils util = new MapUtils();

    private int size = -1;
    private static int mapCount;
    private Tiles[][] mapTiles;
    private boolean tilesGenerated = false;

    private ArrayList<Pair<Integer,Integer>> grassTiles = new ArrayList<>();
    private ArrayList<Pair<Integer,Integer>> waterTiles = new ArrayList<>();
    private Pair<Integer, Integer> treasureTile;

    private ArrayList<Tiles[][]> playerMaps = new ArrayList<>();

    public void initMapCount(){
        mapCount = 0;
    }

    //only for testing purposes
    protected int getMapCount() {
        return mapCount;
    }

    public boolean setMapSize(int size) {
        //if inputted size is less than minimum of 5
        if(size < 5 || size > 50){
            return false;
        }
        else{
            this.size = size;
            return true;
        }
    }

    public int getMapSize() {
        return size;
    }

    public void generate(){

        //single set of tiles
        if(!tilesGenerated){
            generateTileTypes();
            tilesGenerated = true;
        }

        mapCount++;
        Tiles [][] playerMap = generateInitMap();
        playerMaps.add(playerMap);

        util.generateMapHTML(mapCount, playerMap);
    }

    private Tiles[][] generateInitMap() {
        Tiles[][] initMap = new Tiles[size][size];

        Collections.shuffle(grassTiles);
        Pair<Integer, Integer> initTile = grassTiles.get(0);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if(initTile.getKey() == x && initTile.getValue() == y){
                    initMap[x][y] = Tiles.GRASS_INIT;
                }else {
                    initMap[x][y] = Tiles.GREY;
                }
            }
        }
        return initMap;
    }

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

    private int generateTreasureTile(int amountTiles, ArrayList<Integer> grassTiles) {
        //set treasure tile to a tile which is not a grass tile
        Random r = new Random();
        int treasure;
        do{
            treasure = r.nextInt(amountTiles) + 1;
        }while(grassTiles.contains(treasure));
        return treasure;
    }

    private ArrayList<Integer> generateWaterTiles(int amountTiles, int treasure, ArrayList<Integer> grassTiles) {
        ArrayList<Integer> waterTiles = new ArrayList<Integer>();

        for (int i = 1; i < amountTiles+1; i++) {
            if(!grassTiles.contains(i) && i != treasure){
                waterTiles.add(i);
            }
        }
        return waterTiles;
    }

    protected Tiles[][] getTiles() {
        return this.mapTiles;
    }

    protected ArrayList<Pair<Integer, Integer>> getGrassTiles() {
        return grassTiles;
    }


    protected ArrayList<Pair<Integer, Integer>> getWaterTiles() {
        return waterTiles;
    }

    protected Pair<Integer, Integer> getTreasureTile() {
        return treasureTile;
    }

    public char getTileType(int x, int y) {
        if(x >= size || y >= size){
            return 'E';
        }

        char type;

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
            default:
                type = 'E';
                break;
        }
        return type;
    }

    public void updateMap(int x, int y, int playerNum){

    }

    protected Tiles[][] getPlayerMap(int playerNum){
        return playerMaps.get(playerNum-1);
    }

    private void deleteMaps() {
        util.deleteHTMLFiles();
    }

}

