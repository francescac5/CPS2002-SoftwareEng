package edu.cps2002.mazegame.player;

import edu.cps2002.mazegame.game.Observer;
import edu.cps2002.mazegame.game.Subject;
import edu.cps2002.mazegame.game.TeamManager;
import edu.cps2002.mazegame.map.Map;

public class Player implements Observer {
    private Position position;
    int playerNum;
    Subject teamManager;

   //getter for the position
    public Position getPosition() {
        return position;
    }

    //constructor for the player
    public Player(int x, int y) {
        Position p = new Position(x, y);
        setPosition(p);
    }

    public Player(){

    }

    public Player(TeamManager m){
        this.teamManager= m;
        m.register(this);
    }
    @Override
    public void update(int x, int y) {

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
        if (newX < 0 || newX >= Map.getMapSize()
                || newY < 0 || newY >= Map.getMapSize()){
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