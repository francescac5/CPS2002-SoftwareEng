package edu.cps2002.mazegame.map;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestMapSingleton {

    //Testing the system without the singleton pattern
    @Test
    public void BeforeSingleton(){
        Map map1 = new Map();
        Map map2 = new Map();

      assertNotEquals(map1.hashCode(),map2.hashCode());
    }

    //Singleton pattern tests
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
