package edu.cps2002.mazegame.map;

public class MapFactory {
    public Map createMap(String type) {
        if (type.equals("S")){
            return new SafeMap();
        }
        else if (type.equals("H")){
            return new HazardousMap();
        }
        return null;
    }
}
