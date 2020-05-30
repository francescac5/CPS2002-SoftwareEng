package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.player.Player;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
public class TestTeamManager  {

    @Test
    public void testregister() {
        int sizeBefore = TeamManager.observers.size();
      TeamManager t= new TeamManager();
       new Player(t); //player is using register
       int sizeAfter = TeamManager.observers.size();
      assertNotEquals(sizeBefore,sizeAfter);

    };

    @Test
    public void testunregister() {
        int sizeBefore = TeamManager.observers.size();
        TeamManager t= new TeamManager();
        new Player(t);
        int sizeAfter = TeamManager.observers.size();
        assertNotEquals(sizeBefore,sizeAfter);

    };


}
