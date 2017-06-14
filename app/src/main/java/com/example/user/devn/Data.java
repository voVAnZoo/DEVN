package com.example.user.devn;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by user on 6/6/17.
 */

public class Data {
    public static int mapWidth = 100;
    public static int mapHeight = 100;

    public static int cdellWidth = 40;
    public static int cdellHeight = 40;

    public static int camX = 0;
    public static int camY = 0;

    public static int startHeight = 3;
    public static int startWidth = 3;
    public static int finishHeight = 3;
    public static int finishWidth = 3;

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

    }
}