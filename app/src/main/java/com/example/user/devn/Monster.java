package com.example.user.devn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    private Random rand = new Random();

    private float maxSpeedX = 20f;
    private float maxSpeedY = 20f;
    private float oldR = 1000000;
    private int dt = 0;
    Bitmap bitmap1;

    public Monster(float mx, float my, float width, float height, GameMap gm) {
        super(mx, my, width, height, gm);
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap1 = BitmapFactory.decodeResource(gm.getContext().getApplicationContext().getResources(), R.drawable.monster2, options);
        bitmap1 =  Bitmap.createScaledBitmap(bitmap1, (int)width, (int)height, false);

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
            e.printStackTrace();
        }
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {

        //canvas.drawRect((int)mx - Data.camX, (int)my - Data.camY,(int) width  + (int) mx - Data.camX,(int)height + (int)my - Data.camY, paint);
        canvas.drawBitmap(bitmap1,(int)mx - Data.camX, (int)my - Data.camY,paint);
    }

    @Override
    public void addMx(float dx) {
        if(mx + dx < 0 ){
            mx = 0;
        }else{
            if(mx + dx > Data.mapWidth * Data.cellWidth - width){
                mx = Data.mapWidth * Data.cellWidth - width - dx;
            }else{
                if(dx < 0){
                    if((gm.maparr[(int) my/Data.cellHeight][(int) (mx + dx)/Data.cellWidth] == 1)||(
                            gm.maparr[(int) (my + height - 1)/Data.cellHeight][(int) (mx + dx)/Data.cellWidth] == 1)){
                        mx -= (mx % Data.cellWidth);
                    }else{
                        mx += dx;
                    }
                }else{
                    if ((gm.maparr[(int) my/Data.cellHeight][(int) (mx + dx + width)/Data.cellWidth] == 1)||(
                            gm.maparr[(int) (my + height - 1)/Data.cellHeight][(int) (mx + dx + width)/Data.cellWidth] == 1)){
                        mx = mx + dx - (mx + dx + width) % Data.cellWidth;
                    }else{
                        mx += dx;
                    }
                }
            }
        }
    }

    @Override
    public void addMy(float dy) {
        if(my + dy < 0 ){
            my = 0;
        }else{
            if(my + dy > Data.mapHeight*Data.cellHeight - height){
                my = Data.mapHeight*Data.cellHeight - height - dy;
            }else{
                if(dy < 0){
                    if((gm.maparr[(int) (my + dy)/Data.cellHeight][(int) mx/Data.cellWidth] == 1)||(
                            gm.maparr[(int) (my + dy)/Data.cellHeight][(int) (mx + width - 1)/Data.cellWidth] == 1)){
                        my -= (my % Data.cellHeight);
                    }else{
                        my += dy;
                    }
                }else{
                    if ((gm.maparr[(int) (my + dy + height)/Data.cellHeight][(int) mx/Data.cellWidth] == 1)||(
                            gm.maparr[(int) (my + dy + height)/Data.cellHeight][(int) (mx + width - 1)/Data.cellWidth] == 1)){
                        my = my + dy - (my + dy + height) % Data.cellHeight;
                    }else {
                        my += dy;
                    }
                }
            }
        }
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

    @Override
    public void action(){
        if(hp < 0){
            this.death();
        }
        if (speedX != 0) {
            addMx(speedX);
        }
        if (speedY != 0) {
            addMy(speedY);
        }
        dt++;
        float x = gm.player.getMx();
        float y =  gm.player.getMy();
        if (dt > 10) {
            if (Math.abs(mx - x) < Data.sizeX / 2 && Math.abs(my - y) < Data.sizeY / 2) {
                choiceSpeedX(x, y);
                choiceSpeedY(x, y);

            } else {
                randomSpeedX();
                randomSpeedY();

            }
            dt = 0;
        }
        if (my + Data.monsterHeight > y && my < y + Data.cellHeight) {
            if (x > mx && x <= mx + Data.monsterWidth)
                speedX = -maxSpeedX / 2 + gm.player.speedX;
            if (x < mx && x + Data.cellWidth >= mx)
                speedX = maxSpeedX / 2 + gm.player.speedX;
        }
        if (mx + Data.monsterWidth > x && mx < x + Data.cellWidth) {
            if (y > my && y <= my + Data.monsterHeight)
                speedY = -maxSpeedY / 2 + gm.player.speedY;
            if (y < my && y + Data.cellHeight >= my)
                speedY = maxSpeedY / 2 + gm.player.speedY;
        }
    }

    @Override
    public void death() {
        gm.player.addXp(5);
        super.death();
    }
}