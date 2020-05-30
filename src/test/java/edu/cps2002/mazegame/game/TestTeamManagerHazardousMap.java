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
}
