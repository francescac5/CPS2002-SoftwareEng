package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.player.Player;

import java.util.Scanner;

public class Game {
    static int minPlayers = 2;
    static int maxPlayers = 8;
    static int maxMapSize = 50;

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

    public static boolean validityofMapSize(int numPlayers,int size){
        if(size <= maxMapSize && (( numPlayers <= 4 && size >= 5 ) || size >= 8 ) ) {
            return true;
        }else if(numPlayers <= 4) {
            return false;
        }
        return false;
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

    static int chooseMapSize(int numPlayers){
        int size = 0;
        Scanner sc = new Scanner (System.in);
        do {
            System.out.print("Enter map size:\n");
            try {
                size = sc.nextInt();
            } catch (Exception e) {
                sc.next();
            } finally {
                if(validityofMapSize(numPlayers, size)){
                    return size;
                }else{
                    System.out.println("The map size should be larger for the number of players inputted");
                }
            }
        } while (true);
    }


    public static void main(String[] args) {
       int x= getNumPlayers();
       chooseMapSize(x);
    }

}
