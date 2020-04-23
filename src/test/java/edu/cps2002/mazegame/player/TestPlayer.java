package edu.cps2002.mazegame.player;

import edu.cps2002.mazegame.map.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPlayer {
    public Player p1;
    Position pos = new Position (4,5);

    @Before
    public void setup() {
        p1 = new Player(4,5);
        Map.setMapSize(5);
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
    public void testDirectionR(){
        boolean x=p1.move(Player.DIRECTION.RIGHT);
        assertFalse(x);
    }


}
