package edu.cps2002.mazegame.map;

public class MapFactory {
    public Map createMap(String type) {
        if (type.equalsIgnoreCase("S")){
            return new SafeMap();
        }
        else if (type.equalsIgnoreCase("H")){
            return new HazardousMap();
        }
        return null;
    }
}
