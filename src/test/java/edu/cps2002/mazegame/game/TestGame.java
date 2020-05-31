package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.map.MapFactory;
import edu.cps2002.mazegame.map.SafeMap;
import edu.cps2002.mazegame.player.Player;
import edu.cps2002.mazegame.player.Position;
import edu.cps2002.mazegame.utils.MapUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static edu.cps2002.mazegame.game.Game.*;
import static org.junit.Assert.*;

public class TestGame {
    public Player p1;
    Position pos = new Position(4, 5);
    MapUtils utils = new MapUtils();

    @Before
    public void setup() {
        Position pos = new Position(4, 5);
        p1 = new Player(4, 5);
    }

    @After
    public void tearDown() {
        p1 = null;
        pos = null;
        utils.deleteHTMLFiles();
    }


    //******** Game.chooseDirection() tests ********\\
    //testing chooseDirection down (capital letter)
    @Test
    public void testchooseDirectionD() {
        Assert.assertEquals(Player.DIRECTION.DOWN, Game.chooseDirection("D"));

    }

    //testing chooseDirection down (small letter)
    @Test
    public void testchooseDirectiond() {
        Assert.assertEquals(Player.DIRECTION.DOWN, Game.chooseDirection("d"));

    }

    //testing chooseDirection up (capital letter)
    @Test
    public void testchooseDirectionU() {
        assertEquals(Player.DIRECTION.UP, Game.chooseDirection("U"));

    }

    //testing chooseDirection up (small letter)
    @Test
    public void testchooseDirectionu() {
        assertEquals(Player.DIRECTION.UP, Game.chooseDirection("u"));

    }

    //testing chooseDirection left (capital letter)
    @Test
    public void testchooseDirectionL() {
        assertEquals(Player.DIRECTION.LEFT, Game.chooseDirection("L"));

    }

    //testing chooseDirection left (small letter)
    @Test
    public void testchooseDirectionl() {
        assertEquals(Player.DIRECTION.LEFT, Game.chooseDirection("l"));

    }

    //testing chooseDirection right (capital letter)
    @Test
    public void testchooseDirectionR() {
        assertEquals(Player.DIRECTION.RIGHT, Game.chooseDirection("R"));

    }

    //testing chooseDirection right (small letter)
    @Test
    public void testchooseDirectionr() {
        assertEquals(Player.DIRECTION.RIGHT, Game.chooseDirection("r"));

    }

    //******** Game.validityofPlayers() tests ********\\
    @Test
    public void validityofPlayersTest1() {
        boolean result = Game.validityofPlayers(2);
        boolean actual = true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofPlayersTest2() {
        boolean result = Game.validityofPlayers(4);
        boolean actual = true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofPlayersTest3() {
        boolean result = Game.validityofPlayers(5);
        boolean actual = true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofPlayersTest4() {
        boolean result = Game.validityofPlayers(8);
        boolean actual = true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofPlayersTest5() {
        boolean result = Game.validityofPlayers(1);
        boolean actual = false;
        assertEquals(result, actual);

    }

    @Test
    public void validityofPlayersTest6() {
        boolean result = Game.validityofPlayers(9);
        boolean actual = false;
        assertEquals(result, actual);

    }

    //******** Game.validityofMapSize() tests ********\\
    @Test
    public void validityofMapSizeTest1() {
        boolean result = Game.validityofMapSize(2, 5);
        boolean actual = true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest2() {
        boolean result = Game.validityofMapSize(2, 4);
        boolean actual = false;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest3() {
        boolean result = Game.validityofMapSize(2, 8);
        boolean actual = true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest4() {
        boolean result = Game.validityofMapSize(5, 5);
        boolean actual = false;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest5() {
        boolean result = Game.validityofMapSize(8, 5);
        boolean actual = false;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest6() {
        boolean result = Game.validityofMapSize(8, 50);
        boolean actual = true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest7() {
        boolean result = Game.validityofMapSize(8, 55);
        boolean actual = false;
        assertEquals(result, actual);

    }

    //******** Game.validityofMode() tests ********\\
    @Test
    public void testmodeC() {
        assertTrue(Game.validityofMode("C"));

    }

    @Test
    public void testmodec() {
        assertTrue(Game.validityofMode("c"));

    }

    @Test
    public void testmodeI() {
        assertTrue(Game.validityofMode("I"));

    }

    @Test
    public void testmodei() {
        assertTrue(Game.validityofMode("i"));

    }

    @Test
    public void testmodewrong() {
        assertFalse(Game.validityofMode("r"));

    }

    //******** Game.checkGameend() tests ********\\
    @Test
    public void test_addplayerchoice2() {
        Game.checkGameend(false);
        assertFalse(gameend);
    }

    //******** Game.validateuserinput() tests ********\\
    @Test
    public void test_validateuserinput1() {
        assertFalse(Game.validateUserInput("k"));
    }

    @Test
    public void test_validateuserinput2() {
        assertTrue(Game.validateUserInput("R"));
    }

    @Test
    public void test_validateuserinput3() {
        assertTrue(Game.validateUserInput("D"));
    }

    @Test
    public void test_validateuserinput4() {
        assertTrue(Game.validateUserInput("L"));
    }

    @Test
    public void test_validateuserinput5() {
        assertTrue(Game.validateUserInput("U"));
    }


    //******** Game.validityofTeams() tests ********\\

    @Test
    public void test_validityofteams1() {
       boolean flag=  Game.validityofTeams(8, 3);
        assertTrue(flag);
    }

    @Test
    public void test_validityofteams2() {
        boolean flag=  Game.validityofTeams(2, 3);
        assertFalse(flag);
    }
    //******** Game.calculatePlayersPerTeam() tests ********\\
    @Test
    public void test_calculatePlayersperTeam1(){
        int sizeBefore1 = 0;
        int sizeBefore2 = 0;
        Game.calculatePlayersPerTeam(5,5);
     int sizeAfter1 =TeamPlayers.length;
     int sizeAfter2 = TeamPlayersCount.length;
        assertEquals(sizeBefore1+5,sizeAfter1);
        assertEquals(sizeBefore2+5,sizeAfter2);
    }

    @Test
    public void test_calculatePlayersperTeam2(){
        int sizeBefore1 = 0;
        int sizeBefore2 = 0;
        Game.calculatePlayersPerTeam(3,2);
        int sizeAfter1 =TeamPlayers.length;
        int sizeAfter2 = TeamPlayersCount.length;
        assertEquals(sizeBefore1+2,sizeAfter1);
        assertEquals(sizeBefore2+2,sizeAfter2);
    }
}
