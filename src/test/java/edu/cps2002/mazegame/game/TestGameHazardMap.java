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

import static edu.cps2002.mazegame.game.Game.*;
import static org.junit.Assert.*;

public class TestGameHazardMap {

    MapUtils utils = new MapUtils();

    @Before
    public void setup() {
     //   Game.map = MapFactory.getInstance("H");

    }

    @After
    public void tearDown() {
        Game.map.resetMap();
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

    //******** Game.initialiseTeams() tests ********\\
    public Map hazardousMap;
    @Test
    public void test_initialiseTeams1() {
        playerList.clear();
        playerChoice.clear();
        hazardousMap = MapFactory.getInstance("H");
        int players = 5;
        int teams = 2;
        hazardousMap.setMapSize(8);
        calculatePlayersPerTeam(players,teams);
        TeamPlayers[0] =3;
        TeamPlayers[1] =2;
        Game.generateHTMLFiles(TeamPlayers,teams);
        Game.initialiseTeams(TeamPlayers, teams);
        int sizeAfter = teamList.size();
        assertEquals(2,sizeAfter);
    }

    @Test
    public void test_initialiseTeams2() {
        playerList.clear();
        playerChoice.clear();
        hazardousMap = MapFactory.getInstance("H");
        int players = 5;
        int teams = 3;
        hazardousMap.setMapSize(8);
        calculatePlayersPerTeam(players,teams);
        TeamPlayers[0] =3;
        TeamPlayers[1] =2;
        TeamPlayers[2] =2;
        Game.generateHTMLFiles(TeamPlayers,teams);
        Game.initialiseTeams(TeamPlayers, teams);
        int sizeAfter = teamList.size();
        assertNotEquals(2,sizeAfter);
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
        boolean x =Game.moveToNewTile(tile,0,players, Player.DIRECTION.DOWN);
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
        boolean x =Game.moveToNewTile(tile,0,players, Player.DIRECTION.UP);
        assertFalse(x);
        players.clear();
        utils.deleteHTMLFiles();
    }
//    @Test
//    public void testHazardMap_checkwatertile3(){
//        Game.map = MapFactory.getInstance("H");
//        Game.map.setMapSize(8);
//        Game.generateHTMLFiles(2);
//        ArrayList<Player> players = new ArrayList<Player>();
//        Player p1= new Player(4,5, Game.map);
//        Player p2= new Player(5,6, Game.map);
//        players.add(p1);
//        players.add(p2);
//        boolean x =Game.moveToNewTile('W' ,1,players, Player.DIRECTION.RIGHT);
//        assertFalse(x);
//        players.clear();
//        utils.deleteHTMLFiles();
//    }
}
