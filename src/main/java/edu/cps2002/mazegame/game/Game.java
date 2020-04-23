package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.player.Player;
import edu.cps2002.mazegame.player.Position;
import edu.cps2002.mazegame.utils.MapUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static ArrayList<Player> playerList = new ArrayList<Player>();
    static ArrayList<Player.DIRECTION> playerChoice = new ArrayList<>();
    private static MapUtils utils = new MapUtils();
    private static Map map = new Map();
    static int minPlayers = 2;
    static int maxPlayers = 8;
    static int maxMapSize = 50;
    static boolean gameend = false;

    //method to get the direction from the player
    public static Player.DIRECTION chooseDirection(String input) {
while(true) {
    if (input.equalsIgnoreCase("U")) {
        return Player.DIRECTION.UP;
    } else if (input.equalsIgnoreCase("D")) {
        return Player.DIRECTION.DOWN;
    } else if (input.equalsIgnoreCase("L")) {
        return Player.DIRECTION.LEFT;
    } else if (input.equalsIgnoreCase("R")) {
        return Player.DIRECTION.RIGHT;
    }
}
    }

    static Player.DIRECTION chooseMove() {
        Scanner sc= new Scanner(System.in);
        String answer = "";
        while (true) {
            System.out.print("===Choose where to move  L = LEFT, R = RIGHT, D = DOWN, U = UP===\n");
            try {
                answer = sc.next();
            } catch (Exception e) {
                sc.next();
            }finally{
                if(answer.equalsIgnoreCase("L") ||answer.equalsIgnoreCase("R") ||
                        answer.equalsIgnoreCase("D") || answer.equalsIgnoreCase("U")  ){
                    Player.DIRECTION x =chooseDirection(answer);
                    return x;
                }else{
                    System.out.println("Error");
                }
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
                    System.out.println("The map size should be larger for the number of players inputted\n");
                }
            }
        } while (true);
    }

    //method to generate HTML files for every player
    static void generateHTMLFiles(int players){
        for (int i =0; i<players;i++){
            map.generate();
        }
    }

    static void startGame(){
        int players = getNumPlayers();
        generateHTMLFiles(players);
        //getposition and move player in turns -- to be modified
        for(int i=0; i<10;i++) {
          //  giveoneturntoeachPlayer(players);
        }
    }


    static void giveoneturntoeachPlayer(ArrayList<Player> players, ArrayList<Player.DIRECTION> playerchoice) {
        for(int j=0; j<players.size();j++) {
            boolean flag;
            Player.DIRECTION x;
            System.out.println("Player " + (j + 1) + "'s turn");
            do {
                x = chooseMove();
                char tile = map.getTileType(players.get(j).getPosition().getX(), players.get(j).getPosition().getY());
                if (tile == 'W') {
                    Position p1 = new Position(map.getPlayerInitPositionX(j + 1), map.getPlayerInitPositionY(j + 1));
                    playerList.get(j).setPosition(p1);
                    flag= players.get(j).move(x);
                } else {
                    flag=players.get(j).move(x);
                }
            }while(!flag);
            playerChoice.add(x);
        }
        for( int i = 0; i < players.size(); i++) {
            map.updateMap(players.get(i).getPosition().getX(), players.get(i).getPosition().getY(), i + 1);
        }
        playerchoice.clear();
    }

    static void checkGameend(boolean check){
        if (check) {
            gameend = true;
            System.exit(0);
        } else {
            gameend = false;
            System.out.println("No one won in this round");
        }
    }


    public static void main(String[] args) {
        startGame();
    }

    //function to initialise the players
    static void initialisePlayers(int players){
        for (int i =0; i<players; i++){
            Player p1 = new Player(map.getPlayerInitPositionX(i+1),map.getPlayerInitPositionY(i+1));
            playerList.add(p1);
        }
    }

    static boolean checkWinner(){
        boolean winnerFlag = false;
        for(int i = 0; i < playerList.size(); i++){
            Position p = playerList.get(i).getPosition();
            char type= map.getTileType(p.getX(),p.getY());
            if (type=='T'){
                System.out.println("Winner:" + "Player_" + (i+1));
                winnerFlag= true;
            }
        }
        return winnerFlag;
    }
}
