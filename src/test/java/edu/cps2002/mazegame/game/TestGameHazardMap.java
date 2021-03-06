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
import java.util.Arrays;

import static edu.cps2002.mazegame.game.Game.*;
import static org.junit.Assert.*;

public class TestGameHazardMap {

    MapUtils utils = new MapUtils();

    @Before
    public void setup() {
        Game.map = MapFactory.getInstance("H");
    }

    @After
    public void tearDown() {
        utils.deleteHTMLFiles();
        Game.map.resetMap();
        TeamPlayers=null;
        teamList.clear();
    }

    //******** Game.initialisePlayers() tests ********\\

    @Test
    public void testingTheInitialisationOfPlayersFor3Players_HazardMap(){
        int players = 3;

        Game.map.setMapSize(5);
        Game.map.setWaterPercentage(30);

        Game.generateHTMLFiles(3);

        int sizeBefore = Game.playerList.size();

        Game.initialisePlayers(players);

        int sizeAfter = Game.playerList.size();

        assertEquals(sizeBefore+3, sizeAfter);
    }

    @Test
    public void testingTheInitialisationOfPlayersFor10Players_HazardMap(){
        int players = 10;

        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(30);

        Game.generateHTMLFiles(10);

        int sizeBefore = Game.playerList.size();

        Game.initialisePlayers(players);

        int sizeAfter = Game.playerList.size();

        assertEquals(sizeBefore+10, sizeAfter);
    }
    //******** Game.initialiseTeams() tests ********\\
    public Map hazardousMap;
    @Test
    public void testingTheInitialisationOfTeamsfor2Teams_HazardMap() {
        playerList.clear();
        playerChoice.clear();
        hazardousMap= MapFactory.getInstance("H");
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
    public void testingTheInitialisationOfTeamsfor3Teams_HazardMap() {
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
    public void testingtheWinnerWhenPlayerIsOnTreasureTile(){
        boolean flag;

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
    public void testingtheWinnerWhenPlayerIsNotOnTreasureTile(){
        boolean flag;

        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(30);
        Game.generateHTMLFiles(10);

        Player p1 = new Player(4,5, Game.map);
        Game.playerList.add(p1);

        //to ensure player doesn't go out of map
        Pair<Integer, Integer> treasureTile= Map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        if(x < Game.map.getMapSize()-1){
            x+=1;
        }else{
            x-=1;
        }

        Position p = new Position(x,y);
        p1.setPosition(p);
        flag= Game.checkWinner();
        assertFalse(flag);
        Game.playerList.clear();
    }

//******** Game.checkWinnerTeam() tests ********\\

    @Test
    public void checkWinnerWhenOneOfThePlayersInATeamIsOnTreasure(){
        boolean flag;

        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(30);
        Game.generateHTMLFiles(10);

        Player p1 = new Player(4,5, Game.map);
        Game.teamList.add(new ArrayList<>());
        Game.teamList.get(0).add(p1);

        Pair<Integer, Integer> x= Map.getTreasureTile();
        Position p = new Position(x.getKey(),x.getValue());

        p1.setPosition(p);
        flag = Game.checkWinnerTeam();
        assertTrue(flag);
        Game.teamList.clear();
    }

    @Test
    public void checkWinnerWhenOneOfThePlayersInATeamIsNotOnTreasure1(){
        boolean flag;

        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(30);
        Game.generateHTMLFiles(10);

        Player p1 = new Player(4,5, Game.map);
        Game.teamList.add(new ArrayList<>());
        Game.teamList.get(0).add(p1);

        Pair<Integer, Integer> treasureTile = Map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        if(x < Game.map.getMapSize()-1){
            x+=1;
        }else{
            x-=1;
        }

        Position p = new Position(x,y);
        p1.setPosition(p);
        flag = Game.checkWinnerTeam();
        assertFalse(flag);
        Game.teamList.clear();
    }

    @Test
    public void checkWinnerWhenOneOfThePlayersInATeamIsNotOnTreasure2(){
        boolean flag;

        Game.map.setMapSize(10);
        Game.map.setWaterPercentage(30);
        Game.generateHTMLFiles(10);

        Player p1 = new Player(4,5, Game.map);
        Player p2 = new Player(4,5, Game.map);
        Game.teamList.add(new ArrayList<>());
        Game.teamList.get(0).add(p1);

        Pair<Integer, Integer> treasureTile = Map.getTreasureTile();
        int x = treasureTile.getKey();
        int y = treasureTile.getValue();

        if(x < Game.map.getMapSize()-1){
            x+=1;
        }else{
            x-=1;
        }

        Position p = new Position(x,y);
        p2.setPosition(p);
        flag = Game.checkWinnerTeam();
        assertFalse(flag);
        Game.teamList.clear();
    }

    //******** Game.moveToNewTile() tests ********\\

    @Test
    public void test1ForMoveToANewTile(){
        Game.map.setMapSize(5);
        Game.map.setWaterPercentage(30);
        Game.generateHTMLFiles(1);

        ArrayList<Player> players = new ArrayList<>();
        Player p1= new Player(4,6, Game.map);
        players.add(p1);
        char tile = Game.map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
        boolean x =Game.moveToNewTile(tile,1,players.get(0), Player.DIRECTION.DOWN);
        assertFalse(x);
        players.clear();
    }

    @Test
    public void test2ForMoveToANewTile(){
        Game.map.setMapSize(5);
        Game.map.setWaterPercentage(30);
        Game.generateHTMLFiles(1);

        ArrayList<Player> players = new ArrayList<>();
        Player p1= new Player(4,6, Game.map);
        players.add(p1);
        char tile = Game.map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
        boolean x =Game.moveToNewTile(tile,1,players.get(0), Player.DIRECTION.UP);
        assertFalse(x);
        players.clear();
    }

    @Test
    public void testingMovetoWaterTile(){
        Game.map.setMapSize(6);
        Game.map.setWaterPercentage(30);
        Game.generateHTMLFiles(1);

        ArrayList<Player> players = new ArrayList<>();
        Player p1= new Player(5,5, Game.map);
        Player p2= new Player(4,5, Game.map);
        players.add(p1);
        players.add(p2);
        boolean x = Game.moveToNewTile('W' ,1, players.get(0), Player.DIRECTION.RIGHT);
        assertTrue(x);
        players.clear();
    }
}
