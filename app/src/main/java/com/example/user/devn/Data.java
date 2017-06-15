package com.example.user.devn;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by user on 6/6/17.
 */

public class Data {
    public static int mapWidth = 50;
    public static int mapHeight = 50;

    public static int cdellWidth = 100;
    public static int cdellHeight = 100;

    public static int camX = 0;
    public static int camY = 0;

    public static int startHeight = 3;
    public static int startWidth = 3;
    public static int finishHeight = 3;
    public static int finishWidth = 3;

    public static int sizeX = -1;
    public static int sizeY = -1;

    public static int maxR2 = (cdellWidth * cdellWidth + cdellHeight * cdellHeight) * 50;

    public static float monsterWidth = 8 * cdellWidth / 10;
    public static float monsterHeight = 8 * cdellHeight / 10;

    /*
    костылёк
     */
    public static int[][] maparr = null;
    public static GameMap gameMap;

    public static void save(FileWriter out){
        try {
            out.write(Integer.toString(mapWidth) + " ");
            out.write(Integer.toString(mapHeight) + " ");

            out.write(Integer.toString(cdellWidth) + " ");
            out.write(Integer.toString(cdellHeight) + " ");

            out.write(Integer.toString(camX) + " ");
            out.write(Integer.toString(camY) + " ");

            out.write(Integer.toString(startHeight) + " ");
            out.write(Integer.toString(startWidth) + " ");
            out.write(Integer.toString(finishHeight) + " ");
            out.write(Integer.toString(finishWidth) + " ");
        }catch (IOException e){

        }
    }

    public static void open(String s){
        mapWidth = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s.substring(s.indexOf(" ") + 1);
        mapHeight = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s.substring(s.indexOf(" ") + 1);

        cdellWidth = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s.substring(s.indexOf(" ") + 1);
        cdellHeight = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s.substring(s.indexOf(" ") + 1);

        camX = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s.substring(s.indexOf(" ") + 1);
        camY = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s.substring(s.indexOf(" ") + 1);

        startHeight = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s.substring(s.indexOf(" ") + 1);
        startWidth = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s.substring(s.indexOf(" ") + 1);
        finishHeight = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s.substring(s.indexOf(" ") + 1);
        finishWidth = Integer.parseInt(s.substring(0, s.indexOf(" ")));

    }

}