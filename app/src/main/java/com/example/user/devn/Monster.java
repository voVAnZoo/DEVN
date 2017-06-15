package com.example.user.devn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Random;

/**
 * Created by user on 6/9/17.
 */

public class Monster extends Entity {


    public Monster(float mx, float my, float width, float height, GameMap gm) {
        super(mx, my, width, height, gm);

    }

    public Monster(float mx, float my, float width, float height, int level, float hp, GameMap gm) {
        super(mx, my, width, height, level, hp, gm);

    }


    @Override
    public void save(FileWriter out) {
        try {
            out.write("m ");
            super.save(out);
        }catch (IOException e){

        }
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawRect((int)mx - Data.camX, (int)my - Data.camY,(int) width  + (int) mx - Data.camX,(int)height + (int)my - Data.camY, paint);
    }
    private float maxSpeedX = 20f;
    private float maxSpeedY = 20f;
    private Random rand = new Random();
    @Override
    public void addMx(float dx) {
        if(mx + dx < 0 ){
            mx = 0;
        }else{
            if(mx + dx > Data.mapWidth * Data.cdellWidth - width){
                mx = Data.mapWidth * Data.cdellWidth - width - dx;
            }else{
                if(dx < 0){
                    if((Data.maparr[(int) my/Data.cdellHeight][(int) (mx + dx)/Data.cdellWidth] == 1)||(
                            Data.maparr[(int) (my + height - 1)/Data.cdellHeight][(int) (mx + dx)/Data.cdellWidth] == 1)){
                        mx -= (mx % Data.cdellWidth);
                    }else{
                        mx += dx;
                    }
                }else{
                    if ((Data.maparr[(int) my/Data.cdellHeight][(int) (mx + dx + width)/Data.cdellWidth] == 1)||(
                            Data.maparr[(int) (my + height - 1)/Data.cdellHeight][(int) (mx + dx + width)/Data.cdellWidth] == 1)){
                        mx = mx + dx - (mx + dx + width) % Data.cdellWidth;
                    }else{
                        mx += dx;
                    }
                }
            }
        }

        /*if(Data.camX + dx < 0 ){
            Data.camX = 0;
        }else {
            if(Data.camX + dx > Data.mapWidth*Data.cdellWidth - Data.sizeX){
                Data.camX = Data.mapWidth*Data.cdellWidth - Data.sizeX;
            }else {
                Data.camX += dx;
            }
        }*/
    }
    @Override
    public void addMy(float dy) {
        if(my + dy < 0 ){
            my = 0;
        }else{
            if(my + dy > Data.mapHeight*Data.cdellHeight - height){
                my = Data.mapHeight*Data.cdellHeight - height - dy;
            }else{
                if(dy < 0){
                    if((Data.maparr[(int) (my + dy)/Data.cdellHeight][(int) mx/Data.cdellWidth] == 1)||(
                            Data.maparr[(int) (my + dy)/Data.cdellHeight][(int) (mx + width - 1)/Data.cdellWidth] == 1)){
                        my -= (my % Data.cdellHeight);
                    }else{
                        my += dy;
                    }
                }else{
                    if ((Data.maparr[(int) (my + dy + height)/Data.cdellHeight][(int) mx/Data.cdellWidth] == 1)||(
                            Data.maparr[(int) (my + dy + height)/Data.cdellHeight][(int) (mx + width - 1)/Data.cdellWidth] == 1)){
                        my = my + dy - (my + dy + height) % Data.cdellHeight;
                    }else {
                        my += dy;
                    }
                }
            }
        }

      /*  if(Data.camY + dy < 0 ){
            Data.camY = 0;
        }else {
            if(Data.camY + dy > Data.mapHeight*Data.cdellHeight - Data.sizeY){
                Data.camY = Data.mapHeight*Data.cdellHeight - Data.sizeY;
            }else {
                Data.camY += dy;
            }
        }*/
    }
    private float getR2(float x, float y) {
        float s = x - mx;
        float d = y - my;
        return s * s + d * d;
    }
    private void choiceSpeedX(float x, float y) {
        if (rand.nextBoolean() || rand.nextBoolean())
            if (x > mx)
                speedX = maxSpeedX;
            else if (x < mx)
                speedX = -maxSpeedX;
            else
                speedX = 0;
        else
            speedX = rand.nextFloat() * maxSpeedX * 2 - maxSpeedX;
    }
    private void choiceSpeedY(float x, float y) {
        if (rand.nextBoolean())
            if (y > my)
                speedY = maxSpeedY;
            else if (y < my)
                speedY = -maxSpeedY;
            else
                speedY = 0;
        else
            speedY = rand.nextFloat() * maxSpeedY * 2 - maxSpeedY;
    }
    private void randomSpeedX(){
        speedX = rand.nextFloat() * maxSpeedX * 2 - maxSpeedX;
    }
    private void randomSpeedY(){
        speedY = rand.nextFloat() * maxSpeedY * 2 - maxSpeedY;
    }
    private float oldR = 1000000;
    private int dt = 0;
    private int color;

    @Override
    public void action(){
        if (speedX != 0) {
            addMx(speedX);
        }
        if (speedY != 0) {
            addMy(speedY);
        }
        dt++;
        float x = Data.gameMap.player.getMx();
        float y =  Data.gameMap.player.getMy();
        if (dt > 10) {
            if (Math.abs(mx - x) < Data.sizeX / 2 && Math.abs(my - y) < Data.sizeY / 2) {
                choiceSpeedX(x, y);
                choiceSpeedY(x, y);
                color = Color.RED;
            } else {
                randomSpeedX();
                randomSpeedY();
                color = Color.GREEN;
            }
            dt = 0;
        }
        if (my + Data.monsterHeight > y && my < y + Data.cdellHeight) {
            if (x > mx && x <= mx + Data.monsterWidth)
                speedX = -maxSpeedX / 2 + Data.gameMap.player.speedX;
            if (x < mx && x + Data.cdellWidth >= mx)
                speedX = maxSpeedX / 2 + Data.gameMap.player.speedX;
        }
        if (mx + Data.monsterWidth > x && mx < x + Data.cdellWidth) {
            if (y > my && y <= my + Data.monsterHeight)
                speedY = -maxSpeedY / 2 + Data.gameMap.player.speedY;
            if (y < my && y + Data.cdellHeight >= my)
                speedY = maxSpeedY / 2 + Data.gameMap.player.speedY;
        }
    }
}