package edu.cps2002.mazegame.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestMapFactory {
    public MapFactory mapFactory;

    @Before
    public void setup() {
        mapFactory = new MapFactory();
    }

    @After
    public void tearDown() {
        mapFactory = null;
    }

//******** mapFactory.createMap() tests ********\\

    @Test
    public void testCreateMap_safeMap_upperCase(){
        //Exercise
        Map safeMap = mapFactory.createMap("S");

        //Assert
        assertTrue(safeMap instanceof SafeMap);
    }

    @Test
    public void testCreateMap_safeMap_lowerCase(){
        //Exercise
        Map safeMap = mapFactory.createMap("s");

        //Assert
        assertTrue(safeMap instanceof SafeMap);
    }

    @Test
    public void testCreateMap_hazardousMap_upperCase(){
        //Exercise
        Map hazardousMap = mapFactory.createMap("H");

        //Assert
        assertTrue(hazardousMap instanceof HazardousMap);
    }

    @Test
    public void testCreateMap_hazardousMap_lowerCase(){
        //Exercise
        Map hazardousMap = mapFactory.createMap("h");

        //Assert
        assertTrue(hazardousMap instanceof HazardousMap);
    }

    @Test
    public void testCreateMap_invalidMap(){
        //Exercise
        Map invalidMap = mapFactory.createMap("invalid");

        //Assert
        assertNull(invalidMap);
    }
}
