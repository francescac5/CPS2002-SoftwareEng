package edu.cps2002.mazegame.map;

public class MapFactory {

    //stores the single map instance
    //cannot be accessed directly
    private static Map mapInstance= null;

    //instance of class cannot be created
    //from outside the class
    private MapFactory(){
    }

    //returns an existing instance of Map if it has already been
    //initialized otherwise initializes instance and returns it
    public static Map getInstance(String type) {
        if(mapInstance ==null) {
            mapInstance = createMap(type);
        }
        return mapInstance;
    }

    //creates an instance of a Map subclass
    private static Map createMap(String type) {
        if (type.equalsIgnoreCase("S")){
            return new SafeMap();
        }
        else if (type.equalsIgnoreCase("H")){
            return new HazardousMap();
        }
        return null;
    }

    //For testing purposes only
    protected static void TearDown(){
        mapInstance= null;
    }
}
