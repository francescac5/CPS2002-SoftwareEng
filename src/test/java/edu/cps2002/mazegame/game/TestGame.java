package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.map.*;
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


    //******** Game.chooseDirection() tests ********\\
    //testing chooseDirection down (capital letter)
    @Test
    public void testchooseDirectionD(){
        Assert.assertEquals(Player.DIRECTION.DOWN, Game.chooseDirection("D"));

    }

    //testing chooseDirection down (small letter)
    @Test
    public void testchooseDirectiond(){
        Assert.assertEquals(Player.DIRECTION.DOWN, Game.chooseDirection("d"));

    }
    //testing chooseDirection up (capital letter)
    @Test
    public void testchooseDirectionU(){
        assertEquals(Player.DIRECTION.UP, Game.chooseDirection("U"));

    }

    //testing chooseDirection up (small letter)
    @Test
    public void testchooseDirectionu(){
        assertEquals(Player.DIRECTION.UP, Game.chooseDirection("u"));

    }

    //testing chooseDirection left (capital letter)
    @Test
    public void testchooseDirectionL(){
        assertEquals(Player.DIRECTION.LEFT, Game.chooseDirection("L"));

    }

    //testing chooseDirection left (small letter)
    @Test
    public void testchooseDirectionl(){
        assertEquals(Player.DIRECTION.LEFT, Game.chooseDirection("l"));

    }

    //testing chooseDirection right (capital letter)
    @Test
    public void testchooseDirectionR(){
        assertEquals(Player.DIRECTION.RIGHT, Game.chooseDirection("R"));

    }

    //testing chooseDirection right (small letter)
    @Test
    public void testchooseDirectionr(){
        assertEquals(Player.DIRECTION.RIGHT, Game.chooseDirection("r"));

    }

    //******** Game.validityofPlayers() tests ********\\
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

    //******** Game.validityofMapSize() tests ********\\
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
    //******** Game.validityofMode() tests ********\\
    @Test
    public void testmodeC(){
        assertTrue(Game.validityofMode("C"));

    }

    @Test
    public void testmodec(){
        assertTrue(Game.validityofMode("c"));

    }

    @Test
    public void testmodeI(){
        assertTrue(Game.validityofMode("I"));

    }

    @Test
    public void testmodei(){
        assertTrue(Game.validityofMode("i"));

    }

    @Test
    public void testmodewrong(){
        assertFalse(Game.validityofMode("r"));

    }

    //******** Game.initialisePlayers() tests ********\\
    @Test
    public void initialisePlayersTest1_SafeMap(){
        int players = 3;

        Game.map = MapFactory.getInstance("S"); 
        Game.map.setMapSize(5);
        Game.map.setWaterPercentage(5);

        Game.generateHTMLFiles(3);

        int sizeBefore = Game.playerList.size();

        Game.initialisePlayers(players);

        int sizeAfter = Game.playerList.size();

        assertEquals(sizeBefore+3, sizeAfter);

        utils.deleteHTMLFiles();
    }

    @Test
    public void initialisePlayersTest2_SafeMap(){
        int players = 10;

        Game.map = MapFactory.getInstance("S"); 
        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(5);

        Game.generateHTMLFiles(10);

        int sizeBefore = Game.playerList.size();

        Game.initialisePlayers(players);

        int sizeAfter = Game.playerList.size();

        assertEquals(sizeBefore+10, sizeAfter);

        utils.deleteHTMLFiles();
    }

    @Test
    public void initialisePlayersTest1_HazardMap(){
        int players = 3;

        Game.map = MapFactory.getInstance("H"); 
        Game.map.setMapSize(5);
        Game.map.setWaterPercentage(30);

        Game.generateHTMLFiles(3);

        int sizeBefore = Game.playerList.size();

        Game.initialisePlayers(players);

        int sizeAfter = Game.playerList.size();

        assertEquals(sizeBefore+3, sizeAfter);

        utils.deleteHTMLFiles();
    }

    @Test
    public void initialisePlayersTest2_HazardMap(){
        int players = 10;

        Game.map = MapFactory.getInstance("H"); 
        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(30);

        Game.generateHTMLFiles(10);

        int sizeBefore = Game.playerList.size();

        Game.initialisePlayers(players);

        int sizeAfter = Game.playerList.size();

        assertEquals(sizeBefore+10, sizeAfter);

        utils.deleteHTMLFiles();
    }

    //******** Game.checkWinner() tests ********\\
    @Test
    public void test1SafeMap_Winner(){
        boolean flag;
        Game.map = MapFactory.getInstance("S"); 
        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(5);

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
    public void test2SafeMap_Winner(){
        boolean flag;
        Game.map = MapFactory.getInstance("S"); 
        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(5);

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
    public void test1HazardMap_Winner(){
        boolean flag;
        Game.map = MapFactory.getInstance("H"); 
        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(30);

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
    public void test2HazardMap_Winner(){
        boolean flag;
        Game.map = MapFactory.getInstance("H"); 
        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(30);

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

    //******** Game.checkGameend() tests ********\\
    @Test
    public void test_addplayerchoice2(){
        Game.checkGameend(false);
        assertFalse(gameend);
    }

    //******** Game.checkwatertile() tests ********\\
    @Test
    public void testSafeMap_checkwatertile1(){
        Game.map = MapFactory.getInstance("S"); 
        Game.map.setMapSize(5);
        Game.generateHTMLFiles(1);
         ArrayList<Player> players = new ArrayList<Player>();
         Player p1= new Player(4,6);
         players.add(p1);
        char tile = Game.map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
        boolean x =Game.checkwatertile(tile,0,players, Player.DIRECTION.DOWN);
        assertFalse(x);
        players.clear();
      //  hazardousMap.resetMap();
        utils.deleteHTMLFiles();
    }

    @Test
    public void testSafeMap_checkwatertile2(){
        Game.map = MapFactory.getInstance("S"); 
        Game.map.setMapSize(5);
        Game.generateHTMLFiles(1);
        ArrayList<Player> players = new ArrayList<Player>();
        Player p1= new Player(4,6);
        players.add(p1);
        char tile = Game.map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
        boolean x =Game.checkwatertile(tile,0,players, Player.DIRECTION.UP);
        assertFalse(x);
        players.clear();
        utils.deleteHTMLFiles();
    }

    @Test
    public void testSafeMap_checkwatertile3() {
        Game.map = MapFactory.getInstance("S"); 
        Game.map.setMapSize(6);
        Game.generateHTMLFiles(1);
        ArrayList<Player> players = new ArrayList<Player>();
        Player p1 = new Player(4, 6);
        Player p2 = new Player(5, 6);
        players.add(p1);
        players.add(p2);
        boolean x = Game.checkwatertile('W', 0, players, Player.DIRECTION.RIGHT);
        assertFalse(x);
        players.clear();
        utils.deleteHTMLFiles();
    }
        @Test
        public void testHazardMap_checkwatertile1(){
            Game.map = MapFactory.getInstance("H"); 
            Game.map.setMapSize(5);
            Game.generateHTMLFiles(1);
            ArrayList<Player> players = new ArrayList<Player>();
            Player p1= new Player(4,6);
            players.add(p1);
            char tile = Game.map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
            boolean x =Game.checkwatertile(tile,0,players, Player.DIRECTION.DOWN);
            assertFalse(x);
            players.clear();
            //  hazardousMap.resetMap();
            utils.deleteHTMLFiles();
        }

        @Test
        public void testHazardMap_checkwatertile2(){
            Game.map = MapFactory.getInstance("H"); 
            Game.map.setMapSize(5);
            Game.generateHTMLFiles(1);
            ArrayList<Player> players = new ArrayList<Player>();
            Player p1= new Player(4,6);
            players.add(p1);
            char tile = Game.map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
            boolean x =Game.checkwatertile(tile,0,players, Player.DIRECTION.UP);
            assertFalse(x);
            players.clear();
            utils.deleteHTMLFiles();
        }

        @Test
        public void testHazardMap_checkwatertile3(){
            Game.map = MapFactory.getInstance("H"); 
            Game.map.setMapSize(6);
            Game.generateHTMLFiles(1);
            ArrayList<Player> players = new ArrayList<Player>();
            Player p1= new Player(4,6);
            Player p2= new Player(5,6);
            players.add(p1);
            players.add(p2);
            boolean x =Game.checkwatertile('W' ,0,players, Player.DIRECTION.RIGHT);
            assertFalse(x);
            players.clear();
            utils.deleteHTMLFiles();
        }

    //******** Game.validateuserinput() tests ********\\
    @Test
    public void test_validateuserinput1(){
        assertFalse(Game.validateuserinput("k"));
    }

    @Test
    public void test_validateuserinput2(){
        assertTrue(Game.validateuserinput("R"));
    }

    @Test
    public void test_validateuserinput3(){
        assertTrue(Game.validateuserinput("D"));
    }

    @Test
    public void test_validateuserinput4(){
        assertTrue(Game.validateuserinput("L"));
    }

    @Test
    public void test_validateuserinput5(){
        assertTrue(Game.validateuserinput("U"));
    }
}