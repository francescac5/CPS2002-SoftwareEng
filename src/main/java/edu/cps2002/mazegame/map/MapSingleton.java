package edu.cps2002.mazegame.map;

public class MapSingleton {

    static Map map = new Map();

    public static Map getInstance() {
        return map;
    }


}
