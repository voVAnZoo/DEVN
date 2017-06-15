package com.example.user.devn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by user on 6/9/17.
 */

public class Monster extends Entity {

    boolean isagr;
    public Monster(float mx, float my, float width, float height, GameMap gm) {
        super(mx, my, width, height, gm);
        isagr = false;
    }

    public Monster(float mx, float my, float width, float height, int level, float hp,boolean isagr, GameMap gm) {
        super(mx, my, width, height, level, hp, gm);
        this.isagr = isagr;
    }


    @Override
    public void save(FileWriter out) {
        try {
            out.write("m ");
            super.save(out);
            out.write(Boolean.toString(isagr) + " ");
        }catch (IOException e){

        }
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        paint.setColor(Color.GREEN);
        canvas.drawRect((int)mx - Data.camX, (int)my - Data.camY,(int) width  + (int) mx - Data.camX,(int)height + (int)my - Data.camY, paint);
    }

    @Override
    public void save(FileWriter out) {
        try {
            out.write("m ");
            super.save(out);
        }catch (IOException e){

        }
    }
}