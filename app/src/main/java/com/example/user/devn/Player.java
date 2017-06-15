package com.example.user.devn;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Toast;

import java.io.FileWriter;
import java.io.IOException;

public class Player extends Entity{

    int coins;
    float xp; //не больше 100
    Bitmap bitmap1;

    public Player(float mx, float my, int width, int height, GameMap gm) {
        super(mx, my, width, height, gm);
        coins = 0;
        xp = 0;
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap1 = BitmapFactory.decodeResource(gm.getContext().getApplicationContext().getResources(), R.drawable.dprofile1, options);
    }

    public Player(float mx, float my, int width, int height, int level, float hp, int coins, float xp, GameMap gm) {
        super(mx, my, width, height, level, hp, gm);
        this.coins = coins;
        this.xp = xp;
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap1 = BitmapFactory.decodeResource(gm.getContext().getApplicationContext().getResources(), R.drawable.dprofile1, options);
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
            if(mx + dx > Data.mapWidth * Data.cellWidth - width){
                mx = Data.mapWidth * Data.cellWidth - width;
            }else{
                if(dx < 0){
                    if((Data.maparr[(int) my/Data.cellHeight][(int) (mx + dx)/Data.cellWidth] == 1)||(
                            Data.maparr[(int) (my + height - 1)/Data.cellHeight][(int) (mx + dx)/Data.cellWidth] == 1)){
                        mx -= (mx % Data.cellWidth);
                    }else{
                        mx += dx;
                        Data.camX += dx;
                    }
                }else{
                    if ((Data.maparr[(int) my/Data.cellHeight][(int) (mx + dx + width)/Data.cellWidth] == 1)||(
                            Data.maparr[(int) (my + height - 1)/Data.cellHeight][(int) (mx + dx + width)/Data.cellWidth] == 1)){
                        mx = mx + dx - (mx + dx + width) % Data.cellWidth;
                    }else{
                        mx += dx;
                        Data.camX += dx;
                    }
                }
            }
        }

        if(Data.camX < 0 ){
            Data.camX = 0;
        }else {
            if(Data.camX > Data.mapWidth*Data.cellWidth - Data.sizeX){
                Data.camX = Data.mapWidth*Data.cellWidth - Data.sizeX;
            }
        }
    }

    @Override
    public void addMy(float dy) {
        if(my + dy < 0 ){
            my = 0;
        }else{
            if(my + dy > Data.mapHeight*Data.cellHeight - height){
                my = Data.mapHeight*Data.cellHeight - height;
            }else{
                if(dy < 0){
                    if((Data.maparr[(int) (my + dy)/Data.cellHeight][(int) mx/Data.cellWidth] == 1)||(
                            Data.maparr[(int) (my + dy)/Data.cellHeight][(int) (mx + width - 1)/Data.cellWidth] == 1)){
                        my -= (my % Data.cellHeight);
                    }else{
                        my += dy;
                        Data.camY += dy;
                    }
                }else{
                    if ((Data.maparr[(int) (my + dy + height)/Data.cellHeight][(int) mx/Data.cellWidth] == 1)||(
                            Data.maparr[(int) (my + dy + height)/Data.cellHeight][(int) (mx + width - 1)/Data.cellWidth] == 1)){
                        my = my + dy - (my + dy + height) % Data.cellHeight;
                    }else {
                        my += dy;
                        Data.camY += dy;
                    }
                }
            }
        }

        if(Data.camY < 0 ){
            Data.camY = 0;
        }else {
            if(Data.camY > Data.mapHeight*Data.cellHeight - Data.sizeY){
                Data.camY = Data.mapHeight*Data.cellHeight - Data.sizeY;
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        paint.setColor(Color.YELLOW);
//        canvas.drawRect((int)mx - Data.camX, (int)my - Data.camY,(int) width + (int)mx - Data.camX,(int)height - Data.camY + (int)my, paint);
        canvas.drawBitmap(bitmap1,(int)mx - Data.camX, (int)my - Data.camY,paint);
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
