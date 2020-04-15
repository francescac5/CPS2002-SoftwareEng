package edu.cps2002.mazegame.player;

public class Player {
    private Position position;

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

    public void move(char direction) {
        switch (direction) {
            case 'U':
                position.setY(position.getY()+1);
                break;
            case 'D':
                position.setY(position.getY()-1);
                break;
            case 'L':
                position.setX(position.getX()-1);
                break;
            case 'R':
                position.setX(position.getX()+1);
                break;
            default:

        }
    }
}
