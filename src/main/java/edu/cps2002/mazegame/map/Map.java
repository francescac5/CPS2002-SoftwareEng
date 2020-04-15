package edu.cps2002.mazegame.map;

import edu.cps2002.utils.MapUtils;

import java.io.File;

public class Map {

    enum Tiles{
        GRASS,
        WATER,
        TREASURE //1 tile
    }

    private int size = -1;
    private static int mapCount;
    private Tiles[][] mapTiles;

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
        MapUtils util = new MapUtils();

        mapCount++;
        File mapFile1 = util.generateHTMLFile(mapCount);
        util.generateMap(mapFile1, size, mapCount);
        mapCount++;
        File mapFile2 = util.generateHTMLFile(mapCount);
        util.generateMap(mapFile2, size, mapCount);

        //util.deleteHTMLFiles();
    }

    //used temporarily to test utility map files
    public static void main(String[]args){
        Map map = new Map();
        map.setMapSize(5);
        map.generate();
    }

    protected void generateTileTypes() {
    }

    protected Tiles[][] getTiles() {
        return this.mapTiles;
    }
}

