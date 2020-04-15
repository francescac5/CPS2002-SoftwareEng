package edu.cps2002.mazegame.map;

class Tile {
    private String colour;

    protected Tile(String colour){
        this.colour = colour;
    }

    protected Tile(){ }

    protected void setColour(String colour){
        this.colour = colour;
    }

    protected String getTileHTML(){
        return "";
    }
}
