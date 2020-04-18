package edu.cps2002.mazegame.player;

public class Player {
    private static Position position;


    public Position getPosition() {
        return position;
    }

    public Player(int x, int y){
        Position p = new Position(x,y);
        setPosition(p);
    }

    public enum DIRECTION{
        UP,DOWN,RIGHT,LEFT
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public static void move(DIRECTION Direction) {
        switch (Direction) {
            case UP:
                position.setY(position.getY()+1);
                break;
            case DOWN:
                position.setY(position.getY()-1);
                break;
            case LEFT:
                position.setX(position.getX()-1);
                break;
            case RIGHT:
                position.setX(position.getX()+1);
                break;
            default:

        }
    }
}
