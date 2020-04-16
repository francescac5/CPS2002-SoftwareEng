package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.player.Player;

import java.util.Scanner;

public class Game {
    static int minPlayers = 2;
    static int maxPlayers = 8;

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

    public static boolean validityofPlayers(int numPlayers){
        if(numPlayers >= minPlayers && numPlayers <= maxPlayers){
            return true;
        }else{
            return false;
        }
    }

    //method to get the number of players from the user
    public static int getNumPlayers(){
        int numPlayers = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Please choose the number of players: \n");
            try {
                numPlayers = sc.nextInt();
            } catch (Exception e) {
                sc.next();
            } finally {
                if(validityofPlayers(numPlayers)){
                    return numPlayers;

                }else{
                    System.out.println("===The number of players should be between 2 and 8===\n");
                }
            }
        } while(true);
    }

    public static void main(String[] args) {
        getNumPlayers();
    }

    public static boolean validityofMapSize(int i, int i1) {
        return false;
    }
}
