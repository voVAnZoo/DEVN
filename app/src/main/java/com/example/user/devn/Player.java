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
        if((speedX != 0)&&(speedY != 0)) {

            addMx(speedX);
            addMy(speedY);
        }
    }
}
