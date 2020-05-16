package edu.cps2002.mazegame.map;

public class MapSingleton {

    private static MapSingleton mapInstance= null;

    private MapSingleton(){
    }
    public static MapSingleton getInstance() {
                if(mapInstance ==null)
                    mapInstance= new MapSingleton();

        return mapInstance;
    }

}

