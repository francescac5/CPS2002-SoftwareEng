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

    //    mapFactory = new MapFactory();
    }

    @After
    public void tearDown() {
      MapFactory.TearDown();
    }

//******** mapFactory.createMap() tests ********\\

    @Test
    public void testCreateMap_safeMap_upperCase(){
        //Exercise
        Map safeMap = MapFactory.getInstance("S");

        //Assert
        assertTrue(safeMap instanceof SafeMap);
    }

    @Test
    public void testCreateMap_safeMap_lowerCase(){
        //Exercise
        Map safeMap = MapFactory.getInstance("s");

        //Assert
        assertTrue(safeMap instanceof SafeMap);
    }

    @Test
    public void testCreateMap_hazardousMap_upperCase(){
        //Exercise
        Map hazardousMap = MapFactory.getInstance("H");

        //Assert
        assertTrue(hazardousMap instanceof HazardousMap);
    }

    @Test
    public void testCreateMap_hazardousMap_lowerCase(){
        //Exercise
        Map hazardousMap = MapFactory.getInstance("h");


        //Assert
        assertTrue(hazardousMap instanceof HazardousMap);
    }

    @Test
    public void testCreateMap_invalidMap(){
        //Exercise
        Map invalidMap = MapFactory.getInstance("invalid");

        //Assert
        assertNull(invalidMap);
    }
}
