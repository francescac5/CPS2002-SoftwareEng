package edu.cps2002.mazegame.player;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.map.MapFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPlayerHazardousMap {
    private Player p1;
    private Position pos = new Position (4,5);
    private Map hazardousMap = MapFactory.getInstance("H");

    @Before
    public void setup() {
        p1 = new Player(4,5, hazardousMap);
        hazardousMap.setMapSize(5);
    }
    @After
    public void tearDown() {
        p1 = null;
        pos= null;
    }

    //testing SetPosition
    @Test
    public void testSetPosition(){
        p1.setPosition(pos);
        assertEquals(pos, p1.getPosition());
    }

    //******** Player.move() tests ********\\
    @Test
    public void testDirectionD(){
       boolean x=p1.move(Player.DIRECTION.DOWN);
        assertFalse(x);
    }

    @Test
    public void testDirectionU(){

        boolean x =p1.move(Player.DIRECTION.UP);
        assertTrue(x);
    }

    @Test
    public void testDirectionL(){
        boolean x= p1.move(Player.DIRECTION.LEFT);
        assertFalse(x);
    }

    @Test
    public void testDirectionUnknown(){
        boolean x= p1.move(Player.DIRECTION.UNKNOWN);
        assertFalse(x);
    }

    @Test
    public void testDirectionR(){
        boolean x=p1.move(Player.DIRECTION.RIGHT);
        assertFalse(x);
    }

    @Test
    public void testDirectionLeftINVALID(){
        boolean x=p1.move(Player.DIRECTION.LEFT);
        assertFalse(x);
    }

    @Test
    public void testDirectionLEFTINVALID(){
        p1 = new Player(4,5, hazardousMap);
        boolean x=p1.move(Player.DIRECTION.LEFT);
        assertFalse(x);
    }

    @Test
    public void testDirectionLEFTINVALID2(){
        p1 = new Player(5,5, hazardousMap);
        boolean x=p1.move(Player.DIRECTION.RIGHT);
        assertFalse(x);
    }

    @Test
    public void testDirectionLEFTINVALID3(){
        p1 = new Player(0,0, hazardousMap);
        boolean x=p1.move(Player.DIRECTION.LEFT);
        assertFalse(x);
    }

    @Test
    public void testDirectionLEFTINVALID4(){
        p1 = new Player(0,5, hazardousMap);
        boolean x=p1.move(Player.DIRECTION.LEFT);
        assertFalse(x);
    }
}
