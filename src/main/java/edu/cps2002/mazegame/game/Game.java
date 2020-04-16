package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.player.Player;

public class Game {
    public static Player.DIRECTION chooseDirection(String input) {
        while (true) {
            if (input.equalsIgnoreCase("U")) {
                return Player.DIRECTION.UP;
            } else if (input.equalsIgnoreCase("D")) {
                return Player.DIRECTION.DOWN;
            } else if (input.equalsIgnoreCase("L")) {
                return Player.DIRECTION.LEFT;
            } else if (input.equalsIgnoreCase("R")) {
                return Player.DIRECTION.RIGHT;
            } else {
                System.out.println("ERROR: Enter a valid direction");
            }
        }
    }

    public static boolean validityofPlayers(int i) {
        return false;
    }
}
