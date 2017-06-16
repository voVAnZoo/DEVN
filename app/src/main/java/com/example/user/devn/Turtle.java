package com.example.user.devn;

import java.util.Random;

/**
 * Created by user on 6/8/17.
 */

public class Turtle{
    private GameMap gameMap;
    private int x = 0;
    private int y = 0;
    private int height = 1;
    private int width = 1;
    Turtle(GameMap gameMap){
        this.gameMap = gameMap;
    }
    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getHeight(){ return height; }
    public int getWidth(){ return width; }
    public void setX(int value){ x = value; }
    public void setY(int value){ y = value; }
    public void setWidth(int value){ height = value; }
    public void setHeight(int value){ width = value; }
    /*
    1 - right
    -1 - left
    2 - bottom
    -2 - top
     */
    private int randomDirect(){
        Random rand = new Random();
        switch (rand.nextInt(4)){
            case 0:
                return 1;
            case 1:
                return -1;
            case 2:
                return 2;
            case 3:
                return -2;
            default:
                return 1;
        }
    }

    private int choiceStepLength(int direct){
        int max = 0;
        switch (direct) {
            case 1:
                max = Data.mapWidth - x - width;
                break;
            case -1:
                max = x;
                break;
            case 2:
                max = Data.mapHeight - y - height;
                break;
            case -2:
                max = y;
                break;
        }
        Random rand = new Random();
        if (rand.nextBoolean()) {
            return rand.nextInt(max + 1);
        }else {
            return rand.nextInt(max / 2 + 1);
        }
    }

    public void rightStep(int length){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length + width; j++) {
                gameMap.maparr[y + i][x + j] = 0;
            }
        }
        x = x + length;
    }

    private void leftStep(int length){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length + 1; j++) {
                gameMap.maparr[y + i][x - j] = 0;
            }
        }
        x = x - length;
    }

    private void topStep(int length){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length + 1; j++) {
                gameMap.maparr[y - j][x + i] = 0;
            }
        }
        y = y - length;
    }

    private void bottomStep(int length){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length + height; j++) {
                gameMap.maparr[y + j][x + i] = 0;
            }
        }
        y = y + length;
    }

    public void nextStep(){
        int direct = randomDirect();
        int length = choiceStepLength(direct);
        Random rand = new Random();
        if(x < Data.mapWidth - 10)
            width = rand.nextInt(1) + 1;
        if (y < Data.mapHeight - 10)
            height = rand.nextInt(1) + 1;
        switch (direct){
            case 1:
                rightStep(length);
                break;
            case -1:
                leftStep(length);
                break;
            case 2:
                bottomStep(length);
                break;
            case -2:
                topStep(length);
                break;
        }
    }

    public void finish(int finishX, int finishY) {
        if (finishX > x) {
            rightStep(finishX - x);
        }else {
            leftStep(x - finishX);
        }
        if (finishY > y) {
            bottomStep(finishY - y);
        }else {
            topStep(y - finishY);
        }
    }
}