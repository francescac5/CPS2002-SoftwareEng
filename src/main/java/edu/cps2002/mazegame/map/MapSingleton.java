package edu.cps2002.mazegame.map;

import com.sun.prism.PhongMaterial;

import java.util.Scanner;

public class MapSingleton {

    private static Map mapInstance= null;
    private static String mapType = null;

    private MapSingleton(){
    }
    public static Map getInstance() {
        if(mapInstance == null) {
            MapFactory mapFactory = new MapFactory();

            Scanner sc= new Scanner(System.in);

            while (true) {
                System.out.print("===Choose map type  S = SAFE, H = HAZARDOUS===\n");
                try {
                    if(mapType==null){
                        mapType=sc.next();
                    }
                    mapInstance = mapFactory.createMap(mapType);
                } catch (Exception e) {
                    sc.next();
                }

                if(mapInstance == null){
                    System.out.println("Invalid Map Type!\n");
                }else{
                    break;
                }
            }
            assert mapType != null;
            mapInstance = mapFactory.createMap(mapType);
        }
        return mapInstance;
    }

    //Method for testing purposes (to avoid user input)
    protected static void setMapType(String mapTypeInput){
        mapType= mapTypeInput;
    }

}

