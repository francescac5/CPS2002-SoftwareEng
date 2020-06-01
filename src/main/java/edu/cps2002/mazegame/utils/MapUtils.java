package edu.cps2002.mazegame.utils;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.map.PlayerTile;
import edu.cps2002.mazegame.map.Tile;

import java.awt.*;
import java.io.*;

public class MapUtils {

    //system's file separator
    private String ret = File.separator;

    //creates file at given path
    //writes given content to created file
    public void writeToFile(String path, String content){
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("File created: "+ file.getName());
            } else {
                System.out.println("File "+ file.getName()+" updated.");
            }

            FileWriter fWriter;
            BufferedWriter writer;
            try {
                fWriter = new FileWriter(file);
                writer = new BufferedWriter(fWriter);

                writer.write(content);

                writer.newLine();
                writer.close();
            }
            catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //generate html file for individual play
    public void generateMapHTML(int mapCount, Map.Tiles[][] playerMap){
        String path = "src"+ret+"main"+ret+"java"+ret+"edu"+ret+"cps2002"+ret+"mazegame"+ret+"gameMaps"+ret+"map_player_"+mapCount+".html";
        StringBuilder mapTitle = generateMapHTMLTitle(mapCount, playerMap.length);
        generateMapHTMLContents(path, playerMap, mapTitle);
    }

    //generate html file for collaborative play
    public void generateMapHTML(int mapCount, Map.Tiles[][] teamMap, int teamPlayerNum){
        String path = "src"+ret+"main"+ret+"java"+ret+"edu"+ret+"cps2002"+ret+"mazegame"+ret+"gameMaps"+ret+"map_team_"+mapCount+"_player_"+teamPlayerNum+".html";
        StringBuilder mapTitle = generateMapHTMLTitle(mapCount, teamMap.length, teamPlayerNum);
        generateMapHTMLContents(path, teamMap, mapTitle);
    }

    //generate html map title for individual play
    public StringBuilder generateMapHTMLTitle(int mapCount, int playerMapLength){
        StringBuilder mapHTML = new StringBuilder();

        mapHTML.append("<tr>\n");
        mapHTML.append("<th colspan=\"");
        mapHTML.append(playerMapLength);
        mapHTML.append("\">Player ");
        mapHTML.append(mapCount);
        mapHTML.append("</th>\n");
        mapHTML.append("</tr>\n");

        return mapHTML;
    }

    //generate html map title for collaborative play
    public StringBuilder generateMapHTMLTitle(int mapCount, int playerMapLength, int teamPlayerNum){
        StringBuilder mapHTML = new StringBuilder();

        mapHTML.append("<tr>\n");
        mapHTML.append("<th colspan=\"");
        mapHTML.append(playerMapLength);
        mapHTML.append("\">Team ");
        mapHTML.append(mapCount);
        mapHTML.append(" - Player ");
        mapHTML.append(teamPlayerNum);
        mapHTML.append("</th>\n");
        mapHTML.append("</tr>\n");

        return mapHTML;
    }

    //generates html file for map according to given array playerMap
    public void generateMapHTMLContents(String path, Map.Tiles[][] playerMap, StringBuilder mapTitle) {
        StringBuilder mapHTML = new StringBuilder();

        mapHTML.append("<html>\n");
        mapHTML.append("<head>\n");
        mapHTML.append("<script type=\"text/javascript\">\n");
        mapHTML.append("window.onload = setUpRefresh;\n");
        mapHTML.append("function setUpRefresh() {\n");
        mapHTML.append("setTimeout(\"refreshPage();\",3000); \n}\n");
        mapHTML.append("function refreshPage() {\n");
        mapHTML.append("self.window.location = location.href; \n}\n");
        mapHTML.append("</script>\n");
        mapHTML.append("</head>\n");
        mapHTML.append("<body>\n");
        mapHTML.append("<table border ='1' align = \"center\">\n");
        mapHTML.append("<thead>\n");

        mapHTML.append(mapTitle);

        mapHTML.append("<tbody>\n");

        //inputting grid according to map size
        for (int y = 0; y < playerMap.length; y++) {
            mapHTML.append("<tr>");
            for (int x = 0; x < playerMap.length; x++) {
                if(playerMap[x][y] == Map.Tiles.GRASS){
                    mapHTML.append(new Tile().getTileHTML("green"));
                }
                else if(playerMap[x][y] == Map.Tiles.GRASS_PLAYER) {
                    mapHTML.append(new PlayerTile().getTileHTML("green"));
                }
                else if(playerMap[x][y] == Map.Tiles.WATER) {
                    mapHTML.append(new Tile().getTileHTML("blue"));
                }
                else if(playerMap[x][y] == Map.Tiles.TREASURE) {
                    mapHTML.append(new PlayerTile().getTileHTML("yellow"));
                }
                else if(playerMap[x][y] == Map.Tiles.GREY) {
                    mapHTML.append(new Tile().getTileHTML("grey"));
                }
            }
            mapHTML.append("</tr>");
        }

        mapHTML.append("</tbody>\n");
        mapHTML.append("</thead>\n");
        mapHTML.append("</table>\n");

        writeToFile(path, mapHTML.toString());
    }

    //delete all html files in gameMaps directory
    public void deleteHTMLFiles(){
        boolean success;
        File mapFolder = new File("src"+ret+"main"+ret+"java"+ret+"edu"+ret+"cps2002"+ret+"mazegame"+ret+"gameMaps");

        File[] maps = mapFolder.listFiles();
        if (maps != null) {
            for(File map: maps){
                success = map.delete();
                if(!success){
                    //if deletion of file is denied
                    throw new SecurityException();
                }
            }
        }
    }

    //opens all html map files in default browser
    public void openMapsInBrowser(){
        File mapFolder = new File("src"+ret+"main"+ret+"java"+ret+"edu"+ret+"cps2002"+ret+"mazegame"+ret+"gameMaps");

        File[] maps = mapFolder.listFiles();
        if (maps != null) {
            for(File map: maps){
                try {
                    Desktop.getDesktop().browse(map.toURI());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //generates gameMaps directory
    public void generateGameMapsFolder() {
        File mapFolder = new File("src"+ret+"main"+ret+"java"+ret+"edu"+ret+"cps2002"+ret+"mazegame"+ret+"gameMaps");
        if(mapFolder.mkdir()) {
            System.out.println("--> gameMaps directory created <-- ");
        } else {
            System.out.println("--> gameMaps directory already exists <--");
        }
    }
}
