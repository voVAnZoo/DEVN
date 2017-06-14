package com.example.user.devn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.FileWriter;
import java.io.IOException;

public class Player extends Entity{

    int coins;
    float xp; //не больше 100

    public Player(float mx, float my, int width, int height, Context context) {
        super(mx, my, width, height, context);
        coins = 0;
        xp = 0;
    }

    public Player(float mx, float my, int width, int height, int level, float hp, int coins, float xp, Context context) {
        super(mx, my, width, height, level, hp, context);
        this.coins = coins;
        this.xp = xp;
    }

    public int getCoins() {
        return coins;
    }

    public float getXp() {
        return xp;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setXp(float xp) {
        this.xp = xp;
    }

    public void addCoins(int dCoins){
        coins += dCoins;
    }

    public void addXp(float dXp){
        xp += dXp;
    }

    @Override
    public void addMx(float dx) {
        if(mx + dx < 0 ){
            mx = 0;
        }else{
            if(mx + dx > Data.mapWidth * Data.cdellWidth - width){
                mx = Data.mapWidth * Data.cdellWidth - width;
            }else{
                if(dx < 0){
                    if((Data.maparr[(int) my/Data.cdellHeight][(int) (mx + dx)/Data.cdellWidth] == 1)||(
                            Data.maparr[(int) (my + height - 1)/Data.cdellHeight][(int) (mx + dx)/Data.cdellWidth] == 1)){
                        mx -= (mx % Data.cdellWidth);
                    }else{
                        mx += dx;
                        Data.camX += dx;
                    }
                }else{
                    if ((Data.maparr[(int) my/Data.cdellHeight][(int) (mx + dx + width)/Data.cdellWidth] == 1)||(
                            Data.maparr[(int) (my + height - 1)/Data.cdellHeight][(int) (mx + dx + width)/Data.cdellWidth] == 1)){
                        mx = mx + dx - (mx + dx + width) % Data.cdellWidth;
                    }else{
                        mx += dx;
                        Data.camX += dx;
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
                my = Data.mapHeight*Data.cdellHeight - height;
            }else{
                if(dy < 0){
                    if((Data.maparr[(int) (my + dy)/Data.cdellHeight][(int) mx/Data.cdellWidth] == 1)||(
                            Data.maparr[(int) (my + dy)/Data.cdellHeight][(int) (mx + width - 1)/Data.cdellWidth] == 1)){
                        my -= (my % Data.cdellHeight);
                    }else{
                        my += dy;
                        Data.camY += dy;
                    }
                }else{
                    if ((Data.maparr[(int) (my + dy + height)/Data.cdellHeight][(int) mx/Data.cdellWidth] == 1)||(
                            Data.maparr[(int) (my + dy + height)/Data.cdellHeight][(int) (mx + width - 1)/Data.cdellWidth] == 1)){
                        my = my + dy - (my + dy + height) % Data.cdellHeight;
                    }else {
                        my += dy;
                        Data.camY += dy;
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

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        paint.setColor(Color.YELLOW);
        canvas.drawRect((int)mx - Data.camX, (int)my - Data.camY,(int) width + (int)mx - Data.camX,(int)height - Data.camY + (int)my, paint);
    }

    @Override
    public void save(FileWriter out) {
        try {
            out.write("p ");
            super.save(out);
            out.write(Integer.toString(coins) + " ");
            out.write(Float.toString(xp) + " ");
        }catch (IOException e){

        }
    }

    public void go(){
        addMx(speedX);
        addMy(speedY);
    }

    @Override
    public void action() {
        if((speedX != 0)||(speedY != 0)) {
            addMx(speedX);
            addMy(speedY);
        }
    }
}