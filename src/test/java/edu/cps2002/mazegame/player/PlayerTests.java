package edu.cps2002.mazegame.player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerTests {
    public Player p1;
    Position pos = new Position (4,5);

    @Before
    public void setup() {
        Position pos = new Position (4,5);
        p1 = new Player();
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
        p1.move('D');
        assertEquals(4, pos.getY());
    }

    @Test
    public void testDirectionU(){
        p1.move('U');
        assertEquals(6, pos.getY());
    }

    @Test
    public void testDirectionL(){
        p1.move('L');
        assertEquals(3, pos.getX());
    }

    @Test
    public void testDirectionR(){
        p1.move('R');
        assertEquals(5, pos.getX());
    }

    @Test
    public void testDirectionUNKNOWN1(){
        p1.move('P');
        assertEquals(5, pos.getY());
    }

    @Test
    public void testDirectionUNKNOWN2(){
        p1.move('K');
        assertEquals(4, pos.getX());
    }

}
