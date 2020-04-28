package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.map.Pair;
import edu.cps2002.mazegame.player.Player;
import edu.cps2002.mazegame.player.Position;
import edu.cps2002.mazegame.utils.MapUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

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
        utils.deleteHTMLFiles();

    }

    //testing chooseDirection down
    @Test
    public void testchooseDirectionD(){
        Assert.assertEquals(Player.DIRECTION.DOWN, Game.chooseDirection("D"));

    }

    @Test
    public void testchooseDirectiond(){
        Assert.assertEquals(Player.DIRECTION.DOWN, Game.chooseDirection("d"));

    }
    //testing chooseDirection up
    @Test
    public void testchooseDirectionU(){
        assertEquals(Player.DIRECTION.UP, Game.chooseDirection("U"));

    }

    @Test
    public void testchooseDirectionu(){
        assertEquals(Player.DIRECTION.UP, Game.chooseDirection("u"));

    }
    //testing chooseDirection left
    @Test
    public void testchooseDirectionL(){
        assertEquals(Player.DIRECTION.LEFT, Game.chooseDirection("L"));

    }
    @Test
    public void testchooseDirectionl(){
        assertEquals(Player.DIRECTION.LEFT, Game.chooseDirection("l"));

    }

    //testing chooseDirection right
    @Test
    public void testchooseDirectionR(){
        assertEquals(Player.DIRECTION.RIGHT, Game.chooseDirection("R"));

    }

    @Test
    public void testchooseDirectionr(){
        assertEquals(Player.DIRECTION.RIGHT, Game.chooseDirection("r"));

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
    public void validityofMapSizeTest7(){
        boolean result= Game.validityofMapSize(8,55);
        boolean actual= false;
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
        Pair<Integer, Integer> x= Map.getTreasureTile();
        Position p = new Position(x.getKey(),x.getValue());

        p1.setPosition(p);
        flag= Game.checkWinner();
        assertTrue(flag);
        Game.playerList.clear();
    }

    @Test
    public void test2_Winner(){
        boolean flag;
        Map map = new Map();
        map.setMapSize(10);
        Game.generateHTMLFiles(10);
        Player p1 = new Player(4,5);
        Game.playerList.add(p1);
        edu.cps2002.mazegame.map.Pair<Integer, Integer> x= Map.getTreasureTile();
        Position p = new Position(x.getKey()+1,x.getValue()+1);
        p1.setPosition(p);
        flag= Game.checkWinner();
        assertFalse(flag);
        Game.playerList.clear();
    }

    @Test
    public void test_addplayerchoice2(){
        Game.checkGameend(false);
        assertFalse(gameend);
    }

//    @Test
//    public void test_checkmove1(){
//        Map map = new Map();
//        map.setMapSize(5);
//        Game.generateHTMLFiles(1);
//         ArrayList<Player> players = new ArrayList<Player>();
//         Player p1= new Player(4,6);
//         players.add(p1);
//        char tile = map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
//        boolean x =Game.checkwatertile(tile,0,players, Player.DIRECTION.DOWN);
//        assertFalse(x);
//        players.clear();
//      //  map.resetMap();
//        utils.deleteHTMLFiles();
//    }
//
//    @Test
//    public void test_checkmove2(){
//        Map map = new Map();
//        map.setMapSize(5);
//        Game.generateHTMLFiles(1);
//        ArrayList<Player> players = new ArrayList<Player>();
//        Player p1= new Player(4,6);
//        players.add(p1);
//        char tile = map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
//        boolean x =Game.checkwatertile(tile,0,players, Player.DIRECTION.UP);
//        assertFalse(x);
//        players.clear();
//        utils.deleteHTMLFiles();
//    }

//    @Test
//    public void test_checkmove3(){
//        Map map = new Map();
//        map.setMapSize(6);
//        Game.generateHTMLFiles(1);
//        ArrayList<Player> players = new ArrayList<Player>();
//        Player p1= new Player(4,6);
//        Player p2= new Player(5,6);
//        players.add(p1);
//        players.add(p2);
//        boolean x =Game.checkmove('W' ,0,players, Player.DIRECTION.RIGHT);
//        assertFalse(x);
//        players.clear();
//        utils.deleteHTMLFiles();
//    }

}