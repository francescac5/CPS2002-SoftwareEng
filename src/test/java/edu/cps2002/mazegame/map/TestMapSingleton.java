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
    public void getInstanceTestHazardous1(){
        MapSingleton.setMapType("H");
        //Instance 1
        Map instance1 = MapSingleton.getInstance();

        //Instance 2
        Map instance2 = MapSingleton.getInstance();

        //checking that the hashcode is equal
        assertEquals(instance1.hashCode(),instance2.hashCode());
    }

    @Test
    public void getInstanceTestSafe1(){
        MapSingleton.setMapType("S");
        //Instance 1
        Map instance1 = MapSingleton.getInstance();

        //Instance 2
        Map instance2 = MapSingleton.getInstance();

        //checking that the hashcode is equal
        assertEquals(instance1.hashCode(),instance2.hashCode());
    }

    @Test
    public void getInstanceTestHazardous2(){
        MapSingleton.setMapType("H");

        //Instance 1
        Map instance1 = MapSingleton.getInstance();

        //Instance 2
        Map instance2 = MapSingleton.getInstance();

        //Instance 3
        Map instance3 = MapSingleton.getInstance();

        //checking that the hashcode is equal
        assertEquals(instance1.hashCode(),instance3.hashCode());
    }

    @Test
    public void getInstanceTestSafe2(){
        MapSingleton.setMapType("S");

        //Instance 1
        Map instance1 = MapSingleton.getInstance();

        //Instance 2
        Map instance2 = MapSingleton.getInstance();

        //Instance 3
        Map instance3 = MapSingleton.getInstance();

        //checking that the hashcode is equal
        assertEquals(instance1.hashCode(),instance3.hashCode());
    }


}
