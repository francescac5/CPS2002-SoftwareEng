package edu.cps2002.mazegame.map;

import java.util.ArrayList;
import java.util.Arrays;

public class Tile {

    private String tileColour;
    private ArrayList<String> colours = new ArrayList<>( Arrays.asList("green", "yellow", "blue") );

    public Tile(String colour){
        this.tileColour = colour;
    }

    protected Tile(){ }

    protected void setColour(String colour){
        this.tileColour = colour;
    }

    public String getTileHTML(){
        if(!this.colours.contains(this.tileColour)){
            return "";
        }
        else{
            return "<td height=\"50\" width=\"50\" style=\"background-color:"+this.tileColour+";\"></td>";
        }
    }
}
