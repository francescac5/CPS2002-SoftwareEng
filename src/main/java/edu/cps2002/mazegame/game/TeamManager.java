package edu.cps2002.mazegame.game;

import edu.cps2002.mazegame.player.Player;

import java.util.ArrayList;

public class TeamManager implements Subject {
    private int teamNo;
    private int x;
    private int y;
    static ArrayList<Observer> observers;

    public TeamManager(int teamNo){
        this.teamNo = teamNo;
        observers = new ArrayList<>();
    }

    public int getTeamNo() {
        return teamNo;
    }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

//    @Override
//    public void unregister(Observer deleteObserver) {
//        int observerIndex = observers.indexOf(deleteObserver);
//
//        System.out.println("Observer " + (observerIndex+1) + " deleted");
//
//        observers.remove(observerIndex);
//
//    }

    @Override
    public void notifyObserver() {
        for(Observer observer : observers){
            observer.update(x,y);
        }
    }

    public void setRevealedTile(int x, int y){

    }
}
