package edu.cps2002.mazegame.map;

public class Tile {

    public String getTileHTML(String tileColour){
        return "<td height=\"50\" width=\"50\" style=\"background-color:"+tileColour+";\"></td>\n";
    }
}
