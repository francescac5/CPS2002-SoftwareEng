package edu.cps2002.mazegame.player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerTests {
    public Player p1;
    public Position pos;

    @Before
    public void setup() {
        p1 = new Player();
        pos = new Position(4,5);
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
        Player.move('D');
        assertEquals(4, pos.getY());
    }

    @Test
    public void testDirectionU(){
        Player.move('U');
        assertEquals(6, pos.getY());
    }

    @Test
    public void testDirectionL(){
        Player.move('L');
        assertEquals(3, pos.getX());
    }

    @Test
    public void testDirectionR(){
        Player.move('R');
        assertEquals(5, pos.getX());
    }

    @Test
    public void testDirectionUNKNOWN1(){
        Player.move('P');
        assertEquals(5, pos.getY());
    }

    @Test
    public void testDirectionUNKNOWN2(){
        Player.move('P');
        assertEquals(4, pos.getX());
    }



}
