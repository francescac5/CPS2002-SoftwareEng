package edu.cps2002.mazegame.map;

import edu.cps2002.utils.MapUtils;

import java.io.File;

public class Map {

    private int size;
    private static int mapCount;

    public void initMapCount(){
        mapCount = 0;
    }

    //only for testing purposes
    protected int getMapCount() {
        return mapCount;
    }

    public boolean setMapSize(int size) {
        //if inputted size is less than minimum of 5
        if(size < 5){
            return false;
        }
        else{
            this.size = size;
            return true;
        }

    }

    public void generate(){
        MapUtils util = new MapUtils();
        File mapFile1 = util.generateHTMLFile(mapCount);
        util.generateMap(mapFile1, size, mapCount);
        mapCount++;
        File mapFile2 = util.generateHTMLFile(mapCount);
        util.generateMap(mapFile2, size, mapCount);
        mapCount++;

        util.deleteHTMLFiles();
    }

    //used temporarily to test utility map files
    public static void main(String[]args){
        Map map = new Map();
        map.generate();
    }
}

