package edu.cps2002.mazegame.map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestMapSingleton {

    @Test
    public void getInstanceTest1(){

        //Instance 1
        MapSingleton instance1 = MapSingleton.getInstance();

        //Instance 2
        MapSingleton instance2 = MapSingleton.getInstance();

        //checking that the hashcode is equal
        assertEquals(instance1.hashCode(),instance2.hashCode());
    }

    @Test
    public void getInstanceTest2(){
        //  Map singleton = MapSingleton.getInstance();

        //Instance 1
        MapSingleton instance1 = MapSingleton.getInstance();

        //Instance 2
        MapSingleton instance2 = MapSingleton.getInstance();

        //Instance 3
        MapSingleton instance3 = MapSingleton.getInstance();

        //checking that the hashcode is equal
        assertEquals(instance1.hashCode(),instance3.hashCode());
    }

}
