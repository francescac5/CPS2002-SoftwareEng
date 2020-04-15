package edu.cps2002.mazegame.player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestPlayer {
     public Player p;
    @Before
    public void setup() {
        p = new Player();
    }
    @After
    public void tearDown() {
        p = null;
    }

    //testing SetPosition
    @Test
    public void testSetPosition(){
        p.setPosition(10);
        assertEquals(10, p.getPosition());
    }

    //testing GetPosition
    @Test
    public void testGetPosition(){
        assertEquals(3, p.getPosition());
    }

}