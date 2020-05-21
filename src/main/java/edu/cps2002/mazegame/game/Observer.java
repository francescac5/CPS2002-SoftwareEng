package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.player.Position;

public interface Observer {

    public void update(Position p, int x, int y);
}
