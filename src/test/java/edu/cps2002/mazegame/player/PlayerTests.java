package edu.cps2002.mazegame.player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerTests {
    public Player p;
    public Position pn;
    @Before
    public void setup() {
        p = new Player();
        pn = new Position(4,5);
    }
    @After
    public void tearDown() {
        p = null;
        pn= null;
    }

    //testing SetPosition
    @Test
    public void testSetPosition(){
        p.setPosition(pn);
        assertEquals(pn, p.getPosition());
    }

}
