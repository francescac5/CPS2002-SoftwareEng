package edu.cps2002.mazegame.player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

    public class PositionTests {
        public Position p;
        @Before
        public void setup() {
          p = new Position();
        }

        @After
        public void tearDown() {
            p = null;
        }

        @Test
        public void testSetGetX(){
            p.setX(3);
            assertEquals(3, p.getX());
        }
        @Test
        public void testSetGetY(){
            p.setY(4);
            assertEquals(3, p.getY());
        }
    }

