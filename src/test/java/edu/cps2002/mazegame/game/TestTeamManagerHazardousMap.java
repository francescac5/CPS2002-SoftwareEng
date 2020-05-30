package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.map.MapFactory;
import edu.cps2002.mazegame.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTeamManagerHazardousMap {
    private TeamManager teamManager;
    private Map hazardousMap;
    private int teamNo;

    @Before
    public void setup() {
        this.teamManager = new TeamManager(teamNo);
        this.hazardousMap = MapFactory.getInstance("H");
        teamNo = 0;
    }

    @After
    public void tearDown() {
        this.teamManager = null;
        this.hazardousMap = null;
    }

//******** teamManager.getTeamNo() tests ********\\

    @Test
    public void testGetTeamNo_1Team(){
        int num = teamManager.getTeamNo();

        assertEquals(0, num);
    }

    @Test
    public void testGetTeamNo_2Teams(){
        int num = teamManager.getTeamNo();

        //creating second team manager
        TeamManager teamManager1 = new TeamManager(1);
        int num1 = teamManager1.getTeamNo();

        assertEquals(0, num);
        assertEquals(1, num1);
    }

    //******** teamManager.register() tests ********\\
    @Test
    public void testRegister_1Player() {
        int sizeBefore = teamManager.observers.size();
        new Player(3,4,hazardousMap, 0, teamManager); //player is registering to team manager
        int sizeAfter = teamManager.observers.size();

        assertEquals(0, sizeBefore);
        assertEquals(1, sizeAfter);
    }

    @Test
    public void testRegister_2Players() {
        int sizeBefore = teamManager.observers.size();
        new Player(3,4,hazardousMap, 0, teamManager); //player is registering to team manager
        new Player(3,4,hazardousMap, 1, teamManager); //player is registering to team manager
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
    public void testSetRevealedTile(){
        //player registers
        int playerNum = 0;
        Player p = new Player(3, 4, hazardousMap, playerNum, teamManager);
        Map.Tiles[][] mapBefore = hazardousMap.getTeamPlayerMap(teamNo, playerNum);

        teamManager.setRevealedTile(2,1);

        assertEquals(2, teamManager.getX());
        assertEquals(1, teamManager.getY());

        Map.Tiles[][] mapAfter = hazardousMap.getTeamPlayerMap(teamNo, playerNum);

        assertEquals(Map.Tiles.GREY, mapBefore[2][1]);
        assertNotEquals(Map.Tiles.GREY, mapAfter[2][1]);
    }
}
