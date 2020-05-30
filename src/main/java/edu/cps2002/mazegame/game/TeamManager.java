package edu.cps2002.mazegame.game;

import java.util.ArrayList;

public class TeamManager implements Subject {
    private int teamNo;
    private int x;
    private int y;
    protected ArrayList<Observer> observers;

    public TeamManager(int teamNo){
        this.teamNo = teamNo;
        this.observers = new ArrayList<>();
    }

    public int getTeamNo() {
        return teamNo;
    }

    @Override
    public void register(Observer newObserver) {
        this.observers.add(newObserver);
    }

    @Override
    public void unregister(Observer deleteObserver) {
        int observerIndex = this.observers.indexOf(deleteObserver);

        System.out.println("Observer " + (observerIndex+1) + " deleted");

        this.observers.remove(observerIndex);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : this.observers){
            observer.update(x,y);
        }
    }

    public void setRevealedTile(int x, int y){

    }
}
