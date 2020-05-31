package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.map.MapFactory;
import edu.cps2002.mazegame.player.Player;
import edu.cps2002.mazegame.utils.MapUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestTeamManagerHazardousMap {
    private TeamManager teamManager;
    private Map hazardousMap;
    private int teamNo;
    private MapUtils utils = new MapUtils();
    private ArrayList<Map.Tiles> initOrGrey = new ArrayList<>();

    @Before
    public void setup() {
        teamNo = 1;
        this.teamManager = new TeamManager(teamNo);
        this.hazardousMap = MapFactory.getInstance("H");
        this.hazardousMap.setMapSize(5);
        this.initOrGrey.add(Map.Tiles.GREY);
        this.initOrGrey.add(Map.Tiles.GRASS_PLAYER);
    }

    @After
    public void tearDown() {
        this.teamManager = null;
        hazardousMap.resetMap();
        utils.deleteHTMLFiles();
    }

//******** teamManager.getTeamNo() tests ********\\

    @Test
    public void testGetTeamNo_1Team() {
        int num = teamManager.getTeamNo();

        assertEquals(1, num);
    }

    @Test
    public void testGetTeamNo_2Teams() {
        int num = teamManager.getTeamNo();

        //creating second team manager
        TeamManager teamManager1 = new TeamManager(teamNo+1);
        int num1 = teamManager1.getTeamNo();

        assertEquals(1, num);
        assertEquals(2, num1);
    }

    //******** teamManager.register() tests ********\\
    @Test
    public void testRegister_1Player() {
        int sizeBefore = teamManager.observers.size();
        new Player(3, 4, hazardousMap, 0, teamManager); //player is registering to team manager
        int sizeAfter = teamManager.observers.size();

        assertEquals(0, sizeBefore);
        assertEquals(1, sizeAfter);
    }

    @Test
    public void testRegister_2Players() {
        int sizeBefore = teamManager.observers.size();
        new Player(3, 4, hazardousMap, 0, teamManager); //player is registering to team manager
        new Player(3, 4, hazardousMap, 1, teamManager); //player is registering to team manager
        int sizeAfter = teamManager.observers.size();

        assertEquals(0, sizeBefore);
        assertEquals(2, sizeAfter);
    }

//******** teamManager.unregister() tests ********\\

    @Test
    public void testUnregister_1Player() {
        //player registers
        Player p = new Player(3, 4, hazardousMap, 0, teamManager);
        int sizeBefore = teamManager.observers.size();

        //unregister player
        teamManager.unregister(p);
        int sizeAfter = teamManager.observers.size();

        assertEquals(1, sizeBefore);
        assertEquals(0, sizeAfter);
    }

    @Test
    public void testUnregister_2Players() {
        //player registers
        Player p = new Player(3, 4, hazardousMap, 0, teamManager);
        Player p2 = new Player(3, 4, hazardousMap, 1, teamManager);
        int sizeBefore = teamManager.observers.size();

        //unregister player 0
        teamManager.unregister(p);
        int sizeAfter = teamManager.observers.size();

        assertEquals(2, sizeBefore);
        assertEquals(1, sizeAfter);
        sizeBefore = sizeAfter;

        //unregister player 0
        teamManager.unregister(p2);
        sizeAfter = teamManager.observers.size();

        assertEquals(1, sizeBefore);
        assertEquals(0, sizeAfter);
    }

//******** teamManager.setRevealedTile() tests ********\\

    @Test
    public void testSetRevealedTile_1Player_1Team() {
        //player registers
        int playerNum = 0;
        Player p = new Player(3, 4, hazardousMap, playerNum, teamManager);

        //generate team with 1 player
        hazardousMap.generate(1);

        //to ensure revealed tiles are not initial position
        int x = hazardousMap.getPlayerInitPositionX(teamNo);
        int y = hazardousMap.getPlayerInitPositionY(teamNo);

        if(x < hazardousMap.getMapSize()-1){
            x+=1;
        }else{
            x-=1;
        }

        Map.Tiles[][] mapBefore = hazardousMap.getTeamPlayerMap(teamNo, playerNum);
        assertEquals(Map.Tiles.GREY, mapBefore[x][y]);

        teamManager.setRevealedTile(x, y);

        Map.Tiles[][] mapAfter = hazardousMap.getTeamPlayerMap(teamNo, playerNum);

        assertNotEquals(Map.Tiles.GREY, mapAfter[x][y]);
    }

    @Test
    public void testSetRevealedTile_2Players_1Team() {
        //players register
        int playerNum = 0;
        Player p = new Player(3, 4, hazardousMap, playerNum, teamManager);
        Player p1 = new Player(3, 4, hazardousMap, playerNum+1, teamManager);

        //generate team with 2 players
        hazardousMap.generate(2);

        //to ensure revealed tiles are not initial position
        int x = hazardousMap.getPlayerInitPositionX(teamNo);
        int y = hazardousMap.getPlayerInitPositionY(teamNo);

        if(x < hazardousMap.getMapSize()-1){
            x+=1;
        }else{
            x-=1;
        }

        Map.Tiles[][] mapBefore = hazardousMap.getTeamPlayerMap(teamNo, playerNum);
        Map.Tiles[][] mapBefore1 = hazardousMap.getTeamPlayerMap(teamNo, playerNum+1);
        assertEquals(Map.Tiles.GREY, mapBefore[x][y]);
        assertEquals(Map.Tiles.GREY, mapBefore1[x][y]);

        teamManager.setRevealedTile(x, y);

        Map.Tiles[][] mapAfter = hazardousMap.getTeamPlayerMap(teamNo, playerNum);
        Map.Tiles[][] mapAfter1 = hazardousMap.getTeamPlayerMap(teamNo, playerNum+1);

        assertNotEquals(Map.Tiles.GREY, mapAfter[x][y]);
        assertNotEquals(Map.Tiles.GREY, mapAfter1[x][y]);
    }

    @Test
    public void testSetRevealedTile_1Player_2Team() {
        //players register
        int playerNum = 0;
        Player p = new Player(3, 4, hazardousMap, playerNum, teamManager);

        //generate team with 2 players
        hazardousMap.generate(1);

        //to ensure revealed tiles are not initial position
        int x = hazardousMap.getPlayerInitPositionX(teamNo);
        int y = hazardousMap.getPlayerInitPositionY(teamNo);

        if(x < hazardousMap.getMapSize()-1){
            x+=1;
        }else{
            x-=1;
        }

        Map.Tiles[][] mapBefore = hazardousMap.getTeamPlayerMap(teamNo, playerNum);
        assertEquals(Map.Tiles.GREY, mapBefore[x][y]);

        //players register
        int newTeamNo = teamNo + 1;
        TeamManager teamManager2 = new TeamManager(newTeamNo);
        Player p2 = new Player(3, 4, hazardousMap, playerNum, teamManager2);

        //generate team2 with 2 players
        hazardousMap.generate(2);

        //to ensure revealed tiles are not initial position
        int x2 = hazardousMap.getPlayerInitPositionX(newTeamNo);
        int y2 = hazardousMap.getPlayerInitPositionY(newTeamNo);

        if(x2 < hazardousMap.getMapSize()-1){
            x2+=1;
        }else{
            x2-=1;
        }

        Map.Tiles[][] mapBefore2 = hazardousMap.getTeamPlayerMap(newTeamNo, playerNum);
        assertEquals(Map.Tiles.GREY, mapBefore2[x2][y2]);

        teamManager.setRevealedTile(x, y);

        Map.Tiles[][] mapAfter = hazardousMap.getTeamPlayerMap(teamNo, playerNum);

        //assert that 2nd team map was left untouched
        Map.Tiles[][] mapAfter2 = hazardousMap.getTeamPlayerMap(newTeamNo, playerNum);

        assertNotEquals(Map.Tiles.GREY, mapAfter[x][y]);
        assertTrue(initOrGrey.contains(mapAfter2[x][y]));

        teamManager2.setRevealedTile(x2, y2);

        //assert that 1st team map was left untouched
        mapAfter = hazardousMap.getTeamPlayerMap(teamNo, playerNum);

        //assert that 2nd team map was left untouched
        mapAfter2 = hazardousMap.getTeamPlayerMap(newTeamNo, playerNum);

        assertNotEquals(Map.Tiles.GREY, mapAfter2[x2][y2]);
    }

    @Test
    public void testSetRevealedTile_2Player_2Team() {
        //players register
        int playerNum = 0;
        Player p = new Player(3, 4, hazardousMap, playerNum, teamManager);
        Player p1 = new Player(3, 4, hazardousMap, playerNum+1, teamManager);

        //generate team with 2 players
        hazardousMap.generate(2);

        //to ensure revealed tiles are not initial position
        int x = hazardousMap.getPlayerInitPositionX(teamNo);
        int y = hazardousMap.getPlayerInitPositionY(teamNo);

        if(x < hazardousMap.getMapSize()-1){
            x+=1;
        }else{
            x-=1;
        }

        Map.Tiles[][] mapBefore = hazardousMap.getTeamPlayerMap(teamNo, playerNum);
        Map.Tiles[][] mapBefore1 = hazardousMap.getTeamPlayerMap(teamNo, playerNum+1);
        assertEquals(Map.Tiles.GREY, mapBefore[x][y]);
        assertEquals(Map.Tiles.GREY, mapBefore1[x][y]);

        //players register
        playerNum = 0;
        int newTeamNo = teamNo + 1;
        TeamManager teamManager2 = new TeamManager(newTeamNo);
        Player p2 = new Player(3, 4, hazardousMap, playerNum, teamManager2);
        Player p3 = new Player(3, 4, hazardousMap, playerNum+1, teamManager2);

        //generate team2 with 2 players
        hazardousMap.generate(2);

        //to ensure revealed tiles are not initial position
        int x2 = hazardousMap.getPlayerInitPositionX(newTeamNo);
        int y2 = hazardousMap.getPlayerInitPositionY(newTeamNo);

        if(x2 < hazardousMap.getMapSize()-1){
            x2+=1;
        }else{
            x2-=1;
        }

        Map.Tiles[][] mapBefore2 = hazardousMap.getTeamPlayerMap(newTeamNo, playerNum);
        Map.Tiles[][] mapBefore3 = hazardousMap.getTeamPlayerMap(newTeamNo, playerNum+1);
        assertEquals(Map.Tiles.GREY, mapBefore2[x2][y2]);
        assertEquals(Map.Tiles.GREY, mapBefore3[x2][y2]);

        teamManager.setRevealedTile(x, y);

        Map.Tiles[][] mapAfter = hazardousMap.getTeamPlayerMap(teamNo, playerNum);
        Map.Tiles[][] mapAfter1 = hazardousMap.getTeamPlayerMap(teamNo, playerNum+1);

        //assert that 2nd team map was left untouched
        Map.Tiles[][] mapAfter2 = hazardousMap.getTeamPlayerMap(newTeamNo, playerNum);
        Map.Tiles[][] mapAfter3 = hazardousMap.getTeamPlayerMap(newTeamNo, playerNum+1);

        assertNotEquals(Map.Tiles.GREY, mapAfter[x][y]);
        assertNotEquals(Map.Tiles.GREY, mapAfter1[x][y]);

        assertTrue(initOrGrey.contains(mapAfter2[x][y]));
        assertTrue(initOrGrey.contains(mapAfter3[x][y]));

        teamManager2.setRevealedTile(x2, y2);

        //assert that 1st team map was left untouched
        mapAfter = hazardousMap.getTeamPlayerMap(teamNo, playerNum);
        mapAfter1 = hazardousMap.getTeamPlayerMap(teamNo, playerNum+1);

        //assert that 2nd team map was left untouched
        mapAfter2 = hazardousMap.getTeamPlayerMap(newTeamNo, playerNum);
        mapAfter3 = hazardousMap.getTeamPlayerMap(newTeamNo, playerNum+1);

        assertNotEquals(Map.Tiles.GREY, mapAfter2[x2][y2]);
        assertNotEquals(Map.Tiles.GREY, mapAfter3[x2][y2]);

        if(!(x == x2 && y == y2)) {
            assertTrue(initOrGrey.contains(mapAfter[x2][y2]));
            assertTrue(initOrGrey.contains(mapAfter1[x2][y2]));
        }
    }
}