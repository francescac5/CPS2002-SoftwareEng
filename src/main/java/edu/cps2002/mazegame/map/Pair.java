package edu.cps2002.mazegame.map;

//Generic class accepting two parameters
public class Pair<A,B> {
    private final A a;
    private final B b;

    //constructor for Pair
    //sets a and b values
    Pair(A a, B b){
        this.a=a;
        this.b=b;
    }

    //returns value of a
    public A getKey() {
        return a;
    }

    //returns value of b
    public B getValue() {
        return b;
    }
}