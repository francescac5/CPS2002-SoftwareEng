package edu.cps2002.mazegame.map;

public class PlayerTile extends Tile{

    //Returns HTML for tile with given colour with detective image on it
    public String getTileHTML(String tileColour){
        return "<td height=\"50\" width=\"50\" style=\"background-color:"+tileColour+";\"><img src=\"../../../../../resources/detective.png\" height=\"50\" width=\"50\"></td>\n";
    }
}
