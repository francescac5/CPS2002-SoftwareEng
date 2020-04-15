package edu.cps2002.mazegame.map;

import edu.cps2002.utils.MapUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Map {

    enum Tiles{
        GRASS,
        WATER,
        TREASURE //1 tile
    }

    private MapUtils util = new MapUtils();

    private int size = -1;
    private static int mapCount;
    private Tiles[][] mapTiles;
    private boolean tilesGenerated = false;

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
        File mapFile1 = util.generateHTMLFile(mapCount);
        util.generateMap(mapFile1, size, mapCount);
    }

    //used temporarily to test utility map files
    public static void main(String[]args){
        Map map = new Map();
        map.setMapSize(5);
        map.generate();
        map.generate();
        map.deleteMaps();
    }

    private void deleteMaps() {
        util.deleteHTMLFiles();
    }

    protected void generateTileTypes() {
        int amountTiles = size*size;
        int tileCount = 1;

        //generate grass tiles
        ArrayList<Integer> grass = generateGrassTiles(amountTiles);

        //generate treasure tile
        int treasure = generateTreasureTile(amountTiles, grass);

        //generate water tiles
        ArrayList<Integer> water = generateWaterTiles(amountTiles, grass, treasure);

        mapTiles = new Tiles[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if(tileCount == treasure){
                    mapTiles[x][y] = Tiles.TREASURE;
                }
                else if(grass.contains(tileCount)){
                    mapTiles[x][y] = Tiles.GRASS;
                }
                else if(water.contains(tileCount)){
                    mapTiles[x][y] = Tiles.WATER;
                }
                tileCount++;

                //to display map tile types
                //System.out.print(tiles[x][y]+"\t");
            }
            //System.out.println();
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

    private int generateTreasureTile(int amountTiles, ArrayList<Integer> grass) {
        //set treasure tile to a tile which is not a grass tile
        Random r = new Random();
        int treasure;
        do{
            treasure = r.nextInt(amountTiles) + 1;
        }while(grass.contains(treasure));
        return treasure;
    }

    private ArrayList<Integer> generateWaterTiles(int amountTiles, ArrayList<Integer> grass, int treasure) {
        ArrayList<Integer> waterTiles = new ArrayList<Integer>();

        for (int i = 1; i < amountTiles+1; i++) {
            if(!grass.contains(i) && i != treasure){
                waterTiles.add(i);
            }
        }
        return waterTiles;
    }

    protected Tiles[][] getTiles() {
        return this.mapTiles;
    }
}

