package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.map.MapFactory;
import edu.cps2002.mazegame.player.Player;
import edu.cps2002.mazegame.player.Position;
import edu.cps2002.mazegame.utils.MapUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    //arraylist to store the players and their choice
    protected static ArrayList<Player> playerList = new ArrayList<>();
    protected static ArrayList<Player.DIRECTION> playerChoice = new ArrayList<>();
    protected static int[] TeamPlayers;
    private static MapUtils utils = new MapUtils();
    protected static Map map;

    //setting the minimum players, maximum players and maximum map size
    static int minPlayers = 2;
    static int maxPlayers = 8;
    static int maxMapSize = 50;
    //flag to set if the game ended or not
    static boolean gameend = false;

    //method to get the direction from the player by user input
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

    //method to get the direction (move) from the player by user input
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
                if(validateuserinput(answer)){
                    Player.DIRECTION x =chooseDirection(answer);
                    return x;
                }else{
                    System.out.println("Invalid Input");
                }
            }
        }
    }

    //method to check that the input that the user gives is either l,d,u or r
    public static boolean validateuserinput(String answer) {
        return answer.equalsIgnoreCase("L") || answer.equalsIgnoreCase("R") ||
                answer.equalsIgnoreCase("D") || answer.equalsIgnoreCase("U");
    }

    //method to check that the number of players inputted is between 4 and 8
    public static boolean validityofPlayers(int numPlayers){
        return numPlayers >= minPlayers && numPlayers <= maxPlayers;
    }

    //method to check that the number of teams inputted is greater than the number of players inputted
    public static boolean validityofTeams(int numPlayers, int teams){
        return teams <= numPlayers;
    }

    //method to check that the mode entered is either collaborative or individual
    public static boolean validityofMode(String mode){
        if (mode.equalsIgnoreCase("I")) {
            return true;
        }else if(mode.equalsIgnoreCase("C")){
            return true;
        }
        return false;
    }


    //method to check that the Map Size inputted is between 5 and 50 (depending also on the number of players inputted)
    public static boolean validityofMapSize(int numPlayers,int size){
        if(size <= maxMapSize && (( numPlayers <= 4 && size >= 5 ) || size >= 8 ) ) {
            return true;
        }else {
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

    //method to get the number of players from the user
    public static int getNumTeams(int numPlayers){
        int numTeams = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Please choose the number of teams: \n");
            try {
                numTeams = sc.nextInt();
            } catch (Exception e) {
                sc.next();
            } finally {
                if(validityofTeams(numPlayers, numTeams)){
                    return numTeams;
                }else{
                    System.out.println("===The number of teams is greater than the number of players inputted==\n");
                }
            }
        } while(true);
    }

    //method to get the map size from the player
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

    //method to get the game mode from the player
    static String choosegameMode(){
        String mode = null;
        Scanner sc = new Scanner (System.in);
        do {
            System.out.print("Choose between Individual (I) or Collaborative mode (C) \n");
            try {
                mode= sc.next();
            } catch (Exception e) {
                sc.next();
            } finally {
                if(validityofMode(mode)){
                    return mode;
                }else{
                    System.out.println("The mode can be either individual or collaborative\n");
                }
            }
        } while (true);
    }

static void initialiseTeams(int players, int teams) {
    TeamPlayers = new int[teams];
    int remainder = players % teams;
    int playersPerTeam = players / teams;

    Arrays.fill(TeamPlayers, playersPerTeam);

    if (remainder != 0) {
        for (int i = 0; i < remainder; i++) {
            TeamPlayers[i] = TeamPlayers[i] + 1;
        }
    }
}
    //method to generate HTML files for every player
    static void generateHTMLFiles(int[] playersPerTeam, int teams){
            for (int i = 0; i < teams; i++) {
                map.generate(playersPerTeam[i]);
            }
    }

    //method to generate HTML files for every player
    static void generateHTMLFiles(int players){
            for (int i = 0; i < players; i++) {
                map.generate(0);
            }
    }

    //main method of the game
    static void startGame() {
        boolean gameend = false;
       String mode= choosegameMode();
        //Outputting the rules of the game
        if(mode.equalsIgnoreCase("I")) {
            System.out.println("Welcome to our Maze game. " +
                    "The following are the rules of the game:" +
                    "\n" + "1) Each player must use the U(p), D(down), L(eft), and R(ight) keys to move along the map." +
                    "\n2) Each player gets one (valid) move per round. " +
                    "\n3) The first player/s to find the treasure, win/s! " +
                    "\n4) If you land on the water tile you have to go back to your initial position\n");
            chooseMapType();
            int players = getNumPlayers();
            map.setMapSize(chooseMapSize(players));
            do {
                playerList.clear();
                playerChoice.clear();
                generateHTMLFiles(players);
                initialisePlayers(players);
                utils.openMapsInBrowser();
                //for loop that gives 20 turns to each player
                for (int i = 0; i < 20; i++) {
                    giveoneturntoeachPlayer();
                    boolean check = checkWinner();
                    checkGameend(check);
                }
                utils.deleteHTMLFiles();
                map.resetMap();

            } while (!gameend);
        }else if(mode.equalsIgnoreCase("C")){
            System.out.println("Welcome to our Maze game. " +
                    "The following are the rules of the game:" +
                    "\n" + "1) Each player must use the U(p), D(down), L(eft), and R(ight) keys to move along the map." +
                    "\n2) Each team gets one (valid) move per round. " +
                    "\n3) The first team to find the treasure, win/s! " +
                    "\n4) If you land on the water tile you have to go back to your initial position\n");
            chooseMapType();
            int players = getNumPlayers();
            int teams = getNumTeams(players);
            map.setMapSize(chooseMapSize(players));
//            do {
                playerList.clear();
                playerChoice.clear();
                initialiseTeams(players, teams);
                generateHTMLFiles(TeamPlayers,teams);

                utils.openMapsInBrowser();
                //for loop that gives 20 turns to each team
//                for (int i = 0; i < 20; i++) {
//                    giveoneturntoeachPlayer();
//                    boolean check = checkWinner();
//                    checkGameend(check);
//                }
//                utils.deleteHTMLFiles();
//                map.resetMap();

//            } while (!gameend);
        }
    }

    //method to give every player one turn to choose the direction and then move everyone accordingly
    static void giveoneturntoeachPlayer() {
        for(int j=0; j<playerList.size();j++) {
            boolean flag;
            Player.DIRECTION x;
            System.out.println("Player " + (j + 1) + "'s turn");
            do {
                x = chooseMove();
                char tile = map.getTileType(playerList.get(j).getPosition().getX(), playerList.get(j).getPosition().getY());
                flag = checkwatertile(tile,j,playerList,x);
            }while(!flag);
            playerChoice.add(x);
        }
        for( int i = 0; i < playerList.size(); i++) {
            map.updateMap(playerList.get(i).getPosition().getX(), playerList.get(i).getPosition().getY(), i + 1);
        }
        playerChoice.clear();
    }

    //method that checks if a player went on a water tile if so he is sent back to his/her original position
    static boolean checkwatertile(char tile, int j,ArrayList<Player> players, Player.DIRECTION x){
        boolean flag; 
        if (tile == 'W') {
            Position p1 = new Position(map.getPlayerInitPositionX(j + 1), map.getPlayerInitPositionY(j + 1));
            playerList.get(j).setPosition(p1);
            flag= players.get(j).move(x);
        } else {
            flag=players.get(j).move(x);
        }
        return flag;
    }

    //Check if the game ended if so stop the game else print that no one has won
    static void checkGameend(boolean check){
        if (check) {
            gameend = true;

            try {
                System.out.println("Deleting HTML files...");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            utils.deleteHTMLFiles();
            System.exit(0);
        } else {
            gameend = false;
            System.out.println("No one won in this round");
        }
    }

    //method to create an instance for every player and add it to the array of the players
    static void initialisePlayers(int players){
        for (int i =0; i<players; i++){
            Player p1 = new Player(map.getPlayerInitPositionX(i+1),map.getPlayerInitPositionY(i+1), map);
            playerList.add(p1);
        }
    }

    //method that checks if a player is on the treasure tile (winner)
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

    //asks user what type of map he/she would like
    private static void chooseMapType() {

        Scanner sc= new Scanner(System.in);
        String mapType;

        while (true) {
            System.out.print("===Choose map type  S = SAFE, H = HAZARDOUS===\n");
            try {
                mapType = sc.next();
                map = MapFactory.getInstance(mapType);
            } catch (Exception e) {
                sc.next();
            }

            if(map == null){
                System.out.println("Invalid Map Type!\n");
            }else{
                break;
            }
        }
    }
}
