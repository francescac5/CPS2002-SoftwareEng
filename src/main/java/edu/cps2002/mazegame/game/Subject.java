package edu.cps2002.mazegame.game;

public interface Subject {
    //to register an observer
    public void register(Observer o);
    //to unregister an observer
    public void unregister(Observer o);
    //to notify an observer
    public void notifyObserver();
}
