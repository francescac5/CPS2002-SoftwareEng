package edu.cps2002.mazegame.player;

import edu.cps2002.mazegame.map.Map;

public class Player {
    private Position position;


    public Position getPosition() {
        return position;
    }

    public Player(int x, int y) {
        Position p = new Position(x, y);
        setPosition(p);
    }

    public enum DIRECTION {
        UP, DOWN, RIGHT, LEFT
    }

    public void setPosition(Position position) {
        this.position = position;
    }

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
            default:
        }

        if (newX < 0 || newX >= Map.getMapSize()
                || newY < 0 || newY >= Map.getMapSize()){
            return false;
        }
        else{
            position.setX(newX);
            position.setY(newY);
        }
        return true;
           }
    }