package edu.cps2002.mazegame.player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestPlayer {
    public Player p1;
    Position pos = new Position (4,5);

    @Before
    public void setup() {
        p1 = new Player(4,5);
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
        assertEquals(4, p1.getPosition().y);
    }

    @Test
    public void testDirectionU(){
        p1.move('U');
        assertEquals(6, p1.getPosition().y);
    }

    @Test
    public void testDirectionL(){
        p1.move('L');
        assertEquals(3, p1.getPosition().x);
    }

    @Test
    public void testDirectionR(){
        p1.move('R');
        assertEquals(5, p1.getPosition().x);
    }

    @Test
    public void testDirectionUNKNOWN1(){
        p1.move('P');
        assertEquals(5, p1.getPosition().y);
        assertEquals(4, p1.getPosition().x);
    }

    @Test
    public void testDirectionUNKNOWN2(){
        p1.move('K');
        assertEquals(5, p1.getPosition().y);
        assertEquals(4, p1.getPosition().x);
    }

}
