package edu.cps2002.mazegame.player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

    public class TestPosition {
        public Position p;

        @Before
        public void setup() {
          p = new Position(3,4);
        }
        @After
        public void tearDown() {
            p = null;
        }

        //testing setters
        @Test
        public void testSetX(){
            p.setX(10);
            assertEquals(10, p.getX());
        }
        @Test
        public void testSetY(){
            p.setY(10);
            assertEquals(10, p.getY());
        }

        //testing getters
        @Test
        public void testGetX(){
            assertEquals(3, p.getX());
        }
        @Test
        public void testGetY(){
            assertEquals(4, p.getY());
        }
    }

