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

public class TestGameSafeMap {

    MapUtils utils = new MapUtils();

    @Before
    public void setup() {
        Game.map = MapFactory.getInstance("S");
        Game.map.setMapSize(6);
        Game.map.setWaterPercentage(5);
    }

    @After
    public void tearDown() {
        Game.map.resetMap();
        utils.deleteHTMLFiles();
    }

    //******** Game.initialisePlayers() tests ********\\

    @Test
    public void initialisePlayersTest1_SafeMap(){
        int players = 3;
        Game.generateHTMLFiles(players);

        int sizeBefore = Game.playerList.size();

        Game.initialisePlayers(players);

        int sizeAfter = Game.playerList.size();

        assertEquals(sizeBefore+3, sizeAfter);

        utils.deleteHTMLFiles();
        Game.playerList.clear();
    }

    @Test
    public void initialisePlayersTest2_SafeMap(){
        int players = 10;
        Game.generateHTMLFiles(players);

        int sizeBefore = Game.playerList.size();

        Game.initialisePlayers(players);

        int sizeAfter = Game.playerList.size();

        assertEquals(sizeBefore+10, sizeAfter);

        Game.playerList.clear();

    }

    //******** Game.checkWinner() tests ********\\

    @Test
    public void test1SafeMap_Winner(){
        boolean flag;

        Game.generateHTMLFiles(10);
        Player p1 = new Player(4,5, Game.map);
        Game.playerList.add(p1);
        Pair<Integer, Integer> x= Map.getTreasureTile();
        Position p = new Position(x.getKey(),x.getValue());

        p1.setPosition(p);
        flag = Game.checkWinner();
        assertTrue(flag);
        Game.playerList.clear();
    }

    @Test
    public void test2SafeMap_Winner(){
        boolean flag;

        Game.generateHTMLFiles(10);
        Player p1 = new Player(4,5, Game.map);
        Game.playerList.add(p1);
        edu.cps2002.mazegame.map.Pair<Integer, Integer> treasureTile = Map.getTreasureTile();
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

    //******** Game.checkwatertile() tests ********\\

    @Test
    public void testSafeMap_checkwatertile1(){
        Game.generateHTMLFiles(1);
        ArrayList<Player> players = new ArrayList<>();
        Player p1= new Player(4,6, Game.map);
        players.add(p1);
        char tile = Game.map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
        boolean x = Game.checkwatertile(tile,0,players, Player.DIRECTION.DOWN);
        assertFalse(x);
        players.clear();
        //  hazardousMap.resetMap();
    }

    @Test
    public void testSafeMap_checkwatertile2(){
        Game.map = MapFactory.getInstance("S");
        Game.map.setMapSize(5);
        Game.generateHTMLFiles(1);
        ArrayList<Player> players = new ArrayList<Player>();
        Player p1= new Player(4,6, Game.map);
        players.add(p1);
        char tile = Game.map.getTileType(players.get(0).getPosition().getX(), players.get(0).getPosition().getY());
        boolean x =Game.checkwatertile(tile,0,players, Player.DIRECTION.UP);
        assertFalse(x);
        players.clear();
    }

    @Test
    public void testSafeMap_checkwatertile3() {
        Game.map.generate(0);
        Player p1 = new Player(2, 3, Game.map);
        Player p2 = new Player(3, 2, Game.map);
        Game.playerList.add(p1);
        Game.playerList.add(p2);
        boolean x = Game.checkwatertile('W', 0, Game.playerList, Player.DIRECTION.RIGHT);
        assertTrue(x);
        Game.playerList.clear();
    }
}
