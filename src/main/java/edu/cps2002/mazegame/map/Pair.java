package edu.cps2002.mazegame.map;

public class Pair<A,B> {
    private final A a;
    private final B b;

    Pair(A a, B b){
        this.a=a;
        this.b=b;
    }

    public A getKey() {
        return a;
    }

    public B getValue() {
        return b;
    }
}