package edu.cps2002.mazegame.map;

public class MapSingleton {

    private static volatile MapSingleton map;

    private MapSingleton(){
        //Prevent form the reflection api.
        if(map != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }
    public static MapSingleton getInstance() {
        //Double check locking pattern
        if(map == null){
            synchronized (MapSingleton.class){
                //if there is no instance available.. create new one
                if(map ==null)
                    map= new MapSingleton();
            }
        }
        return map;
    }
}

