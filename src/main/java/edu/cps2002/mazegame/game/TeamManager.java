package edu.cps2002.mazegame.game;

import java.util.ArrayList;

public class TeamManager implements Subject {
    private int teamNo;
    private int x;
    private int y;
    protected ArrayList<Observer> observers;

    // Constructor for TeamManager that takes the teamNo
    public TeamManager(int teamNo){
        this.teamNo = teamNo;
        this.observers = new ArrayList<>();
    }

    // method to get the team no
    public int getTeamNo() {
        return teamNo;
    }

    // Registers a new observer
    @Override
    public void register(Observer newObserver) {
        this.observers.add(newObserver);
    }

    //unregisters an observer by removing him from the arraylist
    @Override
    public void unregister(Observer deleteObserver) {
        int observerIndex = this.observers.indexOf(deleteObserver);

        System.out.println("Observer " + (observerIndex+1) + " deleted");

        this.observers.remove(observerIndex);
    }
    //notifies the observer by updating the x and y coordinates
    @Override
    public void notifyObserver() {
        for(Observer observer : this.observers){
            observer.update(x,y);
        }
    }

    //sets the revealed tile and notifies the observer
    public void setRevealedTile(int x, int y){
        this.x = x;
        this.y = y;
        notifyObserver();
    }
}
