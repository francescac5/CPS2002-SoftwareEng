package edu.cps2002.utils;

import edu.cps2002.mazegame.map.Map;
import edu.cps2002.mazegame.map.PlayerTile;
import edu.cps2002.mazegame.map.Tile;

import java.io.*;

public class MapUtils {

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

    public void generateMapHTML(int mapCount, Map.Tiles[][] playerMap) {
        String path = "src\\main\\java\\edu\\cps2002\\mazegame\\gameMaps\\map_player_"+mapCount+".html";

        StringBuilder mapHTML = new StringBuilder();

        mapHTML.append("<html>\n");
        mapHTML.append("<head>\n");
        mapHTML.append("<script type=\"text/javascript\">\n");
        mapHTML.append("window.onload = setUpRefresh;\n");
        mapHTML.append("function setUpRefresh() {\n");
        mapHTML.append("setTimeout(\"refreshPage();\",1000); \n}\n");
        mapHTML.append("function refreshPage() {\n");
        mapHTML.append("self.window.location = location.href; \n}\n");
        mapHTML.append("</script>\n");
        mapHTML.append("</head>\n");
        mapHTML.append("<body>\n");
        mapHTML.append("<table border ='1' align = \"center\">\n");
        mapHTML.append("<thead>\n");
        mapHTML.append("<tr>\n");
        mapHTML.append("<th colspan=\"");
            mapHTML.append(playerMap.length);
            mapHTML.append("\">Player ");
            mapHTML.append(mapCount);
            mapHTML.append("</th>\n");
        mapHTML.append("</tr>\n");
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

    public void deleteHTMLFiles(){
        File mapFolder = new File("src\\main\\java\\edu\\cps2002\\mazegame\\gameMaps");

        File[] maps = mapFolder.listFiles();
        for(File map: maps){
            map.delete();
        }
    }
}
