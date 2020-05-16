package edu.cps2002.mazegame.map;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestMapSingleton {

    //Testing the system without the singleton pattern
    @Test
    public void BeforeSingleton_HazardousSafe(){
        Map map1 = new HazardousMap();
        Map map2 = new SafeMap();

      assertNotEquals(map1.hashCode(),map2.hashCode());
    }

    @Test
    public void BeforeSingleton_TwoHazardous(){
        Map map1 = new HazardousMap();
        Map map2 = new HazardousMap();

        assertNotEquals(map1.hashCode(),map2.hashCode());
    }

    @Test
    public void BeforeSingleton_TwoSafe(){
        Map map1 = new SafeMap();
        Map map2 = new SafeMap();

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
