package edu.cps2002.mazegame.map;

public class PlayerTile extends Tile{

    public String getTileHTML(String tileColour){
        return "<td height=\"50\" width=\"50\" style=\"background-color:"+tileColour+";\"><img src=\"../../../../../resources/detective.png\" height=\"50\" width=\"50\"></td>\n";
    }
}
