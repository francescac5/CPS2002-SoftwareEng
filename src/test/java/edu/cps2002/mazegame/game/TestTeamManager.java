package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.map.MapFactory;
import edu.cps2002.mazegame.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTeamManager  {
    private TeamManager teamManager;
    private Map safeMap;
    private int teamNo;

    @Before
    public void setup() {
        this.teamManager = new TeamManager(teamNo);
        this.safeMap = MapFactory.getInstance("S");
        teamNo = 0;
    }

    @After
    public void tearDown() {
        this.teamManager = null;
        this.safeMap = null;
    }

    @Test
    public void testRegister() {
       int sizeBefore = teamManager.observers.size();
       new Player(3,4,safeMap, 0, teamManager); //player is registering to team manager
       int sizeAfter = teamManager.observers.size();

       assertEquals(0, sizeBefore);
       assertEquals(1, sizeAfter);
    }

    @Test
    public void testUnregister() {
        //player registers
        Player p = new Player(3, 4, safeMap, 0, teamManager);
        int sizeBefore = teamManager.observers.size();

        //unregister player
        teamManager.unregister(p);
        int sizeAfter = teamManager.observers.size();

        assertEquals(1, sizeBefore);
        assertEquals(0, sizeAfter);
    }
}
