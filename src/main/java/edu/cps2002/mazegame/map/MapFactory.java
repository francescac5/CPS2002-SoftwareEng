package edu.cps2002.mazegame.map;

public class MapFactory {

    protected static Map mapInstance= null;

    private MapFactory(){
    }

    public static Map getInstance(String type) {
        if(mapInstance ==null) {
            mapInstance = createMap(type);
        }
        return mapInstance;
    }

    private static Map createMap(String type) {
        if (type.equalsIgnoreCase("S")){
            return new SafeMap();
        }
        else if (type.equalsIgnoreCase("H")){
            return new HazardousMap();
        }
        return null;
    }
}
