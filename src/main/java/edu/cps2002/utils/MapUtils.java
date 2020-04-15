package edu.cps2002.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class MapUtils {

     public File generateHTMLFile(int mapCount) {
        try {
            File mapFile = new File("src\\main\\java\\edu\\cps2002\\mazegame\\gameMaps\\map_player_"+mapCount+".html");
            if (mapFile.createNewFile()) {
                System.out.println("File created: "+ mapFile.getName());
                //mapCount++;

                return mapFile;
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return null;
    }

    public void generateMap(File mapFile, int size, int mapCount, ArrayList<Integer> grassTiles) {
        Collections.shuffle(grassTiles);
        int initTile = grassTiles.get(0);
        int tileCount = 0;

        FileWriter fWriter;
        BufferedWriter writer;
        try {
            fWriter = new FileWriter(mapFile);
            writer = new BufferedWriter(fWriter);
            writer.write("<html>" +
                    "<body>" +
                    "<table border ='1'>" +
                    "<thead>" +
                    "<tr>" +
                    "<th colspan=\"" + size + "\">Player " + mapCount + "</th>" +
                    "</tr>" +
                    "<tbody>");

            //inputting grid according to map size
            for (int i = 0; i < size; i++) {
                writer.write("<tr>");
                for (int j = 0; j < size; j++) {
                    if(tileCount == initTile){
                        writer.write("<td height=\"50\" width=\"50\" style=\"background-color:green;\"><img src=\"/Assignment/src/main/resources/detective.png\" height=\"50\" width=\"50\"></td>");
                        tileCount++;
                    }else {
                        writer.write("<td height=\"50\" width=\"50\" style=\"background-color:grey;\"></td>");
                        tileCount++;
                    }
                }
                writer.write("</tr>");
            }

            writer.write("</tbody>" +
                    "</thead>" +
                    "</table>");
            writer.newLine();
            writer.close();
        }
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void deleteHTMLFiles(){
        File mapFolder = new File("src\\main\\java\\edu\\cps2002\\mazegame\\gameMaps")   ;

        File[] maps = mapFolder.listFiles();
        for(File map: maps){
            map.delete();
        }
    }
}
