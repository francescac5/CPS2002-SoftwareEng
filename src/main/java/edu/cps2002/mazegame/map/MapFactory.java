package edu.cps2002.mazegame.map;

public class MapFactory {
    public Map createMap(String type) {
        if (type.equals("safe")){
            return new SafeMap();
        }
        else if (type.equals("hazardous")){
            return new HazardousMap();
        }
        return null;
    }
}
