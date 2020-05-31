package edu.cps2002.mazegame.player;

import edu.cps2002.mazegame.game.Observer;
import edu.cps2002.mazegame.game.Subject;
import edu.cps2002.mazegame.game.TeamManager;
import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.map.SafeMap;

public class Player implements Observer {
    private Position position;
    private int playerNum;
    private Map m;
    private TeamManager teamManager;

   //getter for the position
    public Position getPosition() {
        return position;
    }

    //constructor for the player
    public Player(int x, int y, Map map) {
        this.m = map;
        Position p = new Position(x, y);
        setPosition(p);
    }
    //for testing purposes
    public Player(int x, int y) {
        Position p = new Position(x, y);
        setPosition(p);
    }

    public Player(int x, int y, Map map, int playerNum, TeamManager teamManager){
        Position p = new Position(x, y);
        setPosition(p);
        this.m = map;

        this.playerNum = playerNum;

        this.teamManager = teamManager;
        teamManager.register(this);
    }

    @Override
    public void update(int x, int y) {
        m.revealTile(x, y, teamManager.getTeamNo(), playerNum);
    }

    //enum for the direction
    public enum DIRECTION {
        UP, DOWN, RIGHT, LEFT, UNKNOWN
    }

     //setter for the position
    public void setPosition(Position position) {
        this.position = position;
    }

    //method move to change the x and y coordinates accordingly
    public boolean move(DIRECTION Direction) {
        int newX = position.getX();
        int newY = position.getY();
        switch (Direction) {
            case UP:
                newY = position.getY() - 1;
                break;
            case DOWN:
                newY = position.getY() + 1;
                break;
            case LEFT:
                newX = position.getX() - 1;
                break;
            case RIGHT:
                newX = position.getX() + 1;
                break;
        }

    //checking the x and y axis in order to ensure that the user does not try to move outside the map
        if (newX < 0 || newX >= m.getMapSize()
                || newY < 0 || newY >= m.getMapSize()){
            System.out.println("You cannot move out of the map");
            return false;
        }
        else{
            position.setX(newX);
            position.setY(newY);
        }
        return true;
           }
    }