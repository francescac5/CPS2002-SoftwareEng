package edu.cps2002.mazegame.map;

import java.io.File;
import java.io.IOException;

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
}
