package edu.cps2002.mazegame.player;

public class Position {

    public int x;
    public int y;

    public Position(int x,int y){
        this.x =x;
        this.y= y;
    }

    //getters and setters for the x-coordinates and y-coordinates
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
