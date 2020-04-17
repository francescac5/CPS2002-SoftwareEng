package edu.cps2002.mazegame.player;

public class Position {

    private static int x;
    private static int y;

    public Position(int x,int y){
        this.x =x;
        this.y= y;
    }

    //getters and setters for the x-coordinates and y-coordinated
    public static int getX() {
        return x;
    }
    public static void setX(int x) {
        Position.x = x;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        Position.y = y;
    }
}
