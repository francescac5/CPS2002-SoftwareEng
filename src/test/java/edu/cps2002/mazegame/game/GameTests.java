package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.player.Player;
import edu.cps2002.mazegame.player.Position;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTests {
    public Player p1;
    Position pos = new Position(4, 5);

    @Before
    public void setup() {
        Position pos = new Position(4, 5);
        p1 = new Player(4, 5);
    }

    @After
    public void tearDown() {
        p1 = null;
        pos = null;
    }

    //testing chooseDirection down
    @Test
    public void testchooseDirectionD() {
        Assert.assertEquals(Player.DIRECTION.DOWN, Game.chooseDirection("D"));

    }

    //testing chooseDirection up
    @Test
    public void testchooseDirectionU() {
        assertEquals(Player.DIRECTION.UP, Game.chooseDirection("U"));

    }

    //testing chooseDirection left
    @Test
    public void testchooseDirectionL() {
        assertEquals(Player.DIRECTION.LEFT, Game.chooseDirection("L"));

    }

    //testing chooseDirection right
    @Test
    public void testchooseDirectionR() {
        assertEquals(Player.DIRECTION.RIGHT, Game.chooseDirection("R"));

    }
}
