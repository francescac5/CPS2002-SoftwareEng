package edu.cps2002.mazegame.map;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestMapSingleton {
    //Singleton pattern tests
    @Test
    public void getInstanceTestHazardous1(){
        //Instance 1
        Map instance1 = MapFactory.getInstance("H");

        //Instance 2
        Map instance2 = MapFactory.getInstance("H");

        //checking that the hashcode is equal
        assertEquals(instance1.hashCode(),instance2.hashCode());
    }

    @Test
    public void getInstanceTestSafe1(){
        //Instance 1
        Map instance1 = MapFactory.getInstance("S");

        //Instance 2
        Map instance2 = MapFactory.getInstance("S");

        //checking that the hashcode is equal
        assertEquals(instance1.hashCode(),instance2.hashCode());
    }

    @Test
    public void getInstanceTestHazardous2(){

        //Instance 1
        Map instance1 = MapFactory.getInstance("H");

        //Instance 2
        Map instance2 = MapFactory.getInstance("H");

        //Instance 3
        Map instance3 = MapFactory.getInstance("H");

        //checking that the hashcode is equal
        assertEquals(instance1.hashCode(),instance3.hashCode());
    }

    @Test
    public void getInstanceTestSafe2(){

        //Instance 1
        Map instance1 = MapFactory.getInstance("S");

        //Instance 2
        Map instance2 = MapFactory.getInstance("S");

        //Instance 3
        Map instance3 = MapFactory.getInstance("S");

        //checking that the hashcode is equal
        assertEquals(instance1.hashCode(),instance3.hashCode());
    }


}
