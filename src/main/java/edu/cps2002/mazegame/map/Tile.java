package edu.cps2002.mazegame.map;

import java.util.ArrayList;
import java.util.Arrays;

class Tile {

    private String tileColour;
    private ArrayList<String> colours = new ArrayList<>( Arrays.asList("green", "yellow", "blue") );

    protected Tile(String colour){
        this.tileColour = colour;
    }

    protected Tile(){ }

    protected void setColour(String colour){
        this.tileColour = colour;
    }

    protected String getTileHTML(){
        if(!this.colours.contains(this.tileColour)){
            return "";
        }
        else if(this.tileColour.equals("green")) {
            return "<td height=\"50\" width=\"50\" style=\"background-color:green;\"><img src=\"/Assignment/src/main/resources/detective.png\" height=\"50\" width=\"50\"></td>";
        }
        else{
            return "<td height=\"50\" width=\"50\" style=\"background-color:"+this.tileColour+";\"></td>";
        }
    }
}
