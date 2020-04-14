package edu.cps2002.utils;

import java.io.File;
import java.io.IOException;

public class MapUtils {

    protected boolean generateHTMLFile(int mapCount) {
        try {
            File mapFile = new File("src\\main\\java\\edu\\cps2002\\mazegame\\gameMap\\map"+mapCount+".html");
            if (mapFile.createNewFile()) {
                System.out.println("File created: " + mapFile.getName());
                //mapCount++;

                return true;
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return false;
    }
}
