package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.player.Player;

import java.util.Scanner;

public class Game {
   private static Map map = new Map();
    static int minPlayers = 2;
    static int maxPlayers = 8;
    static int maxMapSize = 50;

    //method to get the direction from the player
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

    //checking that the number of players inputted is between 4 and 8
    public static boolean validityofPlayers(int numPlayers){
        if(numPlayers >= minPlayers && numPlayers <= maxPlayers){
            return true;
        }else{
            return false;
        }
    }

    //checking that the Map Size inputted is between 5 and 50
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

    //method to get the mapsize from the player
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

    //method to generate HTML files for every player
    static void generateHTMLFiles(){
        int players = getNumPlayers();
        //setmapsize returns boolean //
        map.setMapSize(chooseMapSize(players));
        for (int i =0; i<players;i++){
            map.generate();
        }
    }

    static void startGame(){
        generateHTMLFiles();
        //getposition and move player in turns -- to be modified
        move_player();
    }

    static void move_player(){
         Player p1 = new Player(5,4);
        Scanner sc = new Scanner(System.in);
        String input= sc.nextLine();
         Player.DIRECTION x=chooseDirection(input);
          p1.move(x);
    }


    public static void main(String[] args) {
        startGame();
    }

}
