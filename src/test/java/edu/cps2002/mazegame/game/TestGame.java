package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.player.Player;
import edu.cps2002.mazegame.player.Position;
import edu.cps2002.mazegame.utils.MapUtils;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static edu.cps2002.mazegame.game.Game.gameend;
import static org.junit.Assert.*;

public class TestGame {
    public Player p1;
    Position pos = new Position(4,5);
    MapUtils utils = new MapUtils();

    @Before
    public void setup() {
        Position pos = new Position (4,5);
        p1 = new Player(4,5);
    }
    @After
    public void tearDown() {
        p1 = null;
        pos= null;
    }

    //testing chooseDirection down
    @Test
    public void testchooseDirectionD(){
        Assert.assertEquals(Player.DIRECTION.DOWN, Game.chooseDirection("D"));

    }
    //testing chooseDirection up
    @Test
    public void testchooseDirectionU(){
        assertEquals(Player.DIRECTION.UP, Game.chooseDirection("U"));

    }
    //testing chooseDirection left
    @Test
    public void testchooseDirectionL(){
        assertEquals(Player.DIRECTION.LEFT, Game.chooseDirection("L"));

    }

    //testing chooseDirection right
    @Test
    public void testchooseDirectionR(){
        assertEquals(Player.DIRECTION.RIGHT, Game.chooseDirection("R"));

    }

    @Test
    public void validityofPlayersTest1(){
        boolean result= Game.validityofPlayers(2);
        boolean actual= true;
        assertEquals(result, actual);

    }
    @Test
    public void validityofPlayersTest2(){
        boolean result= Game.validityofPlayers(4);
        boolean actual= true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofPlayersTest3(){
        boolean result= Game.validityofPlayers(5);
        boolean actual= true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofPlayersTest4(){
        boolean result= Game.validityofPlayers(8);
        boolean actual= true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofPlayersTest5(){
        boolean result= Game.validityofPlayers(1);
        boolean actual= false;
        assertEquals(result, actual);

    }

    @Test
    public void validityofPlayersTest6(){
        boolean result= Game.validityofPlayers(9);
        boolean actual= false;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest1(){
        boolean result= Game.validityofMapSize(2,5);
        boolean actual= true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest2(){
        boolean result= Game.validityofMapSize(2,4);
        boolean actual= false;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest3(){
        boolean result= Game.validityofMapSize(2,8);
        boolean actual= true;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest4(){
        boolean result= Game.validityofMapSize(5,5);
        boolean actual= false;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest5(){
        boolean result= Game.validityofMapSize(8,5);
        boolean actual= false;
        assertEquals(result, actual);

    }

    @Test
    public void validityofMapSizeTest6(){
        boolean result= Game.validityofMapSize(8,50);
        boolean actual= true;
        assertEquals(result, actual);

    }

    @Test
    public void initialisePlayersTest1(){
        int players = 3;

        Map map = new Map();
        map.setMapSize(5);

        Game.generateHTMLFiles(3);

        int sizeBefore = Game.playerList.size();

        Game.initialisePlayers(players);

        int sizeAfter = Game.playerList.size();

        assertEquals(sizeBefore+3, sizeAfter);

        utils.deleteHTMLFiles();
    }

    @Test
    public void initialisePlayersTest2(){
        int players = 10;

        Map map = new Map();
        map.setMapSize(10);

        Game.generateHTMLFiles(10);

        int sizeBefore = Game.playerList.size();

        Game.initialisePlayers(players);

        int sizeAfter = Game.playerList.size();

        assertEquals(sizeBefore+10, sizeAfter);

        utils.deleteHTMLFiles();
    }

    @Test
    public void test1_Winner(){
        boolean flag;
        Map map = new Map();
        map.setMapSize(10);
        Game.generateHTMLFiles(10);
        Player p1 = new Player(4,5);
        Game.playerList.add(p1);
        Pair<Integer, Integer> x= map.getTreasureTile();
        x.getKey();
        x.getValue();
        Position p = new Position(x.getKey(),x.getValue());
        p1.setPosition(p);
        flag= Game.checkWinner();
        assertTrue(flag);

        utils.deleteHTMLFiles();
    }

    @Test
    public void test2_Winner(){
        boolean flag;
        Map map = new Map();
        map.setMapSize(10);
        Game.generateHTMLFiles(10);
        Player p1 = new Player(4,5);
        Game.playerList.add(p1);
        Pair<Integer, Integer> x= Map.getTreasureTile();
        x.getKey();
        x.getValue();
        Position p = new Position(x.getKey()+1,x.getValue()+1);
        p1.setPosition(p);
        flag= Game.checkWinner();
        assertFalse(flag);
        utils.deleteHTMLFiles();
    }


    @Test
    public void test_checkGameend(){
        Game.checkGameend(false);
        assertFalse(gameend);
    }
}