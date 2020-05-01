package edu.cps2002.mazegame.map;

public class Tile {

    //Returns HTML for tile with given colour
    public String getTileHTML(String tileColour){
        return "<td height=\"50\" width=\"50\" style=\"background-color:"+tileColour+";\"></td>\n";
    }
}
