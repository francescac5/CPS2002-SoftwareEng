package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.map.MapFactory;
import edu.cps2002.mazegame.map.Pair;
import edu.cps2002.mazegame.player.Player;
import edu.cps2002.mazegame.player.Position;
import edu.cps2002.mazegame.utils.MapUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestGameHazardMap {

    MapUtils utils = new MapUtils();

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {
        utils.deleteHTMLFiles();
    }

    //******** Game.initialisePlayers() tests ********\\

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
    public void test1HazardMap_Winner(){
        boolean flag;
        Game.map = MapFactory.getInstance("H");
        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(30);

        Game.generateHTMLFiles(10);
        Player p1 = new Player(4,5, Game.map);
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
        Player p1 = new Player(4,5, Game.map);
        Game.playerList.add(p1);
        edu.cps2002.mazegame.map.Pair<Integer, Integer> x= Map.getTreasureTile();
        Position p = new Position(x.getKey()+1,x.getValue()+1);
        p1.setPosition(p);
        flag= Game.checkWinner();
        assertFalse(flag);
        Game.playerList.clear();
    }

    //******** Game.checkwatertile() tests ********\\

    @Test
    public void testHazardMap_checkwatertile1(){
        Game.map = MapFactory.getInstance("H");
        Game.map.setMapSize(5);
        Game.generateHTMLFiles(1);
        ArrayList<Player> players = new ArrayList<Player>();
        Player p1= new Player(4,6, Game.map);
        players.add(p1);
        char tile = Game.map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
        boolean x =Game.checkwatertile(tile,1,players.get(0), Player.DIRECTION.DOWN);
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
        Player p1= new Player(4,6, Game.map);
        players.add(p1);
        char tile = Game.map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
        boolean x =Game.checkwatertile(tile,1,players.get(0), Player.DIRECTION.UP);
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
        Player p1= new Player(4,6, Game.map);
        Player p2= new Player(5,6, Game.map);
        players.add(p1);
        players.add(p2);
        boolean x =Game.checkwatertile('W' ,1,players.get(0), Player.DIRECTION.RIGHT);
        assertFalse(x);
        players.clear();
        utils.deleteHTMLFiles();
    }
}
